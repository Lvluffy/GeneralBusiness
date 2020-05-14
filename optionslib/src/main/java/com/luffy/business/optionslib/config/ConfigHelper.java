package com.luffy.business.optionslib.config;

import android.app.Activity;
import android.content.Intent;

import com.luffy.business.optionslib.listener.OptionSelectorListener;
import com.luffy.business.optionslib.ui.OptionActivity;

import java.util.List;

/**
 * Created by lvlufei on 2020-05-14
 *
 * @name 选择库-配置管理类
 */
public class ConfigHelper<T> {
    private Activity mActivity;
    private OptionConfig config;

    public ConfigHelper(Activity activity) {
        this.mActivity = activity;
        this.config = OptionConfig.getInstance();
    }

    /**
     * 设置展示列表 选中下标列表
     *
     * @param showList     展示列表
     * @param selectedList 选中下标列表
     * @return
     */
    public ConfigHelper setShowList(List<T> showList, List<Integer> selectedList) {
        config.setShowList(showList);
        config.setSelectedList(selectedList);
        return this;
    }

    public ConfigHelper setTitle(String title) {
        config.setTitle(title);
        return this;
    }

    public ConfigHelper setContent(String content) {
        config.setContent(content);
        return this;
    }

    public ConfigHelper setSaveSelector(int saveSelector) {
        config.setSaveSelector(saveSelector);
        return this;
    }

    public ConfigHelper setSelectDrawable(int selectDrawable) {
        config.setSelectDrawable(selectDrawable);
        return this;
    }

    public ConfigHelper setContentSize(int contentSize) {
        config.setContentSize(contentSize);
        return this;
    }

    public ConfigHelper setSelectType(SelectType type) {
        config.setType(type);
        return this;
    }

    public ConfigHelper setListener(OptionSelectorListener listener) {
        config.setListener(listener);
        return this;
    }

    /**
     * 跳转类方法-必调
     */
    public void jumpOption() {
        Intent intent = new Intent(mActivity, OptionActivity.class);
        mActivity.startActivity(intent);
    }
}
