package com.eliotshekhtman.clickerquest;

import android.content.res.Resources;
import android.graphics.Bitmap;

import java.util.Random;

/**
 * Created by eliotshekhtman on 4/2/17.
 */

public class monster {
    public String nomine;
    public int mattack;
    public int mhealth;
    public double mspeed;
    public int golddrop;
    public int size;
    public int level;
    public int healthgain;

    public long startTime;
    public long elapsedTime;

    Bitmap epic;
    boolean swarm;
    int snumber;

    public float xpos;
    public float zpos;
    double exs;
    double ezs;

    monster(){
        startTime = System.currentTimeMillis();
        elapsedTime = 0L;
        swarm = false;
        healthgain = 0;
        snumber = 0;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    monster(double d) {
        startTime = System.currentTimeMillis();
        elapsedTime = 0L;
        swarm = false;
        healthgain = 0;
        snumber = 0;
        int tot = 0;
        // level 1
        if (d < 50) tot = 27;
        else if (d < 212) tot = 30;
        else if (d < 900) tot = 35;
        else tot = 36;

        int num = randInt(0, tot);

        if (num <= 20) {
            size = getScreenWidth() / 6;
            level = 1;
            if (d < 175) rat();
            else if (d < 212) fish();
            else if (d < 250) rat();
            else if (d < 425) snake();
            else if (d < 450) ratking();
            else if (d < 500) slime();
            else if (d < 600) ratking();
            else if (d < 800) dwarf();
            else if (d < 912) templedog();
        }
        else if (num > 20 & num <= 25) {
            size = getScreenWidth() / 4;
            level = 2;
            if (d < 150) boar();
            else if (d < 175) bison();
            else if (d < 212) trout();
            else if (d < 400) bison();
            else if (d < 450) goblinscout();
            else if (d < 550) abomination();
            else if (d < 600) goblin();
            else if (d < 800) pterodactyl();
            else if (d < 912) wotw();
        }
        else if (num == 26) {
            size = getScreenWidth() / 3;
            level = 4;
            if (d < 350) questingdonkey();
            else if (d < 700) questinggoat();
            else if (d < 912) questingwombat();
        }
        else if (num == 27) {
            size = (int) (getScreenWidth() / 1.5);
            level = 5;
            if (d < 175) cavetroll();
            else if (d < 212) croc();
            else if (d < 450) cavalry();
            else if (d < 500) croc();
            else if (d < 600) wartroll();
            else if (d < 700) wyrm();
            else if (d < 850) jotunn();
            else if (d < 912) sungod();
        }
        else if (num > 27 & num <= 30) {
            size = getScreenWidth() / 3;
            level = 3;
            if (d < 150) wolf();
            else if (d < 175) armouredhuman();
            else if (d < 212) trout();
            else if (d < 300) armouredhuman();
            else if (d < 500) wotw();
            else if (d < 600) goblin();
            else if (d < 700) mountaingorilla();
            else if (d < 850) griffin();
            else if (d < 912) angel();
        }
        else if (num > 30 & num <= 35) {
            size = getScreenWidth() / 3;
            level = 6;
            if (d < 300) armedhuman();
            else if (d < 450) soldier();
            else if (d < 500) desperateone();
            else if (d < 650) wanderer();
            else if (d < 825) guardian();
            else if (d < 912) cultist();
        }
    }

    monster( String s ) {
        startTime = System.currentTimeMillis();
        elapsedTime = 0L;
        if (s.equals("Soldier")) soldier();
        else if (s.equals("Fish")) fish();
        else if (s.equals("Slime")) slime();
        else if (s.equals("Goblin")) goblin();
        else if (s.equals("War Troll")) goblin();
        else if (s.equals("Sun God")) solarminion();
    }

    private void rat() {
        nomine = "Rat";
        mattack = 2;
        mhealth = 10;
        mspeed = 1;
        golddrop = 5;
    }

    private void boar() {
        nomine = "Boar";
        mattack = 3;
        mhealth = 50;
        mspeed = 2;
        golddrop = 20;
    }

    private void questingdonkey() {
        nomine = "Questing Donkey";
        mattack = 0;
        mhealth = 10;
        mspeed = 1;
        golddrop = 500;
    }

    private void cavetroll() {
        nomine = "Cave Troll";
        mattack = 10;
        mhealth = 150;
        mspeed = 2;
        golddrop = 500;
    }

    private void wolf() {
        nomine = "Wolf";
        mattack = 5;
        mhealth = 100;
        mspeed = 3;
        golddrop = 150;
    }

    private void bison() {
        nomine = "Bison";
        mattack = 5;
        mhealth = 75;
        mspeed = 2.5;
        golddrop = 50;
        healthgain = 15;
    }

    private void fish() {
        nomine = "Fish";
        mattack = 1;
        mhealth = 10;
        mspeed = 5;
        golddrop = 15;
        swarm = true;
        snumber = 1;
    }

    private void croc() {
        nomine = "Crocodile";
        mattack = 15;
        mhealth = 200;
        mspeed = 3;
        golddrop = 200;
    }

    private void trout() {
        nomine = "Trout";
        mattack = 3;
        mhealth = 50;
        mspeed = 3;
        golddrop = 40;
    }

    private void armouredhuman() {
        nomine = "Armoured Human";
        mattack = 3;
        mhealth = 150;
        mspeed = 1.5;
        golddrop = 100;
    }

    private void armedhuman() {
        nomine = "Armed Human";
        mattack = 10;
        mhealth = 50;
        mspeed = 2;
        golddrop = 100;
    }

    private void cavalry() {
        nomine = "Cavalry";
        mattack = 25;
        mhealth = 250;
        mspeed = 4;
        golddrop = 600;
    }

    private void snake() {
        nomine = "Snake";
        mattack = 3;
        mhealth = 90;
        mspeed = 5;
        golddrop = 50;
    }

    private void wotw() {
        nomine = "Will-o-the-Wisp";
        mattack = 15;
        mhealth = 320;
        mspeed = 5;
        golddrop = 100;
    }

    private void soldier() {
        nomine = "Soldier";
        mattack = 10;
        mhealth = 110;
        mspeed = 2;
        golddrop = 150;
        swarm = true;
        snumber = 1;
        healthgain = 30;
    }

    private void questinggoat() {
        nomine = "Questing Goat";
        mattack = 0;
        mhealth = 100;
        mspeed = 5;
        golddrop = 1000;
        healthgain = 10;
    }

    private void goblinscout() {
        nomine = "Goblin Scout";
        mattack = 5;
        mhealth = 80;
        mspeed = 4;
        golddrop = 100;
    }

    private void ratking() {
        nomine = "Rat King";
        mattack = 8;
        mhealth = 150;
        mspeed = 1;
        golddrop = 75;
    }

    private void slime() {
        nomine = "Slime";
        mattack = 4;
        mhealth = 50;
        mspeed = 4;
        golddrop = 40;
        swarm = true;
        snumber = 2;
    }

    private void abomination() {
        nomine = "Abomination";
        mattack = 10;
        mhealth = 210;
        mspeed = 2;
        golddrop = 100;
    }

    private void desperateone() {
        nomine = "Desperate One";
        mattack = 10;
        mhealth = 90;
        mspeed = 3.5;
        golddrop = 50;
    }

    private void wanderer() {
        nomine = "Wanderer";
        mattack = 20;
        mhealth = 140;
        mspeed = 1.5;
        golddrop = 150;
    }

    private void goblin() {
        nomine = "Goblin";
        mattack = 10;
        mhealth = 130;
        mspeed = 2;
        golddrop = 150;
        swarm = true;
        snumber = 1;
    }

    private void wartroll() {
        nomine = "War Troll";
        mattack = 25;
        mhealth = 400;
        mspeed = 1;
        golddrop = 1000;
        swarm = true;
        snumber = 2;
    }

    private void wyrm() {
        nomine = "Wyrm";
        mattack = 25;
        mhealth = 500;
        mspeed = 10;
        golddrop = 1500;
    }

    private void mountaingorilla() {
        nomine = "Mountain Gorilla";
        mattack = 15;
        mhealth = 200;
        mspeed = 2;
        golddrop = 200;
    }

    private void dwarf() {
        nomine = "Dwarf";
        mattack = 10;
        mhealth = 134;
        mspeed = 1;
        golddrop = 100;
    }

    private void pterodactyl() {
        nomine = "Pterodactyl";
        mattack = 6;
        mhealth = 170;
        mspeed = 3;
        golddrop = 130;
    }

    private void guardian() {
        nomine = "Guardian";
        mattack = 10;
        mhealth = 340;
        mspeed = 1.5;
        golddrop = 200;
    }

    private void questingwombat() {
        nomine = "Questing Wombat";
        mattack = 0;
        mhealth = 140;
        mspeed = 4;
        golddrop = 1300;
        healthgain = 15;
    }

    private void jotunn() {
        nomine = "Jötunn";
        mattack = 40;
        mhealth = 680;
        mspeed = 1;
        golddrop = 1000;
    }

    private void griffin() {
        nomine = "Griffin";
        mattack = 15;
        mhealth = 190;
        mspeed = 3;
        golddrop = 200;
    }

    private void templedog() {
        nomine = "Temple Dog";
        mattack = 5;
        mhealth = 115;
        mspeed = 3;
        golddrop = 100;
    }

    private void cultist() {
        nomine = "Cultist";
        mattack = 8;
        mhealth = 175;
        mspeed = 2;
        golddrop = 200;
    }

    private void angel() {
        nomine = "Angel";
        mattack = 4;
        mhealth = 262;
        mspeed = 5;
        golddrop = 300;
    }

    private void sungod() {
        nomine = "Sun God";
        mattack = 10;
        mhealth = 1000;
        mspeed = 3;
        golddrop = 2000;
        swarm = true;
        snumber = 3;
    }

    private void solarminion() {
        nomine = "Solar Minion";
        mattack = 5;
        mhealth = 300;
        mspeed = 1;
        golddrop = 100;
    }

    private static int randInt(int lower, int upper) {
        Random rand = new Random();
        int result = rand.nextInt(upper+1) + lower;
        return result;
    }

    public int gat() {
        Random rand = new Random();
        int fg1 = rand.nextInt(100);
        int fg2 = rand.nextInt(100);
        int fg3 = rand.nextInt(100);
        int fg4 = rand.nextInt(100);

        return mattack * (fg1 + fg2 + fg3 + fg4) / 200;
    }
    public int getHealth() { return mhealth; }
    public double getSpeed() { return mspeed; }
    public int getGolddrop() { return golddrop; }
    public String getNomine() { return nomine; }
    public float getXpos() { return xpos; }
    public float getZpos() { return zpos; }

}
