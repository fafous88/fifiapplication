package com.example.firstpage.tita;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import com.example.firstpage.Common;
import com.example.firstpage.Fifi;
import com.example.firstpage.LineView;
import com.example.firstpage.MainActivity;
import com.example.firstpage.PaintView;
import com.example.firstpage.R;
import com.thebluealliance.spectrum.SpectrumPalette;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import yuku.ambilwarna.AmbilWarnaDialog;


public class GridItem2 extends AppCompatActivity implements SpectrumPalette.OnColorSelectedListener {

    private int REQUEST_STORAGE = 111;
    private int REQUEST_FILE = 222;
    private Uri uri;
    private String stringPath;
    private Intent iData;

    private ImageButton Back;
    ImageButton Color;
    TextView text;
    int DefaultColor;
    ImageView img;
    ImageView img1;
    LineView lineView2;
    Fifi fifi;
    LinearLayout ly;
    ImageButton Save, tita;
    BitmapDrawable drawable;
    Bitmap bitmap;
    LineView lineView;
    PaintView paintview;
    Button btn1;
    private FrameLayout rootLayout;
    private TextView textViewTitle;
    private TextView textViewBody;
    private ImageView imageView;
    private Palette.Swatch vibrantSwatch;
    private Palette.Swatch lightVibrantSwatch;
    private Palette.Swatch darkVibrantSwatch;
    private Palette.Swatch mutedSwatch;
    private Palette.Swatch lightMutedSwatch;
    private Palette.Swatch darkMutedSwatch;
    private int swatchNumber;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);


        text = findViewById(R.id.griddata);
        img = findViewById(R.id.img1);
            newImage();

            //Intent intent = getIntent();
        // text.setText(intent.getStringExtra("text"));
          //  String str=intent.getStringExtra("text");
            //Bitmap b=intent.getSerializableExtra("img");
        //img.setImageResource(intent.getIntExtra("img", "New Image"));
        //=======================
        //img = findViewById(R.id.image);
        //img.setImageResource(intent.getIntExtra("img", (0)));
        //=======================espace de travail
        lineView = new LineView(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutt);
        layout.addView(lineView);

        //////////====================
            Save=findViewById(R.id.save);
            Save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSavePaintingConfirmationDialog();
                }
            });
       //=========================save picture
      /*  Save = findViewById(R.id.save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawable = (BitmapDrawable) img.getDrawable();
                bitmap = drawable.getBitmap();
                FileOutputStream outputStream = null;
                File sdk = Environment.getExternalStorageDirectory();
                File directory = new File(sdk.getAbsolutePath() + "/your folder name");
                directory.mkdirs();
                String filename = String.format("%djpg", System.currentTimeMillis());
                File outfile = new File(directory, filename);
                Toast.makeText(GridItem2.this, "image saved succefelly", Toast.LENGTH_SHORT).show();
                try {
                    outputStream = new FileOutputStream(outfile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(outfile));
                    sendBroadcast(intent);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/
      //==========================Back
        fifi = (Fifi) findViewById(R.id.fifi);
        Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridItem2.this, MainActivity.class);
                startActivity(intent);
            }
        });

         //============================quadrillage bitmap image
        ((ImageView) findViewById(R.id.img1)).setZ(1);
        lineView = new LineView(this);
        lineView.setZ(9999);
        FrameLayout layo = (FrameLayout) findViewById(R.id.layo);
        layo.addView(lineView);
    ///////////===============================colorpicker
        ly = findViewById(R.id.layoutt);
        DefaultColor = ContextCompat.getColor(this, R.color.colorAccent);
        Color = findViewById(R.id.color);
        Color.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                OpenColorPicker(false);

            }
        });
        // initTollbar();
        SpectrumPalette spectrumPalette = findViewById(R.id.palette);
        spectrumPalette.setOnColorSelectedListener(this);
        //===============Coloriage dessin
       /* paintview = new PaintView(this);
        LinearLayout lyout = (LinearLayout) findViewById(R.id.layoutt);
        lyout.addView(paintview);*/
        //===================================
            rootLayout =findViewById(R.id.layo);
            textViewTitle = findViewById(R.id.text_view_title);
            textViewBody =findViewById(R.id.text_view_body);
            imageView = findViewById(R.id.img1);
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            Palette.from(bitmap).maximumColorCount(32).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    vibrantSwatch = palette.getVibrantSwatch();
                    lightVibrantSwatch = palette.getLightVibrantSwatch();
                    darkVibrantSwatch = palette.getDarkVibrantSwatch();
                    mutedSwatch = palette.getMutedSwatch();
                    lightMutedSwatch = palette.getLightMutedSwatch();
                    darkMutedSwatch = palette.getDarkMutedSwatch();
                }
            });
            SpectrumPalette spectrumPalette1 = (SpectrumPalette)findViewById(R.id.palette);
           // findViewById(R.id.bt).setBackgroundColor(liste[6]);

            spectrumPalette.setOnColorSelectedListener(this);
            spectrumPalette1.setOnColorSelectedListener(new SpectrumPalette.OnColorSelectedListener() {
                @Override
                public void onColorSelected(int color) {
                    fifi.getPaint().setColor(color);
                }
            });

    }

    private void showSavePaintingConfirmationDialog() {

        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
        saveDialog.setTitle("Save drawing");
        saveDialog.setMessage("Save drawing to device Gallery?");
        saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                //save drawing
                //fifi.setBackgroundColor(white);
                fifi.setDrawingCacheEnabled(true);
                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), fifi.getDrawingCache(),
                        UUID.randomUUID().toString()+".jpg", "drawing");
                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Image saved succefelly!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
                else{
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }
                // Destroy the current cache.
                fifi.destroyDrawingCache();
            }
        });
        saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        saveDialog.show();
    }


    public void newImage() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE);
        } else {
            SelectImage();
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
                    //Drawable d=new Drawable(bitmap);

                    // ==========
                    img.setImageBitmap(bitmap);
                    img = findViewById(R.id.image);
                    img.setImageBitmap(bitmap);

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



    //==================conver image to bitmap
    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

//========Gomme
    public void clearCanvas(View v) {
        fifi.clearCanvas();
    }

//==========Colorpicker
    private void OpenColorPicker(boolean AlphaSupport) {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                DefaultColor = color;
                ly.setBackgroundColor(DefaultColor);

            }
        });

        ambilWarnaDialog.show();


    }
    public void nextSwatch(View v) {
        Palette.Swatch currentSwatch = null;
        switch (swatchNumber) {
            case 0:
                currentSwatch = vibrantSwatch;
                textViewTitle.setText("Vibrant");
                break;
            case 1:
                currentSwatch = lightVibrantSwatch;
                textViewTitle.setText("Light Vibrant");
                break;
            case 2:
                currentSwatch = darkVibrantSwatch;
                textViewTitle.setText("Dark Vibrant");
                break;
            case 3:
                currentSwatch = mutedSwatch;
                textViewTitle.setText("Muted");
                break;
            case 4:
                currentSwatch = lightMutedSwatch;
                textViewTitle.setText("Light Muted");
                break;
            case 5:
                currentSwatch = darkMutedSwatch;
                textViewTitle.setText("Dark Muted");
                break;
        }
        if (currentSwatch != null) {
            rootLayout.setBackgroundColor(currentSwatch.getRgb());
            textViewTitle.setTextColor(currentSwatch.getTitleTextColor());
            textViewBody.setTextColor(currentSwatch.getBodyTextColor());
        } else {
            rootLayout.setBackgroundColor(Color.getSolidColor());
            textViewTitle.setTextColor(Color.getSolidColor());
            textViewBody.setTextColor(Color.getSolidColor());
        }
        if (swatchNumber < 5) {
            swatchNumber++;
        } else {
            swatchNumber = 0;
        }
    }

  @Override
    public void onColorSelected(int Color) {
        Common.COLOR_SELECTED = Color;
    }




}





