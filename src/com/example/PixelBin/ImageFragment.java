package com.example.PixelBin;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 8/2/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImageFragment extends Fragment {
    ImageView imgView;
    TextView header;
    TextView footer;
    String headerStr;
    String footerStr;
    Drawable img;
    Resources res;
    View.OnClickListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)  {
//        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
        return inflater.inflate(R.layout.image_fragment, container, false);
    }

    public ImageFragment(Resources resources)   {
        this.res = resources;
    }

    public ImageFragment()  {}

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        imgView = (ImageView) view.findViewById(R.id.imageView_imagefragment);
        header = (TextView) view.findViewById(R.id.header_textView_imagefragment);
        footer = (TextView)view.findViewById(R.id.footer_textView_imageFragment);
        if(this.img!=null) imgView.setImageDrawable(this.img);
        if(this.listener != null) imgView.setOnClickListener(listener);
        if(this.headerStr!=null) header.setText(this.headerStr);
        if(this.footerStr!=null) footer.setText(this.footerStr);
    }

    public void setImage(Drawable img)  {
        this.img=img;
    }

    public void setImage(int resource)  {
        setImage(res.getDrawable(resource));
    }

    public void setHeader(String header)    {
        headerStr=header;
    }

    public void setFooter(String footer)    {
        footerStr=footer;
    }

    public void setOnImageClickListener(View.OnClickListener listener)  {
        this.listener = listener;
    }

}
