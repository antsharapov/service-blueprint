package aero.basel.aaq.serviceblueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AuthActivity extends Activity {

    private ListView airport_lv, service_lv, commission_lv;

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

                GlobalVariables.airport  = ((TextView) itemClicked).getText().toString();
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
                GlobalVariables.service = ((TextView) itemClicked).getText().toString();
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

                GlobalVariables.commission = ((TextView) itemClicked).getText().toString();
                commission_lv.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
                if (GlobalVariables.commission.compareTo("Тайный пассажир")==0)
                {
                    Intent intent = new Intent(AuthActivity.this, WhoActivity.class);
                    onDestroy();
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(AuthActivity.this, LogonActivity.class);
                    onDestroy();
                    startActivity(intent);
                }
            }
        });
//**********************************************
        service_lv.setVisibility(View.GONE);
        commission_lv.setVisibility(View.GONE);
     }
}
