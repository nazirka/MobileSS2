package com.example.nazir.homework3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;

import static com.example.nazir.homework3.R.layout.activity_2;

public class MainActivity extends AppCompatActivity {

    private Button m_button_1;
    private Button m_button_2;
    private Button m_button_3;
    private Button m_button_4;
    private ImageView m_image;

    private TextView text1;
    private TextView text2;

    private int new_style_color;
    private boolean new_style_color_changed;
    private Uri imageUri;
    private boolean imageUri_changed;

    public static final int RESULT_GALLERY = 0;
    public static final int RESULT_ACTIVITY = 1;


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
                new_style_color_changed = false;
            }
        };

        OnClickListener button2ClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setTextColor(getResources().getColor(R.color.textColorStyles));
                tv.setBackgroundColor(getResources().getColor(R.color.colorStyle2));
                new_style_color_changed = false;
            }
        };

        OnClickListener button3ClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setTextColor(getResources().getColor(R.color.textColorStyles));
                tv.setBackgroundColor(getResources().getColor(R.color.colorStyle3));
                new_style_color_changed = false;
            }
        };

        OnClickListener button4ClickListener = new OnClickListener() {
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

        m_button_1 = (Button) findViewById(R.id.button1);
        m_button_1.setOnClickListener(button1ClickListener);

        m_button_2 = (Button) findViewById(R.id.button2);
        m_button_2.setOnClickListener(button2ClickListener);

        m_button_3 = (Button) findViewById(R.id.button3);
        m_button_3.setOnClickListener(button3ClickListener);

        m_button_4 = (Button) findViewById(R.id.button4);
        m_button_4.setOnClickListener(button4ClickListener);

        m_image = (ImageView) findViewById(R.id.imageView);
        m_image.setOnClickListener(imageClickListener);

        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);

        if (savedInstanceState != null){
            imageUri_changed = savedInstanceState.getBoolean("saveImage_key_f");
            if (imageUri_changed) {
                imageUri = savedInstanceState.getParcelable("saveImage_key");
                try {
                    Bitmap new_bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    m_image.setImageBitmap(new_bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            new_style_color_changed = savedInstanceState.getBoolean("saveActive1_key_f");
            if (new_style_color_changed) {
                new_style_color = savedInstanceState.getInt("saveActive1_key");
                text1.setBackgroundColor(new_style_color);
                text2.setBackgroundColor(new_style_color);
            }



        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == RESULT_GALLERY) && (data != null)) {
            imageUri = data.getData();

            try {
                Bitmap new_bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                m_image.setImageBitmap(new_bitmap);
                imageUri_changed = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if ((requestCode == RESULT_ACTIVITY) && (resultCode == RESULT_OK)) {

            String strColor = data.getStringExtra("COLOR_KEY");

            new_style_color = 0xFF000000 + Integer.parseInt(strColor, 16);
            text1.setBackgroundColor(new_style_color);
            text2.setBackgroundColor(new_style_color);
            new_style_color_changed = true;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("saveActive1_key_f", new_style_color_changed);
        outState.putInt("saveActive1_key", new_style_color);
        outState.putBoolean("saveImage_key_f", imageUri_changed);
        outState.putParcelable("saveImage_key", imageUri);
    }
}
