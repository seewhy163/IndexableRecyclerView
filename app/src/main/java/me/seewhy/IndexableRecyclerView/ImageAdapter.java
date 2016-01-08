package me.seewhy.IndexableRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by BG204119 on 2015/12/30.
 */
public class ImageAdapter extends RecyclerView.Adapter implements SectionedRecyclerAdapter.SectionedRecyclerDelegate {
    public static final String TAG = "ImageAdapter";
    public static final int TYPE_BANNER = 0;
    private final LayoutInflater mLayoutInflater;

    private List<ImageModel> mImageModels;
    private int mNumberOfImagePerLine = 1;
    private int mLineNumber = 0;
    LinkedHashMap<String, List<ImageModel>> mSectionedHashMap;

    private Context mContext;

    public ImageAdapter(Context context, List<ImageModel> models) {
        mContext = context;
        this.mImageModels = models;
        mSectionedHashMap = new LinkedHashMap<>();
        mLayoutInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        Collections.sort(mImageModels);
        mSections.clear();
        for (int i = 0; i < mImageModels.size(); i++) {
            String ch = HanziToPinyin.getFirstPinYinChar(mImageModels.get(i).name);
            List<ImageModel> imageModels = mSectionedHashMap.get(ch);
            if (imageModels == null) {
                imageModels = new ArrayList<>();
            }
            imageModels.add(mImageModels.get(i));
            mSectionedHashMap.put(ch, imageModels);
        }
        Set<String> keySet = mSectionedHashMap.keySet();
        String strings[] = new String[keySet.size()];
        keySet.toArray(strings);
        Arrays.sort(strings);
        int pos = 0;
        for (String title : strings) {
            SectionedRecyclerAdapter.Section section = new SectionedRecyclerAdapter.Section(pos, title);
            mSections.add(section);
            pos += (mSectionedHashMap.get(title).size() + (mNumberOfImagePerLine - 1)) / mNumberOfImagePerLine;
        }

        mLineNumber = pos;
    }


    @Override
    public List<SectionedRecyclerAdapter.Section> getSections() {
        return mSections;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext).load(mImageModels.get(position).resourceId)
                .into(((BannerViewHolder) holder).mImageView);
    }

    @Override
    public int getItemCount() {
        return mLineNumber;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_BANNER;
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        public BannerViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}


