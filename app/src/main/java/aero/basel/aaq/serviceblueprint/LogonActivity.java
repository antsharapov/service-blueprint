package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogonActivity extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_logon);
    }

    public void NextButtonClick(View theButton)
    {
        EditText username = (EditText)findViewById(R.id.editText1);
        EditText password = (EditText)findViewById(R.id.editText2);
        if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
        {
            Intent intent = new Intent(LogonActivity.this, MainActivity.class);
            startActivity(intent);
            super.onDestroy();

        }
        else{
            Toast.makeText(getApplicationContext(),R.string.wrong_creds,Toast.LENGTH_LONG).show();
        }
    }
}
