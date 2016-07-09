package com.qypt.just.just_retrofit_downfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.qypt.just.just_retrofit_downfile.Model.DownBean;
import com.qypt.just.just_retrofit_downfile.Model.DownResultBean;
import com.qypt.just.just_retrofit_downfile.Model.ProressBean;
import com.qypt.just.just_retrofit_downfile.Model.TopBean;
import com.qypt.just.just_retrofit_downfile.Presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IView, ProgressDialog.ProgressDialogClickListener {

    private MainPresenter mainPresenter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter();
        mainPresenter.onAttach(this);
        progressDialog = new ProgressDialog(this);
        final ImageView image = (ImageView) this.findViewById(R.id.image);
        final String fileName = System.currentTimeMillis() + ".jpeg";

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.dispatchTask(new DownBean("http://sw.bos.baidu.com/","QQ.exe","sw-search-sp/software/30f44738c65/QQ_8.4.18357.0_setup.exe"));
            }
        });

        progressDialog.setCancelClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.dispath(this);
    }

    @Override
    public void onShow() {
        progressDialog.show();
    }

    @Override
    public void onHide() {

        progressDialog.hide();
    }

    @Override
    public <T extends TopBean> void update(T t) {
        if (t instanceof ProressBean) {
            ProressBean proressBean = (ProressBean) t;
            if (proressBean.isUpdate()) {
                progressDialog.setContent("当前进度为:" + proressBean.getCurrent() / 1024 / 1024 + "MB" + "文件大小:" + proressBean.getLength() / 1024 / 1024 + "MB");
            }
        }else if(t instanceof  DownResultBean){
            DownResultBean downResultBean= (DownResultBean) t;
            if(((DownResultBean) t).getResultCode()==1){
                Toast.makeText(this.getApplicationContext(),"下载成功...",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this.getApplicationContext(),downResultBean.getContetResult().toString(),Toast.LENGTH_SHORT).show();;
            }
        }
    }

    @Override
    public void cancelListener(View v) {
        if(v.getId()==R.id.cancel){
            progressDialog.doDismiss();
            mainPresenter.cancelTask();
        }
    }

    @Override
    public void keepListener(View v) {

    }
}
