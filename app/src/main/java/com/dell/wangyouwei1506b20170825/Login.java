package com.dell.wangyouwei1506b20170825;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * 姓名：王有为
 * 时间：2017/8/25.
 */

public class Login extends AppCompatActivity implements View.OnClickListener{
    private TextView text1,text2;
    private File file;
    int blockSize=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        text1=(TextView)findViewById(R.id.loginText1);
        text2=(TextView)findViewById(R.id.loginText2);
        file = new File("/data/data/com.dell.wangyouwei1506b20170825/cache");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginText1:
                file.delete();
                Toast.makeText(Login.this,"删除成功",Toast.LENGTH_SHORT).show();
            break;
            case R.id.loginText2:
                blockSize= (int) file.length();
                Toast.makeText(Login.this,"0",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
