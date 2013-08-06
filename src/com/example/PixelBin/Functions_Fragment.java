package com.example.PixelBin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.actionbarsherlock.app.SherlockFragment;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;

import java.io.ByteArrayOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/27/13
 * Time: 12:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Functions_Fragment extends SherlockFragment {

    EditText rxyEditText;
    EditText gxyEditText;
    EditText bxyEditText;
    Button generateButton;
    RelativeLayout relLayout;
    int width;
    int height;

    public static String[] operators = {"+","-","*","/","%","&","|","^",">>","<<"}; //Main color
    public static String[] variables = {"x","y"}; //Full color
    public static String[] conditionals = {"if",">","<","==",">=","<=","&&","||"}; //Light Color
    public static String[] others = {"{","}"}; //Medium Color

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)  {
//        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
        return inflater.inflate(R.layout.functions_input, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(Build.VERSION.SDK_INT >= 16)setBackgroundImage();
        View view = getView();
        Activity mainActivity = getActivity();
        Intent thisIntent = mainActivity.getIntent();
        width = thisIntent.getIntExtra("Width",0);
        height = thisIntent.getIntExtra("Height",0);
        rxyEditText = (EditText) view.findViewById(R.id.rxy_editText_functions);
        gxyEditText = (EditText) view.findViewById(R.id.gxy_editText_functions);
        bxyEditText = (EditText) mainActivity.findViewById(R.id.bxy_editText_functions);
        generateButton = (Button) view.findViewById(R.id.generate_button_functions);
        relLayout = (RelativeLayout) view.findViewById(R.id.relLayout_functions);
        //Direct focus away from edit texts
        view.findViewById(R.id.relLayout_functions).requestFocus();

        initControls(); //change this so it does not reference the getText methods of each of the edit texts!
    }

    public void initControls()  {

        final GestureDetector gestureDetectorR = new GestureDetector(getActivity(),new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {
                rxyEditText.setText("");
                return true;
            }
        });

        final GestureDetector gestureDetectorG = new GestureDetector(getActivity(),new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {
                gxyEditText.setText("");
                return true;
            }
        });

        final GestureDetector gestureDetectorB = new GestureDetector(getActivity(),new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {
                bxyEditText.setText("");
                return true;
            }
        });

        rxyEditText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetectorR.onTouchEvent(event);
            }
        });

        gxyEditText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetectorG.onTouchEvent(event);
            }
        });

        bxyEditText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetectorB.onTouchEvent(event);
            }
        });

        rxyEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && rxyEditText.getText().toString().contains("R(x,y")) {
                    rxyEditText.setText("");
                }
            }
        });

        gxyEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && gxyEditText.getText().toString().contains("G(x,y")) {
                    gxyEditText.setText("");
                }
            }
        });

        bxyEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && bxyEditText.getText().toString().contains("B(x,y")) {
                    bxyEditText.setText("");
                }
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] formulas = {rxyEditText.getText().toString(),gxyEditText.getText().toString(),bxyEditText.getText().toString()};
                //As of right now, we will leave the alpha channel as 255 (no transparency). Maybe having A(x,y) will be a paid feature?
                new moveToImageViewer().execute(formulas); //Must do this every time you generate a new picture!
            }
        });

        EditTextHighlighter redTextHighlighter = createTextHilighterRed();
        redTextHighlighter.addSettingsToEditText(rxyEditText);

        EditTextHighlighter greenTextHighlighter = createTextHilighterGreen();
        greenTextHighlighter.addSettingsToEditText(gxyEditText);

        EditTextHighlighter blueTextHighlighter = createTextHilighterBlue();
        blueTextHighlighter.addSettingsToEditText(bxyEditText);

    }

    public void moveToImageViewer(Bitmap bmp)   {
        Intent intent = new Intent(getActivity(),PictureViewer.class);
        Picture pic = PictureViewer.currentPicture;
        intent.putExtra("Functions",pic.getFormulaR()+"@@"+pic.getFormulaG()+"@@"+pic.getFormulaB()+"@@"+pic.getWidth()+"@@"+pic.getHeight()+"@@*"); //"rfunction@@gfunction@@bfunction@@width@@height@@*"
        PictureViewer.createdPicture = bmp;   //Not sure if this will work... Lets try. It would be perfect if it did.
        startActivity(intent);
    }

    public void setBackgroundImage()  {
        if(PixelPowerGrapher.mainBackground==null) System.out.println("Even the static one was null :(");
        getView().findViewById(R.id.relLayout_functions).setBackground(PixelPowerGrapher.actionbarBackground);
    }

    protected class moveToImageViewer extends AsyncTask<String[],String,Bitmap>   {
        ProgressDialog progress;

        @Override
        protected Bitmap doInBackground(String[]... formulas) {
            String[] inputs = formulas[0];
            publishProgress("Checking Dimensions...");
            Intent intent = getActivity().getIntent();
            int width = GeneratePicture_Activity.Width;
            int height = GeneratePicture_Activity.Height;
            if(width>0 && height>0) {
                Outputer.w=width;Outputer.width=width;Outputer.Width=width;
                Outputer.h=height;Outputer.height=height;Outputer.Height=height;
                publishProgress("Analyzing Functions...");
                PictureViewer.currentPicture = new Picture(inputs[0],inputs[1],inputs[2],width,height);
                try {
                    PictureViewer.currentPicture.evaluateScripts(getActivity());
                } catch (IllegalStateException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    publishProgress("E"+e.getMessage());
                    return null;
                } catch (Throwable e)   {
                    e.printStackTrace();
                    publishProgress("EThere seems to be problem with one of your inputs");
                    return null;
                }
                publishProgress("Generating Bitmap...");
                Bitmap bitmap = null;
                try {
                bitmap = PictureViewer.currentPicture.generateBitmap();
                } catch (ArithmeticException e) {
                    publishProgress("EYou tried to divide by zero!");
                }
                return bitmap;
            } else publishProgress("Please Enter Valid Dimensions");
            return null;
        }

        @Override
        protected void onProgressUpdate(String... status)  {
            if (progress == null) {
                progress= new ProgressDialog(getActivity());
                progress.setCancelable(false);
            }
            String currentStatus = status[status.length-1];
            if(currentStatus.charAt(0)=='E')  {
                progress.dismiss();
                Crouton.makeText(getActivity(),currentStatus.substring(1), Style.ALERT).show();
            } else  {
                progress.setTitle("Generating Image");
                progress.setMessage(currentStatus);
                progress.show();

            }
        }

        @Override
        protected void onPostExecute(Bitmap img)   {
            if(img != null) {
                progress.dismiss();
                moveToImageViewer(img);
            }  else {
                progress.dismiss();
                System.out.println("Image was null yo!");
            }
        }
    }

    public static byte[] bitmapToByteArray(Bitmap img) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    /**
     * RED COLOR SCHEME
     i=0  R:255 G: 48 B:55
     i=1  R:204 G: 39 B:44
     i=2  R:153 G: 29 B:33
     i=3  R:102 G: 20 B:22
     i=4  R:51 G: 10 B:11
     i=5  R:0 G: 0 B:0
     * @return
     */
    private static EditTextHighlighter createTextHilighterRed()    {
        EditTextHighlighter redTextHighlighter = new EditTextHighlighter(0,0,0);
        redTextHighlighter.addKeysWithSingleValue(variables,255,48,55);
        redTextHighlighter.addKeysWithSingleValue(operators,204,39,44);
        redTextHighlighter.addKeysWithSingleValue(conditionals,153,29,33);
        redTextHighlighter.addKeysWithSingleValue(others,102,20,22);
        redTextHighlighter.addKeysWithSingleValue(Methods_Fragment.methodNames,51,10,11);
        return redTextHighlighter;
    }

    /**
     * GREEN COLOR SCHEME
     i=0  R:143 G: 203 B:0
     i=1  R:115 G: 163 B:0
     i=2  R:86 G: 122 B:0
     i=3  R:58 G: 82 B:0
     i=4  R:29 G: 41 B:0
     i=5  R:0 G: 0 B:0
     * @return
     */
    private static EditTextHighlighter createTextHilighterGreen()    {
        EditTextHighlighter greenTextHighlighter = new EditTextHighlighter(0,0,0);
        greenTextHighlighter.addKeysWithSingleValue(variables, 143, 203, 0);
        greenTextHighlighter.addKeysWithSingleValue(operators, 115, 163, 0);
        greenTextHighlighter.addKeysWithSingleValue(conditionals, 86, 122, 0);
        greenTextHighlighter.addKeysWithSingleValue(others, 58, 82, 0);
        greenTextHighlighter.addKeysWithSingleValue(Methods_Fragment.methodNames,29,41,0);
        return greenTextHighlighter;
    }

    /**
     * BLUE COLOR SCHEME
     i=0  R:10 G: 216 B:255
     i=1  R:8 G: 173 B:204
     i=2  R:6 G: 130 B:153
     i=3  R:4 G: 87 B:102
     i=4  R:2 G: 44 B:51
     i=5  R:0 G: 0 B:0
     * @return
     */
    private static EditTextHighlighter createTextHilighterBlue()    {
        EditTextHighlighter blueTextHighlighter = new EditTextHighlighter(0,0,0);
        blueTextHighlighter.addKeysWithSingleValue(variables, 10, 216, 255);
        blueTextHighlighter.addKeysWithSingleValue(operators, 8, 173, 204);
        blueTextHighlighter.addKeysWithSingleValue(conditionals, 6, 130, 153);
        blueTextHighlighter.addKeysWithSingleValue(others, 4, 87, 102);
        blueTextHighlighter.addKeysWithSingleValue(Methods_Fragment.methodNames,2,44,51);
        return blueTextHighlighter;
    }

}

/* Color scheme for text

RED COLOR SCHEME
i=0  R:255 G: 48 B:55
i=1  R:204 G: 39 B:44
i=2  R:153 G: 29 B:33
i=3  R:102 G: 20 B:22
i=4  R:51 G: 10 B:11
i=5  R:0 G: 0 B:0

GREEN COLOR SCHEME
i=0  R:143 G: 203 B:0
i=1  R:115 G: 163 B:0
i=2  R:86 G: 122 B:0
i=3  R:58 G: 82 B:0
i=4  R:29 G: 41 B:0
i=5  R:0 G: 0 B:0


BLUE COLOR SCHEME
i=0  R:10 G: 216 B:255
i=1  R:8 G: 173 B:204
i=2  R:6 G: 130 B:153
i=3  R:4 G: 87 B:102
i=4  R:2 G: 44 B:51
i=5  R:0 G: 0 B:0


GRAY COLOR SCHEME
i=0  R:132 G: 132 B:132
i=1  R:106 G: 106 B:106
i=2  R:80 G: 80 B:80
i=3  R:53 G: 53 B:53
i=4  R:27 G: 27 B:27
i=5  R:0 G: 0 B:0
 */
