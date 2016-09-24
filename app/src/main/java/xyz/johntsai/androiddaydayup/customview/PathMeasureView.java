package xyz.johntsai.androiddaydayup.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/9/23.
 * 测试PathMeasure
 */

public class PathMeasureView extends View {

    private Paint paint,linePaint;
    private PathMeasure pathMeasure1,pathMeasure2;
    private int w,h;

    public static final String TAG = PathMeasureView.class.getSimpleName();

    public PathMeasureView(Context context) {
        super(context);
        init();
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);
        linePaint.setColor(Color.RED);

        pathMeasure1 = new PathMeasure();
        pathMeasure2 = new PathMeasure();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(w/2,h/2);
        canvas.scale(1,-1);

        //画坐标系
        canvas.drawLine(-w/2,0,w/2,0,linePaint);
        canvas.drawLine(0,h/2,0,-h/2,linePaint);

        canvas.drawLine(0,h/2,(float) (0+30*Math.sin(Math.PI/6)),(float)( h/2-30*Math.cos(Math.PI/6)),linePaint);
        canvas.drawLine(0,h/2,-(float) (0+30*Math.sin(Math.PI/6)),(float)( h/2-30*Math.cos(Math.PI/6)),linePaint);

        canvas.drawLine(w/2,0,(float)(w/2-Math.cos(Math.PI/6)*30),(float)(30*Math.sin(Math.PI/6)),linePaint);
        canvas.drawLine(w/2,0,(float)(w/2-Math.cos(Math.PI/6)*30),-(float)(30*Math.sin(Math.PI/6)),linePaint);


        Path path = new Path();
        path.lineTo(100,0);
        path.lineTo(100,100);
        path.lineTo(0,100);

        pathMeasure1 = new PathMeasure(path,true);
        pathMeasure2 = new PathMeasure(path,false);

        Log.d(TAG,"pathMeasure1:"+pathMeasure1.getLength());
        Log.d(TAG,"pathMeasure2:"+pathMeasure2.getLength());

        Log.d(TAG,"1:"+pathMeasure1.isClosed()+" 2:"+pathMeasure2.isClosed());

//        canvas.drawPath(path,paint);



       /* Path path1 = new Path();

        path1.addCircle(0,0,50, Path.Direction.CW);

        boolean segment = pathMeasure1.getSegment(150, 250, path1, false);
        Log.d(TAG,"getSegment:"+segment);
        if(segment){
            canvas.drawPath(path1,paint);
        }*/

        Path path2 = new Path();
        path2.addCircle(0,0,50, Path.Direction.CW);
        path2.addCircle(0,0,100, Path.Direction.CW);

        PathMeasure pathMeasure = new PathMeasure(path2,false);
        float length = pathMeasure.getLength();
        pathMeasure.nextContour();
        float length1 = pathMeasure.getLength();
        Log.d(TAG,"before:"+length+" after:"+length1);

        canvas.drawPath(path2,paint);

    }
}
