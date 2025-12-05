/*package com.example.drive_2_go.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BarChartView extends View {

    private int[] data = {0, 0, 0, 0}; // données par défaut
    private Paint paint = new Paint();

    public BarChartView(Context context) {
        super(context);
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // Méthode pour mettre à jour les données
    public void setData(int[] newData) {
        this.data = newData;
        invalidate(); // redessine la vue
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / (data.length * 2); // espace entre les barres

        paint.setColor(Color.BLUE);

        for (int i = 0; i < data.length; i++) {
            int barHeight = data[i] * height / 100; // on suppose que les valeurs sont 0-100
            int x = i * barWidth * 2 + barWidth / 2;
            canvas.drawRect(x, height - barHeight, x + barWidth, height, paint);
        }
    }
}*/
