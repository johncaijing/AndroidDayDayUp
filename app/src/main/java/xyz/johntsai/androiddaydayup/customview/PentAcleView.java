package xyz.johntsai.androiddaydayup.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 16/9/22.
 * 五角星
 */

public class PentAcleView extends View {

    private PointF centerPoint;
    private Paint paint;
    private Path pentaclePath;

    private Path fillPath;

    private static final int DEFAULT_RADIUS = 100;

    public PentAcleView(Context context) {
        this(context,null);
    }

    public PentAcleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PentAcleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        centerPoint = new PointF(0,0);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

        pentaclePath = new Path();

        fillPath = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerPoint.set(w/2,h/2);
        Log.d("cj","w="+w+" h="+h);
        Log.d("cj",centerPoint.toString());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Log.d("cj-->onDraw",centerPoint.toString());
        canvas.translate(centerPoint.x,centerPoint.y);

        pentaclePath.reset();
        float radian = degree2Radian(36);
        float radiusIn = (float) (DEFAULT_RADIUS*Math.sin(radian/2)/Math.cos(radian));
        pentaclePath.moveTo((float) (DEFAULT_RADIUS*Math.cos(radian/2)),0);


        Paint pointPaint = new Paint();
        pointPaint.setStrokeWidth(8);
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPoint((float) (DEFAULT_RADIUS*Math.cos(radian/2)),0,pointPaint);

        pentaclePath.lineTo((float)( DEFAULT_RADIUS*Math.cos(radian/2)+radiusIn*Math.sin(radian)),
                (float) (DEFAULT_RADIUS-DEFAULT_RADIUS*Math.sin(radian/2)));
        pentaclePath.lineTo((float) (DEFAULT_RADIUS * Math.cos(radian / 2) * 2),
                (float) (DEFAULT_RADIUS - DEFAULT_RADIUS * Math.sin(radian / 2)));
        pentaclePath.lineTo((float) (DEFAULT_RADIUS * Math.cos(radian / 2) + radiusIn
                        * Math.cos(radian / 2)),
                (float) (DEFAULT_RADIUS + radiusIn * Math.sin(radian / 2)));
        pentaclePath.lineTo(
                (float) (DEFAULT_RADIUS * Math.cos(radian / 2) + DEFAULT_RADIUS
                        * Math.sin(radian)), (float) (DEFAULT_RADIUS + DEFAULT_RADIUS
                        * Math.cos(radian)));
        pentaclePath.lineTo((float) (DEFAULT_RADIUS * Math.cos(radian / 2)),
                (float) (DEFAULT_RADIUS + radiusIn));
        pentaclePath.lineTo(
                (float) (DEFAULT_RADIUS * Math.cos(radian / 2) - DEFAULT_RADIUS
                        * Math.sin(radian)), (float) (DEFAULT_RADIUS + DEFAULT_RADIUS
                        * Math.cos(radian)));
        pentaclePath.lineTo((float) (DEFAULT_RADIUS * Math.cos(radian / 2) - radiusIn
                        * Math.cos(radian / 2)),
                (float) (DEFAULT_RADIUS + radiusIn * Math.sin(radian / 2)));
        pentaclePath.lineTo(0, (float) (DEFAULT_RADIUS - DEFAULT_RADIUS * Math.sin(radian / 2)));
        pentaclePath.lineTo((float) (DEFAULT_RADIUS * Math.cos(radian / 2) - radiusIn
                        * Math.sin(radian)),
                (float) (DEFAULT_RADIUS - DEFAULT_RADIUS * Math.sin(radian / 2)));

        pentaclePath.close();// 使这些点构成封闭的多边形



        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(30);
        canvas.drawPoint(0,0,paint);

        canvas.translate(-DEFAULT_RADIUS,-DEFAULT_RADIUS);


//        fillPath.set(pentaclePath);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        canvas.drawPath(pentaclePath, paint);

//
//        paint.setColor(Color.YELLOW);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawPath(pentaclePath,paint);
    }

    private float degree2Radian(int degree) {
        return (float) (degree*Math.PI/180);
    }
}
