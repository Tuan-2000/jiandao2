package com.example.jd.topic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jd.R;
import com.example.jd.bean.TopicBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TopicBean.DataBean.ListBean> title_list = new ArrayList<>();
    private List<TopicBean.DataBean.BannerListBean> banner_list = new ArrayList<>();
    private static int TYPE_BANNER = 0;
    private static int TYPE_TITLE = 1;

    public TopicAdapter(Context context) {
        this.context = context;
    }

    public void setTitleList(List<TopicBean.DataBean.ListBean> title_list) {
        this.title_list.addAll(title_list);
        notifyDataSetChanged();
    }

    public void setBannerList(List<TopicBean.DataBean.BannerListBean> banner_list) {
        this.banner_list.addAll(banner_list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER){
            View inflate = LayoutInflater.from(context).inflate(R.layout.topic_banner, null);
            return new VH1(inflate);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.topic_title, null);
            return new VH2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == TYPE_BANNER){
            VH1 vh1 = (VH1) holder;
            vh1.banner.setImages(banner_list).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    TopicBean.DataBean.BannerListBean bean = (TopicBean.DataBean.BannerListBean) path;
                    Glide.with(context).load(bean.getImage_url()).into(imageView);
                }
            }).start();
        }else {
            if (banner_list.size()>0){
                position = position-1;
            }
            TopicBean.DataBean.ListBean bean = title_list.get(position);
            VH2 vh2 = (VH2) holder;
            vh2.title.setText(bean.getTheme());
            vh2.name.setText(bean.getColumn_name());
            Glide.with(context).load(bean.getImage_url()).into(vh2.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return banner_list.size()>0?title_list.size()+1:title_list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else {
            return TYPE_TITLE;
        }
    }
    public class VH1 extends RecyclerView.ViewHolder {
        Banner banner;
        public VH1(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public class VH2 extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView name;
        public VH2(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            name = itemView.findViewById(R.id.name);
        }
    }
}
