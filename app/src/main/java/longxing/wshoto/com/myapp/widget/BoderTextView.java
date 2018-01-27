package longxing.wshoto.com.myapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by user on 2018/1/24.
 * 2091320109@qq.com
 */

public class BoderTextView extends android.support.v7.widget.AppCompatTextView {
    private Paint mPaint1;
    private Paint mPaint2;

    public BoderTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint2);
        canvas.save();
//        canvas.translate(10, 0);
        super.onDraw(canvas);
        canvas.restore();
    }
}
