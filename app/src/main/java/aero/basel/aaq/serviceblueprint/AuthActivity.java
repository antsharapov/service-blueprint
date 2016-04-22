package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ArrayAdapter<String> emptyAdapter;
        String[] strings = new String[] {};
        List<String> items = new ArrayList<String>(Arrays.asList(strings));
        emptyAdapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_spinner_item, items);
        Spinner sp = (Spinner) findViewById(R.id.airports_spinner);
        sp.setAdapter(emptyAdapter);
        TextView tv = (TextView) findViewById(R.id.airport_spiner_label);
        tv.setText(R.string.airport);

    }
}
