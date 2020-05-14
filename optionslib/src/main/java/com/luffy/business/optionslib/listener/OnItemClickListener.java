package com.luffy.business.optionslib.listener;

import com.luffy.business.optionslib.ui.OptionAdapter;

/**
 * Created by lvlufei on 2020-05-14
 *
 * @name 选择库-子项点击事件
 */
public interface OnItemClickListener {

    void onItemClick(OptionAdapter adapter, int position);
}
