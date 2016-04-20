package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.res.Resources;

public class MainActivity extends Activity {



    String[] questions;
    String[] answers;

    int i,j;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((RadioGroup)findViewById(R.id.RadioGroup)).check(R.id.ZeroRadioButton);
        ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
        ((TextView) findViewById(R.id.question)).setTextSize(24);
        questions = getResources().getStringArray(R.array.questions);
        i = questions.length;
        j=0;
        ((TextView) findViewById(R.id.question)).setText(questions[j]);
        findViewById(R.id.prev_button).setEnabled(false);
    }

    public void NextButtonClick(View theButton)
        {
            if (j<i-1) {
                j++;
                ((TextView) findViewById(R.id.question)).setText(questions[j]);
                findViewById(R.id.prev_button).setEnabled(true);
            }
            else
            {
                ((TextView) findViewById(R.id.question)).setText("Finished");
                findViewById(R.id.next_button).setEnabled(false);
                findViewById(R.id.prev_button).setEnabled(false);
            }
        }

    public void PrevButtonClick(View theButton)
    {
        if (j>1) {
            j--;
            ((TextView) findViewById(R.id.question)).setText(questions[j]);
            findViewById(R.id.next_button).setEnabled(true);
        }
        else
        {
            j--;
            ((TextView) findViewById(R.id.question)).setText(questions[j]);
            findViewById(R.id.prev_button).setEnabled(false);
        }
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
