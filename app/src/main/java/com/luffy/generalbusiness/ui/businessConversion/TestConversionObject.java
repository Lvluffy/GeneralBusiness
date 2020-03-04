package com.luffy.generalbusiness.ui.businessConversion;

import android.content.Context;

import com.luffy.businessconversionlib.BaseConversionObject;

/**
 * Created by lvlufei on 2019/10/12
 *
 * @name
 * @desc
 */

public class TestConversionObject extends BaseConversionObject<Integer, String> {
    @Override
    public String getConversionObject(Integer businessType) {
        String result;
        switch (businessType) {
            case 1:
                result = "篮球";
                break;
            case 2:
                result = "足球";
                break;
            case 3:
                result = "拍球";
                break;
            case 4:
                result = "台球";
                break;
            case 5:
                result = "棒球";
                break;
            default:
                result = "不识别";
                break;
        }
        return result;
    }

    @Override
    public String getConversionObject(Context mContext, Integer businessType) {
        return null;
    }
}
