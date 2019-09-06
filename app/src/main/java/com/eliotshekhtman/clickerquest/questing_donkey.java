package com.eliotshekhtman.clickerquest;

import android.content.Context;

public class questing_donkey extends tier4 {
    questing_donkey(Context c) {
        super(c);
        name = "QUESTING DONKEY";
        attack = 0;
        health = 10;
        speed = 1;
        resources[0] = 500;
        pic = makePicture(R.drawable.questingdonkey, size);
    }
}
