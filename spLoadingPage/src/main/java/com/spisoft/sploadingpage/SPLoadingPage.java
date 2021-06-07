package com.spisoft.sploadingpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.button.MaterialButton;

public class SPLoadingPage extends RelativeLayout {
    private Context mContext;
    private View rootView;
    private SpinKitView spinKitView;
    private View viewFail;
    private int mKitColor = Color.GRAY;
    private ImageView vIconRetry;
    private TextView vFailText;
    private MaterialButton vBtnRetry;
    private View viewBase;
    private OnRetryClickListener mRetryClickListener;

    public SPLoadingPage(Context context) {
        super(context);
        init(context, null, -1);
    }

    public SPLoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, -1);
    }

    public SPLoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SPLoadingPage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        this.bringToFront();
        mContext = context;
        rootView = inflate(context, R.layout.sps_loading_page, this);
        viewBase = rootView.findViewById(R.id.viewBase);
        spinKitView = rootView.findViewById(R.id.spin_kit);
        viewFail = rootView.findViewById(R.id.lyFail);
        vIconRetry = rootView.findViewById(R.id.icnRetry);
        vFailText = rootView.findViewById(R.id.txtRetry);
        vBtnRetry = rootView.findViewById(R.id.btnRetry);

//        spinKitView.setIndeterminateDrawable(SpinKitView.Large.ThreeBounce);

        if(attrs != null) {
            @SuppressLint({"Recycle", "CustomViewStyleable"}) final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SPLoadingPage, defStyleAttr, 0);

            viewBase.setBackgroundColor(typedArray.getColor(R.styleable.SPLoadingPage_android_colorBackground, Color.WHITE));
            Drawable drawable = typedArray.getDrawable(R.styleable.SPLoadingPage_android_background);
            if(drawable != null)
                viewBase.setBackground(drawable);
            spinKitView.setColor(typedArray.getColor(R.styleable.SPLoadingPage_lv_kitColor, Color.GRAY));
            vIconRetry.setImageResource(typedArray.getResourceId(R.styleable.SPLoadingPage_lv_fail_icon, R.drawable.ic_baseline_warning_24));
            vFailText.setText(typedArray.getString(R.styleable.SPLoadingPage_lv_fail_title));
            vFailText.setTextColor(typedArray.getColor(R.styleable.SPLoadingPage_lv_fail_title_color, Color.GRAY));
            vBtnRetry.setText(typedArray.getString(R.styleable.SPLoadingPage_lv_fail_btn_text));
            vBtnRetry.setTextColor(typedArray.getColor(R.styleable.SPLoadingPage_lv_fail_btn_text_color, Color.WHITE));
            vBtnRetry.setBackgroundColor(typedArray.getColor(R.styleable.SPLoadingPage_lv_fail_btn_color, Color.BLUE));
            typedArray.recycle();
        }

        vBtnRetry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRetryClickListener != null) mRetryClickListener.onEvent();
            }
        });
//        invalidate();
    }

    public void SetStatus(int modeStatus) {
        switch (modeStatus){
            case 0:
                viewFail.setVisibility(GONE);
                spinKitView.setVisibility(VISIBLE);
                break;
            case -1:
                viewFail.setVisibility(VISIBLE);
                spinKitView.setVisibility(GONE);
                break;
            case 1:
                viewFail.setVisibility(GONE);
                spinKitView.setVisibility(VISIBLE);
                this.setVisibility(GONE);
                break;
        }
    }

    public interface OnRetryClickListener {
        void onEvent();
    }

    public void setOnRetryClickListener(OnRetryClickListener eventListener) {
        mRetryClickListener = eventListener;
    }
}
