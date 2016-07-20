package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WhoActivity extends Activity {

    private EditText name_field, flight_field, date_field;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who);

        name_field = (EditText) findViewById(R.id.NameField);
        flight_field = (EditText) findViewById(R.id.FlightNumberField);
        date_field = (EditText) findViewById(R.id.DateField);

        Button next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariables.person_name = name_field.getText().toString();
                GlobalVariables.person_flight = flight_field.getText().toString();
                GlobalVariables.person_flight_date = date_field.getText().toString();

                if (!GlobalVariables.person_name.isEmpty() && !GlobalVariables.person_flight.isEmpty() && !GlobalVariables.person_flight_date.isEmpty()) {
                    /*Intent intent = new Intent(WhoActivity.this, /*AgentSelection//QRCodeActivity.class);
                    startActivity(intent);
                    finish(); */
                    try {
                        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                        intent.putExtra("SCAN_MODE", "SCAN_MODE");
                        startActivityForResult(intent, 0);
                        Toast.makeText(WhoActivity.this, "Отсканируйте код агента", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
                        startActivity(marketIntent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.empty_fields, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            String barcode = result.getStringExtra("SCAN_RESULT");
            //String type = result.getStringExtra("SCAN_RESULT_FORMAT");
            GlobalVariables.agent_name = barcode;
            Intent intent = new Intent (WhoActivity.this,AppearanceActivity.class);
            startActivity(intent);
            finish();
            //Toast.makeText(this, String.format("barcode: '%s' type: %s", barcode, type), Toast.LENGTH_SHORT).show();
        }
        else{

        }
    }
}
