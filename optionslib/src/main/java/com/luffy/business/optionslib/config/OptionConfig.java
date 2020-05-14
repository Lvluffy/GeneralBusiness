package com.luffy.business.optionslib.config;

import com.luffy.business.optionslib.listener.OptionSelectorListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlufei on 2020-05-14
 *
 * @name 选择库-配置类
 */
public class OptionConfig<T> {

    // 展示列表
    private List<T> showList;
    // 选中下标列表
    private List<Integer> selectedList;
    // 列表标题
    private String title;
    // item展示的字符串
    private String content;
    // item-字体大小
    private int contentSize;
    // 多选-保存按钮颜色Selector
    private int saveSelector = -1;
    // 选中图标
    private int selectDrawable;
    // 选择类型
    private SelectType type = SelectType.SINGLE;
    // 数据回调
    private OptionSelectorListener listener;

    public static OptionConfig getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static final class InstanceHolder {
        private static final OptionConfig INSTANCE = new OptionConfig();
    }

    public OptionConfig() {
    }

    public List<T> getShowList() {
        if (showList == null) {
            showList = new ArrayList<>();
        }
        return showList;
    }

    public void setShowList(List<T> showList) {
        this.showList = showList;
    }

    public List<Integer> getSelectedList() {
        if (selectedList == null) {
            selectedList = new ArrayList<>();
        }
        return selectedList;
    }

    public void setSelectedList(List<Integer> selectedList) {
        this.selectedList = selectedList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSaveSelector() {
        return saveSelector;
    }

    public void setSaveSelector(int saveSelector) {
        this.saveSelector = saveSelector;
    }

    public int getSelectDrawable() {
        return selectDrawable;
    }

    public void setSelectDrawable(int selectDrawable) {
        this.selectDrawable = selectDrawable;
    }

    public int getContentSize() {
        return contentSize;
    }

    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }

    public SelectType getType() {
        return type;
    }

    public void setType(SelectType type) {
        this.type = type;
    }

    public OptionSelectorListener getListener() {
        return listener;
    }

    public void setListener(OptionSelectorListener listener) {
        this.listener = listener;
    }
}
