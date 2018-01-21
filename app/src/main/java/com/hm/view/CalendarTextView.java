package com.hm.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/21/021.
 */

public class CalendarTextView extends AppCompatTextView {
    private Paint mPaint;

    public CalendarTextView(Context context) {
        super(context);
    }

    public CalendarTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        getText()

    }
}
