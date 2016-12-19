package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ArrivalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival);

        final Spinner airports_sp = (Spinner) findViewById(R.id.airports_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.airports));
        airports_sp.setAdapter(adapter);

        Button next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ArrivalActivity.this,CommentsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
