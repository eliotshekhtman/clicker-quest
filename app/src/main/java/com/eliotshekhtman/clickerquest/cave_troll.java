package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class cave_troll extends tier5 {
    public cave_troll(Context c) {
        super(c);
        name = "CAVE TROLL";
        attack = 10;
        health = 150;
        speed = 1+fuzz();
        resources[0] = 500;
        pic = makePicture(R.drawable.cavetroll2, size);
        attackDistr = new int[] {2,10,6,3,1,6,3,1,1,6,8,1,6,8};
    }
}
