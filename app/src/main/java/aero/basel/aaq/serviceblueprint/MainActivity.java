package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    String[] questions, answers;
    int i=0,j=0,k=0;
    RadioGroup rg;
    View checked_rb;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((RadioGroup)findViewById(R.id.RadioGroup)).check(-1);
        ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
        ((TextView) findViewById(R.id.question)).setTextSize(20);
        questions = getResources().getStringArray(R.array.questions);
        i = questions.length;
        ((TextView) findViewById(R.id.question)).setText(questions[j]);
        findViewById(R.id.prev_button).setEnabled(false);
    }

    public void NextButtonClick(View theButton)
        {
            rg = (RadioGroup) findViewById(R.id.RadioGroup);
            k = rg.getCheckedRadioButtonId();
            checked_rb = rg.findViewById(k);
            k = rg.indexOfChild(checked_rb);
            //TODO sum
            rg.check(-1);
            //answers[j]= String.valueOf(k);
            if (j<i-1) {
                j++;
                ((TextView) findViewById(R.id.question)).setText(questions[j]);
                findViewById(R.id.prev_button).setEnabled(true);
            }
            else
            {
                ((TextView) findViewById(R.id.question)).setText(R.string.finished);
                findViewById(R.id.next_button).setEnabled(false);
                findViewById(R.id.prev_button).setEnabled(false);
                ((Chronometer) findViewById(R.id.chronometer)).setActivated(false);
                ((Chronometer) findViewById(R.id.chronometer)).stop();
                ((SwitchCompat) findViewById(R.id.TimerSwitch)).setChecked(false);
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
