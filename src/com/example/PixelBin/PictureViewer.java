package com.example.PixelBin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 7/1/13
 * Time: 5:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class PictureViewer extends Activity {
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstance)  {
        super.onCreate(savedInstance);
        setContentView(R.layout.pictureviewer);
    //    byte[] imgBytes = getIntent().getByteArrayExtra("Image");
        Bitmap img = PixelPowerGrapher.createdPicture;
        if(img==null) System.out.println("The bitmap from the intent was null");
        imageView = (ImageView) findViewById(R.id.imageView_pictureviewer);
        imageView.setImageBitmap(img != null? img : PixelPowerGrapher.createdPicture);
    }

    public static Bitmap byteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}
