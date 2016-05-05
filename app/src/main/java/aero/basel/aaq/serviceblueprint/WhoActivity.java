package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
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
                    Intent intent = new Intent(WhoActivity.this, AgentSelectionActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}