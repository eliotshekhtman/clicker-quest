package com.eliotshekhtman.clickerquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Blacksmith_tent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacksmith);
        ListView lv = (ListView) findViewById(R.id.weaponList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.player.getArms() );

        lv.setAdapter(arrayAdapter);
        updateMarket();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                weapons w = MainActivity.player.getWeapon(MainActivity.player.getArmsNames().get(position));
                MainActivity.player.upgrade(w);
                updateMarket();
            }
        });
    }

    public void updateMarket() {
        //saveGame();
        final TextView gtext = (TextView) findViewById(R.id.goldView_bla);
        final ListView lv = (ListView) findViewById(R.id.weaponList);
        gtext.setText("GOLD: " + MainActivity.player.getGold());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.player.getArms() );

        lv.setAdapter(arrayAdapter);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, Market.class);
        startActivity(intent);
    }
}
