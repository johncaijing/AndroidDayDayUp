package xyz.johntsai.androiddaydayup;

import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import xyz.johntsai.androiddaydayup.customview.BoolCalView;
import xyz.johntsai.androiddaydayup.customview.CubicView;

public class MainActivity extends AppCompatActivity {

    BoolCalView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (BoolCalView) findViewById(R.id.customView);

//        final CubicView cubicView = (CubicView) findViewById(R.id.customView);
//
//        final RadioButton radioButton1 = (RadioButton) findViewById(R.id.rbControl1);
//        final RadioButton radioButton2 = (RadioButton) findViewById(R.id.rbControl2);
//
//        radioButton1.toggle();
//
//        radioButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                radioButton2.setChecked(false);
//                cubicView.setIsControl1(true);
//            }
//        });
//        radioButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                radioButton1.setChecked(false);
//                cubicView.setIsControl1(false);
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.difference:
                view.setOp(Path.Op.DIFFERENCE);
                return true;
            case R.id.xor:
                view.setOp(Path.Op.XOR);
                return true;
            case R.id.intersect:
                view.setOp(Path.Op.INTERSECT);
                return true;
            case R.id.reverse_difference:
                view.setOp(Path.Op.REVERSE_DIFFERENCE);
                return true;
            case R.id.union:
                view.setOp(Path.Op.UNION);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.op,menu);
        return true;
    }
}
