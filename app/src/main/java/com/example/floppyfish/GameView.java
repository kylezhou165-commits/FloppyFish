package com.example.floppyfish;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.util.AttributeSet;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback
{
    private static final int PIPE_COUNT = 5;
    private final Bird bird;

    private Bitmap aurabackground;
    private  Pipe[] pipes = new Pipe[PIPE_COUNT];
    private long lastTime;

    private boolean auraMonster;
    private float scale = 1f;
    private float offsetX = 0f;
    private float offsetY = 0f;

    private float virtualHeight = 0f;
    private int score = 0;
    Paint birdPaint = new Paint();
    Paint pipePaint = new Paint();
    public boolean running = true;

    private MediaPlayer mp;
    private MediaPlayer mpDeath;

    Context con;

    private SurfaceHolder mSurfaceHolder;
    private Thread gameThread;

    public GameView(Context context)
    {
        super(context);
        con = context;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        bird = new Bird(context, 100, 200, R.drawable.greenfish);
        createInitialPipes();
        lastTime = System.nanoTime();
        setOnClickListener(v -> bird.jump());
        birdPaint.setColor(Color.YELLOW);
        pipePaint.setColor(Color.GREEN);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        auraMonster = false;
    }

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        con = context;
        mSurfaceHolder = getHolder();
        bird = new Bird(context, 100, 200, R.drawable.greenfish);
        createInitialPipes();
        lastTime = System.nanoTime();
        setOnClickListener(v -> bird.jump());
        birdPaint.setColor(Color.YELLOW);
        pipePaint.setColor(Color.GREEN);
        auraMonster = false;
    }

    public GameView(Context context,  boolean auramonsterMode)
    {
        super(context);
        auraMonster = auramonsterMode;
        con = context;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        if(!auramonsterMode)
            bird = new Bird(context, 100, 200, R.drawable.greenfish);
        else
            bird = new Bird(context, 100, 200, R.drawable.auramonster, true);
        createInitialPipes();
        lastTime = System.nanoTime();
        setOnClickListener(v -> bird.jump());
        birdPaint.setColor(Color.YELLOW);
        pipePaint.setColor(Color.GREEN);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);

        mp = new MediaPlayer();
        mp.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );


        if(auramonsterMode)
            mp = MediaPlayer.create(con, R.raw.aurasong);


        mp.setLooping(true);
        mp.start();

        aurabackground = BitmapFactory.decodeResource(con.getResources(), R.drawable.background);

    }





    private void createInitialPipes()
    {

        for (int i = 0; i < PIPE_COUNT; i++)
        {
            float x = 400 + i * 300;
            float y = 100 + (float) (Math.random() * 300);
            pipes[i] = new Pipe(x, y, auraMonster);
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

            float birdX = bird.getX() + bird.getSize();

            float birdUY = bird.getY();
            float birdDY = bird.getY() + bird.getSize();

            float pipeX = pipes[i].getX();
            float pipeUY = pipes[i].getY();
            float pipeDY = pipes[i].getY()+pipes[i].getGapY();

            if(birdX > pipeX && birdX - bird.getSize() < pipeX + pipes[i].getWidth())
            {
                if(birdUY < pipeUY || birdDY > pipeDY)
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


    public void run()
    {
        Bitmap stairs = BitmapFactory.decodeResource(con.getResources(), R.drawable.stairs);
        if(auraMonster)
            aurabackground = aurabackground.createScaledBitmap(aurabackground, getWidth(), getHeight(), true );

        while (running)
        {
            if (!mSurfaceHolder.getSurface().isValid()) continue;
            Canvas canvas = mSurfaceHolder.lockCanvas();
            if (canvas != null)
            {
                long now = System.nanoTime();
                float dt = (now - lastTime) / 1_000_000_000f;
                lastTime = now;
                update(dt);
                canvas.drawColor(Color.MAGENTA);
                canvas.drawColor(Color.CYAN);
                if(auraMonster)
                {
                    canvas.drawBitmap(aurabackground, 0, 0, null);
                }
                canvas.save();
                canvas.translate(offsetX, offsetY);
                canvas.scale(scale, scale);
                canvas.drawBitmap(bird.getBitmap(), bird.getX(), bird.getY(), null);

                for (int i = 0; i < PIPE_COUNT; i++)
                {
                    Pipe p = pipes[i];
                    if(!auraMonster) {
                        canvas.drawRect(p.getHitboxUp(), pipePaint);
                        canvas.drawRect(p.getHitboxDown(virtualHeight), pipePaint);
                    }
                    else
                    {
                        RectF topper = p.getHitboxUp();
                        RectF bopper = p.getHitboxDown(virtualHeight);
                        Bitmap up = stairs.createScaledBitmap(stairs, (int)p.getWidth(), (int)topper.bottom, true );
                        Bitmap bottom = stairs.createScaledBitmap(stairs, (int)p.getWidth(), (int)(bopper.bottom - bopper.top), true );


                        canvas.drawBitmap(up, topper.left, topper.top, null);
                        canvas.drawBitmap(bottom, bopper.left, bopper.top, null);
                    }

                }
                mSurfaceHolder.unlockCanvasAndPost(canvas);

            }
        }
    }
    public void resume()
    {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void pause()
    {
        running = false;
        if (gameThread != null)
        {
            try {
                gameThread.join(500);
                gameThread = null;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void resetGame()
    {
        mpDeath = MediaPlayer.create(con, R.raw.auredeath);
        mpDeath.start();
        bird.setY(200);
        bird.setVelocity(0f);
        createInitialPipes();
        lastTime = System.nanoTime();
    }

    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceCreated(@NonNull SurfaceHolder holder)
    {
        resume();
    }

    public void surfaceDestroyed(@NonNull SurfaceHolder holder)
    {
        pause();
    }

}