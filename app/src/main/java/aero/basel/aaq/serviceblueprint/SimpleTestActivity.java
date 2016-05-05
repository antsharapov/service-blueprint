package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class SimpleTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_test);
        String f = getIntent().getStringExtra("photo");
        if (!f.isEmpty()) {
            Uri photo = Uri.parse(f);
            ImageView iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageURI(photo);
        }
    }
}
