package com.luffy.countdownlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

/**
 * Created by lvlufei on 2019/3/28
 *
 * @desc 倒计时工具类
 */
public class CountdownUtil extends CountDownTimer {
    Context mContext;
    TextView mTextView;
    int startTxtColor = R.color.colorPrimary;
    int endTxtColor = R.color.colorPrimary;
    int startBgColor = R.drawable.countdown_normal_full_radius_stroke_true_white_selector;
    int endBgColor = R.drawable.countdown_normal_full_radius_stroke_true_white_selector;
    int formatTxt = R.string.countdown_seconds_later_retry;
    int defaultTxt = R.string.countdown_again_obtain;
    OnFinishListener onFinishListener;

    /**
     * 构造函数
     *
     * @param context           上下文对象
     * @param millisInFuture    总时间
     * @param countDownInterval 间隔时间
     */
    public CountdownUtil(Context context, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mContext = context.getApplicationContext();
    }

    public int getStartTxtColor() {
        return startTxtColor;
    }

    public void setStartTxtColor(int startTxtColor) {
        this.startTxtColor = startTxtColor;
    }

    public int getEndTxtColor() {
        return endTxtColor;
    }

    public void setEndTxtColor(int endTxtColor) {
        this.endTxtColor = endTxtColor;
    }

    public TextView getmTextView() {
        return mTextView;
    }

    public void setmTextView(TextView mTextView) {
        this.mTextView = mTextView;
    }

    public int getStartBgColor() {
        return startBgColor;
    }

    public void setStartBgColor(int startBgColor) {
        this.startBgColor = startBgColor;
    }

    public int getEndBgColor() {
        return endBgColor;
    }

    public void setEndBgColor(int endBgColor) {
        this.endBgColor = endBgColor;
    }

    public int getFormatTxt() {
        return formatTxt;
    }

    public void setFormatTxt(int formatTxt) {
        this.formatTxt = formatTxt;
    }

    public int getDefaultTxt() {
        return defaultTxt;
    }

    public void setDefaultTxt(int defaultTxt) {
        this.defaultTxt = defaultTxt;
    }

    public OnFinishListener getOnFinishListener() {
        return onFinishListener;
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    /**
     * 倒计时开始时要做的事情
     *
     * @param millisUntilFinished
     */
    @SuppressLint("StringFormatMatches")
    @Override
    public void onTick(long millisUntilFinished) {
        getmTextView().setEnabled(false);
        getmTextView().setText(String.format(mContext.getResources().getString(getFormatTxt()), millisUntilFinished / 1000));
        getmTextView().setTextColor(ContextCompat.getColor(mContext, getStartTxtColor()));
        getmTextView().setBackgroundResource(getStartBgColor());
    }

    /**
     * 计时器结束的时候要做的事情
     */
    @Override
    public void onFinish() {
        getmTextView().setEnabled(true);
        getmTextView().setText(getDefaultTxt());
        getmTextView().setTextColor(ContextCompat.getColor(mContext, getEndTxtColor()));
        getmTextView().setBackgroundResource(getEndBgColor());
        if (onFinishListener != null)
            onFinishListener.onFinish();
    }

    public interface OnFinishListener {
        /**
         * 倒计时结束回调
         */
        void onFinish();
    }
}