package com.luffy.business.optionslib.listener;

import java.util.List;

/**
 * Created by lvlufei on 2020-05-14
 *
 * @name 选项卡的回调
 */
public interface OptionSelectorListener<T> {

    /**
     * 最终选择结果
     *
     * @param selectlist
     */
    void onOptionSelected(List<T> selectlist);

    /**
     * 列表子项展示的内容
     *
     * @param position 子项下标
     * @return
     */
    String getContent(int position);

}
