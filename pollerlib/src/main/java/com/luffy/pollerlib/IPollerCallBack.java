package com.luffy.pollerlib;

/**
 * Created by lvlufei on 2018/7/24
 *
 * @desc 轮询器-回调
 */
public interface IPollerCallBack {

    /**
     * 验证刷新
     */
    void onVerifyRefresh();

    /**
     * 刷新开始
     */
    void onRefreshStart(int pollTimes);

    /**
     * 刷新结束
     */
    void onRefreshEnd(int pollTimes);
}
