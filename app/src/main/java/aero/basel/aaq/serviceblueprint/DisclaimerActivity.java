package aero.basel.aaq.serviceblueprint;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class DisclaimerActivity extends Activity
{
    TextView disclaimer;
    Button next_button;
    CheckBox agreed_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);

        next_button = (Button) findViewById(R.id.disclaimer_next_button);
        next_button.setEnabled(false);

        disclaimer = (TextView) findViewById(R.id.disclaimer);
        disclaimer.setText("disclaimer");

    }


}
