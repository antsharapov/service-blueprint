package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;


public class AppearanceActivity extends Activity {

    private DatabaseHelper myDbHelper;
    String[] appearance_qustions;
    int i=0,j=0;
    Spinner agent_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appearance);

        agent_spinner = (Spinner) findViewById(R.id.spinner);
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
        String airport = getIntent().getStringExtra("airport");
        String query = "Select name from agents where airport = ?";
        Cursor cursor = sdb.rawQuery(query, new String[] { airport });
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
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(AppearanceActivity.this,android.R.layout.simple_spinner_item, allSpinner);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agent_spinner.setAdapter(spinnerAdapter);
        }
    }