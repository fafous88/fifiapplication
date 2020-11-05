package com.example.firstpage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firstpage.tita.FloodFill;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PremierePage extends AppCompatActivity {
 private Button Btn,Btn2;
 private EditText T1;

 private Button btnsignin,btnsignup;
 FirebaseDatabase database;
 DatabaseReference users;
 private EditText T3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premiere);

        //Firebase
        database=FirebaseDatabase.getInstance();
        users=database.getReference("users");
        Btn=findViewById(R.id.btnsignin);
        T1=findViewById(R.id.ed1);
        T3=findViewById(R.id.ed3);
        Btn2=findViewById(R.id.btnsignup);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent s=new Intent(getApplicationContext() ,MainActivity.class);
              startActivity(s);


            }
        });

    }


}