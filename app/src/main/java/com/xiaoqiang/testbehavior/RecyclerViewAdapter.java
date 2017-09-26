package com.xiaoqiang.testbehavior;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Description:
 * Created by crx on 2017/9/26.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_recycler_view, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text.setText("我就是爱音乐别叫我停下来-" + position + "th");
        holder.image.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_image);
            text = itemView.findViewById(R.id.tv_text);
        }
    }
}
