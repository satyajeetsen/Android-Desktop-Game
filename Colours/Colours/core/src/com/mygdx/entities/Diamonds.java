package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyMainGame;

public class Diamonds extends B2DSprite {
    public Diamonds(Body body) {
        super(body);

        Texture texdiamond = MyMainGame.con.getTexture("diamonds");
        TextureRegion[] sprites = TextureRegion.split(texdiamond, 16, 16)[0];
        setAnimation(sprites, 1 / 12f);
    }
}
