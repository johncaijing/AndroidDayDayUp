package xyz.johntsai.androiddaydayup.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/9/26.
 */

public class MatrixView extends View {

    public static final String TAG = MatrixView.class.getSimpleName();

    public MatrixView(Context context) {
        super(context);
        init();
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Matrix matrix = new Matrix();
        Log.d(TAG,matrix.toString());


        Matrix matrix1 = new Matrix(matrix);
        matrix1.setScale(0.5f,2);

        float [] pts = new float[]{100,200,150,300,400,-100,-90};

        Log.d(TAG,"before:"+ Arrays.toString(pts));
//
//        matrix1.mapPoints(pts);
//
//        Log.d(TAG,"after:"+Arrays.toString(pts));


        float [] results = new float[pts.length];

        matrix1.mapPoints(results,pts);

        Log.d(TAG,"after:"+Arrays.toString(results)+"\n"+Arrays.toString(pts));


        Matrix matrix2 = new Matrix();
        //[1.0, 0.0, 0.0][0.0, 1.0, 0.0][0.0, 0.0, 1.0]

        matrix2.preTranslate(10,5);

        Log.d(TAG,"after translate:"+matrix2.toShortString());
        //[1.0, 0.0, 10.0][0.0, 1.0, 5.0][0.0, 0.0, 1.0]

        matrix2.postScale(2,5);

        Log.d(TAG,"matrix2:"+matrix2.toShortString());

        Matrix matrix3 = new Matrix();

        matrix3.preScale(2,5);
        Log.d(TAG,"after scale:"+matrix3.toShortString());
        matrix3.postTranslate(10,5);

        Log.d(TAG,"matrix3"+matrix3.toShortString());



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
