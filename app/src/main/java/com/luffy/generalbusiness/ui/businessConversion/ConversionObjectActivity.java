package com.luffy.generalbusiness.ui.businessConversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.luffy.generalbusiness.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2019/10/12
 *
 * @name 业务转换
 * @desc
 */
public class ConversionObjectActivity extends AppCompatActivity {

    @BindView(R.id.txt_centent)
    TextView txtCentent;

    StringBuilder mStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_object);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_business_conversion)
    public void onViewClicked() {
        mStringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            TestConversionObject mTestConversionObject = new TestConversionObject();
            int type = new Random().nextInt(10);
            if (i == 5 - 1) {
                mStringBuilder.append(type).append("：").append(mTestConversionObject.getConversionObject(type));
            } else {
                mStringBuilder.append(type).append("：").append(mTestConversionObject.getConversionObject(type)).append("\n");
            }
            txtCentent.setText(mStringBuilder);
        }
    }
}
