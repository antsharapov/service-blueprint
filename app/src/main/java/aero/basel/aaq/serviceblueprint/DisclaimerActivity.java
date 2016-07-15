package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/*import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;*/


public class DisclaimerActivity extends Activity
{
    private Button next_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        next_button = (Button) findViewById(R.id.disclaimer_next_button);
        next_button.setEnabled(false);

        TextView disclaimer = (TextView) findViewById(R.id.disclaimer);
        disclaimer.setText(R.string.disclaimer);

        CheckBox agreed_cb = (CheckBox) findViewById(R.id.disclaimer_agreed_checkbox);
        agreed_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked){
                    next_button.setEnabled(true);
                }
                else
                {
                    next_button.setEnabled(false);
                }
            }
        }
        );

        next_button.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

                                           /*    Thread thread = new Thread(new Runnable()
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
                                               String parameters = "name=test&mail=test@dummy.ru";

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
                                                thread.start();*/
                                               Intent intent = new Intent(DisclaimerActivity.this, AuthActivity.class);
                                               startActivity(intent);
                                               finish();
                                           }
                                       }

        );

    }


}
