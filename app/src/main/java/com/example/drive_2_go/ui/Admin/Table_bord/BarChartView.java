package com.example.drive_2_go.ui.Admin.Table_bord;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

public class BarChartView extends View {

    private List<Integer> data;
    private Paint paint = new Paint();

    public BarChartView(Context c, AttributeSet a) {
        super(c, a);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(60);
    }

    public void setData(List<Integer> d) {
        this.data = d;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (data == null) return;

        int width = getWidth();
        int height = getHeight();
        int barSpacing = width / (data.size() + 1);

        for (int i = 0; i < data.size(); i++) {
            int value = data.get(i);
            int x = barSpacing * (i + 1);
            int y = height - (value * 10); // échelle simple

            canvas.drawLine(x, height, x, y, paint);
        }
    }
}
