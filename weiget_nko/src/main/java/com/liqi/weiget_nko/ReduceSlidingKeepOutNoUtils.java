package com.liqi.weiget_nko;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * 软键盘弹出不遮挡指定区域的控件工具对象：5.0以上缩小效果，5.0以下滑动效果
 * 注：适应于logo图不占用大量屏幕时。logo尺寸大概100dp*100dp左右。（约束有点多，如果不是特别要求，建议使用滑动效果对象去实现）
 * <---------->
 * 视图布局最佳样式：
 * mainLayout对应的控件
 * <ScrollView>
 * <...>
 * logoLayout对应的控件
 * <ImageView
 * android:layout_width="100dp"
 * android:layout_height="100dp">
 * </ImageView>
 * bottomLayout对应的控件
 * <底部展示到那个布局>
 * </底部展示到那个布局>
 * <...>
 * </ScrollView>
 * </------------>
 * 5.0以下调用，调用对象在AndroidMainFest.xml中属性设置android:windowSoftInputMode="adjustResize"。可以多个设置。但是必须要有一个设置为adjustResize
 * Created by LiQi on 2017/1/9.
 */
class ReduceSlidingKeepOutNoUtils implements View.OnLayoutChangeListener, OnReduceSlidingKeepOutNoUtilsListener {
    private static ReduceSlidingKeepOutNoUtils mReduceSlidingKeepOutNoUtils;
    private final int KEY_HEIGHT; //软件盘弹起后所占高度
    private View mLogoLayout;
    /**
     * XY各偏距的距离
     * 默认是x=100
     * y=100
     *
     * <hint>
     * 此处设置的值必须要比图片logo宽高>=20
     * 例子：logo宽度：80 X：100。
     *      logo高度：80 Y：100。
     * </hint>
     */
    private int x = 100, y = 100;

    private ReduceSlidingKeepOutNoUtils(Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        KEY_HEIGHT = point.y / 3;//弹起高度为屏幕高度的1/3
    }

    static OnReduceSlidingKeepOutNoUtilsListener getWidgetKeepOutNoUtils(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        return mReduceSlidingKeepOutNoUtils = null == mReduceSlidingKeepOutNoUtils ? new ReduceSlidingKeepOutNoUtils(activity) : mReduceSlidingKeepOutNoUtils;
    }

    /**
     * 清理内存
     */
    @Override
    public void clear() {
        mLogoLayout = null;
        WidgetKeepOutNoUtils.openScrollViewKeepOutNoUtils().clear();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public OnReduceSlidingKeepOutNoUtilsListener setX(int x) {
        this.x = x;
        return mReduceSlidingKeepOutNoUtils;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public OnReduceSlidingKeepOutNoUtilsListener setY(int y) {
        this.y = y;
        return mReduceSlidingKeepOutNoUtils;
    }

    /**
     * 布局启动
     *
     * @param mainLayout XML父布局
     * @param logoLayout logo布局
     * @param mainLayout button布局
     */
    @Override
    public void start(View mainLayout, View logoLayout, View bottomLayout) {
        this.mLogoLayout = logoLayout;
        //版本5.0以上调用：缩小logo图片来展示底部指定控件范围
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainLayout.addOnLayoutChangeListener(this);//给需要操作的布局设置监听
        }
        //版本5.0以下调用：滑动logo图片来展示底部指定控件范围
        else {
            WidgetKeepOutNoUtils.openScrollViewKeepOutNoUtils().start(mainLayout, bottomLayout);
        }
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > KEY_HEIGHT)) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mLogoLayout.getLayoutParams();//获取布局，设置键盘弹起后logo的宽高
            params.setMargins(0, params.topMargin - y, 0, params.bottomMargin - y);
            params.height = params.height - x;
            params.width = params.width - x;
            mLogoLayout.setLayoutParams(params);

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > KEY_HEIGHT)) {//键盘收回后，logo恢复原来大小，位置同样回到初始位置
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mLogoLayout.getLayoutParams();
            params.setMargins(0, params.topMargin + y, 0, params.bottomMargin + y);
            params.height = params.height + x;
            params.width = params.width + x;
            mLogoLayout.setLayoutParams(params);
        }
    }
}
