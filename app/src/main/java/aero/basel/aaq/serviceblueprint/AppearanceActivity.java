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


        String query = "Select name from agents";
        Cursor cursor = sdb.rawQuery(query, null);
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
        agent_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Cursor cursor = sdb.query(DatabaseHelper.DATABASE_USERS_TABLE, new String[]{DatabaseHelper.USERNAME_USERS_COLUMN,
                        DatabaseHelper.PASSWORD_USERS_COLUMN, DatabaseHelper.REALNAME_USERS_COLUMN},
                null, null,
                null, null, null);

        while (cursor.moveToNext()) {

            //db_username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USERNAME_USERS_COLUMN));
            //db_pass = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PASSWORD_USERS_COLUMN));

            }*/
        }
    }