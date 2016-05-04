package aero.basel.aaq.serviceblueprint;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class SimpleTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_test);
        String f = getIntent().getStringExtra("photo");
        Uri photo = Uri.parse(f);
        ImageView iv = (ImageView) findViewById(R.id.imageView3);
        iv.setImageURI(photo);
    }
}
