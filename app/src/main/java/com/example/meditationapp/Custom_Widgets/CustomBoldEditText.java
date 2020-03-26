package com.example.meditationapp.Custom_Widgets;

/*public class CustomItalictextView {
}*/


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class CustomBoldEditText extends EditText {

    public CustomBoldEditText(Context context) {
        super(context);
        init(context);
    }

    public CustomBoldEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomBoldEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "font/Comfortaa_Bold.ttf");
        setTypeface(tf, 0);
        // setTextColor(Color.WHITE);
    }
}
