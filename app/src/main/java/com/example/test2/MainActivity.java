package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    Button beginTbt;
    Button endbt;
    Intent intent;
    private Myservice myservice;
    private Myservice.MyBinder binder;
    int sb;


    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (Myservice.MyBinder) service;
            binder.test();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         beginTbt=findViewById(R.id.begin_bt);
         endbt=findViewById(R.id.end_bt);
        beginTbt.setOnClickListener(this);
        endbt.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.begin_bt:
                intent =new Intent(this,Myservice.class);
                bindService(intent,connection,BIND_AUTO_CREATE );
                break;
            case R.id.end_bt:
                unbindService(connection);
                break;


        }
    }



}
