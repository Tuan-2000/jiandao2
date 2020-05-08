package com.example.jd.recommend.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jd.R;
import com.example.jd.bean.RecommendRecBean;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;


import java.util.ArrayList;
import java.util.List;



public class RecommendAdapter extends RecyclerView.Adapter {
    private final static int TYPE_VIDEO = 4;
    private final static int SMALL_IMG = 3;
    private final static int BIG_IMG = 2;
    private final static int TYPE_FLASH = 1;
    private final static int TYPE_BANNER = 0;
    private Context context;
    private List<RecommendRecBean.DataBean.ArticleListBean> article_list = new ArrayList<>();
    private List<RecommendRecBean.DataBean.BannerListBean> banner_list = new ArrayList<>();
    private List<RecommendRecBean.DataBean.FlashListBean> flash_list = new ArrayList<>();

    public RecommendAdapter(Context context) {
        this.context = context;
    }

    public void setArticleList(List<RecommendRecBean.DataBean.ArticleListBean> article_list) {
        this.article_list.addAll(article_list);
        notifyDataSetChanged();
    }

    public void setBannerList(List<RecommendRecBean.DataBean.BannerListBean> banner_list) {
        this.banner_list.addAll(banner_list);
        notifyDataSetChanged();
    }

    public void setFlashList(List<RecommendRecBean.DataBean.FlashListBean> flash_list) {
        this.flash_list.addAll(flash_list);
        notifyDataSetChanged();
    }

    public View getLayout(int res) {
        View inflate = LayoutInflater.from(context).inflate(res, null);
        return inflate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_BANNER:
                return new ViewHolderBanner(getLayout(R.layout.topic_banner));
            case TYPE_FLASH:
                return new ViewHolderFlash(getLayout(R.layout.recommenr_flash));
            case SMALL_IMG:
                return new ViewHolderSmall(getLayout(R.layout.recommenr_title));
            case BIG_IMG:
                return new ViewHolderBig(getLayout(R.layout.recommenr_bigimg));
            case TYPE_VIDEO:
                return new ViewHolderVideo(getLayout(R.layout.recommenr_video));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_BANNER:
                ViewHolderBanner viewHolderBanner = (ViewHolderBanner) holder;
                viewHolderBanner.banner.setImages(banner_list).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        RecommendRecBean.DataBean.BannerListBean bean = (RecommendRecBean.DataBean.BannerListBean) path;
                        Glide.with(context).load(bean.getImage_url()).into(imageView);
                    }
                }).start();
                break;
            case TYPE_FLASH:
                ViewHolderFlash viewHolderFlash = (ViewHolderFlash) holder;
                ArrayList<String> datas = new ArrayList<>();
                for (int i = 0; i < flash_list.size(); i++) {
                    String theme = flash_list.get(i).getTheme();
                    datas.add(theme);
                }
                SimpleMF<String> simpleMF = new SimpleMF(context);
                simpleMF.setData(datas);
                viewHolderFlash.simpleMarqueeView.setMarqueeFactory(simpleMF);
                viewHolderFlash.simpleMarqueeView.startFlipping();
                break;
            case SMALL_IMG:
                if (banner_list.size() > 0) {
                    if (flash_list.size() > 0) {
                        position = position - 2;
                    } else {
                    position = position - 1;
                    }
                }
                RecommendRecBean.DataBean.ArticleListBean bean = article_list.get(position);
                ViewHolderSmall viewHolderSmall = (ViewHolderSmall) holder;
                viewHolderSmall.name.setText(bean.getColumn_name());
                viewHolderSmall.title.setText(bean.getTheme());
                Glide.with(context).load(bean.getImage_url()).into(viewHolderSmall.imageView);
                break;
            case BIG_IMG:
                if (banner_list.size() > 0) {
                    if (flash_list.size() > 0) {
                        position = position - 2;
                    } else {
                    position = position - 1;
                    }
                }
                RecommendRecBean.DataBean.ArticleListBean bigBean = article_list.get(position);
                ViewHolderBig viewHolderBig = (ViewHolderBig) holder;
                viewHolderBig.name.setText(bigBean.getColumn_name());
                viewHolderBig.title.setText(bigBean.getTheme());
                Glide.with(context).load(bigBean.getImage_url()).into(viewHolderBig.img);
                break;
            case TYPE_VIDEO:
                if (banner_list.size() > 0) {
                    if (flash_list.size() > 0) {
                        position = position - 2;
                    } else {
                        position = position - 1;
                    }
                }
                RecommendRecBean.DataBean.ArticleListBean videoBean = article_list.get(position);
                ViewHolderVideo viewHolderVideo = (ViewHolderVideo) holder;
                viewHolderVideo.name.setText(videoBean.getColumn_name());
                viewHolderVideo.title.setText(videoBean.getTheme());

                viewHolderVideo.video.setUp(videoBean.getVideo_url(),videoBean.getTheme());
                Glide.with(context).load(videoBean.getImage_url()).into(viewHolderVideo.video.ivThumb);

                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && banner_list.size()>0) {//banner
            return TYPE_BANNER;
        } else if ((position == 1) && flash_list.size()>0) {//跑马灯滚动
            return TYPE_FLASH;
        } else {
            if (flash_list.size()>0){
                position = position-2;
            }else {
                position = position-1;
            }
            int view_type = article_list.get(position).getView_type();
            if (view_type == 2) {//大图
                return BIG_IMG;
            } else if (view_type == 4) {//视频
                return TYPE_VIDEO;
            } else if (view_type == 5) {//跑马灯滚动
                return TYPE_FLASH;
            } else {//小图
                return SMALL_IMG;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (banner_list.size() > 0) {
//            if (flash_list.size() > 0) {
//                return article_list.size() + 1 + 1;
//            } else {
                return article_list.size() + 1;
//            }
        } else {
            return article_list.size();
        }
    }

    public class ViewHolderBanner extends RecyclerView.ViewHolder {
        Banner banner;

        public ViewHolderBanner(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public class ViewHolderFlash extends RecyclerView.ViewHolder {
        SimpleMarqueeView simpleMarqueeView;

        public ViewHolderFlash(@NonNull View itemView) {
            super(itemView);
            simpleMarqueeView = itemView.findViewById(R.id.simpleMarqueeView);
        }
    }

    public class ViewHolderSmall extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView name;

        public ViewHolderSmall(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            name = itemView.findViewById(R.id.name);
        }
    }

    public class ViewHolderBig extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        TextView name;

        public ViewHolderBig(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            name = itemView.findViewById(R.id.name);
        }
    }

    public class ViewHolderVideo extends RecyclerView.ViewHolder {
        //        VideoView video;
        fm.jiecao.jcvideoplayer_lib.JCVideoPlayer video;
        TextView title;
        TextView name;

        public ViewHolderVideo(@NonNull View itemView) {
            super(itemView);
            video = itemView.findViewById(R.id.video);
            title = itemView.findViewById(R.id.title);
            name = itemView.findViewById(R.id.name);
        }
    }
}
