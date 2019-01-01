package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyMainGame;

public class Spikes extends B2DSprite {
    public Spikes(Body body) {
        super(body);

        Texture texspike = MyMainGame.con.getTexture("spikes");
        TextureRegion[] spikesprite = TextureRegion.split(texspike, 32, 32)[0];
        setAnimation(spikesprite, 1 / 20f);
    }
}
