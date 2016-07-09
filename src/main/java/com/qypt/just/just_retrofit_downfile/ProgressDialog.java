package com.qypt.just.just_retrofit_downfile;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/8.
 */
public class ProgressDialog extends Dialog {

    private TextView title;
    private TextView content;
    private Button cancel;
    private Button keepOn;

    public ProgressDialog(Context context) {
        this(context,R.style.progressDialog);
        init(context);

    }

    public ProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        this.getWindow().setAttributes(lp);
        this.setCancelable(false);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View containt = layoutInflater.inflate(R.layout.dialog_layout, null);

        title = (TextView) containt.findViewById(R.id.title);
        content = (TextView) containt.findViewById(R.id.content);
        cancel = (Button) containt.findViewById(R.id.cancel);
        keepOn = (Button) containt.findViewById(R.id.keepOn);
        this.setContentView(containt);

    }
    public void setCancelClickListener(final ProgressDialogClickListener progressDialogClickListener){

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    progressDialogClickListener.cancelListener(cancel);
            }
        });
    }

    public void doShow() {
        this.show();
    }

    public void doDismiss() {
        this.dismiss();
    }

    public void setContent(String content) {
        this.content.setText(content);
    }

    interface ProgressDialogClickListener{
        void cancelListener(View v);
        void keepListener(View v);
    }
}
