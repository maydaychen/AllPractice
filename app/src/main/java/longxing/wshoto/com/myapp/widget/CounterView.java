package longxing.wshoto.com.myapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import longxing.wshoto.com.myapp.R;

/**
 * Created by user on 2018/1/23.
 * 2091320109@qq.com
 */

public class CounterView extends View implements View.OnClickListener {

    // 定义画笔
    private Paint mPaint;
    // 用于获取文字的宽和高
    private Rect mBounds;
    // 计数值，每点击一次本控件，其值增加1
    private int mCount;

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.CounterView);
        mCount = t.getInteger(R.styleable.CounterView_count, 0);
        t.recycle();
        // 初始化画笔、Rect
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        // 本控件的点击事件
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLUE);
        // 绘制一个填充色为蓝色的矩形
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(50);
        String text = String.valueOf(mCount);
        // 获取文字的宽和高
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        // 绘制字符串
        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 600;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        mCount++;

        // 重绘
        invalidate();
    }

}