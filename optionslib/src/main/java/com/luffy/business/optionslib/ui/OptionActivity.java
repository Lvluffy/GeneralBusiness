package com.luffy.business.optionslib.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.luffy.business.optionslib.R;
import com.luffy.business.optionslib.config.CompareUtil;
import com.luffy.business.optionslib.config.OptionConfig;
import com.luffy.business.optionslib.config.SelectType;
import com.luffy.business.optionslib.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlufei on 2020-05-14
 *
 * @name 选择-界面
 */
public class OptionActivity<T> extends Activity {

    private TextView tvTitle;
    private TextView tvSave;
    private RecyclerView rv;

    private OptionAdapter mAdapter;
    private OptionConfig config;
    private List<T> showList;
    private List<Integer> selectIndexList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_layout);
        initConfig();
        initView();
        initRecyclerView();
    }

    /**
     * 获取配置数据
     */
    private void initConfig() {
        this.config = OptionConfig.getInstance();
        this.showList = config.getShowList();
        this.selectIndexList = config.getSelectedList();
    }

    /**
     * 初始化view
     */
    private void initView() {
        // 标题
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(config.getTitle());

        // 保存按钮
        tvSave = findViewById(R.id.tv_save);
        if (-1 != config.getSaveSelector()) {
            tvSave.setTextColor(config.getSaveSelector());
        }
        if (SelectType.SINGLE == config.getType()) {
            tvSave.setVisibility(View.GONE);
        } else {
            tvSave.setVisibility(View.VISIBLE);
            refreshSaveStatus();
        }

        // RecyclerView
        rv = findViewById(R.id.rv);

        // 返回键点击事件
        findViewById(R.id.layout_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 保存按钮点击事件
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (config.getSelectedList().isEmpty()) {
                    Toast.makeText(OptionActivity.this, "还未选择数据！", Toast.LENGTH_SHORT).show();
                    return;
                }
                config.getListener().onOptionSelected(handleSelectData(config.getSelectedList()));
                OptionActivity.this.finish();
            }
        });
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new OptionAdapter();
        rv.setAdapter(mAdapter);
        mAdapter.seOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(OptionAdapter adapter, int position) {
                // 单选
                if (SelectType.SINGLE == config.getType()) {
                    selectIndexList.clear();
                    selectIndexList.add(position);
                    adapter.notifyDataSetChanged();
                    config.getListener().onOptionSelected(handleSelectData(selectIndexList));
                    OptionActivity.this.finish();
                } else { // 多选
                    if (CompareUtil.compareTo(position, selectIndexList)) {
                        deleteSelectedData(position, selectIndexList);
                        adapter.notifyDataSetChanged();
                    } else {
                        selectIndexList.add(position);
                        adapter.notifyDataSetChanged();
                    }
                    refreshSaveStatus();
                }
            }
        });
    }

    /**
     * 刷新保存的状态
     */
    private void refreshSaveStatus() {
        if (!selectIndexList.isEmpty() && selectIndexList.size() > 0) {
            tvSave.setSelected(true);
        } else {
            tvSave.setSelected(false);
        }
    }

    /**
     * 删除已选中的数据
     *
     * @param position   目标数据下标
     * @param selectList 选中的数据下标
     */
    private void deleteSelectedData(int position, List<Integer> selectList) {
        if (!selectList.isEmpty()) {
            int delIndex = -1;
            for (int i = 0; i < selectList.size(); i++) {
                if (position == selectList.get(i)) {
                    delIndex = i;
                }
            }
            if (delIndex != -1) {
                selectList.remove(delIndex);
            }
        }
    }

    /**
     * 组合选中的数据：选中下标-->选中数据
     *
     * @param selectList 选中数据下标
     * @return
     */
    private List<T> handleSelectData(List<Integer> selectList) {
        if (!selectList.isEmpty()) {
            List<T> result = new ArrayList<>();
            for (Integer i : selectList) {
                result.add(showList.get(i));
            }
            return result;
        }
        return new ArrayList<>();
    }

}
