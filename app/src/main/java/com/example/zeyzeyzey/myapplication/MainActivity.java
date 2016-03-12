package com.example.zeyzeyzey.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    public String url = "http://10.200.21.145/api/pad/login";

//    public String url = "http://16.64.36.50/api/pad/login";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public String string;
    public Handler handler;
    public String namestr;
    public String pwdstr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView username = (TextView) findViewById(R.id.username);
        final TextView pwd = (TextView) findViewById(R.id.pwd);
        Button btn = (Button) findViewById(R.id.button1);
        Button finger = (Button) findViewById(R.id.finger);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String str = (String) msg.obj;
                Boolean able = false;
                try {
                    JSONObject jsonObject = new JSONObject(str);

                    String name = jsonObject.get("Name").toString();
                    able = jsonObject.getBoolean("enabled");
                    System.out.println("name"+name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(able){

                    startActivity(new Intent(MainActivity.this,documentActivity.class));
                    finish();
                }else{
                    if (namestr.length() == 0 || pwdstr.length()==0){
                        new AlertDialog.Builder(MainActivity.this).setTitle("提示")
                                .setMessage("用户名或密码为空")
                                .setPositiveButton("好的",null)
                                .show();

                    }else {
                        new AlertDialog.Builder(MainActivity.this).setTitle("提示")
                                .setPositiveButton("好的",null)
                                .setMessage("登录失败")
                                .show();
                    }

                }



            }
        };


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, documentActivity.class);

                intent.setClass(MainActivity.this, documentActivity.class);
                namestr = username.getText().toString();
                pwdstr = pwd.getText().toString();
                documentActivity.username = namestr;
                documentActivity.pwd = pwdstr;



                OkHttpClient client = new OkHttpClient();

                FormBody formBody = new Builder()
                        .add("username", namestr)
                        .add("password", pwdstr)
                        .build();

                Request postRequest = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();

                Response response = null;
                try {
                    Call call = client.newCall(postRequest);


                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                //Log.d("oa", "login response is " + response.body().string());
                                Message message = Message.obtain();
//                                message.arg1 = 123;

                            //    System.out.println(json);
                                String str = response.body().string();
                                System.out.println(str);
                                message.obj = str;
                                handler.sendMessage(message);

                            } else {
                                Log.e("oa","log faild "+response.toString());
                                throw new IOException("Unexpected code " + response);

                            }

                        }


                    });


                } catch (Exception e) {
                    e.printStackTrace();

                }



            }

        });

        finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("提示").setMessage("指纹登录").setPositiveButton("确定", null).show();
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }




    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.zeyzeyzey.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.zeyzeyzey.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

