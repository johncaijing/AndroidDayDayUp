package xyz.johntsai.androiddaydayup.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 16/9/14.
 * Canvas的基本操作
 */

public class CanvasTestView  extends View{

    public static final String TAG = CanvasTestView.class.getSimpleName();

    private Paint paint = new Paint();

    private Paint drawlinePaint = new Paint();

    private int centerX,centerY;

    public CanvasTestView(Context context) {
        this(context,null);
    }

    public CanvasTestView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);

        drawlinePaint.setAntiAlias(true);
        drawlinePaint.setStyle(Paint.Style.STROKE);
        drawlinePaint.setStrokeWidth(1);
        drawlinePaint.setColor(Color.BLUE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CanvasTestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        log("draw");

//        drawPoint(canvas);
//        drawLine(canvas);
//        drawRectangle(canvas);
//        drawRoundRectangle(canvas);
//        drawOval(canvas);
//        drawCircle(canvas);

//        drawArc(canvas);

          Path path = new Path();

          path.moveTo(centerX,centerY);

          path.rLineTo(100,100);

          canvas.drawPath(path,drawlinePaint);



    }

    private void drawArc(Canvas canvas) {
        canvas.save();
        RectF rectF = new RectF(500,500,800,800);
        canvas.drawRect(rectF,drawlinePaint);
        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF,0,45,true,paint);
        paint.setColor(Color.RED);
        canvas.drawArc(rectF,90,45,false,paint);
        canvas.restore();
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(500,500,200,paint);
    }

    private void drawOval(Canvas canvas) {
        RectF rectF = new RectF(500,500,900,800);

        canvas.drawRect(rectF,drawlinePaint);

        canvas.drawOval(rectF,paint);
    }

    private void drawRectangle(Canvas canvas) {
        canvas.drawRect(200,500,300,800,paint);

        Rect rect = new Rect(200,900,300,1000);
        canvas.drawRect(rect,paint);

        RectF rectF = new RectF(500,500,800,800);
        canvas.drawRect(rectF,paint);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void drawRoundRectangle(Canvas canvas) {
        canvas.drawRoundRect(200,500,300,800,50,50,paint);

        RectF rect = new RectF(200,900,300,1000);
        canvas.drawRoundRect(rect,50,50,paint);

        RectF rectF = new RectF(500,500,800,800);
        canvas.drawRoundRect(rectF,50,50,paint);
    }

    private void drawLine(Canvas canvas) {
        canvas.drawLine(200,200,500,600,paint);
        canvas.drawLines(new float[]{200,500,300,800},paint);
    }

    private void drawPoint(Canvas canvas) {
        canvas.drawPoint(100,200,paint);
        canvas.drawPoints(new float[]{500,600,600,600},paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        log("w:"+w+" h:"+h);
        centerX = w/2;
        centerY = h/2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        log("layout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        log("measure");
    }

    private void log(String msg){
        Log.d(TAG,msg);
    }
}
