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
        ((TextView) findViewById(R.id.textView)).setText(result);
    }

    public void BackButtonClick(View theButton)
    {
        moveTaskToBack(true);
        super.onDestroy();
        System.exit(0);
    }
}
