package aero.basel.aaq.serviceblueprint;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class DisclaimerActivity extends Activity
{
    private TextView disclaimer;
    private Button next_button;
    private CheckBox agreed_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        next_button = (Button) findViewById(R.id.disclaimer_next_button);
        next_button.setEnabled(false);

        disclaimer = (TextView) findViewById(R.id.disclaimer);
        disclaimer.setText(R.string.disclaimer);

        agreed_cb = (CheckBox) findViewById(R.id.disclaimer_agreed_checkbox);
        agreed_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked){
                    next_button.setEnabled(true);
                }
                else
                {
                    next_button.setEnabled(false);
                }
            }
        }
        );

        next_button.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Intent intent = new Intent(DisclaimerActivity.this, AuthActivity.class);
                                               startActivity(intent);
                                               finish();
                                           }
                                       }

        );

    }


}
