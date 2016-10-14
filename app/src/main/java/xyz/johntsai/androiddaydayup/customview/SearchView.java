package xyz.johntsai.androiddaydayup.customview;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/9/26.
 * 搜索View
 */

public class SearchView extends View {
    private Paint paint;

    private int width,height;

    public static final int STATE_NONE = 0x1;
    public static final int STATE_STARTING = 0x2;
    public static final int STATE_SEARCHING = 0x3;
    public static final int STATE_ENDING = 0x4;



    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
