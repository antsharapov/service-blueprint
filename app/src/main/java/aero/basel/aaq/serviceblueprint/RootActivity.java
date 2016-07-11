package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class RootActivity extends Activity
{
    private static final ArrayList<Activity> activities= new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        for (int i = 0; i < 70; i++) GlobalVariables.results_array[i] = "end";
        for (int i = 0; i<4; i++) GlobalVariables.timer_base[i] = 0;
        super.onCreate(savedInstanceState);
        activities.add(this);
        Intent intent = new Intent(RootActivity.this, DisclaimerActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        activities.remove(this);
    }

    public static void finishAll()
    {
        for(Activity activity:activities)
            activity.finish();
    }
}
