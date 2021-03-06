package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class TripleTestActivity extends Activity {

    int i=0,j=0;
    TextView tv, counter_tv;
    String[] questions;
    boolean timer_run;
    long timer_pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triple_test);

        final Chronometer timer = (Chronometer) findViewById(R.id.timer);
        Switch timer_switch = (Switch) findViewById(R.id.timer_switch);

        timer_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    timer.setBase(SystemClock.elapsedRealtime() + timer_pause);
                    timer.start();
                    timer_run = true;
                }
                else {
                    timer_pause = timer.getBase() - SystemClock.elapsedRealtime();
                    timer.stop();
                    GlobalVariables.timer_base[GlobalVariables.triple_test_counter] = (SystemClock.elapsedRealtime() - timer.getBase() )/ 1000;
                    timer_run = false;
                }
            }
        });

        if (GlobalVariables.service.compareToIgnoreCase(getString(R.string.sop))==0) {
            questions = getResources().getStringArray(R.array.sop_questions);
        }
        else if (GlobalVariables.service.compareToIgnoreCase(getString(R.string.sab))==0) {
            questions = getResources().getStringArray(R.array.sab_questions);
        }
        else
        {
            Toast.makeText(TripleTestActivity.this,"Impossible error!",Toast.LENGTH_LONG).show();
            finish();
        }

        j=questions.length;

        tv = (TextView) findViewById(R.id.triple_test_tv);
        tv.setText(questions[i]);

        counter_tv = (TextView) findViewById(R.id.triple_counter_textview);
        counter_tv.setText("Повторение №: " + String.valueOf(GlobalVariables.triple_test_counter));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.listview, getResources().getStringArray(R.array.marks));
        ListView marks_lv = (ListView) findViewById(R.id.triple_marks_listView);
        marks_lv.setAdapter(adapter);
        marks_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                if (i<j-1){
                    GlobalVariables.results_array[GlobalVariables.results_array_index]  = ((TextView) itemClicked).getText().toString();
                    GlobalVariables.results_array_index++;
                    i++;
                    tv.setText(questions[i]);
                }
                else {
                    if (GlobalVariables.triple_test_counter<3) {
                        Intent intent = new Intent(TripleTestActivity.this, TripleTestActivity.class);
                        if (timer_run) GlobalVariables.timer_base[GlobalVariables.triple_test_counter] = (SystemClock.elapsedRealtime() - timer.getBase() )/ 1000;
                        GlobalVariables.triple_test_counter++;
                        GlobalVariables.results_array[GlobalVariables.results_array_index]  = ((TextView) itemClicked).getText().toString();
                        GlobalVariables.results_array_index++;
                        finish();
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(TripleTestActivity.this, ResultActivity.class);
                        GlobalVariables.results_array[GlobalVariables.results_array_index]  = ((TextView) itemClicked).getText().toString();
                        if (timer_run) GlobalVariables.timer_base[GlobalVariables.triple_test_counter] = (SystemClock.elapsedRealtime() - timer.getBase() ) / 1000;
                        finish();
                        startActivity(intent);
                    }
                }
            }
        });

    }
}
