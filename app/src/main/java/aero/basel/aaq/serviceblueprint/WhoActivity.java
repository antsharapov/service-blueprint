package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WhoActivity extends Activity {
    String airport, service, name, email, flight;
    Button next_button;
    CheckBox camera_checkbox;
    EditText name_field, email_field, flight_field;
    String mCurrentPhotoPath;
    File f = null;
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
                    sendEmail();
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

                    try {
                        f = createImageFile();
                    } catch (IOException ex) {
                        Toast.makeText(getApplicationContext(),"file fail", Toast.LENGTH_LONG).show();
                    }

                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(cameraIntent, 1);
                }
                else {
                    camera_checkbox.setText("Приложить фото оцениваемого агента");
                    f.delete();
                }
                }
            }
        );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            camera_checkbox.setText("Фото добавлено");
        }
        else{
            camera_checkbox.setChecked(false);
            camera_checkbox.setText("Приложить фото оцениваемого агента");
        }
    }

    protected void sendEmail() {

        String[] TO = {"sharapovav@aaq.basel.aero"};
        String[] CC = {"antsharapov@ya.ru"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("application/image");

        if (camera_checkbox.isChecked()) emailIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SERVICE_BLUEPRINT");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Тест: " + airport + " " + service);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            RootActivity.finishAll();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

}
