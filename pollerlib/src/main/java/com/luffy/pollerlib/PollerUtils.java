package com.luffy.pollerlib;

import android.os.Handler;

/**
 * Created by lvlufei on 2018/7/24
 *
 * @desc 轮询器-辅助工具
 */
public class PollerUtils {

    private PollerUtils() {
    }

    public static PollerUtils getInstance() {
        return PollerUtilsHelper.mPollerUtils;
    }

    private static class PollerUtilsHelper {
        private static PollerUtils mPollerUtils = new PollerUtils();
    }

    /**
     * 是否需要轮询（true:刷新；false:不刷新）
     */
    public boolean needPoll = false;

    /**
     * 轮询数量(总)
     */
    public Integer pollTimesTotal = 60 * 60;

    /**
     * 轮询数量(当前)
     */
    public Integer pollTimesCurrent = 1;

    /**
     * 每次轮询时间
     */
    public long pollTime = 10 * 1000;

    public boolean isNeedPoll() {
        return needPoll;
    }

    public void setNeedPoll(boolean needPoll) {
        this.needPoll = needPoll;
    }

    public Integer getPollTimesTotal() {
        return pollTimesTotal;
    }

    public void setPollTimesTotal(Integer pollTimesTotal) {
        this.pollTimesTotal = pollTimesTotal;
    }

    public Integer getPollTimesCurrent() {
        return pollTimesCurrent;
    }

    public void setPollTimesCurrent(Integer pollTimesCurrent) {
        this.pollTimesCurrent = pollTimesCurrent;
    }

    public long getPollTime() {
        return pollTime;
    }

    public void setPollTime(long pollTime) {
        this.pollTime = pollTime;
    }

    /**
     * 轮询
     *
     * @param pollCallBack 轮询机制-回调
     */
    public void poll(final IPollerCallBack pollCallBack) {
        //需要轮询
        if (isNeedPoll()) {
            //开始轮询
            if (getPollTimesCurrent() < getPollTimesTotal()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //验证轮询
                        pollCallBack.onVerifyRefresh();
                        //轮询开始
                        pollCallBack.onRefreshStart(getPollTimesCurrent());
                        //轮询次数递增
                        pollTimesCurrent++;
                    }
                }, getPollTime());
            }
            //结束轮询
            else {
                setNeedPoll(false);
                pollCallBack.onRefreshEnd(getPollTimesCurrent());
                setPollTimesCurrent(0);
            }
        }
        //不需要轮询（轮询结束）
        else {
            pollCallBack.onRefreshEnd(getPollTimesCurrent());
            setPollTimesCurrent(0);
        }
    }
}
