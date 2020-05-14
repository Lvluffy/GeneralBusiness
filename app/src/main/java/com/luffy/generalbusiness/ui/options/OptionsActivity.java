package com.luffy.generalbusiness.ui.options;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.luffy.business.optionslib.config.SelectType;
import com.luffy.business.optionslib.entrance.OptionCreator;
import com.luffy.business.optionslib.listener.OptionSelectorListener;
import com.luffy.generalbusiness.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2020-05-14
 *
 * @name 选择
 */
public class OptionsActivity extends AppCompatActivity {

    @BindView(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_single, R.id.btn_multiple})
    public void onViewClicked(View view) {
        final List<String> showList = new ArrayList<>();
        showList.add("1月");
        showList.add("2月");
        showList.add("3月");
        showList.add("4月");
        showList.add("5月");
        showList.add("6月");
        showList.add("7月");
        showList.add("8月");
        showList.add("9月");
        showList.add("10月");
        showList.add("11月");
        showList.add("12月");
        switch (view.getId()) {
            case R.id.btn_single:
                OptionCreator.create(this)
                        .openOption()
                        .setSelectType(SelectType.SINGLE)
                        .setShowList(showList, new ArrayList<Integer>())
                        .setListener(new OptionSelectorListener<String>() {

                            @Override
                            public void onOptionSelected(List<String> selectlist) {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (String str : selectlist) {
                                    stringBuilder.append(str + "\n");
                                }
                                content.setText(stringBuilder.toString());
                            }

                            @Override
                            public String getContent(int position) {
                                return showList.get(position);
                            }
                        })
                        .jumpOption();
                break;
            case R.id.btn_multiple:
                OptionCreator.create(this)
                        .openOption()
                        .setSelectType(SelectType.MULTIPLE)
                        .setShowList(showList, new ArrayList<Integer>())
                        .setListener(new OptionSelectorListener<String>() {

                            @Override
                            public void onOptionSelected(List<String> selectlist) {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (String str : selectlist) {
                                    stringBuilder.append(str + "\n");
                                }
                                content.setText(stringBuilder.toString());
                            }

                            @Override
                            public String getContent(int position) {
                                return showList.get(position);
                            }
                        })
                        .jumpOption();
                break;
        }
    }
}
