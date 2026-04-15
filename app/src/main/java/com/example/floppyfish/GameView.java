package com.example.floppyfish;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.util.AttributeSet;

public class GameView extends View {

    private static final int PIPE_COUNT = 5;

    private Bird bird;
    private Pipe[] pipes = new Pipe[PIPE_COUNT];

    private Paint birdPaint;
    private Paint pipePaint;

    private long lastTime;

    public GameView(Context context) {
        super(context);

        bird = new Bird(100, 200);

        birdPaint = new Paint();
        birdPaint.setColor(Color.YELLOW);

        pipePaint = new Paint();
        pipePaint.setColor(Color.GREEN);

        createInitialPipes();

        lastTime = System.nanoTime();

        setOnClickListener(v -> bird.jump());
    }
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bird = new Bird(100, 200);

        birdPaint = new Paint();
        birdPaint.setColor(Color.YELLOW);

        pipePaint = new Paint();
        pipePaint.setColor(Color.GREEN);

        createInitialPipes();

        lastTime = System.nanoTime();

        setOnClickListener(v -> bird.jump());
    }

    private void createInitialPipes() {
        for (int i = 0; i < PIPE_COUNT; i++) {
            float x = GameConstants.BASE_WIDTH + 200 + (float)(Math.random() * 200);
            float y = 80 + (float)(Math.random() * 250);
            pipes[i] = new Pipe(x, y);
        }
    }

    private void update(float dt) {
        bird.update(dt);

        for (int i = 0; i < PIPE_COUNT; i++) {
            pipes[i].update(GameConstants.SCROLL_SPEED * dt);
        }

        if (pipes[0].isOffScreen()) {
            for (int i = 0; i < PIPE_COUNT - 1; i++) {
                pipes[i] = pipes[i + 1];
            }

            float x = GameConstants.BASE_WIDTH + 150 + (float)(Math.random() * 200);
            float y = 80 + (float)(Math.random() * 250);
            pipes[PIPE_COUNT - 1] = new Pipe(x, y);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long now = System.nanoTime();
        float dt = (now - lastTime) / 1_000_000_000f;
        lastTime = now;

        update(dt);

        canvas.drawColor(Color.CYAN);

        canvas.drawRect(bird.getHitbox(), birdPaint);

        for (int i = 0; i < PIPE_COUNT; i++) {
            Pipe p = pipes[i];
            canvas.drawRect(p.getHitboxUp(), pipePaint);
            canvas.drawRect(p.getHitboxDown(), pipePaint);
        }

        invalidate();
    }
}
