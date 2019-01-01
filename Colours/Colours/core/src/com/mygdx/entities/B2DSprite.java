package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.manager.Animation;
import com.mygdx.manager.Box2DVariables;

public class B2DSprite {
    protected Body body;
    protected Animation animation;
    protected float width;
    protected float height;

    public B2DSprite(Body body){
        this.body=body;
        animation=new Animation();
    }
    public void setAnimation(TextureRegion[] texreg,float delay){
        animation.setFrames(texreg,delay);
        width=texreg[0].getRegionWidth();
        height=texreg[0].getRegionHeight();
        System.out.println("Region width"+width);
        System.out.println("Region height"+height);
    }
    public void update(float dt){
        animation.update(dt);

    }
    public void render(SpriteBatch sb){
     sb.begin();
     sb.draw(animation.getFrame(),body.getPosition().x* Box2DVariables.PPM-width/2,body.getPosition().y*Box2DVariables.PPM-height/2);
     sb.end();
    }

    public Body getBody(){
        return body;
    }
    public Vector2 getPosition(){
              return body.getPosition();
    }
    public void setPosition(int x,int y){
        body.getPosition().x=x;
        body.getPosition().y=y;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }
}
