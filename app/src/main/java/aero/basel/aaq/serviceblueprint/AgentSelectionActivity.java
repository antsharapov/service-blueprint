package aero.basel.aaq.serviceblueprint;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

public class AgentSelectionActivity extends AppCompatActivity {

    Spinner agent_spinner;
    DatabaseHelper myDbHelper;
    Button next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_selection);

        agent_spinner = (Spinner) findViewById(R.id.spinner2);
        myDbHelper = new DatabaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        SQLiteDatabase sdb = myDbHelper.getReadableDatabase();
        sdb.close();
        sdb = myDbHelper.getReadableDatabase();
        String query = "Select name from agents where airport = ?";
        Cursor cursor = sdb.rawQuery(query, new String[] { GlobalVariables.airport });
        ArrayList<String> spinnerContent = new ArrayList<String>();
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

        next_btn = (Button) findViewById(R.id.button3);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String agent_name = agent_spinner.getSelectedItem().toString();
                Intent intent = new Intent (AgentSelectionActivity.this,SimpleTestActivity.class);
               /* intent.putExtra("airport",airport);
                intent.putExtra("service",service);
                intent.putExtra("name", name);
                intent.putExtra("flight", flight);
                intent.putExtra("date",date);
                intent.putExtra("agent_name", agent_name);
                intent.putExtra("photo",photo);*/
                startActivity(intent);
                finish();
            }
        });

    }


}

