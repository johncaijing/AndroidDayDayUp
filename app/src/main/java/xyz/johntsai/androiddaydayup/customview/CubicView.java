package xyz.johntsai.androiddaydayup.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 16/9/22.
 * 三次贝塞尔曲线
 */

public class CubicView extends View {

    //中心点
    private int centerX,centerY;
    //画笔
    private Paint linePaint,cubicPaint,pointPaint;
    //数据点,控制点
    private PointF dataPoint1,dataPoint2,controlPoint1,controlPoint2;
    private Path path;
    private boolean isControl1 = true;

    public void setIsControl1(boolean b){
        this.isControl1 = b;
    }

    public CubicView(Context context) {
        super(context);
        init();
    }

    private void init() {

        dataPoint1 = new PointF(0,0);
        dataPoint2 = new PointF(0,0);

        controlPoint1 = new PointF(0,0);
        controlPoint2 = new PointF(0,0);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.GRAY);
        linePaint.setStrokeWidth(2);
        linePaint.setStyle(Paint.Style.STROKE);

        cubicPaint = new Paint();
        cubicPaint.setAntiAlias(true);
        cubicPaint.setStyle(Paint.Style.STROKE);
        cubicPaint.setStrokeWidth(5);
        cubicPaint.setColor(Color.RED);

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(5);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(Color.BLUE);

        path = new Path();
    }

    public CubicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CubicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;

        dataPoint1.set(centerX-300,centerY+100);
        dataPoint2.set(centerX+200,centerY-300);

        controlPoint1.set(centerX+800,centerY+400);
        controlPoint2.set(centerX+400,centerY-300);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isControl1){
           controlPoint1.set(event.getX(),event.getY());
        }else{
           controlPoint2.set(event.getX(),event.getY());
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画点
        canvas.drawPoint(dataPoint1.x,dataPoint1.y,pointPaint);
        canvas.drawPoint(dataPoint2.x,dataPoint2.y,pointPaint);
        canvas.drawPoint(controlPoint1.x,controlPoint1.y,pointPaint);
        canvas.drawPoint(controlPoint2.x,controlPoint2.y,pointPaint);

        //画线
        canvas.drawLine(dataPoint1.x,dataPoint1.y,controlPoint1.x,controlPoint1.y,linePaint);
        canvas.drawLine(controlPoint1.x,controlPoint1.y,controlPoint2.x,controlPoint2.y,linePaint);
        canvas.drawLine(controlPoint2.x,controlPoint2.y,dataPoint2.x,dataPoint2.y,linePaint);


        path.reset();
        path.moveTo(dataPoint1.x,dataPoint1.y);
        path.cubicTo(controlPoint1.x,controlPoint1.y,controlPoint2.x,controlPoint2.y,dataPoint2.x,dataPoint2.y);
        canvas.drawPath(path,cubicPaint);
    }
}
