package xyz.johntsai.androiddaydayup.touchevent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import xyz.johntsai.androiddaydayup.R;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/9/29.
 * 触摸事件相关
 */

public class TouchEventActivity extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String TAG = TouchEventActivity.class.getSimpleName();

    private Button button;
    private ImageView imageView;
    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchevent);

        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);

//        imageView.setClickable(true);

        gestureDetectorCompat = new GestureDetectorCompat(this,new MyGestureDetector());
//        gestureDetectorCompat.setOnDoubleTapListener(this);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int actionMasked = MotionEventCompat.getActionMasked(event);
                switch (actionMasked){
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG,"DOWN");
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG,"MOVE");
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG,"UP");
                        return true;
                    default:
                        Log.d(TAG,"other motion");
                        return true;
                }
            }
        });

        /*imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"image click");
            }
        });*/

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int actionMasked = MotionEventCompat.getActionMasked(event);
                switch (actionMasked){
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG,"DOWN");
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG,"MOVE");
                        return false;
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG,"UP");
                        return false;
                    default:
                        Log.d(TAG,"other motion");
                        return false;
                }
            }
        });

    }

    public void clickButton(View view){
        Log.d(TAG,"button click");
    }


    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG,"down-->"+e.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
       Log.d(TAG,"showPress-->"+e.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG,"singleTapUp-->"+e.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG,"scroll-->"+e1.toString()+" "+e2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG,"longpress-->"+e.toString());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG,"fling-->"+e1.toString()+" "+e2.toString());
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(TAG,"single tap confirmed-->"+e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG,"double tap-->"+e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(TAG,"double tap event-->"+e.toString());
        return false;
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{
        private final String GESTURE_TAG = MyGestureDetector.class.getSimpleName();
        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(GESTURE_TAG,"down-->"+e.toString());
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(GESTURE_TAG,"scroll-->"+e1.toString()+" "+e2.toString());
            return true;
        }
    }
}
