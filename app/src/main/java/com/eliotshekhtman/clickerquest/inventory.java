package com.eliotshekhtman.clickerquest;

import android.content.Context;

import java.util.ArrayList;

public class inventory {
    private double gold;
    private int fur, iron, scale, Q;
    private ArrayList<weapons> arms;
    private Context context;

    public inventory(Context context) {
        gold = 100; fur = 0; iron = 0; scale = 0; Q = 0;
        this.context = context;
        arms = new ArrayList<weapons>();
        arms.add(new stick(context));
    }

    public ArrayList<weapons> getArms() { return arms; }

    public weapons peekWeapon(String s) {
        for(weapons w: arms)
            if(w.name.equals(s)) return w;
        return null;
    }

    public weapons getWeapon(String s) {
        weapons w = peekWeapon(s);
        arms.remove(w);
        return w;
    }

    public void addWeapon(weapons w) {
        arms.add(w);
    }

    public int getTotalNumberOfUpgrades() {
        int tot = 0;
        for(weapons w:arms) {
            tot += w.getUpgradeNumber();
        }
        return tot;
    }

    public double getGold() { gold = niceify(gold); return gold; }
    public int getFur() { return fur; }
    public int getIron() { return iron; }
    public int getScale() { return scale; }
    public int getQ() { return Q; }

    public boolean addGold(double g) {
        if(gold + g < 0) return false;
        gold += g; gold = niceify(gold); return true;
    }
    public boolean addFur(int f) {
        if(fur + f < 0) return false;
        fur += f; return true;
    }
    public boolean addIron(int i) {
        if(iron + i < 0) return false;
        iron += i; return true;
    }
    public boolean addScale(int s) {
        if(scale + s < 0) return false;
        scale += s; return true;
    }
    public boolean addQ(int q) {
        if(Q + q < 0) return false;
        Q += q; return true;
    }

    private double niceify(double n) {
        return (double) ((int) (n * 100)) / 100;
    }
}
