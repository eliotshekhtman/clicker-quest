package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class fish extends tier1 {
    fish(Context c) {
        super(c);
        name = "FISH";
        attack = 1;
        health = 10;
        speed = 3+2*fuzz();
        resources[0] = 15;
        pic = makePicture(R.drawable.fish2, size);
        attackDistr = new int[] {0,1,4,0,0,4,0,0,10,4,1,10,4,1};
    }


}