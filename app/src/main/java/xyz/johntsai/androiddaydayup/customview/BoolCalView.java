package xyz.johntsai.androiddaydayup.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/9/23.
 * 测试布尔运算
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public class BoolCalView extends View {

    public static final String TAG = BoolCalView.class.getSimpleName();

    private Paint paint,linePaint,dotlinePaint;
    private int centerX,centerY;

    private Path path1,path2;


    private Path.Op op = Path.Op.DIFFERENCE;

    public void setOp(Path.Op op){
        this.op = op;
        postInvalidateDelayed(500);
    }

    public BoolCalView(Context context) {
        super(context);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.RED);
        linePaint.setStrokeWidth(1);

        dotlinePaint = new Paint();
        dotlinePaint.setStyle(Paint.Style.STROKE);
        dotlinePaint.setColor(Color.GRAY);
        dotlinePaint.setPathEffect(new DashPathEffect(new float[]{10,20},0));

        path1 = new Path();
        path2 = new Path();


    }

    public BoolCalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoolCalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
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


        path1.reset();
        path2.reset();

        canvas.translate(centerX,centerY);
        canvas.scale(1,-1);

        canvas.drawLine(-centerX,0,centerX,0,linePaint);
        canvas.drawLine(0,centerY,0,-centerY,linePaint);



        path1.addCircle(80,0,100, Path.Direction.CW);

        path2.addCircle(-80,0,100, Path.Direction.CW);

        path1.op(path2, this.op);

        canvas.drawPath(path1,paint);

        canvas.drawCircle(80,0,100,dotlinePaint);
        canvas.drawCircle(-80,0,100,dotlinePaint);

        RectF rectF = new RectF();

        path1.computeBounds(rectF,true);

        Log.d(TAG,rectF.toShortString());

        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        canvas.drawRect(rectF,paint);
    }
}
