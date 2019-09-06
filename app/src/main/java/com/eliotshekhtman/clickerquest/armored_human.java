package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class armored_human extends tier3 {
    public armored_human(Context c) {
        super(c);
        name = "ARMORED HUMAN";
        attack = 3;
        health = 150;
        speed = 0.5+fuzz();
        resources[0] = 50;
        pic = makePicture(R.drawable.armouredhuman, size);
        attackDistr = new int[] {5,10,4,2,1,4,2,1,0,1,1,0,1,1};
    }
}
