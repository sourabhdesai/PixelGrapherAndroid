package com.example.PixelBin;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 7/9/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditTextHighlighter {
    private SettingsDatabase database;
    private int defaultColor;
    int spaceToRight;
    int endOfWordLeft;
    /**
     * Initialize a new EditTextHighlighter object with three integer parameters that indicate the RGB color component values for the default color
     * @param rDefault the R component of the default text color
     * @param gDefault the G component of the default text color
     * @param bDefault the B component of the default text color
     */
    public EditTextHighlighter(int rDefault, int gDefault, int bDefault) {
        this.defaultColor = Color.rgb(rDefault,gDefault,bDefault);
        this.database = new SettingsDatabase("",this.defaultColor);
    }

    /**
     * Initialize a new EditTextHighlighter object with a single integer parameter that indicates the number of
     * @param color the default color of the text as expressed as a single integer value.
     */
    public EditTextHighlighter(int color) {
        this.defaultColor = color;
        this.database = new SettingsDatabase("",color);

    }

    private EditTextHighlighter() {}

    /**
     * Adds a keyword along with its highlight color defined in its RGB color components.
     * @param keyword
     * @param r
     * @param g
     * @param b
     *
     */
    public void addKeyWord(String keyword,int r, int g, int b) {
        int color = Color.rgb(r,g,b);

    }

    /**
     * Adds a keyword along with its highlight color defined as a single integer value.
     * @param keyword
     * @param color
     */
    public void addKeyWord(String keyword,int color) {
         this.database.addColoredWord(keyword,color);
    }

    /**
     *
     * @param key
     * @return color of keyword as set earlier
     */
    public int getColor(String key)    {
        int color = this.database.getColor(key);
        return color == -1?this.defaultColor:color;
    }

    /**
     * Add an array of keywords that all have the same color which is expressed in its RGB components
     * @param keys
     * @param r
     * @param g
     * @param b
     */
    public void addKeysWithSingleValue(String[] keys, int r, int g, int b)   {
        this.addKeysWithSingleValue(keys,Color.rgb(r,g,b));
    }

    /**
     * Add an array of keywords that all have the same color which is expressed as a single integer value
     * @param keys
     * @param color
     */
    public void addKeysWithSingleValue(String[] keys, int color)   {
        this.database.addWordsOfSameColor(keys,color);
    }

    public void addSettingsToEditText(EditText editText1)   {
        final EditText editText = editText1;
        final EditTextHighlighter highlighter = this;
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //To change body of implemented methods use File | Settings | File Templates.

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //before: length of text before text was changed
                //start: start index of changed text
                //count: length of new text
                System.out.println(s+"\n yo!");
                System.out.println("Start: "+start+"\nBefore: "+before+"\nCount: "+count+"\n yo!");
                if(s.length()>0)    {
                    String text = s.toString();
                    Editable editable = editText.getText();
                    //TODO: Make the text highlight to the right color when a keyword is written
                    if(start>=before)    { //new character(s) at the end of the text
                        for(int i = start;i>=0;i--)  {
                            if(s.charAt(i)== ' ')   {
                                String word = text.substring(i);
                                int color = highlighter.getColor(word);
                                editable.setSpan(new ForegroundColorSpan(color),i,i+count,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                editText.setText(editable);
                                break;
                            }
                        }

                    } else  { //new character(s) in the middle of the text somewhere
                        int left = 0;
                        int right = 0;
                        for(int i=start;i>=0;i--)   {
                            if(s.charAt(i)==' ') {
                                left = i;
                                break;
                            }
                        }

                        for(int i=start;i<s.length();i++)   {
                            if(s.charAt(i)==' ') {
                                right = i;
                                break;
                            }
                        }
                        String word = s.subSequence(left,right).toString();
                        int color = highlighter.getColor(word);
                        editable.setSpan(new ForegroundColorSpan(color),left,right,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editText.addTextChangedListener(textWatcher);
    }

}
