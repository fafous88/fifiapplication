package com.example.firstpage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
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
import android.R.color;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import android.view.Menu;
import com.thebluealliance.spectrum.SpectrumPalette;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.cert.Extension;
import java.util.List;
import java.util.UUID;
import android.app.Dialog;
import android.content.DialogInterface;


import yuku.ambilwarna.AmbilWarnaDialog;



public class GridItem extends AppCompatActivity implements SpectrumPalette.OnColorSelectedListener {

    private ImageButton Back;
    ImageButton Color;
    TextView text;
    int DefaultColor;
    ImageView img;
    ImageView img1;
    LineView lineView2;
    Fifi fifi;
    LinearLayout layi;
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
    int liste[];
    List<Palette.Swatch> pl;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // liste=new int[7];

        setContentView(R.layout.activity_grid_item);
        text = findViewById(R.id.griddata);
        img = findViewById(R.id.img1);
        Intent intent = getIntent();
        // text.setText(intent.getStringExtra("text"));
        img.setImageResource(intent.getIntExtra("img", (0)));
        //=======================
        img = findViewById(R.id.image);
        img.setImageResource(intent.getIntExtra("img", (0)));
        //=======================espace de travail
        lineView = new LineView(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutt);
        layout.addView(lineView);
        /////////==================



    //=========================save picture
       Save = findViewById(R.id.save);
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
                Toast.makeText(GridItem.this, "image saved succefelly", Toast.LENGTH_SHORT).show();
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
        });
        //==========================Back
        fifi = (Fifi) findViewById(R.id.fifi);
        Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridItem.this, MainActivity.class);
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
                  pl= palette.getSwatches();
                    vibrantSwatch = palette.getVibrantSwatch();
                    lightVibrantSwatch = palette.getLightVibrantSwatch();
                    darkVibrantSwatch = palette.getDarkVibrantSwatch();
                    mutedSwatch = palette.getMutedSwatch();
                    lightMutedSwatch = palette.getLightMutedSwatch();
                    darkMutedSwatch = palette.getDarkMutedSwatch();
                }
            });
        //for(int i=0;i<7;i++)
          //  liste[i] = pl.get(i).getTitleTextColor();
            SpectrumPalette spectrumPalette1 = (SpectrumPalette)findViewById(R.id.palette);
           // findViewById(R.id.bt).setBackgroundColor(liste[6]);
         //   spectrumPalette1.setColors(liste);
            spectrumPalette1.setOnColorSelectedListener(new SpectrumPalette.OnColorSelectedListener() {
                @Override
                public void onColorSelected(int color) {
                    fifi.getPaint().setColor(color);
                }
            });
    }

    private void showSavePaintingConfirmationDialog() {
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





