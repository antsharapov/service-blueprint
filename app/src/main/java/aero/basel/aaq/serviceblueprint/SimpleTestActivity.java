package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class SimpleTestActivity extends Activity {

    int i=0,j=0;
    TextView tv;
    String[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_test);

        if (GlobalVariables.service.compareToIgnoreCase(getString(R.string.sop))==0) {
            questions = getResources().getStringArray(R.array.sop_questions);
        }
        else if (GlobalVariables.service.compareToIgnoreCase(getString(R.string.sab))==0) {
            questions = getResources().getStringArray(R.array.sab_questions);
        }
        else
        {
            Toast.makeText(SimpleTestActivity.this,"Impossible error!",Toast.LENGTH_LONG).show();
            finish();
        }

        j=questions.length;

        tv = (TextView) findViewById(R.id.simple_test_tv);
        tv.setText(questions[i]);

        final Chronometer timer = (Chronometer) findViewById(R.id.simple_test_timer);
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.listview, getResources().getStringArray(R.array.marks));
        ListView marks_lv = (ListView) findViewById(R.id.marks_listView);
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
                    GlobalVariables.results_array[GlobalVariables.results_array_index]  = ((TextView) itemClicked).getText().toString();
                    //Intent intent = new Intent (SimpleTestActivity.this,CommentsActivity.class);
                    GlobalVariables.timer_base[0] = (SystemClock.elapsedRealtime() - timer.getBase() )/ 1000;
                    //finish();
                    //startActivity(intent);

            new AlertDialog.Builder(SimpleTestActivity.this)
                    .setTitle("Прилёт")
                    .setMessage("Вы прилетаете в аэропорт группы компаний \"БазэлАэро\"?")
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent (SimpleTestActivity.this,ArrivalActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent (SimpleTestActivity.this,CommentsActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
                }
            }
        });

    }
}
