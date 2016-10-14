package xyz.johntsai.androiddaydayup.imageload;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestHandler;
import com.squareup.picasso.StatsSnapshot;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import xyz.johntsai.androiddaydayup.R;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/10/11.
 * 图片加载库Picasso的分析和使用
 */

public class PicassoActivity extends Activity {

    private static final String TAG = "PicassoActivity";

    private LinearLayout linearLayout;
    private SeekBar seekBar;
    private Context context;
    private BlurTransformation blurTransformation;

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Log.d(TAG,bitmap.toString()+" from:"+from.name());
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
           Log.d(TAG,errorDrawable.toString());
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
           Log.d(TAG,placeHolderDrawable.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        context = this;

        blurTransformation = new BlurTransformation(context);

        linearLayout = (LinearLayout) findViewById(R.id.layout);
        seekBar = (SeekBar) findViewById(R.id.seekbar);

        final ImageView imageView1 = new ImageView(this);
        imageView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Picasso.with(context).setIndicatorsEnabled(true);
        Picasso.with(context).setLoggingEnabled(true);

        StatsSnapshot statsSnapshot = Picasso.with(context).getSnapshot();
        Log.d("cj",statsSnapshot.toString());

        Picasso
                .with(this)
                .load("https://avatars0.githubusercontent.com/u/22720606?v=3&s=200")
                .placeholder(R.mipmap.ic_launcher)
                .rotate(45,100,100)
                .error(R.drawable.arrow)
                .into(imageView1);

        linearLayout.addView(imageView1);

        ImageView imageView2 = new ImageView(this);
        imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        final ImageView imageView3 = new ImageView(this);
        imageView3.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        final int resourceId = R.mipmap.newzealand;

        linearLayout.addView(imageView2);
        linearLayout.addView(imageView3);


        List<Transformation> transformationList = new ArrayList<>();
//        transformationList.add(new BlurTransformation(context));
        transformationList.add(new GrayscaleTransformation(Picasso.with(context)));

        Picasso
                .with(this)
                .load(resourceId)
                .transform(transformationList)
//                .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
//                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(imageView2);


        Picasso
                .with(this)
                .load(resourceId)
                .transform(new jp.wasabeef.picasso.transformations.BlurTransformation(this,25))
                .into(imageView3);


        Log.d("cj",statsSnapshot.toString());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blurTransformation.setRadius(progress==0?0.1f:progress);
                Picasso.with(context).load(resourceId).transform(blurTransformation).noPlaceholder().into(imageView3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        //通用的获取Picasso标准实例的方法
        Picasso picasso = Picasso.with(context);


        //获取Picasso自定义实例的方法
        //自定义所需的Picasso组件化属性
        Picasso.Builder builder = new Picasso.Builder(context);
        Picasso localPicasso = builder
                .downloader(new OkHttp3Downloader(new OkHttpClient()))
                .addRequestHandler(new RequestHandler() {
                    @Override
                    public boolean canHandleRequest(Request data) {
                        return false;
                    }

                    @Override
                    public Result load(Request request, int networkPolicy) throws IOException {
                        return null;
                    }
                })
                .build();

        //设置全局唯一实例
        try {
            Picasso.setSingletonInstance(localPicasso);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


}
