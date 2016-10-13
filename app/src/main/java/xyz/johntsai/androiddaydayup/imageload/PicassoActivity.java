package xyz.johntsai.androiddaydayup.imageload;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.LinearGradient;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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

        Picasso
                .with(this)
                .load("https://avatars0.githubusercontent.com/u/178706384?v=3&s=200")
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


        Picasso
                .with(this)
                .load("http://img5.imgtn.bdimg.com/it/u=2945200961,993933618&fm=21&gp=0.jpg")
                .priority(Picasso.Priority.HIGH)
                .into(imageView2);


        Picasso
                .with(this)
                .load(resourceId)
                .transform(blurTransformation)
                .into(imageView3);


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


    }


}
