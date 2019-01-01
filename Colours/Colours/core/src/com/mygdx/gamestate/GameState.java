package com.mygdx.gamestate;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyMainGame;
import com.mygdx.manager.BoundedCamera;
import com.mygdx.manager.GameStateManager;

public abstract class GameState {

    protected GameStateManager gsm;
    protected MyMainGame game;
    protected SpriteBatch sb;
    protected BoundedCamera cam;
    protected OrthographicCamera displayCam;

    protected GameState(GameStateManager gsm){
        this.gsm=gsm;
        game=gsm.game();
        sb=game.getSb();
        cam=game.getCam();
        displayCam=game.getdisplayCam();
        init();

    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void render(float delta);
    public abstract void handleInput(float dt);
    public abstract void dispose();
    public abstract void resize(int width,int height);




}
