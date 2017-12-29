package com.liqi.weiget_nko;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/**软键盘弹出不遮挡指定区域的控件工具对象：5.0以上缩小效果，5.0以下滑动效果 -->对外暴露接口
 * Created by LiQi on 2017/9/28.
 */

public interface OnReduceSlidingKeepOutNoUtilsListener {
    int getX();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    OnReduceSlidingKeepOutNoUtilsListener setX(int x);

    int getY();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    OnReduceSlidingKeepOutNoUtilsListener setY(int y);

    void start(View mainLayout, View logoLayout, View bottomLayout);
    /**
     * 清理内存
     */
    void clear();
}
