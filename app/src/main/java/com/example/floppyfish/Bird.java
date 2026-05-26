package com.example.floppyfish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class Bird {

    private float size = 24f;          // square size
    private float x;                   // center or left position
    private float y;                   // top position
    private float velocity = 0f;       // vertical speed
    private Bitmap fish;
    ;
    private RectF hitbox;

    public Bird(Context context, float startX, float startY, int resourceId) {
        x = startX;
        y = startY;
        hitbox = new RectF(x, y, x + size, y + size);
        Bitmap rawFish = BitmapFactory.decodeResource(context.getResources(), resourceId);
        fish = rawFish.createScaledBitmap(rawFish, 64, 64, true );
    }

    public void update(float dt) {
        velocity += GameConstants.GRAVITY * dt;

        if (velocity > GameConstants.MAX_FALL_SPEED) {
            velocity = GameConstants.MAX_FALL_SPEED;
        }

        y += velocity * dt;

        hitbox.set(x, y, x + size, y + size);
    }

    public void jump() {
        velocity = -GameConstants.JUMP_POWER;
    }

    public RectF getHitbox() {
        return hitbox;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float b) {
        y = b;
    }

    public void setVelocity(float g) {
        velocity = g;
    }

    public float getSize() {
        return size;
    }
    public Bitmap getBitmap() {return fish;}
}
