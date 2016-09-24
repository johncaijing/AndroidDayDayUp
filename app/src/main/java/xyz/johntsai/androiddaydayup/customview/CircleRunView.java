package xyz.johntsai.androiddaydayup.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import xyz.johntsai.androiddaydayup.R;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/9/23.
 * 绕着圆心旋转的View
 */

public class CircleRunView extends View {

    private PathMeasure pathMeasure;
    private Paint paint;
    private ValueAnimator valueAnimator;
    private Matrix matrix;
    private Bitmap bitmap;

    private static int RADIUS = 300;
    private static float CIRCUMFERENCE = (float) (Math.PI * 2 * RADIUS);

    private float currentDistance = 0;
    private float pos[];
    private float tan[];

    private int halfW, halfH;

    private boolean istart;

    public CircleRunView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        matrix = new Matrix();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 10;

        pathMeasure = new PathMeasure();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);

    }

    public CircleRunView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleRunView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        halfH = h / 2;
        halfW = w / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(halfW, halfH);


        canvas.drawLine(-halfW, 0, halfW, 0, paint);
        canvas.drawLine(0, halfH, 0, -halfH, paint);


        Path path = new Path();
        path.addCircle(0, 0, RADIUS, Path.Direction.CW);
        pathMeasure.setPath(path, false);

        matrix.reset();
        float degree = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);

        matrix.postRotate(degree,bitmap.getWidth()/2,bitmap.getHeight()/2);
        matrix.postTranslate(pos[0]-bitmap.getWidth()/2, pos[1]-bitmap.getHeight()/2);

        canvas.drawPath(path, paint);

        canvas.drawBitmap(bitmap, matrix, paint);


    }

    protected ValueAnimator startRunAnim(float startF, float endF, long duration
            , TimeInterpolator timeInterpolator) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(timeInterpolator);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentDistance = (float) animation.getAnimatedValue();

                Log.d("cj", "distance:" + currentDistance);

                if (null != pathMeasure) {
                    pathMeasure.getPosTan(currentDistance * CIRCUMFERENCE, pos, tan);
                    Log.d("aaa", "pos[0]=" + pos[0] + " pos[1]=" + pos[1]);
                    Log.d("aaa","tan[0]="+tan[0]+"tan[1]"+tan[1]);
                }
                invalidate();
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                istart = false;
            }
        });
        if (!valueAnimator.isRunning()) {
            valueAnimator.start();
        }
        currentDistance = 0;
        return valueAnimator;
    }

    public void startAnim() {
        if (istart) return;
        valueAnimator = startRunAnim(0, 1, 3000, new LinearInterpolator());
        istart = true;
    }

    public void stopAnim() {
        if (!istart || valueAnimator == null) return;
        istart = false;
        valueAnimator.cancel();
        valueAnimator = null;
    }

    public boolean isStart() {
        return istart;
    }


}
