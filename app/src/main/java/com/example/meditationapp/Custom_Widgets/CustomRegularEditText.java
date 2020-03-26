package com.example.meditationapp.Custom_Widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomRegularEditText extends EditText {

    public CustomRegularEditText(Context context) {
        super(context);
        init(context);
    }

    public CustomRegularEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomRegularEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "font/Comfortaa_Regular.ttf");
        setTypeface(tf, 0);
        // setTextColor(Color.WHITE);
    }
}
