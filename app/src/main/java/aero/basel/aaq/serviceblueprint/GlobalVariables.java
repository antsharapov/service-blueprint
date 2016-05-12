package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class GlobalVariables extends Activity {
    public static String airport;
    public static String service;
    public static String commission;
    public static String person_name;
    public static String person_flight;
    public static String person_flight_date;
    public static String commission_name;
    public static String agent_name;
    public static String agent_photo = "";
    public static String[] results_array = new String[100];
    public static int results_array_index = 0;

    protected void sendEmail() {

        String[] TO = {"sharapovav@aaq.basel.aero"};
        String[] CC = {"antsharapov@ya.ru"};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

        emailIntent.setData(Uri.parse("mailto:"));

        if (!agent_photo.isEmpty()) emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(agent_photo));


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
