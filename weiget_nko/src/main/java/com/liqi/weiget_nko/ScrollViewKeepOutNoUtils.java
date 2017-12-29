package com.liqi.weiget_nko;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * 软键盘弹出不遮挡指定区域的控件工具对象：滑动效果
 * <---------->
 * 视图布局最佳样式：
 * mainLayout对应的控件
 * <ScrollView>
 * <...>
 * bottomLayout对应的控件
 * <底部展示到那个布局>
 * </底部展示到那个布局>
 * </...>
 * </ScrollView>
 * <---------->
 * 调用对象在AndroidMainFest.xml中属性设置android:windowSoftInputMode="adjustResize"。可以多个设置。但是必须要有一个设置为adjustResize
 * Created by LiQi on 2017/9/28.
 */

class ScrollViewKeepOutNoUtils implements ViewTreeObserver.OnGlobalLayoutListener, OnScrollViewKeepOutNoUtilsListener {

    private static ScrollViewKeepOutNoUtils mScrollViewKeepOutNoUtils;
    private View mMainLayout, mBottomLayout;
    //记录第一次滚动值，以第一次滚动值为准。
    private int mSrollHeight;

    private ScrollViewKeepOutNoUtils() {

    }

    static OnScrollViewKeepOutNoUtilsListener getScrollViewKeepOutNoUtils() {
        return mScrollViewKeepOutNoUtils = null == mScrollViewKeepOutNoUtils ? new ScrollViewKeepOutNoUtils() : mScrollViewKeepOutNoUtils;
    }

    @Override
    public void start(View mainLayout, View bottomLayout) {
        mSrollHeight = 0;
        mMainLayout = mainLayout;
        mBottomLayout = bottomLayout;
        mMainLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        mMainLayout.getWindowVisibleDisplayFrame(rect);
        int mainInvisibleHeight = mMainLayout.getRootView().getHeight() - rect.bottom;
        if (mainInvisibleHeight > 100) {
            int[] location = new int[2];
            mBottomLayout.getLocationInWindow(location);
            int srollHeight = (location[1] + mBottomLayout.getHeight()) - rect.bottom;
            //记录第一次滚动的值。滚动值要大于0，经过调试发现滚动都大于才是能正确滚动到指定视图底部。
            if (mSrollHeight == 0&&srollHeight>0) {
                mSrollHeight = srollHeight;
            }
            //如果srollHeight小于40，代表可点击按钮完全可见。那么就不需要滚动。
            if (srollHeight > 40) {
                //休眠100毫秒，防止软件没有加载完毕就执行了
                mMainLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMainLayout.scrollTo(0, mSrollHeight);
                    }
                }, 100);
            }
        } else {
            mMainLayout.scrollTo(0, 0);
        }
    }

    /**
     * 清理内存
     */
    public void clear() {
        mBottomLayout = null;
        mMainLayout = null;
        mSrollHeight = 0;
    }
}
