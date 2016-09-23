package xyz.johntsai.androiddaydayup.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/9/23.
 * 太极图
 */

public class TaijiView extends View {

    private Paint blackPaint,whitePaint,linePaint;

    private Path path1,path2,path3,path4;

    private int centerX,centerY;

    public TaijiView(Context context) {
        super(context);
        init();
    }

    public TaijiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TaijiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setAntiAlias(true);
        whitePaint.setStyle(Paint.Style.FILL);

        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setAntiAlias(true);
        blackPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.BLACK);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(1);

        path1 = new Path();
        path2 = new Path();
        path3 = new Path();
        path4 = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(centerX,centerY);

        path1.reset();
        path2.reset();
        path3.reset();
        path4.reset();


        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);


        path1.op(path2, Path.Op.DIFFERENCE);
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);

        canvas.drawPath(path1,blackPaint);


        canvas.drawCircle(0,0,200,linePaint);

        canvas.drawCircle(0,-100,25,whitePaint);
        canvas.drawCircle(0,100,25,blackPaint);


        blackPaint.setColor(Color.parseColor("#80ff0000"));
        blackPaint.setStrokeWidth(1);
        blackPaint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(-centerX,0,centerX,0,blackPaint);
        canvas.drawLine(0,centerY,0,-centerY,blackPaint);

        canvas.drawRect(0,-200,200,200,blackPaint);
        canvas.drawCircle(0,100,100,blackPaint);
        canvas.drawCircle(0,-100,100,blackPaint);


    }
}
