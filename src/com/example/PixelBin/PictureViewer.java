package com.example.PixelBin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;
import uk.co.senab.photoview.PhotoViewAttacher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 7/1/13
 * Time: 5:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class PictureViewer extends Activity {
    ImageView imageView;
    ImageButton saveButton;
    private PhotoViewAttacher mAttacher;
    boolean willBeVisibleNext;
    Context context;
    TextView positionTextView;
    TextView colorTextView;
    Animation fadein;
    Animation fadeout;
    public static Picture currentPicture =null;

    public static Bitmap createdPicture = null;

    @Override
    public void onCreate(Bundle savedInstance)  {
        super.onCreate(savedInstance);
        setContentView(R.layout.pictureviewer);
        willBeVisibleNext = true;
        context = this;
        fadein = AnimationUtils.loadAnimation(this,R.anim.fadein);
        fadeout = AnimationUtils.loadAnimation(this,R.anim.fadeout);
        fadein.setFillAfter(true);
        fadeout.setFillAfter(true);

        Bitmap img = createdPicture;
        img.setHasAlpha(false);
        if(img==null) Log.d("Error yo!","The bitmap from the intent was null");
        imageView = (ImageView) findViewById(R.id.imageView_pictureviewer);
        saveButton = (ImageButton) findViewById(R.id.save_imageButton_pictureviewer);
        positionTextView = (TextView) findViewById(R.id.position_textView_pictureviewer);
        colorTextView = (TextView) findViewById(R.id.color_textView_pictureviewer);
        positionTextView.setVisibility(View.INVISIBLE);
        colorTextView.setVisibility(View.INVISIBLE);
        saveButton.setClickable(false);
        saveButton.clearAnimation();
        saveButton.startAnimation(fadeout);
        imageView.setImageBitmap(img);

        positionTextView.setAlpha(127f);
        colorTextView.setAlpha(127f);

        mAttacher = new PhotoViewAttacher(imageView);
        mAttacher.setOnMatrixChangeListener(new MatrixChangeListener());
        mAttacher.setOnPhotoTapListener(new PhotoTapListener());


        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(willBeVisibleNext)   {
                    positionTextView.setVisibility(View.VISIBLE);
                    colorTextView.setVisibility(View.VISIBLE);
                    saveButton.setClickable(true);
                    saveButton.clearAnimation();
                    saveButton.startAnimation(fadein);
                    willBeVisibleNext = false;
                } else {
                    saveButton.setClickable(false);
                    saveButton.clearAnimation();
                    saveButton.startAnimation(fadeout);
                    positionTextView.setVisibility(View.INVISIBLE);
                    colorTextView.setVisibility(View.INVISIBLE);
                    willBeVisibleNext = true;
                }
                return true;
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new saveBitmapImage().execute(createdPicture!=null?createdPicture:((BitmapDrawable) imageView.getDrawable()).getBitmap());
            }
        });

    }

    private class PhotoTapListener implements PhotoViewAttacher.OnPhotoTapListener {

        @Override
        public void onPhotoTap(View view, float x, float y) {
            if(view instanceof ImageView)   {
                int X = Math.round(x*createdPicture.getWidth());
                int yInverted = Math.round(y*createdPicture.getHeight());
                int Y = createdPicture.getHeight()-yInverted;
                int color = createdPicture.getPixel(X,yInverted);
                positionTextView.setText("("+X+","+Y+")");
                int r = RGBUtilities.toRed(color);
                int g = RGBUtilities.toGreen(color);
                int b = RGBUtilities.toBlue(color);
                colorTextView.setText(r+","+g+","+b);
                int colorInvert = RGBUtilities.toRGB(255-r,255-g,255-b);
                colorTextView.setTextColor(colorInvert);
                positionTextView.setTextColor(colorInvert);
                colorTextView.setBackgroundColor(Color.argb(127,r,g,b));
                positionTextView.setBackgroundColor(Color.argb(127,r,g,b));
            }
        }
    }

    private class MatrixChangeListener implements PhotoViewAttacher.OnMatrixChangedListener {

        @Override
        public void onMatrixChanged(RectF rect) {

        }
    }

    protected class saveBitmapImage extends AsyncTask<Bitmap,String,Boolean> {
        ProgressDialog progressDialog;
        @Override
        protected Boolean doInBackground(Bitmap... params) {
            String TAG = "Saving yo!";
            publishProgress("Obtaining Directory...");
            File imgFile = null;
            File functionFile = null;
            try {
                File[] files = getOutputMediaFile();
                imgFile = files[0];
                functionFile = files[1];
            } catch (IOException e) {
                Log.d(TAG,e.getMessage());
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                return false;
            }
            if (imgFile == null) {
                Log.d(TAG,
                        "Error creating media file, check storage permissions: ");// e.getMessage());
                return false;
            }
            try {
                FileOutputStream fos = new FileOutputStream(imgFile);
                publishProgress("Compressing..");
                params[0].compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
            } catch (FileNotFoundException e) { //This exception is thrown
                Log.d(TAG, "File not found: " + e.getMessage());
                return false;
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
                return false;
            }

            try {
                FileOutputStream fos = new FileOutputStream(functionFile);
                String pictureInfo = getIntent().getStringExtra("Functions");//"rfunction@@gfunction@@bfunction@@width@@height@@*"   @@* means it has not been uploaded to the pixelbin yet...@@^ means it has
                fos.write(pictureInfo.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                return false;
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                return false;
            }

            return true;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        protected void onProgressUpdate(String...updates)   {
            if(progressDialog==null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setTitle("Saving Image");
                progressDialog.show();
            }
            String currentUpdate = updates[updates.length-1];
            progressDialog.setMessage(currentUpdate);

        }

        @Override
        protected void onPostExecute(Boolean saved)    {
            if(saved)   {
                progressDialog.setMessage("Saved Image");
            } else {
                progressDialog.setMessage("Save was Unsuccessful");
            }
            Thread timer = new Thread() {
                public void run()   {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } finally {
                        progressDialog.dismiss();
                    }
                }
            };
            timer.start();
        }
    }

    /** Create a File for saving an image or video */
    private  File[] getOutputMediaFile() throws IOException {
        File external = Environment.getExternalStorageDirectory();
        File pixelBinFolder = new File(external,"/PixelBin");

        if(!pixelBinFolder.exists())    {
            pixelBinFolder.mkdir();
        }
        File imagesFolder = new File(pixelBinFolder,"/Saved Images");
        if(!imagesFolder.exists())  {
            imagesFolder.mkdir();
        }


        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss");
        String currentDateAndTime = sdf.format(new Date()).replace(" ","");
        File imgFile = new File(imagesFolder,"/"+currentDateAndTime+".png");
        File functionFile = new File(imagesFolder,"/"+currentDateAndTime+".txt");
        imgFile.createNewFile();
        functionFile.createNewFile();
        return new File[] {imgFile,functionFile};
    }
}
