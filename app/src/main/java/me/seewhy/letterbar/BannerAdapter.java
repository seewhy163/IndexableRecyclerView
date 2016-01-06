package me.seewhy.letterbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
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
    private int mNumberOfImagePerLine = 1;
    private int mLineNumber = 0;
    LinkedHashMap<String, List<BannerModel>> mSectionedHashMap;

    private Context mContext;

    public BannerAdapter(Context context, List<BannerModel> models) {
        mContext = context;
        this.mBannerModels = models;
        mSectionedHashMap = new LinkedHashMap<>();
        mLayoutInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        mSections.clear();
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
        ((BannerViewHolder) holder).mTextView.setText(mBannerModels.get(position).url);
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
        TextView mTextView;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView;
            mTextView = (TextView) itemView.findViewById(R.id.tvBannerName);
        }
    }
}


