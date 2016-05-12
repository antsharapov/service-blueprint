package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class AppearanceActivity extends Activity {

    private Button next_btn, back_btn;
    private RadioGroup rg1, rg2, rg3;
    private View checked_rb1, checked_rb2, checked_rb3;
    private TextView app_question_header, app_question1_title, app_question2_title, app_question3_title;
    private String[] header_array, questions_array;
    private int i=0,j=0, x=0,sum=0,header_array_length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appearance);

        next_btn = (Button) findViewById(R.id.app_button_next);
        back_btn = (Button) findViewById(R.id.app_button_back);
        back_btn.setEnabled(false);

        header_array = getResources().getStringArray(R.array.headline_appearance);
        header_array_length = header_array.length;
        questions_array = getResources().getStringArray(R.array.questions_appearance);
        app_question_header = (TextView) findViewById(R.id.app_question_header);
        app_question1_title = (TextView) findViewById(R.id.app_question1_title);
        app_question2_title = (TextView) findViewById(R.id.app_question2_title);
        app_question3_title = (TextView) findViewById(R.id.app_question3_title);
        app_question_header.setText(header_array[i]);
        app_question1_title.setText(questions_array[j]);
        j++;
        app_question2_title.setText(questions_array[j]);
        j++;
        app_question3_title.setText(questions_array[j]);
    }

    public void NextButtonClick (View theButton)
    {
        back_btn.setEnabled(true);


        rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
        checked_rb1 = rg1.findViewById(rg1.getCheckedRadioButtonId());


        switch (rg1.indexOfChild(checked_rb1)){
            case -1:
                Toast.makeText(AppearanceActivity.this,"Пропущены поля!", Toast.LENGTH_LONG).show();
                i++;
                j+=3;
                back_btn.performClick();
                return;
            case 0:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "+";
                GlobalVariables.results_array_index++;
                break;
            case 1:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "0";
                GlobalVariables.results_array_index++;
                break;
            case 2:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "-";
                GlobalVariables.results_array_index++;
                break;
            case 3:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "x";
                GlobalVariables.results_array_index++;
                break;

        }


        rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
        checked_rb2 = rg2.findViewById(rg2.getCheckedRadioButtonId());

        switch (rg2.indexOfChild(checked_rb2)){
            case -1:
                Toast.makeText(AppearanceActivity.this,"Пропущены поля!", Toast.LENGTH_LONG).show();
                i++;
                j+=3;
                GlobalVariables.results_array_index-=1;
                back_btn.performClick();
                return;
            case 0:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "+";
                GlobalVariables.results_array_index++;
                break;
            case 1:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "0";
                GlobalVariables.results_array_index++;
                break;
            case 2:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "-";
                GlobalVariables.results_array_index++;
                break;
            case 3:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "x";
                GlobalVariables.results_array_index++;
                break;

        }

        rg3 = (RadioGroup) findViewById(R.id.radioGroup3);
        checked_rb3 = rg3.findViewById(rg3.getCheckedRadioButtonId());

        switch (rg3.indexOfChild(checked_rb3)){
            case -1:
                Toast.makeText(AppearanceActivity.this,"Пропущены поля!", Toast.LENGTH_LONG).show();
                i++;
                j+=3;
                GlobalVariables.results_array_index-=2;
                back_btn.performClick();
                return;
            case 0:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "+";
                GlobalVariables.results_array_index++;
                break;
            case 1:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "0";
                GlobalVariables.results_array_index++;
                break;
            case 2:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "-";
                GlobalVariables.results_array_index++;
                break;
            case 3:
                GlobalVariables.results_array[GlobalVariables.results_array_index] = "x";
                GlobalVariables.results_array_index++;
                break;

        }

        rg1.check(-1);
        rg2.check(-1);
        rg3.check(-1);

        if (i<header_array_length-1) {
            i++;
            app_question_header.setText(header_array[i]);
            j++;
            app_question1_title.setText(questions_array[j]);
            j++;
            app_question2_title.setText(questions_array[j]);
            j++;
            app_question3_title.setText(questions_array[j]);
        }
        else{
            next_btn.setEnabled(false);
            Toast.makeText(AppearanceActivity.this,String.valueOf(GlobalVariables.results_array_index),Toast.LENGTH_LONG).show();
        }
    }

    public void BackButtonClick (View theButton)
    {
        if (i>1) {
            i--;
            app_question_header.setText(header_array[i]);
            j = j - 5;
            app_question1_title.setText(questions_array[j]);
            j++;
            app_question2_title.setText(questions_array[j]);
            j++;
            app_question3_title.setText(questions_array[j]);
            next_btn.setEnabled(true);
        }
        else
        {
            i--;
            app_question_header.setText(header_array[i]);
            j = j - 5;
            app_question1_title.setText(questions_array[j]);
            j++;
            app_question2_title.setText(questions_array[j]);
            j++;
            app_question3_title.setText(questions_array[j]);
            next_btn.setEnabled(true);
            back_btn.setEnabled(false);
        }
    }

}