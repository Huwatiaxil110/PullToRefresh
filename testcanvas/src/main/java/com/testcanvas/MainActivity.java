package com.testcanvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.testcanvas.fsview.FSView1;
public class MainActivity extends AppCompatActivity {
    Button btnRotate, btnRotateCircle;
    FSView1 mFSView1;
    float rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        btnRotate = (Button) findViewById(R.id.button);
        btnRotateCircle = (Button) findViewById(R.id.button1);
        mFSView1 = (FSView1) findViewById(R.id.fsview1);

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate = rotate + 5;
            }
        });
        btnRotateCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}


























