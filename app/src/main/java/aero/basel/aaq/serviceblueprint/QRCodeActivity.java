package aero.basel.aaq.serviceblueprint;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class QRCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);


        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "SCAN_MODE");
            startActivityForResult(intent, 0);
            Toast.makeText(this, "Отсканируйте код агента", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
            startActivity(marketIntent);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            String barcode = result.getStringExtra("SCAN_RESULT");
            //String type = result.getStringExtra("SCAN_RESULT_FORMAT");
            GlobalVariables.agent_name = barcode;
            Intent intent = new Intent (QRCodeActivity.this,AppearanceActivity.class);
            startActivity(intent);
            finish();
            //Toast.makeText(this, String.format("barcode: '%s' type: %s", barcode, type), Toast.LENGTH_SHORT).show();
        }
        else{

        }
    }
}
