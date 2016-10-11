package xyz.johntsai.androiddaydayup.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

import xyz.johntsai.androiddaydayup.R;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/9/27.
 * 测试Matrix类的setPolyToPoly方法
 */

public class MatrixSetPolyToPolyView extends View {

    private static final String TAG = MatrixSetPolyToPolyView.class.getSimpleName();

    private Bitmap bitmap;

    private static final int times = 4;

    private Matrix[] matrixs;

    private int beforePerWidth, afterPerWidth;

    private static final float ratio = 0.8f;

//    private Paint paint;

    private Paint solidPaint;

    private Paint shadowPaint;
    private Matrix shaodwMatrix;
    private LinearGradient shadowGradient;

    public MatrixSetPolyToPolyView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.android);

        beforePerWidth = bitmap.getWidth() / times;
        afterPerWidth = (int) (ratio * bitmap.getWidth() / times);

        matrixs = new Matrix[times];

        int height = bitmap.getHeight();

        /**
         * 左上
         * 右上
         * 左下
         * 右下
         */
        float[] src = new float[8];
        float[] dst = new float[8];

        int depth = (int) Math.sqrt(beforePerWidth * beforePerWidth - afterPerWidth * afterPerWidth)/2;

        for (int i = 0; i < times; i++) {
            matrixs[i] = new Matrix();

            //左上
            src[0] = i * beforePerWidth;
            src[1] = 0;

            //右上
            src[2] = src[0] + beforePerWidth;
            src[3] = 0;

            //右下
            src[4] = src[2];
            src[5] = height;

            //左下
            src[6] = src[0];
            src[7] = src[5];

            //after:

            boolean isEven = i % 2 == 0;

            //左上
            dst[0] = i * afterPerWidth;
            dst[1] = isEven ? 0 : depth;

            //右上
            dst[2] = dst[0] + afterPerWidth;
            dst[3] = isEven ? depth : 0;

            //右下
            dst[4] = dst[2];
            dst[5] = isEven ? height - depth : height;

            //左下
            dst[6] = dst[0];
            dst[7] = isEven ? height : height - depth;

            Log.d(TAG,"------------------------------");

            Log.d(TAG,"src:"+ Arrays.toString(src));

            Log.d(TAG,"dst:"+Arrays.toString(dst));

            matrixs[i].setPolyToPoly(src, 0, dst, 0, src.length >> 1);

        }

//        paint = new Paint();
//        paint.setColor(Color.GRAY);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setAntiAlias(true);

        solidPaint = new Paint();
        solidPaint.setColor(Color.argb((int) (255*ratio*0.8),0,0,0));

        shadowPaint = new Paint();
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowGradient = new LinearGradient(0,0,0.5f,0,Color.BLACK,Color.TRANSPARENT, Shader.TileMode.CLAMP);
        shadowPaint.setShader(shadowGradient);

        shaodwMatrix = new Matrix();
        shaodwMatrix.setScale(beforePerWidth,1);

        shadowGradient.setLocalMatrix(shaodwMatrix);
        shadowPaint.setAlpha((int) (255*ratio*0.8));


    }

    public MatrixSetPolyToPolyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MatrixSetPolyToPolyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < times; i++) {
            canvas.save();

            canvas.concat(matrixs[i]);

            Log.d(TAG,"matrixs:"+matrixs[i]);

            canvas.clipRect(i * beforePerWidth, 0, i * beforePerWidth + beforePerWidth, bitmap.getHeight());

//            canvas.drawRect(i * beforePerWidth, 0, i * beforePerWidth + beforePerWidth, bitmap.getHeight(),paint);

            canvas.drawBitmap(bitmap, 0, 0, null);

            canvas.translate(beforePerWidth*i,0);
            if(i%2==0){
                canvas.drawRect(0,0,beforePerWidth,bitmap.getHeight(),solidPaint);
            }else{
                canvas.drawRect(0,0,beforePerWidth,bitmap.getHeight(),shadowPaint);
            }

            canvas.restore();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
