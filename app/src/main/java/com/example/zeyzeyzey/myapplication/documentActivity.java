package com.example.zeyzeyzey.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by zeyzeyzey on 16/3/2.
 */
public class documentActivity extends MainActivity {
    private TextView textView;
    public static String username;
    public static String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_page);
        textView = (TextView)this.findViewById(R.id.zhi);
        textView.setText("username-->"+username+"pwd-->"+pwd);


    }
}
