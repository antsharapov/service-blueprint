package aero.basel.aaq.serviceblueprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        final EditText comments_et = (EditText) findViewById(R.id.comments);
        Button comments_next_btn = (Button) findViewById(R.id.comments_next_btn);
        final Spinner comments_type_sp = (Spinner) findViewById(R.id.comments_type_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.comments_type));
        comments_type_sp.setAdapter(adapter);
        comments_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariables.comments_type = comments_type_sp.getSelectedItem().toString();
                GlobalVariables.comments = comments_et.getText().toString();
                Intent intent = new Intent (CommentsActivity.this,ResultActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
