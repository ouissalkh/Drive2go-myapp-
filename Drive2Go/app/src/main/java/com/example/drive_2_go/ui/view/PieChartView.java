package com.example.drive_2_go.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PieChartView extends View {

    private float[] data = {25f, 25f, 25f, 25f};
    private int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private Paint paint = new Paint();

    public PieChartView(Context context) { super(context); }
    public PieChartView(Context context, AttributeSet attrs) { super(context, attrs); }
    public PieChartView(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

    public void setData(float[] newData) {
        this.data = newData;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        float startAngle = 0;
        RectF rect = new RectF(0,0,width,height);

        for (int i = 0; i < data.length; i++) {
            paint.setColor(colors[i % colors.length]);
            canvas.drawArc(rect, startAngle, data[i] * 360 / 100, true, paint);
            startAngle += data[i] * 360 / 100;
        }
    }
}
