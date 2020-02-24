package com.github.edwincheng.commontoolbar.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.edwincheng.commontoolbar.R;


/**
 * Create by edwincheng on 19-2-14.
 *
 * 属性表：
 * @attrs titleText  标题文字
 * @attrs textColor  标题文字颜色
 * @attrs L_BtnIcon  左按钮图标
 * @attrs R_BtnDrawableLeft  右按钮drawableLeft
 * @attrs R_BtnDrawableTop  右按钮drawableTop
 * @attrs R_BtnDrawableRight  右按钮drawableRight
 * @attrs R_BtnDrawableBottom  右按钮drawableBottom
 * @attrs R_BtnText  右按钮文字
 * @attrs R_drawablePadding  右按钮文字与图标间距
 *
 * setClickListener  设置点击事件
 */

@SuppressLint("RestrictedApi")
public class CommonToolBar extends Toolbar {
    private static final int DEFAULT = 0;
    private static final int TOP = 1;
    private static final int LEFT = 2;
    private static final int BOTTOM = 3;
    private static final int RIGHT = 4;

    private LayoutInflater mInflater;
    private View mView;
    private TextView mTitle;
    private ImageView mLeftBtn;
    private TextView mRightBtn;

    public CommonToolBar(Context context) {
        this(context, null);
    }

    public CommonToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        //设置边距
        setContentInsetsRelative(0,0);

        if (attrs != null){
            final TintTypedArray tta = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.CommonToolBar,
                    defStyleAttr,0);

            final CharSequence titleText = tta.getText(R.styleable.CommonToolBar_titleText);
            if (titleText != null){
                setTitleText(titleText);
            }

            final int titleColor = tta.getColor(R.styleable.CommonToolBar_textColor, defStyleAttr);
            if (titleColor != 0){
                setTitleColor(titleColor);
            }

            final Drawable leftIcon = tta.getDrawable(R.styleable.CommonToolBar_L_BtnIcon);
            if (leftIcon != null){
                setLeftBtnDrawable(leftIcon);
            }

            final Drawable R_drawableLeft = tta.getDrawable(R.styleable.CommonToolBar_R_BtnDrawableLeft);
            if (R_drawableLeft != null){
                setRightBtnDrawable(R_drawableLeft, LEFT);
            }

            final Drawable R_drawableTop = tta.getDrawable(R.styleable.CommonToolBar_R_BtnDrawableTop);
            if (R_drawableTop != null){
                setRightBtnDrawable(R_drawableTop, TOP);
            }

            final Drawable R_drawableRight = tta.getDrawable(R.styleable.CommonToolBar_R_BtnDrawableRight);
            if (R_drawableRight != null){
                setRightBtnDrawable(R_drawableRight, RIGHT);
            }

            final Drawable R_drawableBottom = tta.getDrawable(R.styleable.CommonToolBar_R_BtnDrawableBottom);
            if (R_drawableBottom != null){
                setRightBtnDrawable(R_drawableBottom, BOTTOM);
            }

            final int R_drawablePadding = tta.getDimensionPixelSize(R.styleable.CommonToolBar_R_drawablePadding, 0);
            if (R_drawablePadding != 0){
                setRightDrawablePadding(R_drawablePadding);
            }

            final CharSequence R_text = tta.getText(R.styleable.CommonToolBar_R_BtnText);
            if (R_text != null){
                setRightText(R_text);
            }
            tta.recycle();
        }

    }


    /////////////////  提供使用的方法  BEGIN ///////////////////
    //左按钮
    public void setLeftBtnDrawable(Drawable icon){
        setL(mLeftBtn, icon);
    }

    public void setLeftBtnDrawable(int id){
        setLeftBtnDrawable(getResources().getDrawable(id));
    }

    public void hideLeftButton(){
        hideView(mLeftBtn);
    }

    //右按钮
    public void setRightBtnDrawable(Drawable icon){
        setRightBtnDrawable(icon, 0);
    }

    public void setRightBtnDrawable(Drawable icon, int direction){
        setR(mRightBtn, icon, direction);
    }

    public void setRightBtnDrawable(int id){
        setRightBtnDrawable(getResources().getDrawable(id));
    }

    public void setRightBtnDrawable(int id, int direction){
        setRightBtnDrawable(getResources().getDrawable(id), direction);
    }

    private void setRightDrawablePadding(int padding) {
        setDrawablePadding(mRightBtn,padding);
    }

    public void hideRightButton(){
        hideView(mRightBtn);
    }

    public void setRightText(CharSequence str){
        setViewText(mRightBtn, str);
    }

    public void setRightText(int id){
        setRightText(getResources().getString(id));
    }

    //标题
    public void setTitleText(CharSequence str){
        setViewText(mTitle, str);
    }

    public void setTitleText(int id){
        setTitleText(getResources().getString(id));
    }

    public void setTitleColor(int id){
        setTextColor(mTitle, id);
    }

    public void hideTitleText(){
        hideView(mTitle);
    }

    //左按钮事件
    public void setClickListener(OnClickListener leftClick, OnClickListener rightClick){
        if (leftClick != null){
            mLeftBtn.setOnClickListener(leftClick);
        }
        if (rightClick != null){
            mRightBtn.setOnClickListener(rightClick);
        }
    }

    /////////////////  提供使用的方法  END  ///////////////////


    /**
     * 初始化View
     */
    private void initView(Context context) {
        if(mView == null) {
            mInflater = LayoutInflater.from(context);
            mView = mInflater.inflate(R.layout.toolbar, null);
            mTitle =  mView.findViewById(R.id.toolbar_title);
            mLeftBtn = mView.findViewById(R.id.toolbar_left);
            mRightBtn =  mView.findViewById(R.id.toolbar_right);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mView, lp);
        }
    }

    //左按钮
    private void setL(ImageView view, Drawable icon){
        if(view !=null){
            view.setImageDrawable(icon);
            view.setVisibility(VISIBLE);
        }
    }

    //右按钮
    private void setR(TextView view, Drawable icon, int direction){
        if(view !=null){
            switch (direction){
                case TOP:
                    icon.setBounds(0,0,icon.getIntrinsicWidth(),icon.getIntrinsicHeight());
                    view.setCompoundDrawables(null, icon, null, null);
                    view.setVisibility(VISIBLE);
                    break;
                case DEFAULT:
                case LEFT:
                    icon.setBounds(0,0,icon.getIntrinsicWidth(),icon.getIntrinsicHeight());
                    view.setCompoundDrawables(icon, null, null, null);
                    view.setVisibility(VISIBLE);
                    break;
                case BOTTOM:
                    icon.setBounds(0,0,icon.getIntrinsicWidth(),icon.getIntrinsicHeight());
                    view.setCompoundDrawables(null, null, null, icon);
                    view.setVisibility(VISIBLE);
                    break;
                case RIGHT:
                    icon.setBounds(0,0,icon.getIntrinsicWidth(),icon.getIntrinsicHeight());
                    view.setCompoundDrawables(null, null, icon, null);
                    view.setVisibility(VISIBLE);
                    break;
            }
        }
    }

    //设置与图标边距离
    public void setDrawablePadding(TextView view, int padding) {
        view.setCompoundDrawablePadding(padding);
    }

    //隐藏按钮
    private void hideView(View view){
        if(view !=null){
            view.setVisibility(INVISIBLE);
        }
    }

    //TextView
    private void setViewText(TextView view, CharSequence title){
        if(view !=null){
            view.setText(title);
            view.setVisibility(VISIBLE);
        }
    }

    private void setTextColor(TextView view, int id){
        if(view !=null){
            view.setTextColor(id);
            view.setVisibility(VISIBLE);
        }
    }

    public TextView getmRightBtn() {
        return mRightBtn;
    }
}
