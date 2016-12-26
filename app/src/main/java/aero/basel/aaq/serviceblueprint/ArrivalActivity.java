package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
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
                GlobalVariables.arrival_airport = airports_sp.getSelectedItem().toString();
                RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
                GlobalVariables.arrival_transport = (int) rb.getRating();
                rb = (RatingBar) findViewById(R.id.ratingBar2);
                GlobalVariables.arrival_cleanliness = (int) rb.getRating();
                rb = (RatingBar) findViewById(R.id.ratingBar3);
                GlobalVariables.arrival_navi_ease = (int) rb.getRating();
                rb = (RatingBar) findViewById(R.id.ratingBar4);
                GlobalVariables.arrival_fids = (int) rb.getRating();
                rb = (RatingBar) findViewById(R.id.ratingBar5);
                GlobalVariables.arrival_sound = (int) rb.getRating();
                rb = (RatingBar) findViewById(R.id.ratingBar6);
                GlobalVariables.arrival_bordercontrol = (int) rb.getRating();
                rb = (RatingBar) findViewById(R.id.ratingBar7);
                GlobalVariables.arrival_baggage_waittime = (int) rb.getRating();
                rb = (RatingBar) findViewById(R.id.ratingBar8);
                GlobalVariables.arrival_customs = (int) rb.getRating();
                Intent intent = new Intent (ArrivalActivity.this,CommentsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
