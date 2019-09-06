package com.eliotshekhtman.clickerquest;

import android.content.Context;
import android.graphics.Bitmap;

public class ego {
    public int health_total, health_instant, enhancements;
    public inventory inv;
    public transient Context c;
    public transient int[] body = new int[14];
    public transient Object mFragments;
    /*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
     */
    public double speed, latest_score, high_score;
    public weapons[] arms;
    public int activeWeapon;
    // private ArrayList<armor> protection = new ArrayList<armor>();
    // shield sh;

    public ego(Context context) {
        c = context;
        inv = new inventory(context);
        health_total = 30; health_instant = 30; enhancements = 0;
        speed = 1;
        arms = new weapons[3];
        addWeapon(inv.getWeapon("STICK"));
        autoFistAdd();
        activeWeapon = 0;
    }

    public void setContext(Context context) { c = context; }

    public boolean addWeapon(weapons w) {
        autoFistRem();
        if(arms[0] != null & arms[1] != null & arms[2] != null) return false;
        sortArms(); arms[2] = w; sortArms();
        autoFistAdd();
        return true;
    }

    public boolean removeWeapon(weapons w) {
        if(w instanceof fist) return false;
        if(arms[0] == w) arms[0] = null;
        else if(arms[1] == w) arms[1] = null;
        else if(arms[2] == w) arms[2] = null;
        else return false;
        inv.addWeapon(w);
        sortArms();
        autoFistAdd();
        return true;
    }

    public void goOnQuest() {
        heal();
        autoFistAdd();
        activeWeapon = 0;
        setLatest_score(0);
    }

    public void swapWeapons() {
        activeWeapon++; if(activeWeapon > 2) activeWeapon = 0;
    }
    public Bitmap getActive() {
        return arms[activeWeapon].getPic();
    }

    public void heal() { health_instant = health_total; }
    public void heal(double healthGain) { health_instant += healthGain; if(health_instant > health_total) heal(); }
    public int attack() { return arms[activeWeapon].attack(); }
    public boolean getHit(int[] attack) {
        int damage = attack[0];
        if(attack[1] == 0) damage *= 2;
        health_instant -= damage;
        if(health_instant > 0) return true;
        health_instant = 0;
        return false;
    }

    public void setLatest_score(double loc) { latest_score = niceify(loc); }
    public void setHigh_score() {
        if(latest_score > high_score) high_score = latest_score;
    }
    public void collectResources(int[] loot) {
        inv.addGold(loot[0]);
        inv.addFur(loot[2]);
        inv.addIron(loot[3]);
        inv.addScale(loot[4]);
        inv.addQ(loot[5]);
        heal(loot[1]);
    }

    public boolean addHealth() {
        double priceEnhance = priceEnhance();
        if(inv.getGold() < priceEnhance)
            return false;
        inv.addGold((-1) * priceEnhance);
        health_total += 5; heal(); enhancements++;
        return true;
    }
    public boolean addSpeed() {
        double priceEnhance = priceEnhance();
        if(inv.getGold() < priceEnhance)
            return false;
        inv.addGold((-1) * priceEnhance);
        speed += 0.1; enhancements++;
        return true;
    }

    public boolean upgradeActive() {
        double priceUpgrade = priceUpgrade(arms[activeWeapon]);
        if(inv.getGold() < priceUpgrade)
            return false;
        boolean didit = arms[activeWeapon].upgrade();
        if(didit)
            inv.addGold((-1) * priceUpgrade);
        return true;
    }

    private void sortArms() {
        autoFistRem();
        if(arms[0] == null & arms[1] != null) { arms[0] = arms[1]; arms[1] = null; }
        if(arms[1] == null & arms[2] != null) { arms[1] = arms[2]; arms[2] = null; }
        if(arms[0] == null & arms[1] != null) { arms[0] = arms[1]; arms[1] = null; }
        autoFistAdd();
    }

    private void autoFistAdd() {
        if(arms[0] == null) arms[0] = new fist(c);
        if(arms[1] == null) arms[1] = new fist(c);
        if(arms[2] == null) arms[2] = new fist(c);
    }
    private void autoFistRem() {
        if(arms[0] instanceof fist) arms[0] = null;
        if(arms[1] instanceof fist) arms[1] = null;
        if(arms[2] instanceof fist) arms[2] = null;
    }

    private double niceify(double n) {
        return (double) ((int) (n * 100)) / 100;
    }

    public double priceEnhance() {
        autoFistAdd();
        int tot = inv.getTotalNumberOfUpgrades() + enhancements + arms[0].getUpgradeNumber() + arms[1].getUpgradeNumber() + arms[2].getUpgradeNumber();
        return niceify(10*Math.pow(1.054, tot));
    }

    public double priceUpgrade(weapons w) {
        int tot = enhancements + w.getUpgradeNumber();
        return niceify(10*Math.pow(1.054, tot));
    }

    public double getGold() { return inv.getGold(); }
    public int getHealth() { return health_instant; }
    public int getHealth_total() { return health_total; }
    public double getSpeed() { return speed; }
    public int getAttack() { return arms[activeWeapon].baseAttack + arms[activeWeapon].getUpgradeNumber(); }
    public double getLatest_score() { return latest_score; }
    public double getHigh_score() { return high_score; }
    public int activeUpgrades() { return arms[activeWeapon].getUpgradeNumber(); }
}
