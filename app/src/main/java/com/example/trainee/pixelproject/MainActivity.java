package com.example.trainee.pixelproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Bitmap imag2= null;
    int n=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cdrd_image);
        imag2 = BitmapFactory.decodeResource(getResources(), R.drawable.art5);

        Bitmap pixelBMP;
        RecyclerView recyclerView = findViewById(R.id.RVImage);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gr = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gr);
        Bitmap mutable = imag2.copy(Bitmap.Config.ARGB_8888, false);

        pixelBMP = Bitmap.createScaledBitmap(mutable, mutable.getWidth()*2,
                mutable.getHeight()*2, false);
        int width = pixelBMP.getWidth();
        int height = pixelBMP.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = pixelBMP.getPixel(x, y);

                int pixelAlpha = Color.alpha(p);
                // получим цвет каждого пикселя
                int pixelRed = Color.red(p);
                int pixelGreen = Color.green(p);
                int pixelBlue = Color.blue(p);

                //calculate average
                int avg = (pixelRed + pixelGreen + pixelBlue) / 3;

                int newPixel = Color.argb(pixelAlpha, avg, avg, avg);

                //replace RGB value with avg
                pixelBMP.setPixel(x, y, newPixel);
            }
        }
        ArrayList<Bitmap> chunkedImages = splitImage(pixelBMP, 9);
        RVAdapter adapter = new RVAdapter(chunkedImages);
        recyclerView.setAdapter(adapter);

        }
    private ArrayList<Bitmap> splitImage(Bitmap bitmap, int chunkNumbers) {

        //For the number of rows and columns of the grid to be displayed
        int rows,cols;

        //For height and width of the small image chunks
        int chunkHeight,chunkWidth;

        //To store all the small image chunks in bitmap format in this list
        ArrayList<Bitmap> chunkedImages = new ArrayList<>(chunkNumbers);


        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        rows = cols = (int) Math.sqrt(chunkNumbers);
        chunkHeight = bitmap.getHeight()/rows;
        chunkWidth = bitmap.getWidth()/cols;

        int yCoord = 0;
        for(int x=0; x<rows; x++){
            int xCoord = 0;
            for(int y=0; y<cols; y++){
                chunkedImages.add(Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight));
                xCoord += chunkWidth;
            }
            yCoord += chunkHeight;
        }

        return chunkedImages;
        /* Now the chunkedImages has all the small image chunks in the form of Bitmap class.
         * You can do what ever you want with this chunkedImages as per your requirement.
         * I pass it to a new Activity to show all small chunks in a grid for demo.
         * You can get the source code of this activity from my Google Drive Account.
         */

        //Start a new activity to show these chunks into a grid
    }
}
