package com.zbase.view.decor;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public final class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private final int mSpanCount;

    private final int mSpacing;

    private final boolean mIncludeEdge;

    private int mHeaderCount;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.mSpanCount = spanCount;
        this.mSpacing = spacing;
        this.mIncludeEdge = includeEdge;
    }

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge, int headerCount) {
        this.mSpanCount = spanCount;
        this.mSpacing = spacing;
        this.mIncludeEdge = includeEdge;
        this.mHeaderCount = headerCount;
    }


    /**
     * 摆放每个 itemView 时就添加间距
     *
     * (left,top)
     *     --------------------------------------
     *     |              outRect               |
     *     |         -------------------        |
     *     |         |    itemView     |        |
     *     |         |                 |        |
     *     |         -------------------        |
     *     |                                    |
     *     --------------------------------------
     *                                     (right,bottom)
     *
     * https://blog.csdn.net/briblue/article/details/70161917
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view) - mHeaderCount; // item position
        if (position >= 0) {
            int column = position % mSpanCount; // item column
            if (mIncludeEdge) {
                // 设置的都是偏移量
                outRect.left = mSpacing - column * mSpacing / mSpanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * mSpacing / mSpanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < mSpanCount) { // top edge
                    outRect.top = mSpacing;
                }
                outRect.bottom = mSpacing; // item bottom
            } else {
                outRect.left = column * mSpacing / mSpanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= mSpanCount) {
                    outRect.top = mSpacing; // item top
                }
            }
        } else {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            outRect.bottom = 0;
        }
    }

    /**
     * 在 itemView 下面绘制，需要先重写 getItemOffsets 方法把 itemView 撑开，然后才能在撑开的位置绘制图形
     *
     * 此方法是当 RecyclerView 摆放好所有 itemView 后，再遍历每个 itemView 进行绘制
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    /**
     * 在 itemView 上面绘制
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

}
