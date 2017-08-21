package com.example.nazir.homework3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.nazir.homework3.R.layout.activity_2;

public class MainActivity extends AppCompatActivity {

    private Button buttonStyle1;
    private Button buttonStyle2;
    private Button buttonStyle3;
    private Button buttonStyleCustom;
    private ImageView imageLodka;

    private TextView textHeader;
    private TextView textMain;

    private int new_style_color;
    private Uri imageUri;

    public static final int RESULT_GALLERY = 0;
    public static final int RESULT_ACTIVITY = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnClickListener button1StyleClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setTextColor(getResources().getColor(R.color.textColorStyles));
                tv.setBackgroundColor(getResources().getColor(R.color.colorStyle1));
            }
        };

        OnClickListener button2StyleClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setTextColor(getResources().getColor(R.color.textColorStyles));
                tv.setBackgroundColor(getResources().getColor(R.color.colorStyle2));
            }
        };

        OnClickListener button3StyleClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setTextColor(getResources().getColor(R.color.textColorStyles));
                tv.setBackgroundColor(getResources().getColor(R.color.colorStyle3));
            }
        };

        OnClickListener buttonStyleCustomClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Act2 = new Intent(MainActivity.this, Activity2.class);
                startActivityForResult(Act2, RESULT_ACTIVITY);
            }
        };

        OnClickListener imageClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_GALLERY);
            }
        };

        buttonStyle1 = (Button) findViewById(R.id.button1);
        buttonStyle1.setOnClickListener(button1StyleClickListener);

        buttonStyle2 = (Button) findViewById(R.id.button2);
        buttonStyle2.setOnClickListener(button2StyleClickListener);

        buttonStyle3 = (Button) findViewById(R.id.button3);
        buttonStyle3.setOnClickListener(button3StyleClickListener);

        buttonStyleCustom = (Button) findViewById(R.id.button4);
        buttonStyleCustom.setOnClickListener(buttonStyleCustomClickListener);

        imageLodka = (ImageView) findViewById(R.id.imageView);
        imageLodka.setOnClickListener(imageClickListener);

        textHeader = (TextView) findViewById(R.id.textView1);
        textMain = (TextView) findViewById(R.id.textView2);

        if (savedInstanceState != null){
            boolean imageUri_changed = savedInstanceState.containsKey("saveImage_key");
            if (imageUri_changed) {
                imageUri = savedInstanceState.getParcelable("saveImage_key");
                imageLodka.setImageURI(imageUri);
           }

            boolean new_style_color_changed = savedInstanceState.containsKey("saveActive1_key");
            if (new_style_color_changed) {
                new_style_color = savedInstanceState.getInt("saveActive1_key");
                textHeader.setBackgroundColor(new_style_color);
                textMain.setBackgroundColor(new_style_color);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == RESULT_GALLERY) && (data != null)) {
            imageUri = data.getData();
            imageLodka.setImageURI(imageUri);
        }

        if ((requestCode == RESULT_ACTIVITY) && (resultCode == RESULT_OK)) {

            String strColor = data.getStringExtra("COLOR_KEY");

            new_style_color = 0xFF000000 + Integer.parseInt(strColor, 16);
            textHeader.setBackgroundColor(new_style_color);
            textMain.setBackgroundColor(new_style_color);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("saveActive1_key", new_style_color);
        outState.putParcelable("saveImage_key", imageUri);
    }
}
