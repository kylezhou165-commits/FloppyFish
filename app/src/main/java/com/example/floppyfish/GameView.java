package com.example.floppyfish;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View
{
    private static final int PIPE_COUNT = 5;
    private Bird bird;
    private Pipe[] pipes = new Pipe[PIPE_COUNT];
    private long lastTime;
    private float scale = 1f;
    private float offsetX = 0f;
    private float offsetY = 0f;
    private float virtualHeight = 0f;
    private int score = 0;

    public GameView(Context context)
    {
        super(context);
        bird = new Bird(100, 200);
        createInitialPipes();
        lastTime = System.nanoTime();
        setOnClickListener(v -> bird.jump());
    }

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        bird = new Bird(100, 200);
        createInitialPipes();
        lastTime = System.nanoTime();
        setOnClickListener(v -> bird.jump());
    }


    private void createInitialPipes()
    {
        for (int i = 0; i < PIPE_COUNT; i++)
        {
            float x = 400 + i * 300;
            float y = 100 + (float) (Math.random() * 300);
            pipes[i] = new Pipe(x, y);
        }
    }

    private void update(float dt)
    {
        bird.update(dt);
        if (bird.getY() > virtualHeight || bird.getY() < 0)
        {
            resetGame();
        }

        for (int i = 0; i < PIPE_COUNT; i++)
        {
            pipes[i].update(GameConstants.SCROLL_SPEED * dt);

            if (RectF.intersects(bird.getHitbox(), pipes[i].getHitboxUp()) ||
                    RectF.intersects(bird.getHitbox(), pipes[i].getHitboxDown(virtualHeight)))
            {
                resetGame();
            }
        }

        if (pipes[0].isOffScreen())
        {
            Pipe first = pipes[0];
            for (int i = 0; i < PIPE_COUNT - 1; i++)
            {
                pipes[i] = pipes[i + 1];
            }
            float nX = pipes[PIPE_COUNT - 2].getX() + 300;
            float nY = 100 + (float) (Math.random() * 300);
            first.reset(nX, nY);
            pipes[PIPE_COUNT - 1] = first;
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        scale = (float) w / GameConstants.BASE_WIDTH;
        virtualHeight = (float) h / scale;
        offsetX = 0;
        offsetY = 0;
    }

    protected void onDraw(Canvas canvas)
    {
        long now = System.nanoTime();
        float dt = (now - lastTime) / 1_000_000_000f;
        lastTime = now;
        update(dt);

        Paint birdPaint = new Paint();
        birdPaint.setColor(Color.YELLOW);
        Paint pipePaint = new Paint();
        pipePaint.setColor(Color.GREEN);

        canvas.drawColor(Color.CYAN);
        canvas.save();
        canvas.translate(offsetX, offsetY);
        canvas.scale(scale, scale);

        canvas.drawRect(bird.getHitbox(), birdPaint);
        for (int i = 0; i < PIPE_COUNT; i++)
        {
            Pipe p = pipes[i];
            canvas.drawRect(p.getHitboxUp(), pipePaint);
            canvas.drawRect(p.getHitboxDown(virtualHeight), pipePaint);
        }
        canvas.restore();
        invalidate();
    }
    private void resetGame()
    {
        bird.setY(200);
        createInitialPipes();
        lastTime = System.nanoTime();
    }
}
