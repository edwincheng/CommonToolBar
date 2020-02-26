package com.github.edwincheng.commontoolbar.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.edwincheng.commontoolbar.R;


/**
 * Create by edwincheng on 19-2-14.
 *
 * 属性表 Attrs：
 * @attrs titleText                  标题文字
 * @attrs textColor                  标题文字颜色
 * @attrs subTitleText               副标题文字颜色
 * @attrs subTitleColor              副标题文字颜色
 * @attrs L_BtnDrawableLeft          左按钮drawableLeft
 * @attrs L_BtnDrawableTop           左按钮drawableTop
 * @attrs L_BtnDrawableRight         左按钮drawableRight
 * @attrs L_BtnDrawableBottom        左按钮drawableBottom
 * @attrs L_drawablePadding          左按钮图标与文字间距
 * @attrs L_BtnText                  左按钮图标文字
 * @attrs R_BtnDrawableLeft          右按钮drawableLeft
 * @attrs R_BtnDrawableTop           右按钮drawableTop
 * @attrs R_BtnDrawableRight         右按钮drawableRight
 * @attrs R_BtnDrawableBottom        右按钮drawableBottom
 * @attrs R_BtnText                  右按钮文字
 * @attrs R_drawablePadding          右按钮文字与图标间距
 * @attrs SecondR_BtnDrawableLeft    右倒数第二个按钮drawableLeft
 * @attrs SecondR_BtnDrawableTop     右倒数第二个按钮drawableTop
 * @attrs SecondR_BtnDrawableRight   右右倒数第二个按钮drawableRight
 * @attrs SecondR_BtnDrawableBottom  右倒数第二个按钮drawableBottom
 * @attrs SecondR_drawablePadding    右倒数第二个按钮与图标间距
 * @attrs SecondR_BtnText            右倒数第二个按钮文字
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
    private TextView mTitle, mSubTitle;
    private TextView mLeftBtn, mSecondRightBtn, mRightBtn;

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

            //主标题
            final CharSequence titleText = tta.getText(R.styleable.CommonToolBar_titleText);
            if (titleText != null){
                setTitleText(titleText);
            }

            final int titleColor = tta.getColor(R.styleable.CommonToolBar_titleTextColor, defStyleAttr);
            if (titleColor != 0){
                setTitleColor(titleColor);
            }

            //副标题
            final CharSequence subTitleText = tta.getText(R.styleable.CommonToolBar_subTitleText);
            if (subTitleText != null){
                setSubtitle(subTitleText);
            }

            final int subTitleColor = tta.getColor(R.styleable.CommonToolBar_subTitleColor, defStyleAttr);
            if (subTitleColor != 0){
                setSubtitle(subTitleColor);
            }

            //左按钮
            final Drawable L_drawableLeft = tta.getDrawable(R.styleable.CommonToolBar_L_BtnDrawableLeft);
            if (L_drawableLeft != null) {
                setLeftBtnDrawable(L_drawableLeft, LEFT);
            }

            final Drawable L_drawableTop = tta.getDrawable(R.styleable.CommonToolBar_L_BtnDrawableTop);
            if (L_drawableTop != null) {
                setLeftBtnDrawable(L_drawableTop, TOP);
            }

            final Drawable L_drawableRight = tta.getDrawable(R.styleable.CommonToolBar_L_BtnDrawableRight);
            if (L_drawableRight != null) {
                setLeftBtnDrawable(L_drawableRight, RIGHT);
            }

            final Drawable L_drawableBottom = tta.getDrawable(R.styleable.CommonToolBar_L_BtnDrawableBottom);
            if (L_drawableBottom != null) {
                setLeftBtnDrawable(L_drawableBottom, BOTTOM);
            }

            final CharSequence L_text = tta.getText(R.styleable.CommonToolBar_L_BtnText);
            if (L_text != null){
                setLeftText(L_text);
            }

            final int L_TextColor = tta.getColor(R.styleable.CommonToolBar_L_BtnTextColor, defStyleAttr);
            if (L_TextColor != 0){
                setTitleColor(L_TextColor);
            }

            //右按钮
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

            final int R_TextColor = tta.getColor(R.styleable.CommonToolBar_R_BtnTextColor, defStyleAttr);
            if (R_TextColor != 0){
                setTitleColor(R_TextColor);
            }

            //右二按钮
            final Drawable SecondR_drawableLeft = tta.getDrawable(R.styleable.CommonToolBar_SecondR_BtnDrawableLeft);
            if (SecondR_drawableLeft != null){
                setSecondRightBtnDrawable(SecondR_drawableLeft, LEFT);
            }

            final Drawable SecondR_drawableTop = tta.getDrawable(R.styleable.CommonToolBar_SecondR_BtnDrawableTop);
            if (SecondR_drawableTop != null){
                setSecondRightBtnDrawable(SecondR_drawableTop, TOP);
            }

            final Drawable SecondR_drawableRight = tta.getDrawable(R.styleable.CommonToolBar_SecondR_BtnDrawableRight);
            if (SecondR_drawableRight != null){
                setSecondRightBtnDrawable(SecondR_drawableRight, RIGHT);
            }

            final Drawable SecondR_drawableBottom = tta.getDrawable(R.styleable.CommonToolBar_SecondR_BtnDrawableBottom);
            if (SecondR_drawableBottom != null){
                setSecondRightBtnDrawable(SecondR_drawableBottom, BOTTOM);
            }

            final int SecondR_drawablePadding = tta.getDimensionPixelSize(R.styleable.CommonToolBar_SecondR_drawablePadding, 0);
            if (SecondR_drawablePadding != 0){
                setSecondRightDrawablePadding(SecondR_drawablePadding);
            }

            final CharSequence SecondR_text = tta.getText(R.styleable.CommonToolBar_SecondR_BtnText);
            if (SecondR_text != null){
                setSecondRightText(SecondR_text);
            }

            final int SecondR_TextColor = tta.getColor(R.styleable.CommonToolBar_SecondR_BtnTextColor, defStyleAttr);
            if (SecondR_TextColor != 0){
                setSecondRightTextColor(SecondR_TextColor);
            }

            tta.recycle();
        }

    }


    /////////////////  提供使用的API  BEGIN ///////////////////
    //左按钮
    public void setLeftBtnDrawable(Drawable icon, int direction) {
        showLeftButton();
        setTextViewIcon(mLeftBtn, icon, direction);
    }

    public void setLeftBtnDrawable(Drawable icon){
        setLeftBtnDrawable(icon, DEFAULT);
    }

    public void setLeftBtnDrawable(int id){
        setLeftBtnDrawable(getResources().getDrawable(id), DEFAULT);
    }

    public void setLeftBtnDrawable(int id, int direction){
        setLeftBtnDrawable(getResources().getDrawable(id), direction);
    }

    private void setLeftDrawablePadding(int padding) {
        setDrawablePadding(mLeftBtn, padding);
    }

    public void showLeftButton(){
        showView(mLeftBtn);
    }

    public void hideLeftButton(){
        hideView(mLeftBtn);
    }

    public void invisibleLeftButton(){
        invisibleView(mLeftBtn);
    }

    public void setLeftText(CharSequence str){
        showLeftButton();
        setViewText(mLeftBtn, str);
    }

    public void setLeftText(int id){
        setLeftText(getResources().getString(id));
    }

    public void setLeftTextColor(int id) {
        setTextColor(mLeftBtn, id);
    }

    //右按钮
    public void setRightBtnDrawable(Drawable icon, int direction){
        showRightButton();
        setTextViewIcon(mRightBtn, icon, direction);
    }

    public void setRightBtnDrawable(Drawable icon){
        setRightBtnDrawable(icon, DEFAULT);
    }

    public void setRightBtnDrawable(int id){
        setRightBtnDrawable(getResources().getDrawable(id), DEFAULT);
    }

    public void setRightBtnDrawable(int id, int direction){
        setRightBtnDrawable(getResources().getDrawable(id), direction);
    }

    private void setRightDrawablePadding(int padding) {
        setDrawablePadding(mRightBtn, padding);
    }

    public void showRightButton(){
        showView(mRightBtn);
    }

    public void hideRightButton(){
        hideView(mRightBtn);
    }

    public void invisibleRightButton(){
        invisibleView(mRightBtn);
    }

    public void setRightText(CharSequence str){
        showRightButton();
        setViewText(mRightBtn, str);
    }

    public void setRightText(int id){
        setRightText(getResources().getString(id));
    }

    public void setRightTextColor(int id) {
        setTextColor(mRightBtn, id);
    }

    //倒数右二按钮
    public void setSecondRightBtnDrawable(Drawable icon, int direction){
        showSecondRightButton();
        setTextViewIcon(mSecondRightBtn, icon, direction);
    }

    public void setSecondRightBtnDrawable(Drawable icon){
        setSecondRightBtnDrawable(icon, DEFAULT);
    }

    public void setSecondRightBtnDrawable(int id){
        setSecondRightBtnDrawable(getResources().getDrawable(id), DEFAULT);
    }

    public void setSecondRightBtnDrawable(int id, int direction){
        setSecondRightBtnDrawable(getResources().getDrawable(id), direction);
    }

    private void setSecondRightDrawablePadding(int padding) {
        setDrawablePadding(mSecondRightBtn, padding);
    }

    public void showSecondRightButton(){
        showView(mSecondRightBtn);
    }

    public void hideSecondRightButton(){
        hideView(mSecondRightBtn);
    }

    public void invisibleSecondRightButton(){
        invisibleView(mSecondRightBtn);
    }

    public void setSecondRightText(CharSequence str){
        showSecondRightButton();
        setViewText(mSecondRightBtn, str);
    }

    public void setSecondRightText(int id){
        setSecondRightText(getResources().getString(id));
    }

    public void setSecondRightTextColor(int id) {
        setTextColor(mSecondRightBtn, id);
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

    //副标题
    public void setSubTitleText(CharSequence str){
        setViewText(mSubTitle, str);
    }

    public void setSubTitleText(int id){
        setSubTitleText(getResources().getString(id));
    }

    public void setSubTitleColor(int id){
        setTextColor(mSubTitle, id);
    }

    public void hideSubTitleText(){
        hideView(mSubTitle);
    }

    //按钮事件
    public void setClickListener(OnClickListener leftClick, OnClickListener rightClick, OnClickListener seconRightClick){
        if (leftClick != null){
            mLeftBtn.setOnClickListener(leftClick);
        }

        if (rightClick != null){
            showRightButton();
            mRightBtn.setOnClickListener(rightClick);
        }

        if (seconRightClick != null){
            showSecondRightButton();
            mSecondRightBtn.setOnClickListener(seconRightClick);
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
            mTitle =  mView.findViewById(R.id.tv_toolbar_title);
            mSubTitle = mView.findViewById(R.id.tv_subtitle);
            mLeftBtn = mView.findViewById(R.id.tv_toolbar_left);
            mRightBtn =  mView.findViewById(R.id.tv_toolbar_right);
            mSecondRightBtn = mView.findViewById(R.id.tv_toolbar_second_right);

            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mView, lp);
        }
    }

    //TextView icon
    private void setTextViewIcon(TextView view, Drawable icon, int direction){
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

    //显示按钮
    private void showView(View view){
        if(view !=null){
            view.setVisibility(VISIBLE);
        }
    }

    //显示多个按钮
    private void showViews(View... views){
        for (View view : views) {
            if (view != null){
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    //隐藏按钮
    private void hideView(View view){
        if(view !=null){
            view.setVisibility(GONE);
        }
    }

    //隐藏多个按钮
    private void hideViews(View... views){
        for (View view : views) {
            if (view != null){
                view.setVisibility(View.GONE);
            }
        }
    }

    //隐藏按钮但是占据空间
    private void invisibleView(View view){
        if(view !=null){
            view.setVisibility(INVISIBLE);
        }
    }

    //隐藏多个按钮但是占据空间
    private void invisibleViews(View... views){
        for (View view : views) {
            if (view != null){
                view.setVisibility(View.INVISIBLE);
            }
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

    /////////////// getter /////////////
    public TextView getmRightBtn() {
        return mRightBtn;
    }

    public TextView getmTitle() {
        return mTitle;
    }

    public TextView getmSubTitle() {
        return mSubTitle;
    }

    public TextView getmLeftBtn() {
        return mLeftBtn;
    }

    public TextView getmSecondRightBtn() {
        return mSecondRightBtn;
    }
}
