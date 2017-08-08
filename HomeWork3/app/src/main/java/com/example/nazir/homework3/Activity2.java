package com.example.nazir.homework3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {

    private Button m_button_1;
    public static final int RESULT_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        View.OnClickListener button1ClickListener = new View.OnClickListener() {
            @Override
                public void onClick(View v) {

                    Intent answerIntent = new Intent();
                    EditText txt = (EditText)findViewById(R.id.colorEditText);
                    answerIntent.putExtra("COLOR_KEY", txt.getText().toString());

                    setResult(RESULT_OK, answerIntent);
                    finish();
                }
        };

        m_button_1 = (Button) findViewById(R.id.buttonAct2);
        m_button_1.setOnClickListener(button1ClickListener);

    }
}
