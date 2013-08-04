package com.example.PixelBin;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.parse.ParseObject;
import com.parse.ParseUser;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 7/31/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class SavedFunctionsActivity extends ExpandableListActivity implements ExpandableListView.OnChildClickListener{
    File[] pictureFiles = null;
    MyExpandableListAdapter listAdapter;
    boolean isLoggedIn;
    ParseUser user;
    Animation fadeout;
    Activity thisActivity;
    @Override
    public void onCreate(Bundle savedInstance)  {
        super.onCreate(savedInstance);
        pictureFiles = getPictureFiles();
        user = ParseUser.getCurrentUser();
        isLoggedIn = user != null;
        thisActivity = this;
        fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        fadeout.setFillAfter(true);
        if(pictureFiles.length>0) {
            String[] fileNames = new String[pictureFiles.length];
            for(int i=0;i<pictureFiles.length;i++)    {
                String fullname = pictureFiles[i].getName();
                fileNames[i] = fullname.substring(0,fullname.indexOf(".txt"));
            }

            //"rfunction@@gfunction@@bfunction@@width@@height@@*"   @@* means it has not been uploaded to the pixelbin yet...@@^ means it has
            String[][] pictureInfos = new String[fileNames.length][6];
            for(int i=0;i<pictureInfos.length;i++)  {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(pictureFiles[i]);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                StringBuffer fileContent = new StringBuffer("");

                byte[] buffer = new byte[1024];
                try {
                    while (fis.read(buffer) != -1) {
                        fileContent.append(new String(buffer));
                    }
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                String[] picInfo = fileContent.toString().split("@@");
                String[] picInfoAdjusted=null;
                if(picInfo[5].contains("*")) {
                    picInfoAdjusted = new String[5];
                    for(int a=0;a<5;a++)    {
                        picInfoAdjusted[a]=picInfo[a];
                    }
                } else {
                    picInfo[5] = "Uploaded";
                    picInfoAdjusted=picInfo;
                }
                pictureInfos[i]=picInfoAdjusted;
            }
            listAdapter = new MyExpandableListAdapter();
            listAdapter.setGroups(fileNames);
            listAdapter.setChildren(pictureInfos);
            setListAdapter(listAdapter);
        }
        setContentView(R.layout.saved_functions);
    }

    public static File[] getPictureFiles()  {
        File external = Environment.getExternalStorageDirectory();
        File pixelBinFolder = new File(external,"/PixelBin");

        if(!pixelBinFolder.exists())    {
            pixelBinFolder.mkdir();
            return new File[0];
        }
        File imagesFolder = new File(pixelBinFolder,"/Saved Images");
        if(!imagesFolder.exists())  {
            imagesFolder.mkdir();
            return new File[0];
        }
        FilenameFilter txtFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".txt");
            }
        };
        return imagesFolder.listFiles(txtFilter);
    }

    /**
     * A simple adapter which maintains an ArrayList of photo resource Ids.
     * Each photo is displayed as an image. This adapter supports clearing the
     * list of photos and adding a new photo.
     *
     */
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {
        // Sample data set.  children[i] contains the children (String[]) for groups[i].
        private String[] groups = { "People Names", "Dog Names", "Cat Names", "Fish Names" };
        private String[][] children = {
                { "Arnold", "Barry", "Chuck", "David" },
                { "Ace", "Bandit", "Cha-Cha", "Deuce" },
                { "Fluffy", "Snuggles" },
                { "Goldy", "Bubbles" }
        };
        private boolean[] isFaded = new boolean[groups.length];

        public void setGroups(String[] g)   {
            this.groups = g;
            this.isFaded = new boolean[g.length];
        }
        public void setChildren(String[][] childs)  {
            this.children = childs;
        }

        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 64);

            TextView textView = new TextView(SavedFunctionsActivity.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            textView.setPadding(36, 0, 0, 0);
            textView.setTextColor(Color.rgb(0,0,0));
            return textView;
        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            textView.setTextColor(Color.rgb(0,0,0));
            return textView;
        }

        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        public int getGroupCount() {
            return groups.length;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                                 ViewGroup parent) {
            final int groupPos = groupPosition;
            final View view = getLayoutInflater().inflate(R.layout.group_row,null);
            final TextView textView = (TextView) view.findViewById(R.id.row_name);
            final TextView deleteTextView = (TextView) view.findViewById(R.id.deleteTxtView);
            final TextView uploadTxtView = (TextView) view.findViewById(R.id.uploadTextView);
            if(children[groupPosition].length==6) {
                if(isFaded[groupPosition]) uploadTxtView.setAlpha(0.1f);
                else {
                    uploadTxtView.startAnimation(fadeout);  //Means its been uploaded already
                    isFaded[groupPosition] = true;
                }
            }
            else {
                uploadTxtView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] child = children[groupPos];
                        if(uploadPicture(new Picture(child[0],child[1],child[2],Integer.parseInt(child[3]),Integer.parseInt(child[4])))) {
                            FileOutputStream fos = null;
                            try {
                                fos = new FileOutputStream(pictureFiles[groupPos]);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                            String pictureInfo =child[0]+"@@"+child[1]+"@@"+child[2]+"@@"+child[3]+"@@"+child[4]+"@@^";//"rfunction@@gfunction@@bfunction@@width@@height@@*"   @@* means it has not been uploaded to the pixelbin yet...@@^ means it has
                            String[] newChild = new String[6];
                            for(int i=0;i<5;i++)    {
                                newChild[i] = children[groupPos][i];
                            }
                            newChild[5] = "Uploaded";
                            children[groupPos] = newChild;
                            listAdapter.notifyDataSetChanged();
                            try {
                                fos.write(pictureInfo.getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                            Crouton.makeText(thisActivity,"Upload was Successful",Style.CONFIRM).show();
                        } else {
                            Crouton.makeText(thisActivity,"Upload was Unsuccessful",Style.ALERT).show();
                        }

                    }
                });

            }
            textView.setHeight(deleteTextView.getHeight());
            textView.setText(getGroup(groupPosition).toString());
            deleteTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String deletedGroup = groups[groupPos];
                    removeGroup(groupPos);
                    Crouton.makeText(thisActivity,"Deleted "+deletedGroup, Style.CONFIRM).show();
                }
            });
            return view;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public boolean hasStableIds() {
            return true;
        }

    }

    public void removeGroup(int groupPosition)   {
        File[] newPicFiles = new File[pictureFiles.length-1];
        pictureFiles[groupPosition].delete();
        for(int i=0,a=0;i<newPicFiles.length;i++)  {
            if(a!=groupPosition)    {
                newPicFiles[i] = pictureFiles[a];
            } else i--;
            a++;
        }
        String[] newGroups = new String[listAdapter.groups.length-1];
        String[][] newChildren = new String[listAdapter.children.length-1][];
        boolean[] newIsFadeds = new boolean[listAdapter.isFaded.length-1];
        for(int i=0,a=0;i<newPicFiles.length;i++)   {
            if(a!=groupPosition)    {
                newGroups[i]=listAdapter.groups[a];
                newChildren[i] = listAdapter.children[a];
                newIsFadeds[i] = listAdapter.isFaded[a];
            } else i--;
            a++;
        }
        this.listAdapter.groups = newGroups;
        this.listAdapter.children = newChildren;
        this.listAdapter.isFaded = newIsFadeds;
        this.pictureFiles = newPicFiles;
        this.listAdapter.notifyDataSetChanged();
    }

    /**
     *
     * @param pic
     * @return true if upload was successful, false otherwise
     */
    public static boolean uploadPicture(Picture pic)    {
        ParseUser user = ParseUser.getCurrentUser();
        if(user==null) return false;
        ParseObject pictureObject = new ParseObject("Pictures");
        pictureObject.put("RFunction",pic.getFormulaR());
        pictureObject.put("GFunction",pic.getFormulaG());
        pictureObject.put("BFunction",pic.getFormulaB());
        pictureObject.put("Width",pic.getWidth());
        pictureObject.put("Height",pic.getHeight());
        pictureObject.put("User",user.getUsername());
        pictureObject.put("Upvotes",0);
        pictureObject.put("CurrentTimeMillis",System.currentTimeMillis());
        pictureObject.saveInBackground();
        return true;
    }

}
