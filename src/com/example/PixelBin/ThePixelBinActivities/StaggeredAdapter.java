package com.example.PixelBin.ThePixelBinActivities;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.PixelBin.Picture;
import com.example.PixelBin.R;
import com.origamilabs.library.views.StaggeredGridView;
import com.parse.ParseObject;
import net.yscs.android.square_progressbar.SquareProgressBar;
import net.yscs.android.square_progressbar.SquareProgressView;

import java.util.List;

public class StaggeredAdapter extends ArrayAdapter<ParseObject> {
    List<ParseObject> pictures;
    Context contxt;
    Animation fadein;
    Animation fadeout;
    public StaggeredAdapter(Context context, int textViewResourceId, List<ParseObject> objects) {
        super(context, textViewResourceId, objects);
        this.contxt = context;
        fadein = AnimationUtils.loadAnimation(context, R.anim.fadein);
        fadeout = AnimationUtils.loadAnimation(context, R.anim.fadeout);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflator = LayoutInflater.from(getContext());
        View view = layoutInflator.inflate(R.layout.loading_image,parent);
        SquareProgressBar bar = (SquareProgressBar) view.findViewById(R.id.imageHolder_SquareProgressBar_staggeredLoaders);
        Picture pic = this.initializeViews(view,pictures.get(position));
        new startGeneratingBitmap().execute(new Holder(pic,bar,view.getWidth(),view.getHeight()));
        return view;
    }

    public ParseObject getParseObjectAt(int i)    {
        return this.pictures.get(i);
    }

    public Picture initializeViews(View view,ParseObject pObject)    {
        Picture pic = new Picture(pObject);
        TextView rTextView = (TextView) view.findViewById(R.id.rfunction_textView_staggeredLoaders);
        rTextView.setText(pic.getFormulaR());
        TextView gTextView = (TextView) view.findViewById(R.id.gfunction_textView_staggeredLoaders);
        gTextView.setText(pic.getFormulaG());
        TextView  bTextView = (TextView) view.findViewById(R.id.bfunction_textView_staggeredLoaders);
        bTextView.setText(pic.getFormulaB());
        TextView authorTextView = (TextView) view.findViewById(R.id.author_textView_staggeredLoader);
        authorTextView.setText(pic.getAuthor());
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.textViewsHolder_LinearLayout_staggeredLoaders);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(linearLayout.getAlpha()<0.5f)    {
                    linearLayout.startAnimation(fadein);
                } else linearLayout.startAnimation(fadeout);
                return true;
            }
        });
        linearLayout.startAnimation(fadeout);
        return pic;
    }

    protected class startGeneratingBitmap extends AsyncTask<Holder,Float,Bitmap> {
        SquareProgressBar squareProgressView;
        @Override
        protected Bitmap doInBackground(Holder... params) {
            Picture pic = params[0].pic;
            squareProgressView = params[0].progressView;
            int Width = params[0].width;
            int Height = params[0].height;
            try {
                pic.evaluateScripts(contxt);
            } catch (Throwable throwable) {
                throwable.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                return null;
            }
            Bitmap img = Bitmap.createBitmap(Width,Height, Bitmap.Config.ARGB_8888);
            for(int x=0;x<Width;x++)    {
                publishProgress((float)x/(float)Width);
                for(int y=0;y<Height;y++)   {
                    int Y = Height-1-y;
                    int mappedX =Math.round(x/Width)*pic.getWidth();
                    int mappedY = Math.round(Y/Height)*pic.getHeight();
                    img.setPixel(mappedX,mappedY,pic.getOutputAt(mappedX,mappedY));
                }
            }
            return img;
        }

        @Override
        protected void onProgressUpdate(Float...progress) {
            float prog = progress[progress.length-1];
            squareProgressView.setProgress(100*prog);
        }

        @Override
        protected void onPostExecute(Bitmap img)    {

        }
    }

    private class Holder    {
        Picture pic;
        SquareProgressBar progressView;
        int width;
        int height;
        public Holder(Picture p, SquareProgressBar s,int w,int h)  {
            this.pic = p;
            this.progressView = s;
            this.width=w;
            this.height=h;
        }
    }
}