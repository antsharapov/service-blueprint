package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((RadioGroup)findViewById(R.id.RadioGroup)).check(R.id.ZeroRadioButton);
        ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
    }

    public void NextButtonClick(View theButton)
    {
        ((TextView) findViewById(R.id.question)).setTextSize(24);
        ((TextView) findViewById(R.id.question)).setText("\nДинамически формируемый вопрос");

    }

    public void PrevButtonClick(View theButton)
    {

    }

    public void TimerSwitchClick(View theButton)
    {
        if ( findViewById(R.id.chronometer).isActivated())
        {
            ((Chronometer) findViewById(R.id.chronometer)).setActivated(false);
            ((Chronometer) findViewById(R.id.chronometer)).stop();
        }
        else
        {
            ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
            ((Chronometer) findViewById(R.id.chronometer)).setActivated(true);
            ((Chronometer) findViewById(R.id.chronometer)).start();
        }
    }
}
