package com.luffy.businessconversionlib;

import android.content.Context;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 业务转换-对象A（对象A：1,int 2,String ......）转对象B（对象B：1,String 2,Map 3,List ......）
 * 说明：实现类可以实现单例，也可以不实现，根据具体业务自行选择
 */
public abstract class BaseConversionObject<T, D> {
    /**
     * 获取转换对象
     *
     * @param businessType 业务类型
     * @return
     */
    public abstract D getConversionObject(T businessType);

    /**
     * 获取转换对象
     *
     * @param mContext     上下文对象
     * @param businessType 业务类型
     * @return
     */
    public abstract D getConversionObject(Context mContext, T businessType);
}
