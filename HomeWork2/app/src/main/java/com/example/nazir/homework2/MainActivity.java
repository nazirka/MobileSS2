package com.example.nazir.homework2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button m_button_1;
    private Button m_button_2;
    private Button m_button_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnClickListener button1ClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setTextColor(getResources().getColor(R.color.textColorStyles));
                tv.setBackgroundColor(getResources().getColor(R.color.colorStyle1));
            }
        };

        OnClickListener button2ClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setTextColor(getResources().getColor(R.color.textColorStyles));
                tv.setBackgroundColor(getResources().getColor(R.color.colorStyle2));
            }
        };

        OnClickListener button3ClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setTextColor(getResources().getColor(R.color.textColorStyles));
                tv.setBackgroundColor(getResources().getColor(R.color.colorStyle3));
            }
        };


        m_button_1 = (Button) findViewById(R.id.button1);
        m_button_1.setOnClickListener(button1ClickListener);

        m_button_2 = (Button) findViewById(R.id.button2);
        m_button_2.setOnClickListener(button2ClickListener);

        m_button_3 = (Button) findViewById(R.id.button3);
        m_button_3.setOnClickListener(button3ClickListener);


    }
}
