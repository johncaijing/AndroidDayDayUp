package xyz.johntsai.androiddaydayup.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import xyz.johntsai.androiddaydayup.R;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/10/8.
 * Dialog的基本使用
 */

public class DialogActivity extends Activity {

    private Context context;
    private DialogManageFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_dialog);
    }

    public void showDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Test Message")
                .setPositiveButton("PositiveBtn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"Click positive button!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("NegativeBtn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(context,"Click negative button!",Toast.LENGTH_SHORT).show();;
                    }
                })
                .setTitle("This is the title")
                .setNeutralButton("NeutralBtn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"click neutral button!",Toast.LENGTH_SHORT).show();;
                    }
                });

        fragment = new DialogManageFragment(builder.create());
        fragment.show(this.getFragmentManager(),"dialog");
    }

    public void dismissDialog(View view){
        if(fragment!=null){
            fragment.dismissAllowingStateLoss();
        }
    }
}
