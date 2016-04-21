package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        String result = getIntent().getExtras().getString("result");
        String time = getString(R.string.time) + getIntent().getExtras().getString("time") + getString(R.string.seconds);
        ((TextView) findViewById(R.id.textView)).setText(result);
        ((TextView) findViewById(R.id.textView2)).setText(time);

    }

    public void BackButtonClick(View theButton)
    {
        moveTaskToBack(true);
        super.onDestroy();
        System.exit(0);
    }
}
