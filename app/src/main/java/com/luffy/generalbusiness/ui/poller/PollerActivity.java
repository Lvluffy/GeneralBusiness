package com.luffy.generalbusiness.ui.poller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.luffy.generalbusiness.R;
import com.luffy.pollerlib.IPollerCallBack;
import com.luffy.pollerlib.PollerUtils;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2019/10/12
 *
 * @name 轮询器
 * @desc
 */
public class PollerActivity extends AppCompatActivity {

    PollerUtils mPollUtils = PollerUtils.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poller);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_poll)
    public void onViewClicked() {
        mPollUtils.setNeedPoll(true);
        mPollUtils.setPollTime(1000);
        startPoll();
    }

    private void startPoll() {
        mPollUtils.poll(new IPollerCallBack() {
            @Override
            public void onVerifyRefresh() {
                if (new Random().nextInt(10) == 5) {
                    mPollUtils.setNeedPoll(false);
                } else {
                    mPollUtils.setNeedPoll(true);
                }
            }

            @Override
            public void onRefreshStart(int pollTimes) {
                Toast.makeText(PollerActivity.this, "轮询开始——" + pollTimes, Toast.LENGTH_SHORT).show();
                startPoll();
            }

            @Override
            public void onRefreshEnd(int pollTimes) {
                Toast.makeText(PollerActivity.this, "轮询结束——" + pollTimes, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
