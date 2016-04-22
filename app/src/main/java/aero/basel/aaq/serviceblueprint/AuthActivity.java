package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AuthActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        TextView tv = (TextView) findViewById(R.id.airport_spiner_label);
        tv.setText(R.string.airport);

    }
}
