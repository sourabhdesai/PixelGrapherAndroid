import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.RelativeLayout;
import com.example.PixelBin.Home_Activity;
import com.example.PixelBin.PixelPowerGrapher;
import com.example.PixelBin.R;
import com.example.PixelBin.Registration_Activity;
import com.parse.Parse;
import com.parse.ParseUser;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/25/13
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class SplashScreen extends Activity {
    public static int galaxyS4Width = 1080;
    @Override
    public void onCreate(Bundle splashScreen) {
        super.onCreate(splashScreen);
        setContentView(R.layout.main);

        //gets screen dimensions and sets them to screenWidth and screenHeight variables
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        if(PixelPowerGrapher.screenSize == null) PixelPowerGrapher.screenSize = new int[2];
        PixelPowerGrapher.screenSize[0]=size.x;
        PixelPowerGrapher.screenSize[1]=size.y;

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutSplashScreen);
        if(PixelPowerGrapher.screenSize[0]> galaxyS4Width )   {
            relativeLayout.setBackgroundResource(R.drawable.funky_chalkboard);
        } else {
            relativeLayout.setBackgroundResource(R.drawable.funky_chalkboard_cropped);
        }

        ParseUser user = null;
        try {
            user = ParseUser.getCurrentUser();


        } catch(Exception e) {
            e.printStackTrace();
        }   finally {
            if(user == null)    {
                //Means user has not logged in, in the past. So move to registration screen
                Intent registrationScreen= new Intent (getApplicationContext(), Registration_Activity.class);
                startActivity(registrationScreen);
                finish();
            }  else {
               moveToHomeScreen();
            }
        }
    }

    public void moveToHomeScreen()    {
        //Code to move to home screen
        Intent intent = new Intent(this,Home_Activity.class);
        startActivity(intent);
        this.finish();
    }

}
