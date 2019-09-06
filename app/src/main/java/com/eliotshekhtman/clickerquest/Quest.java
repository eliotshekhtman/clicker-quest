package com.eliotshekhtman.clickerquest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Random;

public class Quest extends AppCompatActivity {

    GameView gameView;
    String ls;
    int health;
    final Context context = this;

    float tapx;
    float tapy;

    ArrayList<mon> monsterList = new ArrayList<mon>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questing);
        tapx = 1; tapy = 0;
        health = MainActivity.player.getHealth();
        // Initialize gameView and set it as the view
        gameView = new GameView(this);
        setContentView(gameView);
    }

    // GameView class will go here

    // Here is our implementation of GameView
    // It is an inner class.
    // Note how the final closing curly brace }
    // is inside SimpleGameEngine

    // Notice we implement runnable so we have
    // A thread and can override the run method.
    class GameView extends SurfaceView implements Runnable {

        Bitmap defBackground_sample = makePicture(R.drawable.defbackground_sample2, getScreenWidth(), getScreenHeight());
        Bitmap board = makePicture(R.drawable.board2, getScreenWidth(), (int) (getScreenHeight() * 63/200));
        // This is our thread
        Thread gameThread = null;
        // This is new. We need a SurfaceHolder
        // When we use Paint and Canvas in a thread
        // We will see it in action in the draw method soon.
        SurfaceHolder ourHolder;
        // A boolean which we will set and unset
        // when the game is running- or not.
        volatile boolean playing;
        // A Canvas and a Paint object
        Canvas canvas;
        Paint paint;
        // This variable tracks the game frame rate
        long fps;
        // This is used to help calculate the fps
        private long timeThisFrame;
        // When it is first opened, it knows that you aren't tapping
        boolean tap = false;
        // When the we initialize (call new()) on gameView
        // This special constructor method runs
        public GameView(Context context) {
            // The next line of code asks the
            // SurfaceView class to set up our object.
            // How kind.
            super(context);

            // Initialize ourHolder and paint objects
            ourHolder = getHolder();
            paint = new Paint();

            // Set our boolean to true - game on!
            playing = true;

        }

        @Override
        public void run() {
            while (playing) {

                // Capture the current time in milliseconds in startFrameTime
                long startFrameTime = System.currentTimeMillis();

                // Update the frame
                update();

                // Draw the frame
                draw();
                // Calculate the fps this frame
                // We can then use the result to
                // time animations and more.
                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame > 0) {
                    fps = 1000 / timeThisFrame;
                }

            }

        }

        // Everything that needs to be updated goes in here
        public void update() {
            // If you died, no need to update anything
            if(MainActivity.player.getHealth() <= 0) return;
            // If tap, chance for monster spawning, you moving, or you attacking
            if(tap) {
                tap = false;
                // 3.0/16 * getScreenWidth, +=getScreenHeight * 3.0/5
                if(Math.sqrt(Math.pow(tapx - 3.0/16 * getScreenWidth(),2) + Math.pow(tapy - (3.0/8 * getScreenWidth() + 3.0/5 * getScreenHeight()),2)) <= getScreenWidth() / 8) {
                    MainActivity.player.swapWeapons();
                }
                // If you click on the board and there aren't any monsters, can move and possibly spawn a monster
                else if(tapy > (int) (getScreenHeight() * 3.5/5) && monsterList.size() == 0) {
                    MainActivity.player.setLatest_score(MainActivity.player.getLatest_score() + MainActivity.player.getSpeed());
                    mon m = Spawner.spawnMonster(MainActivity.player.getLatest_score(), context);
                    if(m != null)
                        monsterList.add(m);
                }
                // You can attack a monster
                for(int i = 0; i < monsterList.size(); i++) {
                    mon m = monsterList.get(i);
                    double change = Math.sqrt((tapx - m.getXpos()) * (tapx - m.getXpos()) + (tapy - m.getZpos()) * (tapy - m.getZpos()));
                    if(change <= m.getSize()) {
                        boolean b = m.getHit(MainActivity.player.attack());
                        Log.d("Hit", change + "");
                        if(!b) {
                            MainActivity.player.collectResources(m.getResources());
                            monsterList.remove(m); i--;
                        }
                    }
                }
            }
            // Either way, the monster can attack you and move, or another may spawn
            for(int i = 0; i < monsterList.size(); i++) {
                mon m = monsterList.get(i);
                MainActivity.player.getHit(m.getAttack());
                m.move(fps);
                mon z = Spawner.spawnMonster(m.getName(), MainActivity.player.getLatest_score(), context);
                if(z != null)
                    monsterList.add(z);
            }
            // Make sure the game knows what stage you're in (forest, etc.)
            //distance = (double) ((int) (distance * 10)) / 10.0;
            location();
        }

        // Draw the newly updated scene
        public void draw() {
            // Make sure our drawing surface is valid or we crash
            if (ourHolder.getSurface().isValid()) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas();
                // Draw the background color
                canvas.drawColor(locColorScheme()[0]);
                if(ls.equals("Forest"))
                    canvas.drawBitmap(defBackground_sample, 0, 0, paint);
                canvas.drawBitmap(board, 0, (int) (getScreenHeight() * (1-63.0/200)), paint);
                // Draw enemy's position
                for(mon m: monsterList) {
                    canvas.drawBitmap(m.getPic(), m.getXpos() /*- m.getSize()/2*/, m.getZpos() /*- m.getSize()/2*/, paint);
                }
                paint.setStrokeWidth(50);
                int radius = (int) (getScreenWidth() / 8);
                int displacement = (int) (getScreenWidth() * 1.0/16);
                int hbarLength = (int) (getScreenWidth() * 13/14 - (int) (displacement + radius + radius * Math.sqrt(3)/2.0));
                paint.setTextSize(radius / 2);
                // Make the healthbar
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb(255,  255, 215, 0));
                canvas.drawRect((int) (displacement + radius + radius * Math.sqrt(3)/2.0), (int) (getScreenHeight() * 3.5/5 + displacement + radius * 1.0/2), getScreenWidth() * 13/14, (int) (getScreenHeight() * 3.5/5 + displacement + radius), paint);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor((Color.argb(255, 192, 192, 192)));
                canvas.drawRect((int) (displacement + radius + radius * Math.sqrt(3)/2.0), (int) (getScreenHeight() * 3.5/5 + displacement + radius * 1.0/2), getScreenWidth() * 13/14, (int) (getScreenHeight() * 3.5/5 + displacement + radius), paint);
                // Fill in the healthbar
                paint.setColor(Color.argb(255, 255, 0, 0));
                canvas.drawRect((int) (displacement + radius + radius * Math.sqrt(3)/2.0), (int) (getScreenHeight() * 3.5/5 + displacement + radius * 1.0/2), (int) (displacement + radius + radius * Math.sqrt(3)/2.0 + hbarLength * MainActivity.player.getHealth()/health), (int) (getScreenHeight() * 3.5/5 + displacement + radius), paint);
                // Make the weapon holder
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb(255,  255, 215, 0));
                canvas.drawCircle((int) (displacement + radius), (int) (getScreenHeight() * 3.5/5 + displacement + radius), radius, paint);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor((Color.argb(255, 192, 192, 192)));
                canvas.drawCircle((int) (displacement + radius), (int) (getScreenHeight() * 3.5/5 + displacement + radius), radius, paint);
                // Give the current distance
                canvas.drawText("DISTANCE: " + MainActivity.player.getLatest_score(), (int) (displacement * 2 + radius * 2), (int) (getScreenHeight() * 3.5/5 + displacement + radius * 1.75), paint);

                canvas.drawBitmap(MainActivity.player.getActive(), displacement, (int) (getScreenHeight() * 3.5/5) + displacement, paint);

                if (MainActivity.player.getHealth() <= 0) {
                    canvas.drawText("YOU DIED. " + "\n" + "RETURN TO THE MARKET.", 40, getScreenHeight() / 2 - 300, paint);
                    MainActivity.player.setHigh_score();
                    //Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    //startActivity(intent);
                }
                // Draw everything to the screen
                ourHolder.unlockCanvasAndPost(canvas);
            }

        }

        // If SimpleGameEngine Activity is paused/stopped
        // shutdown our thread.
        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }

        }

        // If SimpleGameEngine Activity is started then
        // start our thread.
        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        // The SurfaceView class implements onTouchListener
        // So we can override this method and detect screen touches.
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

                // Player has touched the screen
                case MotionEvent.ACTION_DOWN:

                    // Set tap so tap is put down
                    tap = true;
                    tapx = (int)motionEvent.getX();
                    tapy = (int)motionEvent.getY();
                    break;

                // Player has removed finger from screen
                case MotionEvent.ACTION_UP:

                    // Set tap is raised
                    tap = false;

                    break;
            }
            return true;
        }
    }
    // This is the end of our GameView inner class

    // More SimpleGameEngine methods will go here

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        gameView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        gameView.pause();
    }

    private static int randInt(int lower, int upper) {
        Random rand = new Random();
        int result = rand.nextInt(upper+1) + lower;
        return result;
    }

    public void location() {
        if (MainActivity.player.getLatest_score() < 100) ls = "Forest";
        else if (MainActivity.player.getLatest_score() < 175) ls = "Steppe";
        else if (MainActivity.player.getLatest_score() < 212) ls = "River";
        else if (MainActivity.player.getLatest_score() < 300) ls = "Steppe";
        else if (MainActivity.player.getLatest_score() < 450) ls = "Abandoned City";
        else if (MainActivity.player.getLatest_score() < 500) ls = "Sewer";
        else if (MainActivity.player.getLatest_score() < 600) ls = "Abandoned City";
        else if (MainActivity.player.getLatest_score() < 850) ls = "Mountain";
        else if (MainActivity.player.getLatest_score() < 912) ls = "Temple";
        else if (MainActivity.player.getLatest_score() < 1000) ls = "Tunnel";
        else if (MainActivity.player.getLatest_score() < 1500) ls = "Cave";
        else ls = "Hell";
    }

    public int[] locColorScheme() {
        int[] colorScheme = new int[2];
        if (ls.equals("Forest")) {
            colorScheme[0] = Color.argb(255,  19, 124, 9);
        }
        else if (ls.equals("Steppe")) {
            colorScheme[0] = Color.argb(255,  172, 214, 23);
        }
        else if (ls.equals("River")) {
            colorScheme[0] = Color.argb(255,  9, 180, 247);
        }
        else if (ls.equals("Abandoned City")) {
            colorScheme[0] = Color.argb(255,  153, 177, 198);
        }
        else if (ls.equals("Sewer")) {
            colorScheme[0] = Color.argb(255,  48, 61, 49);
        }
        else if (ls.equals("Mountain")) {
            colorScheme[0] = Color.argb(255,  221, 221, 221);
        }
        else {
            colorScheme[0] = Color.argb(255,  155, 15, 15);
        }
        return colorScheme;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public Bitmap makePicture(int id, int s) {
        return makePicture(id, s, s);
    }

    public Bitmap makePicture(int id, int s, int z) {
        Bitmap b = BitmapFactory.decodeResource(this.getResources(), id);
        b = Bitmap.createScaledBitmap(b, s, z, false);
        return b;
    }
}
