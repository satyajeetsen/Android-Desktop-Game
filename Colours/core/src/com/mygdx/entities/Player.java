package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyMainGame;
import com.mygdx.gamestate.Play;

public class Player extends B2DSprite {
    private int numDiamonds;
    private int totalDiamonds;
    public boolean grounded =false;


    public Player(Body body) {
        super(body);
        Texture spritetex =MyMainGame.con.getTexture("sprite");
        TextureRegion[] sprites=TextureRegion.split(spritetex,32,32)[0];

        setAnimation(sprites,1/22f);
        width=sprites[0].getRegionWidth();
        height=sprites[0].getRegionHeight();
        System.out.println("Width of sprite "+width);
        System.out.println("Height of sprite "+height);
    }



    public void collectDiamonds(){
        numDiamonds++;
        Play.score+=100;
    }



    public void setTotalDiamonds(int i) {
        totalDiamonds=i;
    }



    public int getNumDiamonds() {
        return numDiamonds;
    }

    public int getTotalDiamonds() {
        return totalDiamonds;
    }






}
