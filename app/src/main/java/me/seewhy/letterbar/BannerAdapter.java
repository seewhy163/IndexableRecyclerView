package me.seewhy.letterbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BG204119 on 2015/12/30.
 */
public class BannerAdapter extends SectionedRecyclerAdapter {
    public static final int TYPE_BANNER = 0;
    public static final int TYPE_TITLE = 1;
    private final LayoutInflater mLayoutInflater;

    private List<BannerModel> mBannerModels;
    private int mNumberOfImagePerLine = 3;
    HashMap<String, List<BannerModel>> mSectionedHashMap;

    List<Integer> mTitlePosition = new ArrayList<>();
    SectionHelper mSectionHelper;

    public BannerAdapter(Context context, List<BannerModel> models) {
        super(context, R.layout.banner_item, R.id.tvName);
        this.mBannerModels = models;
        dispatch();
        calculatePosition();
        mLayoutInflater = LayoutInflater.from(context);
    }

    private void calculatePosition() {
        mTitlePosition.add(0);
        for (int i = 0; i < 26; i++) {

        }
    }

    private void dispatch() {
        for (int i = 0; i < mBannerModels.size(); i++) {
            String ch = HanziToPinyin.getFirstPinYinChar(mBannerModels.get(i).name);
            List<BannerModel> bannerModels = mSectionedHashMap.get(ch);
            if (bannerModels == null) {
                bannerModels = new ArrayList<>();
            }
            bannerModels.add(mBannerModels.get(i));
            mSectionedHashMap.put(ch, bannerModels);
        }
        super.setSections(mSectionedHashMap.keySet());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER)
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_item, parent, false));
        else if (viewType == TYPE_TITLE) {
            return new TitleViewHolder(mLayoutInflater.inflate(R.layout.title_item, parent, false));
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mSectionHelper.isSectionHeaderPosition(position)) {
        }
    }

    @Override
    public int getItemCount() {
        return mBannerModels.size() + mTitlePosition.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mSectionHelper.isSectionHeaderPosition(position)) {
            return TYPE_TITLE;
        } else {
            return TYPE_BANNER;
        }
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public BannerViewHolder(View itemView) {
            super(itemView);

        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public TitleViewHolder(View itemView) {
            super(itemView);

        }
    }
}
