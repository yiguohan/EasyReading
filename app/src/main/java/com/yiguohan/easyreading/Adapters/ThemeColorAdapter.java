package com.yiguohan.easyreading.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yiguohan.easyreading.Beans.ThemeColor;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Utils.ThemeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yiguohan on 2017/9/14.
 * GitHub: https://github.com/yiguohan
 * E-mail: yiguohan@gmail.com
 */

public class ThemeColorAdapter extends RecyclerView.Adapter<ThemeColorAdapter.ViewHolder> {
    private List<ThemeColor> themeColorList;

    private Context mContext;

    public ThemeColorAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_color_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ThemeColor themeColor = this.themeColorList.get(position);
        holder.theme_color.setBackgroundColor(themeColor.getColor());
        holder.theme_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (themeColor.isChosen()){
                    holder.iv_choose.setVisibility(View.VISIBLE);
                    ThemeUtil.setThemePosition(position);
                }else {
                    holder.iv_choose.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return themeColorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.theme_color)
        CircleImageView theme_color;
        @BindView(R.id.choose)
        ImageView iv_choose;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setData(List<ThemeColor> themes){
        this.themeColorList = themes;
    }
}
