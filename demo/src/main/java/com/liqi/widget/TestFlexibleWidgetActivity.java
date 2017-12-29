package com.liqi.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.liqi.popup.ViewOperatetUtils;
import com.liqi.weiget_nko.WidgetKeepOutNoUtils;

/**软键盘弹出不遮挡指定区域用例界面
 * <p>
 * MVC模式
 * 注意看类注释和调用方法内注释
 * </p>
 * Created by LiQi on 2017/12/11.
 */

public class TestFlexibleWidgetActivity extends AppCompatActivity implements View.OnClickListener {
    private View mSlideLayout, mMixtureLayout;
    private ScrollView mSlideMainLayout;
    private Button mSlideSwitch;
    private EditText mSlideAccounts;

    private ScrollView mMixtureMainLayout;
    private ImageView mMixtureImage;
    private Button mMixtureSwitch;
    private EditText mMixtureAccounts;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_flexible_widget_activity);
        mSlideLayout =findViewById(R.id.slide_layout);
        mMixtureLayout =findViewById(R.id.mixture_layout);
        mSlideMainLayout = (ScrollView) findViewById(R.id.slide_main_layout);
        mSlideSwitch = (Button) findViewById(R.id.slide_switch);
        mSlideSwitch.setOnClickListener(this);
        mMixtureMainLayout = (ScrollView) findViewById(R.id.mixture_main_layout);
        mMixtureImage = (ImageView) findViewById(R.id.mixture_image);
        mMixtureSwitch = (Button) findViewById(R.id.mixture_switch);
        mMixtureSwitch.setOnClickListener(this);
        mSlideAccounts = (EditText) findViewById(R.id.slide_accounts);
        mMixtureAccounts = (EditText) findViewById(R.id.mixture_accounts);
        //弹出软键盘
        ViewOperatetUtils.unfoldEditTextIn(mSlideAccounts);
        //软键盘弹出不遮挡指定区域-->滑动效果开启
        WidgetKeepOutNoUtils.openScrollViewKeepOutNoUtils().start(mSlideMainLayout, mSlideSwitch);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.slide_switch:
                mSlideLayout.setVisibility(View.GONE);
                mMixtureLayout.setVisibility(View.VISIBLE);
                //指定休眠时间后弹出软键盘
                ViewOperatetUtils.asynUnfoldEditTextIn(mMixtureAccounts,100);
                //软键盘弹出不遮挡指定区域-->滑动+缩放效果开启
                WidgetKeepOutNoUtils.openReduceSlidingKeepOutNoUtils(this).start(mMixtureMainLayout, mMixtureImage, mMixtureSwitch);
                break;
            case R.id.mixture_switch:
                mSlideLayout.setVisibility(View.VISIBLE);
                mMixtureLayout.setVisibility(View.GONE);
                //指定休眠时间后弹出软键盘
                ViewOperatetUtils.asynUnfoldEditTextIn(mSlideAccounts,100);
                //软键盘弹出不遮挡指定区域-->滑动效果开启
                WidgetKeepOutNoUtils.openScrollViewKeepOutNoUtils().start(mSlideMainLayout, mSlideSwitch);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WidgetKeepOutNoUtils.openScrollViewKeepOutNoUtils().clear();
        WidgetKeepOutNoUtils.openScrollViewKeepOutNoUtils().clear();
    }
}
