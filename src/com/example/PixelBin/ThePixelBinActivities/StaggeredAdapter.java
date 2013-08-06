package com.example.PixelBin.ThePixelBinActivities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.PixelBin.ImageFragment;
import com.example.PixelBin.Picture;
import com.example.PixelBin.PictureViewer;
import com.example.PixelBin.R;
import com.parse.ParseObject;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;
import net.yscs.android.square_progressbar.SquareProgressBar;

import java.util.List;

public class StaggeredAdapter extends ArrayAdapter<ParseObject> {
    List<ParseObject> pictures;
    Context contxt;
    Animation fadein;
    Animation fadeout;
    Bitmap tempBmp;
    Activity activity;
    int viewWidth;
    int viewHeight;
    boolean[] wasFadeds;
    boolean[] doneLoading;
    Picture[] picObjects;
    View[] savedViews;
    public StaggeredAdapter(Context context, int textViewResourceId, List<ParseObject> objects,Activity actvty) {
        super(context, textViewResourceId, objects);
        this.contxt = context;
        this.activity =actvty;
        this.pictures = objects;
        fadein = AnimationUtils.loadAnimation(context, R.anim.fadein);
        fadein.setFillAfter(true);
        fadeout = AnimationUtils.loadAnimation(context, R.anim.fadeout);
        fadeout.setFillAfter(true);
        wasFadeds = new boolean[objects.size()];
        doneLoading = new boolean[wasFadeds.length];
        picObjects = new Picture[wasFadeds.length];
        savedViews = new View[wasFadeds.length];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(savedViews[position]!=null) return savedViews[position];
        if(position==0) {
            viewWidth = parent.getWidth()/2;
            viewHeight = parent.getHeight()/(int)Math.ceil((float)this.pictures.size()/2f);
            tempBmp = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_4444);
            tempBmp.setHasAlpha(false);
        }
        LayoutInflater layoutInflator = LayoutInflater.from(getContext());
        View view = layoutInflator.inflate(R.layout.loading_image,null);
        SquareProgressBar bar = (SquareProgressBar) view.findViewById(R.id.imageHolder_SquareProgressBar_staggeredLoaders);
        bar.setImage(tempBmp);
        bar.setWidth(20); //tweak this to perfection. MUST CALL setImage(...) BEFORE THIS TO INSTANTIATE IMAGE VIEW. 8 was ok...experimenting
        Picture pic = this.initializeViews(view,pictures.get(position),position);
        int colorMod = position%3;
        if(colorMod==0)new startGeneratingBitmap().execute(new Holder(position,pic,bar,viewWidth,viewHeight+(int)Math.round(Math.random()*60f),255,48,55,view)); //progress bar is red
        else if(colorMod==1) new startGeneratingBitmap().execute(new Holder(position,pic,bar,viewWidth,viewHeight+(int)Math.round(Math.random()*60f),143,203,0,view)); //progress bar is green
        else new startGeneratingBitmap().execute(new Holder(position,pic,bar,viewWidth,viewHeight+(int)Math.round(Math.random()*60f),10,216,255,view)); //progress bar is blue
        savedViews[position] = view;
        convertView = view;
        return view;
    }

    public ParseObject getParseObjectAt(int i)    {
        return this.pictures.get(i);
    }

    public Picture initializeViews(View view,ParseObject pObject,final int position)    {
        final Picture pic = new Picture(pObject);
        picObjects[position] = pic;
        TextView rTextView = (TextView) view.findViewById(R.id.rfunction_textView_staggeredLoaders);
        rTextView.setText(pic.getFormulaR());
        TextView gTextView = (TextView) view.findViewById(R.id.gfunction_textView_staggeredLoaders);
        gTextView.setText(pic.getFormulaG());
        TextView  bTextView = (TextView) view.findViewById(R.id.bfunction_textView_staggeredLoaders);
        bTextView.setText(pic.getFormulaB());
        TextView authorTextView = (TextView) view.findViewById(R.id.author_textView_staggeredLoader);
        authorTextView.setText("By "+pic.getAuthor());
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.textViewsHolder_LinearLayout_staggeredLoaders);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(wasFadeds[position])    {
                    linearLayout.startAnimation(fadein);
                    wasFadeds[position] = false;
                } else {
                    linearLayout.startAnimation(fadeout);
                    wasFadeds[position] = true;
                }
                return true;
            }
        });
        return pic;
    }

    public Picture getPictureObjectAt(int position) {
        return picObjects[position];
    }

    protected class startGeneratingBitmap extends AsyncTask<Holder,Float,Bitmap> {
        SquareProgressBar squareProgressView;
        int position;
        Picture pic;
        View view;
        @Override
        protected Bitmap doInBackground(Holder... params) {
            pic = params[0].pic;
            squareProgressView = params[0].progressView;
            position = params[0].position;
            final int Width = params[0].width;
            final int Height = params[0].height;
            view = params[0].view;
            try {
                pic.evaluateScripts(contxt);
            } catch (Throwable throwable) {
                throwable.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                return null;
            }
            final Bitmap img = Bitmap.createBitmap(Width,Height, Bitmap.Config.ARGB_8888);
            Thread looper = new Thread()    {
             public void run() {
                 for(int x=0;x<Width;x++)    {
                    for(int y=0;y<Height;y++)   {
                        int Y = Height-1-y;
                        img.setPixel(x,y,pic.getPixelAt(x, Y));
                    }
                    publishProgress((float)x/((float)Width-1));
                 }
             }
            };
            looper.start();
            img.setHasAlpha(false);
            return img;
        }

        @Override
        protected void onProgressUpdate(Float...progress) {
            float prog = progress[progress.length-1];
            squareProgressView.setProgress(100*prog);
        }

        @Override
        protected void onPostExecute(Bitmap img)    {
            if(img!=null) {
                squareProgressView.setImage(img);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new moveToImageViewer().execute(new Holder(position,pic));
                    }
                });
            }
        }
    }

    protected class moveToImageViewer extends AsyncTask<Holder,String,Bitmap> {
        ProgressDialog progress;
        Holder holder;
        @Override
        protected Bitmap doInBackground(Holder... holders) {
            holder = holders[0];
            Picture pic = holder.pic;
            if(pic.getWidth()>0 && pic.getHeight()>0) {
                PictureViewer.currentPicture = pic;
                publishProgress("Generating Bitmap...");
                Bitmap bitmap = null;
                try {
                    bitmap = PictureViewer.currentPicture.generateBitmap();
                } catch (ArithmeticException e) {
                    publishProgress("EYou tried to divide by zero!");
                }
                PictureViewer.createdPicture = bitmap;
                return bitmap;
            } else publishProgress("The Dimensions of this Picture are not valid");
            return null;
        }

        @Override
        protected void onProgressUpdate(String... status)  {
            if (progress == null) {
                progress= new ProgressDialog(contxt);
                progress.setCancelable(false);
            }
            String currentStatus = status[status.length-1];
            if(currentStatus.charAt(0)=='E')  {
                progress.dismiss();
                Crouton.makeText(activity, currentStatus.substring(1), Style.ALERT).show();
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
                Picture pic = holder.pic;
                Intent intent  = new Intent(contxt,PictureViewer.class);
                intent.putExtra("Functions",pic.getFormulaR()+"@@"+pic.getFormulaG()+"@@"+pic.getFormulaB()+"@@"+pic.getWidth()+"@@"+pic.getHeight()+"@@^");
                contxt.startActivity(intent);
            }  else {
                progress.dismiss();
                System.out.println("Image was null yo!");
            }
        }
    }

    private class Holder    {
        Picture pic;
        SquareProgressBar progressView;
        int width;
        int height;
        int position;
        View view;
        public Holder(int pos,Picture p, SquareProgressBar s,int w,int h,int rColor,int gColor, int bColor,View v)  {
            this.position = pos;
            this.pic = p;
            this.progressView = s;
            this.progressView.setColorRGB(rColor,gColor,bColor);
            this.width=w;
            this.height=h;
            this.view = v;
        }

        public Holder(int pos,Picture picture)  {
            this.pic = picture;
            this.position = pos;
        }
    }

}

/*
RED COLOR SCHEME
R:255 G: 48 B:55

GREEN COLOR SCHEME
R:143 G: 203 B:0

BLUE COLOR SCHEME
R:10 G: 216 B:255

*/