package com.example.PixelBin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
        getActivity().setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
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

        initControls(); //change this so it does not reference the getText methods of each of the edit texts!
    }

    public void initControls()  {

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
                String[] formulas = {rxyEditText.getText().toString(),gxyEditText.getText().toString(),bxyEditText.getText().toString(),255+""};
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
        PixelPowerGrapher.createdPicture = bmp;   //Not sure if this will work... Lets try. It would be perfect if it did.
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
            int width = intent.getIntExtra("Width",0);
            int height = intent.getIntExtra("Height",0);
            if(width>0 && height>0) {
                Outputer.w=width;Outputer.width=width;Outputer.Width=width;
                Outputer.h=height;Outputer.height=height;Outputer.Height=height;
                publishProgress("Analyzing Functions...");
                PixelPowerGrapher.currentPicture = new Picture(inputs[0],inputs[2],inputs[3],width,height);
                try {
                    PixelPowerGrapher.currentPicture.evaluateScripts(getActivity());
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    publishProgress("E");
                }
                publishProgress("Generating Bitmap...");
                Bitmap bitmap = PixelPowerGrapher.currentPicture.generateBitmap();
                return bitmap;
            } else publishProgress("Please Enter Valid Dimensions");
            return null;
        }

        @Override
        protected void onProgressUpdate(String... status)  {
            if (progress == null) {
                progress= new ProgressDialog(getActivity());
            //    progress.setMax(100);
            }
            String currentStatus = status[status.length-1];
            if(currentStatus.equals("E"))  {
                progress.dismiss();
                Crouton.makeText(getActivity(),"There seems to be a Syntax Error with One or More of your Inputs", Style.ALERT).show();
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
            }  else System.out.println("Image was null");
        }
    }

    public static byte[] bitmapToByteArray(Bitmap img) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private static EditTextHighlighter createTextHilighterRed()    {
        EditTextHighlighter redTextHighlighter = new EditTextHighlighter(255,255,255);
        redTextHighlighter.addKeysWithSingleValue(variables,255,0,0);
        redTextHighlighter.addKeysWithSingleValue(operators,255,48,55);
        redTextHighlighter.addKeysWithSingleValue(conditionals,255,127,133);
        redTextHighlighter.addKeysWithSingleValue(others,255,230,230);
        return redTextHighlighter;
    }

    private static EditTextHighlighter createTextHilighterGreen()    {
        EditTextHighlighter greenTextHighlighter = new EditTextHighlighter(255,255,255);
        greenTextHighlighter.addKeysWithSingleValue(variables, 0, 255, 0);
        greenTextHighlighter.addKeysWithSingleValue(operators, 143, 203, 0);
        greenTextHighlighter.addKeysWithSingleValue(conditionals, 174, 203, 151);
        greenTextHighlighter.addKeysWithSingleValue(others, 230, 255, 230);
        return greenTextHighlighter;
    }

    private static EditTextHighlighter createTextHilighterBlue()    {
        EditTextHighlighter blueTextHighlighter = new EditTextHighlighter(255,255,255);
        blueTextHighlighter.addKeysWithSingleValue(variables, 0, 0, 255);
        blueTextHighlighter.addKeysWithSingleValue(operators, 10, 216, 255);
        blueTextHighlighter.addKeysWithSingleValue(conditionals, 144, 229, 255);
        blueTextHighlighter.addKeysWithSingleValue(others, 230, 230, 255);
        return blueTextHighlighter;
    }

}

/* Color scheme for text

RED COLOR SCHEME
Red: 255,48,55
Medium Red: 255,88,94
Light Red: 255,127,133
Redish White : 255,230,230

GREEN COLOR SCHEME
Green: 143,203,0
Light Green: 174,203,151
Greenish White : 230,255,230


BLUE COLOR SCHEME
Blue: 10,216,255
Light Blue: 144,229,255
Bluish White : 230,230,255


GRAY COLOR SCHEME
Gray: 132,132,132
Light Gray: 217,217,217
Grayish White: 240,240,240
 */
