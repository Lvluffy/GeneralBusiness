package com.luffy.generalbusiness.ui.countdown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.luffy.countdownlib.CountdownUtil;
import com.luffy.generalbusiness.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2019/10/12
 *
 * @name 倒计时界面
 * @desc
 */
public class CountDownActivity extends AppCompatActivity {

    @BindView(R.id.txt_verification_code_hint)
    TextView txtVerificationCodeHint;
    @BindView(R.id.edit_verification_code)
    EditText editVerificationCode;
    @BindView(R.id.txt_get_verification_code)
    TextView txtGetVerificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.txt_get_verification_code)
    public void onViewClicked() {
        CountdownUtil countdownUtil = new CountdownUtil(this, 60 * 1000, 1000);
        countdownUtil.setStartBgColor(0);
        countdownUtil.setStartTxtColor(R.color.colorPrimary);
        countdownUtil.setEndBgColor(0);
        countdownUtil.setEndTxtColor(R.color.colorPrimary);
        countdownUtil.setmTextView(txtGetVerificationCode);
        countdownUtil.start();
    }
}
