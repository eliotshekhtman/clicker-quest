package com.eliotshekhtman.clickerquest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    // TODO: break into classes, make each new stage or 100m give a multiplier to gold achieved, make move forward automatic but can make faster by tapping

    public static boolean questing = false;
    public static boolean click = false;
    public static boolean togglesound = true;
    public final Context context = this;
    static ego player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = new ego(context);
        loadGame();
        if(player == null)
            player = new ego(context);
        updateMarket();
    }

    public void buyHealth( View view ) {
        player.addHealth();
        updateMarket();
    }

    public void buySpeed( View view ) {
        player.addSpeed();
        updateMarket();
    }

    public void buyAttack( View view ) {
        player.upgradeActive();
        updateMarket();
    }

    public void updateMarket() {
        //saveGame();
        final TextView gtext = (TextView) findViewById(R.id.gtext);
        final TextView ptext = (TextView) findViewById(R.id.ptext);
        final TextView atext = (TextView) findViewById(R.id.atext);
        final TextView htext = (TextView) findViewById(R.id.htext);
        final TextView stext = (TextView) findViewById(R.id.stext);
        final TextView hstext = (TextView) findViewById(R.id.hstext);
        final TextView lstext = (TextView) findViewById(R.id.lstext);
        gtext.setText("GOLD: " + player.getGold());
        ptext.setText("PRICE E: " + player.priceEnhance());
        atext.setText(player.getAttack() + " DAMAGE PER CLICK");
        htext.setText(player.getHealth_total() + " HITPOINTS");
        stext.setText(player.getSpeed() + " KM PER CLICK");
        hstext.setText("HIGHSCORE:  " + player.getHigh_score() + "KILOMETERS");
        lstext.setText("LAST SCORE: " + player.getLatest_score() + "KILOMETERS");
    }

    /*
    public void updateMarket( View view ) {
        saveGame();
        final TextView gtext = (TextView) findViewById(R.id.gtext);
        final TextView ptext = (TextView) findViewById(R.id.ptext);
        final TextView atext = (TextView) findViewById(R.id.atext);
        final TextView htext = (TextView) findViewById(R.id.htext);
        final TextView stext = (TextView) findViewById(R.id.stext);
        final TextView hstext = (TextView) findViewById(R.id.hstext);
        final TextView lstext = (TextView) findViewById(R.id.lstext);
        gtext.setText("GOLD: " + player.getGold());
        ptext.setText("PRICE E: " + player.priceEnhance());
        atext.setText(player.getAttack() + " DAMAGE PER CLICK");
        htext.setText(player.getHealth_total() + " HITPOINTS");
        stext.setText(player.getSpeed() + " KM PER CLICK");
        hstext.setText("HIGHSCORE:  " + player.getHigh_score() + "KILOMETERS");
        lstext.setText("LAST SCORE: " + player.getLatest_score() + "KILOMETERS");
    }*/

    public void updateMarket(View view) {
        Intent intent = new Intent(this, Market.class);
        startActivity(intent);
    }

    public void startQuest( View view ) {
        player.setContext(App.getContext());
        saveGame();
        questing = true;
        player.goOnQuest();
        Intent intent = new Intent(this, Quest.class);
        startActivity(intent);
    }

    public void toggleSound( View view ) {
        if (togglesound) togglesound = false;
        else togglesound = true;
    }

    public double tenthilizer( double a ) {
        return ((double) ((int) (10 * a))) / 10;
    }

    public void loadGame() {
        SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        player = gson.fromJson(json, ego.class);
    }

    public void saveGame() { /*
        SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        //gson.toJson(new Object());
        Log.d("String", gson.toJson(player));
        //String json = gson.toJson(player);
        //prefsEditor.putString("MyObject", json);
        prefsEditor.commit(); */
    }

    public void restartGame( View view) {
        player = new ego(context);
        questing = false;
        click = false;
        togglesound = true;

        updateMarket();
    }

    public void popUp(String s) {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.basic_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final TextView promptText = (TextView) promptsView
                .findViewById(R.id.textView1);
        promptText.setText(s);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
