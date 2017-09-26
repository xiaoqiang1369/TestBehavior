package com.xiaoqiang.testbehavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 展示控件与另一个控件关联，FootActivity
 */
public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        textView = (TextView)findViewById(R.id.text_view);
        button = (Button)findViewById(R.id.button);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    view.setX(motionEvent.getRawX() - view.getWidth()/2);
                    view.setY(motionEvent.getRawY() - view.getHeight()/2);
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    startActivity(new Intent(MainActivity.this, FootActivity.class));
                }
                return false;
            }
        });
    }


}
