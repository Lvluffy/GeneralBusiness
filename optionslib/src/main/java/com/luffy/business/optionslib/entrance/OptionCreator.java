package com.luffy.business.optionslib.entrance;

import android.app.Activity;

import com.luffy.business.optionslib.config.ConfigHelper;

/**
 * Created by lvlufei on 2020-05-14
 *
 * @name 选择库-起始类
 */
public class OptionCreator {

    private Activity activity;

    private OptionCreator(Activity mActivity) {
        this.activity = mActivity;
    }

    public static OptionCreator create(Activity activity) {
        return new OptionCreator(activity);
    }

    public ConfigHelper openOption() {
        return new ConfigHelper(activity);
    }

}
