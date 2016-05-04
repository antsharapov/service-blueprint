package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class WhoActivity extends Activity {
    String airport, service, name, email, flight;
    Button next_button;
    CheckBox camera_checkbox;
    EditText name_field, email_field, flight_field;
    Bitmap agent_photo;
    ImageView photo;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who);

        photo = (ImageView) findViewById(R.id.imageView3);

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

                }
                else{
                    Toast.makeText(getApplicationContext(),"Заполните все поля!", Toast.LENGTH_LONG).show();
                }
            }
        });

        camera_checkbox = (CheckBox) findViewById(R.id.CameraCheckBox);
        camera_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 0);
                    photo.setVisibility(View.VISIBLE);
                }
                else
                {
                    photo.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            agent_photo = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(agent_photo);
        }
        else{
            camera_checkbox.setChecked(false);
        }
    }
}
