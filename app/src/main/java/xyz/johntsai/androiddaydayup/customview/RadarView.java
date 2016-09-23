package xyz.johntsai.androiddaydayup.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 16/9/22.
 * 雷达图
 */

public class RadarView extends View {

    private int count = 6;
    private float angle = (float) (Math.PI*2/count);
    private float radius;
    private int centerX,centerY;
    private Paint valuePaint;
    private Paint linePaint;

    private Path spiderWebPath;
    private Path valuePath;

    private float [] values = {40,20,60,35,100,66};

    public RadarView(Context context) {
        this(context,null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        valuePaint = new Paint();
        valuePaint.setColor(Color.parseColor("#80ff0000"));
        valuePaint.setAntiAlias(true);
        valuePaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint();
        linePaint.setStrokeWidth(1);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.BLACK);

        spiderWebPath = new Path();
        valuePath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;

        radius = Math.abs(Math.min(centerX,centerY))/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画布移动到中心点
//        canvas.translate(centerX,centerY);

        //绘制蛛网
        drawSpiderWeb(canvas);
        //绘制线
        drawLines(canvas);

        drawValue(canvas);
    }

    private void drawValue(Canvas canvas) {
        valuePath.reset();
       for(int i = 0;i<count;i++){
           float x = (float) (centerX+values[i]/100.0*radius*Math.cos(i*angle));
           float y = (float) (centerY+values[i]/100.0*radius*Math.sin(i*angle));

           if(i==0){
               valuePath.moveTo(x,y);
           }else{
               valuePath.lineTo(x,y);
           }
       }
        valuePath.close();
        canvas.drawPath(valuePath,valuePaint);
    }

    private void drawLines(Canvas canvas) {
        for(int i = 0;i<count;i++){
            float x = (float) (centerX+radius*Math.cos(i*angle));
            float y = (float) (centerY+radius*Math.sin(i*angle));

            canvas.drawLine(centerX,centerY,x,y,linePaint);
        }
    }

    private void drawSpiderWeb(Canvas canvas) {
        float r = radius/(count-1);
        for(int i = 1;i<count;i++){
            float currentR = r*i;
            spiderWebPath.reset();
            for(int j = 0;j<count;j++){
                if(j==0){
                    spiderWebPath.moveTo(centerX+currentR,centerY);
                }else{
                    float x = (float) (centerX+currentR*Math.cos(j*angle));
                    float y = (float) (centerY+currentR*Math.sin(j*angle));
                    spiderWebPath.lineTo(x,y);
                }
            }
            spiderWebPath.close();
            canvas.drawPath(spiderWebPath,linePaint);
        }
    }
}
