package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class LogonActivity extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_logon);
    }

    public void NextButtonClick(View theButton) throws SQLException {
        EditText username = (EditText) findViewById(R.id.LoginEditText);
        EditText password = (EditText) findViewById(R.id.PassEditText);
        String db_username;
        String db_pass;
        boolean success = false;

        DatabaseHelper myDbHelper = new DatabaseHelper(this);
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

        Cursor cursor = sdb.query(DatabaseHelper.DATABASE_USERS_TABLE, new String[]{DatabaseHelper.USERNAME_USERS_COLUMN,
                        DatabaseHelper.PASSWORD_USERS_COLUMN, DatabaseHelper.REALNAME_USERS_COLUMN},
                null, null,
                null, null, null);

        while (cursor.moveToNext()) {

            db_username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USERNAME_USERS_COLUMN));
            db_pass = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PASSWORD_USERS_COLUMN));

            if (username.getText().toString().equals(db_username) && password.getText().toString().equals(db_pass)) {
                success = true;
                Intent intent = new Intent(LogonActivity.this, AgentSelectionActivity.class);
                GlobalVariables.commission_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.REALNAME_USERS_COLUMN));
                startActivity(intent);
                finish();
                break;
            }
        }

        if (!success) Toast.makeText(getApplicationContext(), R.string.wrong_creds, Toast.LENGTH_LONG).show();
        cursor.close();
    }
}
