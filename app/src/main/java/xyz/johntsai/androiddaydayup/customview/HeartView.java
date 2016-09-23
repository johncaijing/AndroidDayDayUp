package xyz.johntsai.androiddaydayup.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 16/9/22.
 * 心形View
 */

public class HeartView extends View {

    /**
     * 半径
     */
    private int radius;

    /**
     * 画笔
     */
    private Paint linePaint,pointPaint;

    /**
     * 圆心
     */
    private int centralX,centralY;

    /**
     * 数据点
     */
    private PointF dataPoint1,dataPoint2,dataPoint3,dataPoint4;

    /**
     * 控制点
     */
    private PointF cp1,cp2,cp3,cp4,cp5,cp6,cp7,cp8;

    public HeartView(Context context) {
        super(context);
        init();
    }

    private void init() {

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(3);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.RED);


        dataPoint1 = new PointF(0,0);
        dataPoint2 = new PointF(0,0);
        dataPoint3 = new PointF(0,0);
        dataPoint4 = new PointF(0,0);

        cp1= new PointF(0,0);
        cp2= new PointF(0,0);
        cp3= new PointF(0,0);
        cp4= new PointF(0,0);
        cp5= new PointF(0,0);
        cp6= new PointF(0,0);
        cp7= new PointF(0,0);
        cp8= new PointF(0,0);

    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();

        path.moveTo(dataPoint1.x,dataPoint1.y);
        path.cubicTo(cp1.x,cp1.y,cp2.x,cp2.y,dataPoint2.x,dataPoint2.y);

        path.moveTo(dataPoint2.x,dataPoint2.y);
        path.cubicTo(cp3.x,cp3.y,cp4.x,cp4.y,dataPoint3.x,dataPoint3.y);

        path.moveTo(dataPoint3.x,dataPoint3.y);
        path.cubicTo(cp5.x,cp5.y,cp6.x,cp6.y,dataPoint4.x,dataPoint4.y);

        path.moveTo(dataPoint4.x,dataPoint4.y);
        path.cubicTo(cp7.x,cp7.y,cp8.x,cp8.y,dataPoint1.x,dataPoint1.y);

        canvas.drawPath(path,linePaint);

        canvas.drawCircle(centralX+radius+10,centralY+radius+10,radius,linePaint);

        canvas.drawLine(0,centralY,2*centralX,centralY,linePaint);
        canvas.drawLine(centralX,0,centralX,2*centralY,linePaint);

        float x1 = (float) (2*centralX-30*Math.cos(Math.PI/6));
        float y1 = (float) (centralY+30*Math.sin(Math.PI/6));

        float y2 = (float) (centralY-30*Math.sin(Math.PI/6));

        canvas.drawLine(x1,y1,2*centralX,centralY,linePaint);
        canvas.drawLine(x1,y2,2*centralX,centralY,linePaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centralX = w/2;
        centralY = h/2;

        radius = Math.min(Math.abs(centralX),Math.abs(centralY))/4;

        dataPoint1.set(centralX+radius,centralY);
        dataPoint2.set(centralX,centralY+radius);
        dataPoint3.set(centralX-radius,centralY);
        dataPoint4.set(centralX,centralY-radius);

        cp1.set(centralX+radius,centralY+radius/2);
        cp8.set(centralX+radius,centralY-radius/2);

        cp2.set(centralX+radius/2,centralY+radius);
        cp3.set(centralX-radius/2,centralY+radius);

        cp4.set(centralX-radius,centralY+radius/2);
        cp5.set(centralX-radius,centralY-radius/2);

        cp6.set(centralX-radius/2,centralY-radius);
        cp7.set(centralX+radius/2,centralY-radius);




    }
}
