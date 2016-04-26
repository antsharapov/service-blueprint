package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthActivity extends Activity {

    ListView airport_lv, service_lv, commission_lv;
    CharSequence airport, service, commission;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        final TextView tv = (TextView) findViewById(R.id.listViewLabel);
        tv.setText("Выберите аэропорт:");

//*******************Airport ListView init&click
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.listview, getResources().getStringArray(R.array.airports));
        airport_lv = (ListView) findViewById(R.id.AirportListView);
        airport_lv.setAdapter(adapter);
        airport_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
                        Toast.LENGTH_SHORT).show();
                airport = ((TextView) itemClicked).getText();
                airport_lv.setVisibility(View.GONE);
                service_lv.setVisibility(View.VISIBLE);
                tv.setText("Выберите службу:");
            }
        });
//**********************************************

// *******************Service ListView init&click
        adapter = new ArrayAdapter<>(this,
                R.layout.listview, getResources().getStringArray(R.array.services));
        service_lv = (ListView) findViewById(R.id.ServiceListView);
        service_lv.setAdapter(adapter);
        service_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
                        Toast.LENGTH_SHORT).show();
                service = ((TextView) itemClicked).getText();
                service_lv.setVisibility(View.GONE);
                commission_lv.setVisibility(View.VISIBLE);
                tv.setText("Выберите коммиссию:");
            }
        });
//**********************************************


// *******************Commission ListView init&click
        adapter = new ArrayAdapter<>(this,
                R.layout.listview, getResources().getStringArray(R.array.commission));
        commission_lv = (ListView) findViewById(R.id.CommissionListView);
        commission_lv.setAdapter(adapter);
        commission_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
                        Toast.LENGTH_SHORT).show();
                commission = ((TextView) itemClicked).getText();
                commission_lv.setVisibility(View.GONE);
                CharSequence ch = ("Выбор сделан! Аэропорт " + airport + ", " + service + ", " + commission);
                tv.setText(ch);
            }
        });
//**********************************************
        service_lv.setVisibility(View.GONE);
        commission_lv.setVisibility(View.GONE);
     }
}
