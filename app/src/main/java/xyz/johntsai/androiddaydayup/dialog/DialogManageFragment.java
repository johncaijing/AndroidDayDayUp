package xyz.johntsai.androiddaydayup.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/10/8.
 */

public class DialogManageFragment extends DialogFragment {

    private Dialog dialog;

    public DialogManageFragment(){

    }

    @SuppressLint("ValidFragment")
    public DialogManageFragment(Dialog dialog){
        this.dialog = dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //create Dialog here
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof DialogListener){
            this.listener = (DialogListener) activity;
        }else{

        }
    }

    public interface DialogListener{
        public void onPositiveButtonClickListener(DialogFragment dialogFragment);
        public void onNegativeButtonClickListener(DialogFragment dialogFragment);
    }

    private DialogListener listener;
}
