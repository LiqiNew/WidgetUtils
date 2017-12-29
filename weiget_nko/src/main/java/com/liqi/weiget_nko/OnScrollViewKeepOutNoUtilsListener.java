package com.liqi.weiget_nko;

import android.view.View;

/**
 * 软键盘弹出不遮挡指定区域的控件工具对象：滑动效果 -->对外暴露接口
 * Created by LiQi on 2017/9/28.
 */

public interface OnScrollViewKeepOutNoUtilsListener {
    void start(View mainLayout, View bottomLayout);

    /**
     * 清理内存
     */
    void clear();
}
