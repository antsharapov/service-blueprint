package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TripleTestActivity extends Activity {

    String[] questions;
    int i=0,j=0;
    int x=0,sum=0;
    RadioGroup rg;
    View checked_rb;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triple_test);
        ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
        ((TextView) findViewById(R.id.question)).setTextSize(20);
        questions = getResources().getStringArray(R.array.questions);
        i = questions.length;
        ((TextView) findViewById(R.id.question)).setText(questions[j]);
        findViewById(R.id.prev_button).setEnabled(false);
    }

    public void NextButtonClick(View theButton)
        {
            rg = (RadioGroup) findViewById(R.id.RadioGroup1);
            x = rg.getCheckedRadioButtonId();
            checked_rb = rg.findViewById(x);
            sum += rg.indexOfChild(checked_rb);
            //TODO sum
            rg.check(-1);
            rg = (RadioGroup) findViewById(R.id.RadioGroup2);
            x = rg.getCheckedRadioButtonId();
            checked_rb = rg.findViewById(x);
            sum += rg.indexOfChild(checked_rb);
            rg.check(-1);
            rg = (RadioGroup) findViewById(R.id.RadioGroup3);
            x = rg.getCheckedRadioButtonId();
            checked_rb = rg.findViewById(x);
            sum += rg.indexOfChild(checked_rb);
            rg.check(-1);
            if (j<i-1) {
                j++;
                ((TextView) findViewById(R.id.question)).setText(questions[j]);
                findViewById(R.id.prev_button).setEnabled(true);
            }
            else
            {
                String res = getString(R.string.result) + String.valueOf(sum);
                ((TextView) findViewById(R.id.question)).setText(res);
                findViewById(R.id.next_button).setEnabled(false);
                findViewById(R.id.prev_button).setEnabled(false);
                findViewById(R.id.chronometer).setActivated(false);
                ((Chronometer) findViewById(R.id.chronometer)).stop();
                ((SwitchCompat) findViewById(R.id.TimerSwitch)).setChecked(false);
                Toast.makeText(getApplicationContext(),R.string.finished,Toast.LENGTH_LONG).show();
                long t = SystemClock.elapsedRealtime() - ((Chronometer) findViewById(R.id.chronometer)).getBase();
                t = t/1000;
                Intent intent = new Intent(TripleTestActivity.this, ResultActivity.class);
                intent.putExtra("result", res);
                intent.putExtra("time", String.valueOf(t));
                finish();
                startActivity(intent);
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
            findViewById(R.id.chronometer).setActivated(false);
            ((Chronometer) findViewById(R.id.chronometer)).stop();
        }
        else
        {
            ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
           findViewById(R.id.chronometer).setActivated(true);
            ((Chronometer) findViewById(R.id.chronometer)).start();
        }
    }
}
