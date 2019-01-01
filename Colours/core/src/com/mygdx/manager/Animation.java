package com.mygdx.manager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
    private TextureRegion[] frames;
    private float time;
    private float delay;
    private int currentFrame;
    private int timesPlayed;

    public Animation(){

    }
    public Animation(TextureRegion[] frames){
         this(frames,1/30f);
    }
    public Animation(TextureRegion[] frames,float delay){
                      setFrames(frames,delay);
    }

    public void setFrames(TextureRegion[] frames,float delay){
        this.delay=delay;
        this.frames=frames;
        currentFrame=0;
        time=0;
        timesPlayed=0;
    }
    public void update(float dt){
        if(delay<=0){
            return;
        }
        time+=dt;
        while(time >=delay)
            step();


    }
    public void step(){
        time-=delay;
        currentFrame++;
        if(currentFrame==frames.length){
            currentFrame=0;
            timesPlayed++;
        }
    }

    public TextureRegion getFrame() {
        return frames[currentFrame];
    }
    public int getTimesPlayed(){
        return timesPlayed;
    }
}
