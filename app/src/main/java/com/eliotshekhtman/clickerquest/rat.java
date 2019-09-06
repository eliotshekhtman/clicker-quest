package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class rat extends tier1 {
    rat(Context c) {
        super(c);
        name = "RAT";
        attack = 2;
        health = 10;
        speed = 1+fuzz();
        resources[0] = 5;
        pic = makePicture(R.drawable.rat2, size);
        attackDistr = new int[] {0,2,4,0,0,4,0,0,10,7,3,10,7,3};
    }


}