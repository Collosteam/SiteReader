package com.collosteam.sitereader.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Collos on 9/29/2014.
 */
public class CustomFontTextView extends TextView {

    public CustomFontTextView(Context context) {
        super(context);
        initTypeface(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeface(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTypeface(context);
    }

    private void initTypeface(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Italic.ttf");
        this.setTypeface(typeface);
    }


}
