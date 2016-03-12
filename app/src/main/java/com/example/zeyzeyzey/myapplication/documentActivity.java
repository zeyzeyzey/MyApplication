package com.example.zeyzeyzey.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * Created by zeyzeyzey on 16/3/2.
 */
public class documentActivity extends MainActivity {
    private TextView textView;
    public static String username;
    public static String pwd;
    public ActionBar actionBar;
    private ImageButton button_yiban;
    private ImageButton button_signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_page);
        button_yiban = (ImageButton)findViewById(R.id.yiban);
        button_signout = (ImageButton)findViewById(R.id.singout);

        button_yiban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(documentActivity.this,yibanActivity.class);
                intent.setClass(documentActivity.this,yibanActivity.class);
                startActivity(intent);
            }
        });

        button_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(documentActivity.this)
                        .setTitle("退出提醒")
                        .setMessage("确认退出？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setContentView(R.layout.activity_main);

                            }

                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });

        //actionBar = this.getSupportActionBar();
        //actionBar.setTitle("12312123231");
        //actionBar.hide();

        textView = (TextView)this.findViewById(R.id.zhi);
        textView.setText("username-->" + username + "pwd-->"+pwd);


    }

}
