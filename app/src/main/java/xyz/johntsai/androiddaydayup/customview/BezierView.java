package xyz.johntsai.androiddaydayup.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 16/9/22.
 * 二次贝塞尔曲线
 */

public class BezierView extends View {

    public static final String TAG = BezierView.class.getSimpleName();

    private Paint paint;
    private PointF start,end,control;
    private int centrerX,centrerY;
    private Path path;

    public BezierView(Context context) {
        this(context,null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        start = new PointF(0,0);
        end = new PointF(0,0);
        control = new PointF(0,0);

        paint = new Paint();
        paint.setAntiAlias(true);

        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.centrerX = w/2;
        this.centrerY = h/2;

        //初始化数据点和控制点
        this.start.x = centrerX-200;
        this.start.y = centrerY-100;
        this.end.x = centrerX+100;
        this.end.y = centrerY+100;
        this.control.x = centrerX;
        this.control.y = centrerY-100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //重设控制点
        control.set(event.getX(),event.getY());
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制点
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(8);

        canvas.drawPoint(start.x,start.y,paint);
        canvas.drawPoint(end.x,end.y,paint);
        canvas.drawPoint(control.x,control.y,paint);

        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);
        canvas.drawText("("+control.x+","+control.y+")",control.x+5,control.y+5,paint);

        //绘制辅助线
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(start.x,start.y,control.x,control.y,paint);
        canvas.drawLine(end.x,end.y,control.x,control.y,paint);

        //绘制贝塞尔曲线
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        path.reset();
        Log.d(TAG,"path empty?-->"+path.isEmpty());
        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);
        Log.d(TAG,"path empty?-->"+ path.isEmpty());
        canvas.drawPath(path,paint);



    }
}
