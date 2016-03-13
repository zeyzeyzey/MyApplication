package com.example.zeyzeyzey.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;





/**
 * Created by zeyzeyzey on 16/3/2.
 */
public class documentActivity extends MainActivity {
    public static String username;
    public static String pwd;
    private ImageButton button_yiban;
    private ImageButton button_signout;
    public String url_task;
    public Handler handler;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private List<taskImg> taskImgList = new ArrayList<taskImg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_page);
        initTaskList();
        taskAdapter adapter = new taskAdapter(documentActivity.this,R.layout.tasklist,taskImgList);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                documentActivity.this,android.R.layout.simple_list_item_1,data
//        );
        ListView listView = (ListView)findViewById(R.id.new_taskList);
        listView.setAdapter(adapter);
        button_yiban = (ImageButton) findViewById(R.id.yiban);
        button_signout = (ImageButton) findViewById(R.id.singout);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String task = (String) msg.obj;
//                try {
//                    Gson gson = new Gson();
//
//                    JsonParser parser = new JsonParser();
//                    JsonArray jsonArray = parser.parse(task).getAsJsonArray();
//                for (int i = 0; i < jsonArray.size(); i++) {
//
//                       JsonObject temp = (JsonObject) jsonArray.get(i);
//                     String id = String.valueOf(temp.get("id"));
//                      System.out.println(id);
//                  }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }
        };


        button_yiban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(documentActivity.this, yibanActivity.class);
                intent.setClass(documentActivity.this, yibanActivity.class);
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

        url_task = "http://10.200.21.145/api/getTasks/" + username;
        System.out.println(url_task);

        String authorization="weiquanmiao:wqm8215075";
//        String encodedAuth="Basic "+new String(Base64.encodeBase64(authorization.getBytes()));
        String encodedAuth = null;
       // String strBase64 = new String(Base64.encode(str.getBytes(), Base64.DEFAULT));
        try {
            encodedAuth = "Basic "+new String(Base64.encode(authorization.getBytes(),Base64.DEFAULT)).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }


        OkHttpClient getTask = new OkHttpClient();
//        String credential;
//        credential = Credentials.class;
        FormBody formBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(url_task)
                .header("Authorization", encodedAuth)
                .post(formBody)
//                .header("Authorization", "auth-token")
                .build();
        Call call = getTask.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    Message message = Message.obtain();
                    System.out.println(response.body().string());
//                    message.obj =str;
                    handler.sendMessage(message);
                } else {
                    throw new IOException("Unexpected code " + response);
                }

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    //列表添加内容
    private void initTaskList(){
        taskImg task1 = new taskImg("A",R.drawable.office);
        taskImgList.add(task1);
        taskImg task2 = new taskImg("B",R.drawable.office);
        taskImgList.add(task1);
        taskImg task3 = new taskImg("C",R.drawable.office);
        taskImgList.add(task1);
        taskImg task4 = new taskImg("D",R.drawable.office);
        taskImgList.add(task1);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "document Page", // TODO: Define a title for the content shown.
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
                "document Page", // TODO: Define a title for the content shown.
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


