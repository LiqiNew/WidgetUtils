package com.liqi.weiget_nko;

import android.app.Activity;

/**
 * 软键盘弹出不遮挡指定区域的控件工具对象
 * Created by LiQi on 2017/9/28.
 */

public class WidgetKeepOutNoUtils {
    /**
     * 获取软键盘弹出不遮挡指定区域的控件工具对象：滑动效果对象
     *
     * @return 滑动效果对象
     */
    public static OnScrollViewKeepOutNoUtilsListener openScrollViewKeepOutNoUtils() {
        return ScrollViewKeepOutNoUtils.getScrollViewKeepOutNoUtils();
    }

    /**
     * 获取软键盘弹出不遮挡指定区域的控件工具对象：5.0以上缩小效果，5.0以下滑动效果对象
     *
     * @param activity 依赖界面
     * @return 5.0以上缩小效果，5.0以下滑动效果对象
     */
    public static OnReduceSlidingKeepOutNoUtilsListener openReduceSlidingKeepOutNoUtils(Activity activity) {
        return ReduceSlidingKeepOutNoUtils.getWidgetKeepOutNoUtils(activity);
    }
}
