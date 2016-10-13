package xyz.johntsai.androiddaydayup.imageload;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.squareup.picasso.Transformation;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/10/13.
 * 图片模糊处理
 */
@TargetApi(17)
public class BlurTransformation implements Transformation {

    private RenderScript renderScript;
    public static final int DEFAULT_RADIUS= 10;
    private float radius = DEFAULT_RADIUS;

    public BlurTransformation(Context context){
        renderScript = RenderScript.create(context);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap blurredBitmap = source.copy(Bitmap.Config.ARGB_8888,true);

        Allocation input = Allocation.createFromBitmap(renderScript,blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL,Allocation.USAGE_SHARED);
        Allocation output = Allocation.createTyped(renderScript,input.getType());

        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);

        scriptIntrinsicBlur.setRadius(radius);
        scriptIntrinsicBlur.forEach(output);

        output.copyTo(blurredBitmap);
        source.recycle();
        return blurredBitmap;
    }

    public void setRadius(float radius){
        this.radius = radius;
    }


    @Override
    public String key() {
        return "blur";
    }
}
