package com.example.drive_2_go.ui.Admin.Table_bord;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

public class PieChartView extends View {

    private List<Integer> data;
    private Paint paint = new Paint();

    public PieChartView(Context c, AttributeSet a) {
        super(c, a);
        paint.setStyle(Paint.Style.FILL);
    }

    public void setData(List<Integer> d) {
        this.data = d;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (data == null) return;

        int total = 0;
        for (int n : data) total += n;

        int startAngle = 0;
        int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.GRAY};

        RectF rect = new RectF(50, 50, getWidth() - 50, getHeight() - 50);

        for (int i = 0; i < data.size(); i++) {
            paint.setColor(colors[i % colors.length]);
            int sweep = (data.get(i) * 360) / total;
            canvas.drawArc(rect, startAngle, sweep, true, paint);
            startAngle += sweep;
        }
    }
}
