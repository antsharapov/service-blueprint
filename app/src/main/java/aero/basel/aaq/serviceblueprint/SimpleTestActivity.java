package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class SimpleTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_test);

        if (!GlobalVariables.agent_photo.isEmpty()) {
            Uri photo = Uri.parse(GlobalVariables.agent_photo);
            ImageView iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageURI(photo);
        }
        else{
            Toast.makeText(this,"Test",Toast.LENGTH_LONG).show();
        }
    }
}
