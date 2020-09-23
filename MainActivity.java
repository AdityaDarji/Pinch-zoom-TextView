package com.example.demotask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    TextView text;

    int baseDistance;
    float ratio = 1.0f;
    float baseRatio;

    final static float step = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.content);
    }

    public boolean onTouchEvent(MotionEvent MEvent) {
        if (MEvent.getPointerCount() == 2)
        {
            int action = MEvent.getAction();
            int pureaction  = action & MotionEvent.ACTION_MASK;

            if (pureaction == MotionEvent.ACTION_POINTER_DOWN)
            {
                baseDistance = getDstance(MEvent);
                baseRatio = ratio;
            }
            else {
                float delta = (getDstance(MEvent)-baseDistance)/step;
                float multi = (float)(Math.pow(2, delta));
                ratio = Math.min(1024.0f, Math.max(0.1f, multi * baseRatio));
                text.setTextSize(ratio + 13 );
            }
        }
        return true;
    }
    int getDstance(MotionEvent motionEvent)
    {
        int x = (int)(motionEvent.getX(0)-motionEvent.getX(1));
        int y = (int)(motionEvent.getY(0)-motionEvent.getY(1));
        return (int)(Math.sqrt(x * x + y * y));
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}