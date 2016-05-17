package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.*;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ResultActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(GlobalVariables.results_csv), ',');
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (writer != null) {
            writer.writeNext(new String[] {"\t"});
            writer.writeNext(new String[]{"Дата: ", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date())});
            writer.writeNext(new String[] {"\t"});
            writer.writeNext(new String[]{"Аэропорт: ", GlobalVariables.airport});
            writer.writeNext(new String[] {"\t"});
            writer.writeNext(new String[]{"Проверяемая служба:", GlobalVariables.service});
            writer.writeNext(new String[] {"\t"});
            writer.writeNext(new String[]{"Проверяющая служба:", GlobalVariables.commission});
            if (GlobalVariables.commission.compareToIgnoreCase(getString(R.string.secret_passenger))==0)
            {
                writer.writeNext(new String[] {"\t"});
                writer.writeNext(new String[]{"Ф.И.О тайного пассажира:", GlobalVariables.person_name});
                writer.writeNext(new String[] {"\t"});
                writer.writeNext(new String[]{"Рейс тайного пассажира:", GlobalVariables.person_flight});
                writer.writeNext(new String[] {"\t"});
                writer.writeNext(new String[]{"Дата рейса тайного пассажира:", GlobalVariables.person_flight_date});
            }
            else {
                writer.writeNext(new String[] {"\t"});
                writer.writeNext(new String[]{"Ф.И.О проверяющего:", GlobalVariables.commission_name});
            }
            writer.writeNext(new String[] {"\t"});
            writer.writeNext(new String[]{"Фамилия агента:", GlobalVariables.agent_name});
            writer.writeNext(new String[] {"\t"});
            writer.writeNext(new String[]{"Форменная одежда","\t","\t","Пропуск/бейдж","\t","\t","Внешний вид"});
            writer.writeNext(new String[] {"\t"});

            if (GlobalVariables.service.compareToIgnoreCase(getString(R.string.sop))==0) {
                String[] res = new String[getResources().getStringArray(R.array.questions_appearance).length+getResources().getStringArray(R.array.sop_questions).length];
                System.arraycopy(getResources().getStringArray(R.array.questions_appearance),0,res,0,getResources().getStringArray(R.array.questions_appearance).length);
                System.arraycopy(getResources().getStringArray(R.array.sop_questions),0,res, getResources().getStringArray(R.array.questions_appearance).length, getResources().getStringArray(R.array.sop_questions).length);
                writer.writeNext(res);
            }
            else {
                String[] res = new String[getResources().getStringArray(R.array.questions_appearance).length+getResources().getStringArray(R.array.sab_questions).length];
                System.arraycopy(getResources().getStringArray(R.array.questions_appearance),0,res,0,getResources().getStringArray(R.array.questions_appearance).length);
                System.arraycopy(getResources().getStringArray(R.array.sab_questions),0,res, getResources().getStringArray(R.array.questions_appearance).length, getResources().getStringArray(R.array.sab_questions).length);
                writer.writeNext(res);
            }

            writer.writeNext(new String[] {"\t"});
            writer.writeNext(GlobalVariables.results_array);
        }
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        String[] CC = {""};

        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);

        emailIntent.setData(Uri.parse("mailto:"));


        ArrayList<Uri> files = new ArrayList<>();

        if (!GlobalVariables.agent_photo.isEmpty()) files.add(Uri.parse("file://" + GlobalVariables.agent_photo));
        if (!GlobalVariables.results_csv.isEmpty()) files.add(Uri.parse("file://" + GlobalVariables.results_csv));

        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SERVICE_BLUEPRINT");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Анкета Service Blueprint: " + GlobalVariables.airport + " " + GlobalVariables.service);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
