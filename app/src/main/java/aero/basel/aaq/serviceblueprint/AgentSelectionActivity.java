package aero.basel.aaq.serviceblueprint;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AgentSelectionActivity extends Activity {

    private Spinner agent_spinner;
    private CheckBox camera_checkbox;
    private File f = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_selection);

        agent_spinner = (Spinner) findViewById(R.id.agent_name_spinner);

        DatabaseHelper myDbHelper = new DatabaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database!");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        SQLiteDatabase sdb = myDbHelper.getReadableDatabase();
        sdb.close();
        sdb = myDbHelper.getReadableDatabase();
        String query = "Select name from agents where airport = ? and service = ?";
        Cursor cursor = sdb.rawQuery(query, new String[] { GlobalVariables.airport, GlobalVariables.service });
        ArrayList<String> spinnerContent = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String word = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                spinnerContent.add(word);
            }while(cursor.moveToNext());
        }
        cursor.close();

        String[] allSpinner = new String[spinnerContent.size()];
        allSpinner = spinnerContent.toArray(allSpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(AgentSelectionActivity.this,android.R.layout.simple_spinner_item, allSpinner);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agent_spinner.setAdapter(spinnerAdapter);

        Button next_btn = (Button) findViewById(R.id.next_button);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariables.agent_name = agent_spinner.getSelectedItem().toString();
                Intent intent = new Intent (AgentSelectionActivity.this,AppearanceActivity.class);
                if (camera_checkbox.isChecked()) GlobalVariables.agent_photo = f.getAbsolutePath();
                startActivity(intent);
                finish();
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
                                                                   Toast.makeText(getApplicationContext(),"File creation failed!", Toast.LENGTH_LONG).show();
                                                               }

                                                               cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                                                               startActivityForResult(cameraIntent, 1);
                                                           }
                                                           else {
                                                               camera_checkbox.setText(R.string.add_photo);
                                                               f.delete();
                                                           }
                                                       }
                                                   }
        );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            camera_checkbox.setText(R.string.photo_success);
        }
        else{
            camera_checkbox.setChecked(false);
            camera_checkbox.setText(R.string.add_photo);
        }
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        String imageFileName = "JPG_" + timeStamp;
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
    }

}


