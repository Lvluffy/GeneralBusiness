package com.luffy.generalbusinesslib.mask;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.luffy.generalbusinesslib.R;


/**
 * Created by lvlufei on 2017/11/1
 *
 * @desc 蒙层对话框辅助类
 */
public class MaskDialog {

    private MaskDialog() {
    }

    public static MaskDialog getInstance() {
        return MaskDialogHelper.mMaskDialog;
    }

    /**
     * 静态内部类实现单例
     */
    private static class MaskDialogHelper {
        private static MaskDialog mMaskDialog;

        static {
            mMaskDialog = new MaskDialog();
        }
    }

    //自定义全屏显示的dialog
    public Dialog getFullSreenDialog(Context context, View view) {
        Dialog dialog = new Dialog(context, R.style.MaskDialogFullScreen);
        if (null != view) {
            dialog.setContentView(view);
        }
        return dialog;
    }
}
