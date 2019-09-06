package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class armed_human extends tier6 {
    armed_human(Context c) {
        super(c);
        name = "ARMED HUMAN";
        attack = 10;
        health = 50;
        speed = 1+fuzz();
        resources[0] = 100;
        pic = makePicture(R.drawable.armedhuman, size);
        attackDistr = new int[] {5,10,4,2,1,4,2,1,0,1,1,0,1,1};
    }
}
