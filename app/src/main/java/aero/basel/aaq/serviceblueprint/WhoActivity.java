package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class WhoActivity extends Activity {
    String airport, service, name, email, flight;
    Button next_button;
    CheckBox camera_checkbox;
    EditText name_field, email_field, flight_field;
    Bitmap agent_photo;
    private final int CAMERA_RESULT = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who);


        airport = getIntent().getStringExtra("airport");
        service = getIntent().getStringExtra("service");

        name_field = (EditText) findViewById(R.id.NameField);
        email_field = (EditText) findViewById(R.id.EmailField);
        flight_field = (EditText) findViewById(R.id.FlightNumberField);

        next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = name_field.getText().toString();
                email = email_field.getText().toString();
                flight = flight_field.getText().toString();
                if (!name.isEmpty() && !email.isEmpty() && !flight.isEmpty()) {
                    camera_checkbox = (CheckBox) findViewById(R.id.CameraCheckBox);
                    if (camera_checkbox.isChecked()) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_RESULT);

                    } else {

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Заполните все поля!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_RESULT) {
            agent_photo = (Bitmap) data.getExtras().get("data");
            ImageView photo = (ImageView) findViewById(R.id.imageView3);
            photo.setImageBitmap(agent_photo);
        }
    }
}
