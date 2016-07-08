package com.gg.githubtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Created by GG on 2016/7/1.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(R.id.tv);

        if (tv != null) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NumberPickView numberPickView = new NumberPickView(MainActivity.this);
                    numberPickView.SetMinFromValue(0);
                    numberPickView.SetMaxFromValue(8);
                    numberPickView.SetMinToValue(0);
                    numberPickView.SetMaxToValue(10);
                    numberPickView.setOnNumberPickListener(new NumberPickView.OnNumberChangeListener() {
                        @Override
                        public void onNumberPicked(int from, int to) {
                            Toast.makeText(MainActivity.this, "从" + from + "到" + to, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

    }
}
