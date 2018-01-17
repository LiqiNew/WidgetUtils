package com.liqi.popup;

import android.app.Activity;
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
     * 异步延迟展开指定的editText对应的键盘，并且获取输入焦点（dialog里面展开键盘需要异步展开）
     *
     * @param editText 指定的editText
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
                if (null != inputManager) {
                    inputManager.showSoftInput(editText, 0);
                } else {
                    System.out.print("com.liqi.popup.ViewOperatetUtils->asynUnfoldEditTextIn()->InputMethodManager==null");
                }
            }
        }, time);
    }

    /**
     * 展开指定的editText对应的软键盘，并且获取输入焦点
     *
     * @param editText 指定的editText
     */
    public static void unfoldEditTextIn(EditText editText) {
        // 弹出软键盘,并且让EditText获取可输入的焦点
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) editText
                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != inputManager) {
            inputManager.showSoftInput(editText, 0);
        } else {
            System.out.print("com.liqi.popup.ViewOperatetUtils->unfoldEditTextIn()->InputMethodManager==null");
        }
    }

    /**
     * 关闭指定的EditText对应的输入软键盘
     *
     * @param editText 指定的EditText
     */
    public static void closeEditKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != imm) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } else {
            System.out.print("com.liqi.popup.ViewOperatetUtils->closeEditKeyboard()->InputMethodManager==null");
        }
    }

    /**
     * 异步延迟关闭指定EditText对应的输入软键盘
     *
     * @param editText
     * @param time     延迟多久关闭键盘
     */
    public static void asynCloseEditKeyboard(final EditText editText, long time) {
        // 在异步去执行键盘的展开
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (null != imm) {
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                } else {
                    System.out.print("com.liqi.popup.ViewOperatetUtils->asynCloseEditKeyboard()->InputMethodManager==null");
                }
            }
        }, time);
    }

    /**
     * 关闭当前界面弹出的软键盘。不需要指定editText
     *
     * @param activity
     */
    public static void allCloseEditKeyboard(Activity activity) {
        if (null != activity) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            } else {
                System.out.print("com.liqi.popup.ViewOperatetUtils->allCloseEditKeyboard()->InputMethodManager==null");
            }
        }
    }

    /**
     * 异步延迟关闭当前界面弹出的软键盘。不需要指定editText
     *
     * @param activity
     * @param time     延迟多久展示键盘
     */
    public static void allAsynCloseEditKeyboard(final Activity activity, long time) {
        if (null != activity) {
            // 在异步去执行键盘的展开
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
                    } else {
                        System.out.print("com.liqi.popup.ViewOperatetUtils->allAsynCloseEditKeyboard()->InputMethodManager==null");
                    }
                }
            }, time);
        }
    }
}
