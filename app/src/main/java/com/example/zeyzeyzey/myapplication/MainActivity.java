package com.example.zeyzeyzey.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView username = (TextView)findViewById(R.id.username);
        final TextView pwd = (TextView)findViewById(R.id.pwd);
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,documentActivity.class);

                intent.setClass(MainActivity.this, documentActivity.class);
                String namestr = username.getText().toString();
                String pwdstr = pwd.getText().toString();
//                intent.putExtra("username",str);
                documentActivity.username = namestr;
                documentActivity.pwd = pwdstr;
                startActivity(intent);
            }
        });
    }
}
