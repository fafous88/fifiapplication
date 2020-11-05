package com.example.firstpage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstpage.tita.GridItem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imgV;
    Button Btn;
    private int REQUEST_STORAGE = 111;
    private int REQUEST_FILE = 222;
    private Uri uri;
    private String stringPath;
    private Intent iData;
    GridView gridView;
    String[] figuresNoms = {"figure1", "figure2", "figure3", "figure4", "figure5", "figure6", "figure7", "figure8", "figure9", "figure10", "figure11", "figure12"};
    int[] figuresImages = {R.drawable.figure1, R.drawable.figure2, R.drawable.figure3, R.drawable.figure4, R.drawable.figure5, R.drawable.figure6, R.drawable.figure7, R.drawable.figure8, R.drawable.figure9, R.drawable.figure10, R.drawable.figure11, R.drawable.figure12};
    private Object view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        Toast.makeText(MainActivity.this, "connected to firebase", Toast.LENGTH_LONG).show();
        gridView = findViewById(R.id.gridview);
        CustomAdapter customAdapter = new CustomAdapter();


        gridView.setAdapter((ListAdapter) customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), GridItem.class);
                intent.putExtra("text", figuresNoms[i]);
                intent.putExtra("img", figuresImages[i]);

                startActivity(intent);

            }
        });

        imgV = findViewById(R.id.selected);
       /* imgV.setAdapter((ListAdapter)customAdapter);
        imgV.setOnClickListener(new AdapterView.OnItemClickListener()){
            @Override
                    public void OnItemClick(AdapterView<?> adapterView,View view, int i,long l){
                Intent intent=new Intent(getApplicationContext(),GridItem.class);

            }

        };*/
        Btn = findViewById(R.id.BTN);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), GridItem2.class);

                startActivity(intent);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE);
                } else {
                    SelectImage();
                }


            }


        });
        imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkReadStorageAllowed()) {


                    File imgFile = new File(stringPath);

                    if (imgFile.exists()) {
                        Uri uri;

                        uri = FileProvider.getUriForFile(MainActivity.this,MainActivity.this.getPackageName() + "." + BuildConfig.APPLICATION_ID+ ".provider",imgFile);
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setDataAndType(uri, "image/*");

                        MainActivity.this.startActivity(intent);


                    }
                }else {
                    ActivityCompat.requestPermissions((Activity) MainActivity.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},REQUEST_STORAGE);

                }
            }


        });


    }


    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return figuresImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewl = getLayoutInflater().inflate(R.layout.row_data, null);
            TextView text = viewl.findViewById(R.id.figure);
            ImageView img = viewl.findViewById(R.id.picture);


            text.setText(figuresNoms[i]);
            img.setImageResource(figuresImages[i]);


            return viewl;
        }
    }


    private void SelectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_FILE);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FILE && resultCode == RESULT_OK) {
            if (data != null) {
                uri = data.getData();
                iData = data;
                getStringPath(uri);
                InputStream inputStream = null;
                try {
                    inputStream = getContentResolver().openInputStream(uri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Intent intent = new Intent(getApplicationContext(), GridItem2.class);

                startActivity(intent);

                // ==========
                //imgV.setImageBitmap(bitmap);
            }

        }
    }

    private String getStringPath(Uri myUri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(myUri, filePathColumn, null, null, null);
        if (cursor == null) {
            stringPath = myUri.getPath();

        } else {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            stringPath = cursor.getString(columnIndex);
            cursor.close();
        }
        return stringPath;
    }

    public boolean checkReadStorageAllowed() {
        if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        return false;
    }


}