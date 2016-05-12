package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AppearanceActivity extends Activity {

    private Button next_btn, back_btn;
    private TextView app_question_header, app_question1_title, app_question2_title, app_question3_title;
    private String[] header_array, questions_array;
    private int i=0,j=0,header_array_length;

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