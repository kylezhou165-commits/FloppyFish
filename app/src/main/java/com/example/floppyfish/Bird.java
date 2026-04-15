package com.example.floppyfish;

import android.graphics.RectF;

public class Bird {

    private float size = 24f;          // square size
    private float x;                   // center or left position
    private float y;                   // top position
    private float velocity = 0f;       // vertical speed

    private RectF hitbox;

    public Bird(float startX, float startY) {
        x = startX;
        y = startY;

        hitbox = new RectF(x, y, x + size, y + size);
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

    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public float getSize()
    {
        return size;
    }
}