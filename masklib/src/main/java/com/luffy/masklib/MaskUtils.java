package com.luffy.masklib;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.luffy.generalutilslib.utils.AppUtils;
import com.luffy.generalutilslib.utils.SharedPreferencesUtils;
import com.luffy.generalutilslib.utils.ValidUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lvlufei on 2017/11/1
 *
 * @desc 蒙版工具
 */
public class MaskUtils {
    /*显示的位置*/
    private int TIMES = 1;

    private MaskUtils() {
    }

    public static MaskUtils getInstance() {
        return MaskUtilsHelper.mMaskUtils;
    }

    /**
     * 静态内部类实现单例
     */
    private static class MaskUtilsHelper {
        private static MaskUtils mMaskUtils = new MaskUtils();
    }

    /**
     * 显示单张蒙版提示
     */
    private void showSingleMark(final Context context, View view, final String key) {
        final Dialog mDialog = MaskDialog.getInstance().getFullSreenDialog(context, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMark(context, key);
                if (null != mDialog && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });
        mDialog.show();
    }

    /**
     * 显示多张蒙版
     *
     * @param context
     * @param views
     * @param key
     */
    private void showMultipleMark(final Context context, final List<View> views, final String key) {
        final Dialog mDialog = MaskDialog.getInstance().getFullSreenDialog(context, views.get(0));
        //防止蒙版显示中途退出  回来再次重叠显示的问题
        if (TIMES == 1) {
            views.get(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ring(context, mDialog, views, key);
                }
            });
        }
        mDialog.show();
    }

    private void ring(Context context, Dialog mDialog, List<View> views, String key) {
        //蒙版显示完
        if (TIMES == views.size()) {
            saveMark(context, key);
            if (null != mDialog && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            TIMES = 1;
        } else {
            mDialog.setContentView(views.get(TIMES));
            childClick(context, mDialog, views.get(TIMES), views, key);
            ++TIMES;
        }
    }

    /**
     * 子项点击
     *
     * @param context
     * @param v
     * @param views
     * @param key
     */
    private void childClick(final Context context, final Dialog mDialog, View v, final List<View> views, final String key) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ring(context, mDialog, views, key);
            }
        });
    }

    /**
     * 保存蒙层
     *
     * @param context 上下文对象
     * @param key     当前蒙层Key
     */
    private void saveMark(Context context, String key) {
        SharedPreferencesUtils.getInstance().put(context, key, true);
    }

    /**
     * 单张蒙版
     * <p>
     * 显示蒙版条件：
     * 1.蒙版对应版本
     * 2.首次加载
     *
     * @param ct
     * @param key
     * @param view
     */
    public void readSingleMark(Context ct, String key, View view) {
        String version = null;
        if (!TextUtils.isEmpty(key) && key.length() > 3) {
            version = key.substring(key.length() - 3, key.length());
        }
        // 1.版本号校验
        if (ValidUtils.getInstance().isValid(version) && AppUtils.getInstance().getVersionCode(ct) == Integer.parseInt(version)) {
            // 2.是否显示过蒙版
            boolean isread = (boolean) SharedPreferencesUtils.getInstance().get(ct, key, false);
            if (!isread) {
                showSingleMark(ct, view, key);
            }
        }
    }

    /**
     * 多张蒙版
     * <p>
     * 蒙版显示条件：
     * 1.蒙版对应版本
     * 2.首次加载
     *
     * @param context
     * @param key
     * @param view
     */
    public void readMultipleMark(Context context, String key, List<View> view) {
        String version = null;
        if (!TextUtils.isEmpty(key) && key.length() > 3) {
            version = getVersion(key);
        }
        int v = AppUtils.getInstance().getVersionCode(context);
        //1.版本号校验
        if (!TextUtils.isEmpty(version) && version.equals(v + "")) {
            // 2.是否显示过蒙版
            boolean isread = (boolean) SharedPreferencesUtils.getInstance().get(context, key, false);
            if (!isread) {
                showMultipleMark(context, view, key);
            }
        }
    }

    /**
     * 截取字符串版本号
     */
    private String getVersion(String versionStr) {
        if (ValidUtils.getInstance().isValid(versionStr)) {
            //这个3是指连续数字的最少个数
            Pattern p = Pattern.compile("\\d{3,}");
            Matcher m = p.matcher(versionStr);
            while (m.find()) {
                int i1 = m.group().length();
                //字符串尾端数字截取
                if (versionStr.length() == versionStr.indexOf(m.group()) + i1) {
                    return m.group();
                }
            }
        }
        return "";
    }
}