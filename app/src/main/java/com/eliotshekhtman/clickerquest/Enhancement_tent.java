package com.eliotshekhtman.clickerquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Enhancement_tent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enhancement);
        updateMarket();
    }

    public void buyHealth( View view ) {
        MainActivity.player.addHealth();
        updateMarket();
    }

    public void buySpeed( View view ) {
        MainActivity.player.addSpeed();
        updateMarket();
    }

    public void updateMarket() {
        //saveGame();
        final TextView gtext = (TextView) findViewById(R.id.goldView_enh);
        final TextView ptext = (TextView) findViewById(R.id.priceView_enh);
        final TextView htext = (TextView) findViewById(R.id.healthView);
        final TextView stext = (TextView) findViewById(R.id.speedView);
        gtext.setText("GOLD: " + MainActivity.player.getGold());
        ptext.setText("PRICE: " + MainActivity.player.priceEnhance());
        htext.setText(MainActivity.player.getHealth_total() + " HITPOINTS");
        stext.setText(MainActivity.player.getSpeed() + " KM PER CLICK");
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, Market.class);
        startActivity(intent);

    }
}
