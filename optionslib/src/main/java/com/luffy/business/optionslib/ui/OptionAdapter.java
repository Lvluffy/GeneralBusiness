package com.luffy.business.optionslib.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luffy.business.optionslib.R;
import com.luffy.business.optionslib.config.CompareUtil;
import com.luffy.business.optionslib.config.OptionConfig;
import com.luffy.business.optionslib.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by lvlufei on 2020-05-14
 *
 * @name 选择-适配器
 */
public class OptionAdapter<T> extends RecyclerView.Adapter<OptionAdapter.HomeHolder> {

    private OptionConfig config;
    private List<T> showList;
    private OnItemClickListener itemClickListener;

    public OptionAdapter() {
        config = OptionConfig.getInstance();
        this.showList = config.getShowList();
    }

    @Override
    public OptionAdapter.HomeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_option_layout, viewGroup, false);
        HomeHolder mHomeHolder = new HomeHolder(view);
        return mHomeHolder;
    }

    @Override
    public void onBindViewHolder(final OptionAdapter.HomeHolder homeHolder, final int position) {
        homeHolder.tvTitle.setText(config.getListener().getContent(position));
        if (CompareUtil.compareTo(position, config.getSelectedList())) {
            homeHolder.ivTick.setVisibility(View.VISIBLE);
        } else {
            homeHolder.ivTick.setVisibility(View.GONE);
        }
        homeHolder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(OptionAdapter.this, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    /**
     * 设置子项的点击事件
     *
     * @param listener
     */
    public void seOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    /**
     * holder
     */
    class HomeHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItem;
        TextView tvTitle;
        ImageView ivTick;

        public HomeHolder(View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_parent);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivTick = itemView.findViewById(R.id.iv_tick);
        }
    }
}
