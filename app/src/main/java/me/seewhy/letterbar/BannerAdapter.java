package me.seewhy.letterbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by BG204119 on 2015/12/30.
 */
public class BannerAdapter extends RecyclerView.Adapter implements SectionedRecyclerAdapter.SectionedRecyclerDelegate {
    public static final String TAG = "BannerAdapter";
    public static final int TYPE_BANNER = 0;
    private final LayoutInflater mLayoutInflater;

    private List<BannerModel> mBannerModels;
    private int mNumberOfImagePerLine = 3;
    private int mLineNumber = 0;
    LinkedHashMap<String, List<BannerModel>> mSectionedHashMap;

    //key是每个section最后一行的position,value是从左往右数的位置
    private SparseArray<Integer> mLastPositionOfSections = new SparseArray<>();

    //记录字母ascii对应的位置
    private SparseArray<Integer> mKeyPositionMap = new SparseArray<>();
    private Set<Integer> mLastPositions = new HashSet<>();
    private Context mContext;

    public BannerAdapter(Context context, List<BannerModel> models) {
        mContext = context;
        this.mBannerModels = models;
        mSectionedHashMap = new LinkedHashMap<>();
        mLayoutInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        for (int i = 0; i < mBannerModels.size(); i++) {
            String ch = HanziToPinyin.getFirstPinYinChar(mBannerModels.get(i).name);
            List<BannerModel> bannerModels = mSectionedHashMap.get(ch);
            if (bannerModels == null) {
                bannerModels = new ArrayList<>();
            }
            bannerModels.add(mBannerModels.get(i));
            mSectionedHashMap.put(ch, bannerModels);
        }
        Set<String> keySet = mSectionedHashMap.keySet();
        String strings[] = new String[keySet.size()];
        keySet.toArray(strings);
        Arrays.sort(strings);
        int pos = 0;
        int positionWithSection = 0;
        int sectionNumber = 1;
        for (String title : strings) {
            if (title.charAt(0) >= 'A' && title.charAt(0) <= 'Z') {
                mKeyPositionMap.put(title.charAt(0) - 'A' + 1, positionWithSection);
            } else {
                //如果不是字母，用 0 表示
                mKeyPositionMap.put(0, 0);
            }

            SectionedRecyclerAdapter.Section section = new SectionedRecyclerAdapter.Section(pos, title);
            mSections.add(section);
            pos += (mSectionedHashMap.get(title).size() + (mNumberOfImagePerLine - 1)) / mNumberOfImagePerLine;
            positionWithSection = pos + sectionNumber++;
            mLastPositionOfSections.append(pos - 1, mSectionedHashMap.get(title).size() % mNumberOfImagePerLine);
        }
        for (int i = 0; i < mLastPositionOfSections.size(); i++) {
            int key = mLastPositionOfSections.keyAt(i);
            mLastPositions.add(key);
        }
        mLineNumber = pos;
    }

    public Integer getSectionPosition(int asciiPosition) {
        Integer integer = mKeyPositionMap.get(asciiPosition);
        Log.v(TAG, "origin position: " + asciiPosition + " mapped position: " + integer);
        return integer;
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
        int images2Show = mNumberOfImagePerLine;
        if (mLastPositions.contains(position)) {
            images2Show = mLastPositionOfSections.get(position);
        }
        BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
        bannerViewHolder.mLinearLayout.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        params.setMargins(10, 10, 10, 10);
        for (int i = 0; i < images2Show; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.drawable.pic);
            imageView.setLayoutParams(params);
            bannerViewHolder.mLinearLayout.addView(imageView);
        }

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
        LinearLayout mLinearLayout;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView;
        }
    }
}


