/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class ElasticLayout extends LinearLayout {

    // ----------------------------------------------------
    // Member Variable Block
    // ----------------------------------------------------
    private final String TAG = "ElasticLayout";
    private Context mContext;
    private View mScrolledChildView;

    // Header
    public View mHeaderView;
    public int mHeaderViewHeight;

    // Scroll
    private int mDownY, mTouchSlop, mScrolledYDistance;
    private Scroller mScroller;
    private float mScrollRate = .5f;
    private boolean mIsUIRefreshing, mIsUILoading;
    private boolean mRefresh;
    private boolean mLoadMore;
    private boolean mIsForceRefresh;

    // User is Scrolled ElasticLayout
    private boolean mIsScrolled;

    // is this initialized
    private boolean mIsFirstInit = true;

    // CallBack
    private OnRefreshListener mOnRefreshListener;
    private OnLoadMoreListener mOnLoadMoreListener;
    private OnFingerPullDownListener mOnFingerPullDownListener;

    /**
     * perform seq:
     * constructor -> set properties -> onMeasure -> onLayout -> onDraw
     */
    public ElasticLayout(Context context) {
        super(context);
        mContext = context;
        mScroller = new Scroller(context);
        init();
    }

    public ElasticLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mScroller = new Scroller(context);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        setRefresh(mRefresh);
        setLoadMore(mLoadMore);
        mTouchSlop = ViewConfiguration.get(mContext).getScaledTouchSlop();
        setOnFingerPullDownListener(mOnFingerPullDownListener = new DefaultFingerPullDownListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mHeaderView != null && mIsFirstInit) {
            mHeaderViewHeight = mHeaderView.getHeight();
            if (!mIsForceRefresh) {
                scrollTo(0, mHeaderViewHeight);
            }
            mIsFirstInit = false;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mRefresh) {
            mScrolledChildView = getChildAt(1);
        } else {
            mScrolledChildView = getChildAt(0);
        }
        measureChild(mScrolledChildView, widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 当正在刷新、加载更多时阻止用户继续滑动
                if (mIsUIRefreshing || mIsUILoading) {
                    intercepted = true;
                }
                double detaY = ev.getY() - mDownY;
                // refresh
                if (detaY > 0 && detaY > mTouchSlop && isChildScrollToTop()) {
                    intercepted = true;
                }
                // loadmore
                if (detaY < 0 && Math.abs(detaY) > mTouchSlop && isChildScrollToBottom()) {
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                move(event);
                break;
            case MotionEvent.ACTION_UP:
                up(event);
                break;
            default:
                break;
        }
        return true;
    }

    private void move(MotionEvent event) {
        if (mIsUIRefreshing || mIsUILoading) return;
        mScrolledYDistance = Math.round((event.getY() - mDownY) * mScrollRate);
        if (Math.abs(mScrolledYDistance) >= mTouchSlop) mIsScrolled = true;
        if (mScrolledYDistance > 0 && mRefresh) {
            mOnFingerPullDownListener.onPulling(mScrolledYDistance);
            int dis = mScrolledYDistance - mHeaderViewHeight;
            if (dis < mHeaderViewHeight) {
                mOnFingerPullDownListener.onBeforeRefreshPosition();
            } else if (dis == mHeaderViewHeight) {
                mOnFingerPullDownListener.onRefreshPosition();
            } else if (dis > mHeaderViewHeight) {
                mOnFingerPullDownListener.onAfterRefreshPosition();
            }
            scrollTo(0, mHeaderViewHeight - mScrolledYDistance);
        } else if (mScrolledYDistance < 0
                && mLoadMore
                && mScrolledChildView != null
                && isChildScrollToBottom()
                && mOnLoadMoreListener != null
                && !mIsUILoading) {
            mIsUILoading = true;
            mOnLoadMoreListener.onLoading();
        }
        if (!mRefresh && !mLoadMore){
            scrollTo(0, mHeaderViewHeight - mScrolledYDistance);
        }
    }

    private void up(MotionEvent event) {
        if (!mIsScrolled) return;
        if (mIsUIRefreshing || mIsUILoading) return;
        if (mRefresh && mScrolledYDistance > 0 && mScrolledYDistance - mHeaderViewHeight >= mHeaderViewHeight) {
            mIsUIRefreshing = true;
            int externalDeltaY = Math.abs(mScrolledYDistance) - mHeaderViewHeight;
            smoothScrollTo(-externalDeltaY, externalDeltaY);
            mOnFingerPullDownListener.onRefreshPosition();
            if (mOnRefreshListener != null) {
                mOnRefreshListener.onRefreshing();
            }
        } else {
            smoothScrollTo(mHeaderViewHeight - mScrolledYDistance, mScrolledYDistance);
        }
        mIsScrolled = false;
    }

    private void smoothScrollTo(int startY, int deltaY) {
        mScroller.startScroll(0, startY, 0, deltaY, 500);
        postInvalidate();
    }

    /**
     * invalidate()、postInvalidate() 方法调用时
     * computeScroll() 方法会被回调
     * <p>
     * computeScrollOffset 方法返回 true 时，滑动还未结束
     */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }

    /**
     * @return is child scroll to top
     */
    public boolean isChildScrollToTop() {
        return !mScrolledChildView.canScrollVertically(-1);
    }

    /**
     * @return is child scroll to bottom
     */
    public boolean isChildScrollToBottom() {
        if (mScrolledChildView instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) mScrolledChildView;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int count = recyclerView.getAdapter().getItemCount();
            if (layoutManager instanceof LinearLayoutManager && count > 0) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                return linearLayoutManager.findLastCompletelyVisibleItemPosition() == count - 1;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                int[] lastItems = new int[2];
                staggeredGridLayoutManager
                        .findLastCompletelyVisibleItemPositions(lastItems);
                int lastItem = Math.max(lastItems[0], lastItems[1]);
                return lastItem == count - 1;
            }
            return false;
        } else if (mScrolledChildView instanceof AbsListView) {
            final AbsListView absListView = (AbsListView) mScrolledChildView;
            int count = absListView.getAdapter().getCount();
            int firstPos = absListView.getFirstVisiblePosition();
            int lastPos = absListView.getLastVisiblePosition();
            View firstChildView = absListView.getChildAt(0);
            if (firstPos == 0 && firstChildView != null && firstChildView.getTop() >= absListView.getPaddingTop()) {
                return false;
            }
            if (lastPos > 0 && count > 0 && lastPos == count - 1) {
                return true;
            }
            return false;
        } else if (mScrolledChildView instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) mScrolledChildView;
            View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
            if (view != null) {
                int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
                return diff == 0;
            }
        } else if (mScrolledChildView instanceof NestedScrollView) {
            NestedScrollView scrollView = (NestedScrollView) mScrolledChildView;
            View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
            if (view != null) {
                int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
                return diff == 0;
            }
        }
        return false;
    }

    // API start
    public void setScrollRate(float scrollRate) {
        this.mScrollRate = scrollRate;
    }

    public void setRefresh(boolean refresh) {
        this.mRefresh = refresh;
        if (mHeaderView != null) {
            this.removeView(mHeaderView);
            mHeaderView = null;
        }
        if (refresh) {
            mHeaderView = new ProgressBar(mContext);
            mHeaderView.setBackgroundColor(Color.parseColor("#EEEEEE"));
            this.addView(mHeaderView, 0);
        }
    }

    public void setRefresh(boolean refresh, View headerView) {
        this.mRefresh = refresh;
        if (mHeaderView != null) {
            this.removeView(mHeaderView);
            mHeaderView = null;
        }
        if (refresh) {
            mHeaderView = headerView;
            this.addView(mHeaderView, 0);
        }
    }

    public void setLoadMore(boolean loadMore) {
        this.mLoadMore = loadMore;
    }

    public void setOnRefreshListener(@NonNull OnRefreshListener onRefreshListener) {
        this.mOnRefreshListener = onRefreshListener;
    }

    public void setOnLoadMoreListener(@NonNull OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    public void setOnFingerPullDownListener(@NonNull OnFingerPullDownListener onFingerPullDownListener) {
        this.mOnFingerPullDownListener = onFingerPullDownListener;
    }

    public void stopRefresh() {
        if (!mIsUIRefreshing) return;
        mOnFingerPullDownListener.onRefreshFinish();
        smoothScrollTo(0, mHeaderViewHeight);
        mIsUIRefreshing = false;
        if (mOnRefreshListener != null) {
            mOnRefreshListener.onRefreshFinish();
        }
    }

    public void stopLoad() {
        if (!mIsUILoading) return;
        mIsUILoading = false;
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener.onLoadFinish();
        }
    }

    public void forceToRefresh() {
        if (mRefresh && mOnRefreshListener != null) {
            mIsUIRefreshing = true;
            mIsForceRefresh = true;
            mIsFirstInit = true;
            smoothScrollTo(mHeaderViewHeight,-mHeaderViewHeight);
            mOnRefreshListener.onRefreshing();
        }
    }
    // API end

    // Interface
    public interface OnRefreshListener {

        void onRefreshing();

        void onRefreshFinish();

    }

    public interface OnLoadMoreListener {

        void onLoading();

        void onLoadFinish();

    }

    public interface OnFingerPullDownListener {

        void onPulling(int distance);

        void onBeforeRefreshPosition();

        void onRefreshPosition();

        void onAfterRefreshPosition();

        void onRefreshFinish();

    }

    // default ui interface implements
    public class DefaultFingerPullDownListener implements OnFingerPullDownListener {

        @Override
        public void onPulling(int distance) {

        }

        @Override
        public void onBeforeRefreshPosition() {

        }

        @Override
        public void onRefreshPosition() {

        }

        @Override
        public void onAfterRefreshPosition() {

        }

        @Override
        public void onRefreshFinish() {

        }

    }

}
