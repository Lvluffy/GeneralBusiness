package com.luffy.business.optionslib.config;

import java.util.List;

/**
 * Created by lvlufei on 2020-05-14
 *
 * @name 数据工具类
 */
public class CompareUtil {

    /**
     * 比较是否是选中
     *
     * @param position   展示下标
     * @param selectList 选中的数据下标
     * @return
     */
    public static boolean compareTo(int position, List<Integer> selectList) {
        for (Integer index : selectList) {
            if (index == position) {
                return true;
            }
        }
        return false;
    }
}
