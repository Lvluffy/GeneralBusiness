package com.luffy.generalbusiness.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.luffy.generalbusiness.R;
import com.luffy.generalbusiness.ui.businessConversion.ConversionObjectActivity;
import com.luffy.generalbusiness.ui.countdown.CountDownActivity;
import com.luffy.generalbusiness.ui.poller.PollerActivity;
import com.luffy.generalutilslib.utils.DoubleClickExitUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2019/10/11
 *
 * @name 主界面
 * @desc
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.item_1,
            R.id.item_2,
            R.id.item_3
    })
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.item_1) {
            Intent intent = new Intent(this, CountDownActivity.class);
            startActivity(intent);
        } else if (i == R.id.item_2) {
            Intent intent = new Intent(this, ConversionObjectActivity.class);
            startActivity(intent);
        }else if (i == R.id.item_3) {
            Intent intent = new Intent(this, PollerActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return DoubleClickExitUtils.getInstance().exit(this, null);
        }
        return super.onKeyDown(keyCode, event);
    }
}
