package com.example.firstpage;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;

public class LastPage extends AppCompatActivity {
    ImageView img;
    LineView lineView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lastpage);
        img = findViewById(R.id.img1);
        Intent intent = getIntent();
        // text.setText(intent.getStringExtra("text"));
        img.setImageResource(intent.getIntExtra("img", (0)));




    }
}
