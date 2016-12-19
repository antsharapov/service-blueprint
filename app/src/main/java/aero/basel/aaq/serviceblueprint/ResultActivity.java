package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.*;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ResultActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);


             Thread thread = new Thread(new Runnable()
                                               {
                                                   @Override
                                                   public void run()
                                                   {
                                                       try
                                                       {

                                               HttpURLConnection connection;
                                               OutputStreamWriter request = null;
                                               URL url = null;
                                                           String response = null;
                                               String parameters = "name="+GlobalVariables.comments_type+"&mail="+GlobalVariables.comments;

                                                   url = new URL("http://sd-glpi.basel.aero.local/phpmyadmin/and.php");
                                                   connection = (HttpURLConnection) url.openConnection();
                                                   connection.setDoOutput(true);
                                                   connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                                   connection.setRequestMethod("POST");
                                                   request = new OutputStreamWriter(connection.getOutputStream());
                                                   request.write(parameters);
                                                   request.flush();
                                                   request.close();
                                                           String line = "";
                                                           InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                                                           BufferedReader reader = new BufferedReader(isr);
                                                           StringBuilder sb = new StringBuilder();
                                                           while ((line = reader.readLine()) != null)
                                                           {
                                                               sb.append(line + "\n");
                                                           }
                                                           // Response from server after login process will be stored in response variable.
                                                           response = sb.toString();

                                                           isr.close();
                                                           reader.close();
                                                       }
                                                       catch (Exception e)
                                                       {
                                                           e.printStackTrace();
                                                       }
                                                   }
                                               });
                                                thread.start();


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
                writer.writeNext(new String[] {"\t"});
                writer.writeNext(new String[]{"Номер стойки регистрации:", GlobalVariables.desk_num});
                writer.writeNext(new String[] {"\t"});
                writer.writeNext(new String[]{"Время, затраченное на опрос:", String.valueOf(GlobalVariables.timer_base[0]) + "c."});
            }
            else {
                writer.writeNext(new String[] {"\t"});
                writer.writeNext(new String[]{"Ф.И.О проверяющего:", GlobalVariables.commission_name});
                writer.writeNext(new String[] {"\t"});
                writer.writeNext(new String[]{"Таймеры:", String.valueOf(GlobalVariables.timer_base[1]) + "c.",String.valueOf(GlobalVariables.timer_base[2]) + "c.",String.valueOf(GlobalVariables.timer_base[3]) + "c."});
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

        String[] TO;
        String[] CC;

        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);

        if (GlobalVariables.commission.compareToIgnoreCase(getString(R.string.secret_passenger))==0){
            TO = new String[]{"SharapovAV@aaq.basel.aero"};
            CC = new String[]{""};
        }
        else {
            TO = new String[]{"SharapovAV@aaq.basel.aero"};
            CC = new String[]{""};
        }

        emailIntent.setData(Uri.parse("mailto:"));


        ArrayList<Uri> files = new ArrayList<>();

        if (!GlobalVariables.agent_photo.isEmpty()) files.add(Uri.parse("file://" + GlobalVariables.agent_photo));
        if (!GlobalVariables.results_csv.isEmpty()) files.add(Uri.parse("file://" + GlobalVariables.results_csv));

        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Анкета Service Blueprint");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Анкета Service Blueprint: " + GlobalVariables.airport + ", " + GlobalVariables.service + ". Комментарии пользователя:\n" + GlobalVariables.comments_type + ": " + GlobalVariables.comments);

        try {
            startActivity(Intent.createChooser(emailIntent, "Отправить на почту через:"));
            //finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "Не найдена почтовая программа!", Toast.LENGTH_LONG).show();
            RootActivity.finishAll();
        }
    }

}
