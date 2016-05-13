package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        sendEmail();

        TextView tv = (TextView) findViewById(R.id.result_textView);
        tv.setText("Спасибо за использование\nнашего сервиса!");

    }

    public void BackButtonClick(View theButton)
    {
        RootActivity.finishAll();
        System.exit(0);
    }
    protected void sendEmail() {

        String[] TO = {"sharapovav@aaq.basel.aero"};
        String[] CC = {"antsharapov@ya.ru"};

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

        emailIntent.setData(Uri.parse("mailto:"));

        if (!GlobalVariables.agent_photo.isEmpty()) emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(GlobalVariables.agent_photo));


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SERVICE_BLUEPRINT");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Тест: " + GlobalVariables.airport + " " + GlobalVariables.service);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
