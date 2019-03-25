package com.iprismtech.komodeo.customtextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class MyCustomFontTextView extends TextView {

    public MyCustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyCustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomFontTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/tunga_regular.ttf");
        setTypeface(tf);
    }

}