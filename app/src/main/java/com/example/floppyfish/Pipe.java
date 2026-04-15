package com.example.floppyfish;

import android.graphics.RectF;

public class Pipe {
    private float width = 52f;
    private float gapY = 110f;
    private float x;
    private float y; //top of the bottom pipe
    private RectF hitboxUp;
    private RectF hitboxDown;
    public Pipe(float startX, float topY) {
        x = startX; //Left point of pipe
        y = topY; //Right point of pipe

        hitboxUp = new RectF(x, 0f, x + width, y); //Hitbox for up
        hitboxDown = new RectF(x, y + gapY, x + width, GameConstants.BASE_HEIGHT); //Hitbox for lower
    }
    public RectF getHitboxUp()
    {
        return hitboxUp;
    }
    public RectF getHitboxDown()
    {
        return hitboxDown;
    }
    public void update(float speed) {
        x -= speed;
        hitboxUp.offset(-speed, 0);
        hitboxDown.offset(-speed, 0);
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }
}
