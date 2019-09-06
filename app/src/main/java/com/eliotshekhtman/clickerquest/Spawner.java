package com.eliotshekhtman.clickerquest;

import android.content.Context;

import java.util.Random;

public abstract class Spawner {
    static Context context;
    public static mon spawnMonster(double d, Context c) {
        context = c;
        int a = randInt(1,2);
        if(a != 1) return null;
        a = randInt(1, 50);
        if(a <= 20) return spawnTier1(d);
        else if(a <= 25) return spawnTier2(d);
        else if(a == 26) return spawnTier4(d);
        else if(a == 27) return spawnTier5(d);
        else if(d > 50 & a <= 30) return spawnTier3(d);
        else if(d > 212 & a <= 35) return spawnTier6(d);
        else if(d > 900 & a == 36) return null;
        else return null;
    }

    public static mon spawnMonster(String s, double d, Context c) {
        context = c;
        int a = randInt(1, 600);
        if(a == 1) return spawnMonster(d,c);
        a = randInt(1, 600);
        if(s.equals("RAT") & a <= 3) return new rat(context);
        if(s.equals("CAVE TROLL") & a <= 6) return spawnMonster(d,c);
        if(s.equals("FISH") & a <= 6) return new fish(context);
        if(s.equals("TROUT") & a <= 2) return new fish(context);
        if(s.equals("ARMORED HUMAN") & a <= 1) return spawnHuman(d);
        if(s.equals("ARMED HUMAN") & a <= 1) return spawnHuman(d);
        if(s.equals("CAVALRY") & a <= 1) return spawnHuman(d);
        if(s.equals("SOLDIER") & a <= 2) return spawnHuman(d);
        return null;
    }

    private static mon spawnHuman(double d) {
        int a = randInt(1,2);
        if(d < 300) {
            if(a == 1) return new armored_human(context);
            else if(a == 2) return new armed_human(context);
        }
        else {
            if(a == 1) return new soldier(context);
            else if(a == 2) return new armed_human(context);
        }
        return null;
    }

    private static mon spawnTier1(double d) {
        if(d < 175) return new rat(context);
        else if(d < 212) return new fish(context);
        else if(d < 250) return new rat(context);
        else if(d < 425) return new snake(context);
        else return null;
    }

    private static mon spawnTier2(double d) {
        if(d < 150) return new boar(context);
        else if(d < 175) return new bison(context);
        else if(d < 212) return new trout(context);
        return null;
    }

    private static mon spawnTier3(double d) {
        if(d < 150) return new wolf(context);
        else if(d < 175) return new armored_human(context);
        else if(d < 212) return new trout(context);
        else if(d < 300) return new armored_human(context);
        else if(d < 500) return new will_o_the_wisp(context);
        return null;
    }

    private static mon spawnTier4(double d) {
        if(d < 350) return new questing_donkey(context);
        return null;
    }

    private static mon spawnTier5(double d) {
        if(d < 175) return new cave_troll(context);
        if(d < 212) return new crocodile(context);
        if(d < 450) return new cavalry(context);
        if(d < 500) return new crocodile(context);
        return null;
    }

    private static mon spawnTier6(double d) {
        if(d < 300) return new armed_human(context);
        else if(d < 450) return new soldier(context);
        return null;
    }

    /*
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
     */

    private static int randInt(int lower, int upper) {
        Random rand = new Random();
        int result = rand.nextInt(upper+1) + lower;
        return result;
    }
}
