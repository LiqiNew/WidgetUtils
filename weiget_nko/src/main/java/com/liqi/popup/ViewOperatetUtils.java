package com.liqi.popup;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * EditText控件显示和关闭辅助工具
 *
 * @author Liqi
 */
public class ViewOperatetUtils {
    /**
     * 异步展开editText键盘并且获取输入焦点（dialog里面展开键盘需要异步展开）
     *
     * @param editText
     * @param time     延迟多久展示键盘
     */
    public static void asynUnfoldEditTextIn(final EditText editText, long time) {
        // 弹出软键盘,并且让EditText获取可输入的焦点
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        // 在异步去执行键盘的展开
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) editText
                        .getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, time);
    }

    /**
     * 展开editText键盘并且获取输入焦点
     *
     * @param editText
     */
    public static void unfoldEditTextIn(EditText editText) {
        // 弹出软键盘,并且让EditText获取可输入的焦点
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) editText
                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    /**
     * //关闭输入软键盘
     *
     * @param editText
     */
    public static void closeEditKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 异步关闭输入软键盘
     *
     * @param editText
     */
    public static void asynCloseEditKeyboard(final EditText editText, long time) {
        // 在异步去执行键盘的展开
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        }, time);
    }
}
