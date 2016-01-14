package me.seewhy.IndexableRecyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Author: seewhy
 * Date: 2016/1/14
 */
public class IndexableRecyclerView extends RelativeLayout {
    public static final int DEFAULT_COLUMN_NUMBER = 3;
    private RecyclerView mRecyclerView;
    private TextView mTipText;
    private LetterBar mLetterBar;
    private Context mContext;
    public int mColumnNumber = DEFAULT_COLUMN_NUMBER;
    private SectionedRecyclerAdapter mRecyclerAdapter;

    public IndexableRecyclerView(Context context) {
        this(context, null);
    }

    public IndexableRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexableRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews(context);

        init(attrs, defStyleAttr);
    }

    private void initViews(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_indexable, this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLetterBar = (LetterBar) view.findViewById(R.id.letter_bar);
        mTipText = (TextView) view.findViewById(R.id.tip_text);
    }

    public void setAdapter(IndexableRecyclerViewAdapter adapter) {
        mRecyclerAdapter = new SectionedRecyclerAdapter(mContext, R.layout.title_item, R.id.tvName, adapter);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.IndexableRecyclerView, defStyleAttr, 0);
        mColumnNumber = typedArray.getInteger(R.styleable.IndexableRecyclerView_recyclerColumns, DEFAULT_COLUMN_NUMBER);
        typedArray.recycle();

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, mColumnNumber);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mRecyclerAdapter.isSectionHeaderPosition(position) ? mColumnNumber : 1;
            }
        });
        mLetterBar.setOnLetterSelectListener(new LetterBar.OnLetterSelectListener() {
            @Override
            public void onLetterSelect(int position, String letter, boolean confirmed) {
                if (confirmed) {
                    mTipText.setVisibility(View.GONE);
                } else {
                    mTipText.setVisibility(View.VISIBLE);
                    mTipText.setText(letter);
                }
                Integer sectionPosition = mRecyclerAdapter.getSectionPosition(position);
                if (sectionPosition != null)
                    gridLayoutManager.scrollToPositionWithOffset(sectionPosition, 0);
            }
        });
    }


}
