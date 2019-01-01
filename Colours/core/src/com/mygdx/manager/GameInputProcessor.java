package com.mygdx.manager;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class GameInputProcessor extends InputAdapter {

    @Override
    public boolean keyDown(int k) {
        if (k == Input.Keys.UP) {
            MyInput.setKey(MyInput.UP,true);
        }

        if (k == Input.Keys.LEFT) {
            MyInput.setKey(MyInput.LEFT,true);
        }
        if (k == Input.Keys.DOWN) {
            MyInput.setKey(MyInput.DOWN,true);
        }
        if (k == Input.Keys.RIGHT) {
            MyInput.setKey(MyInput.RIGHT,true);
        }
        if (k == Input.Keys.ENTER) {
            MyInput.setKey(MyInput.ENTER,true);
        }
        if (k == Input.Keys.ESCAPE) {
            MyInput.setKey(MyInput.ESCAPE,true);
        }
        if (k == Input.Keys.SPACE) {
            MyInput.setKey(MyInput.SPACE,true);
        }
        if (k == Input.Keys.SHIFT_LEFT ||k == Input.Keys.SHIFT_RIGHT){
            MyInput.setKey(MyInput.SHIFT,true);
        }





        return true;
    }

    @Override
    public boolean keyUp(int k) {

        if (k == Input.Keys.UP) {
            MyInput.setKey(MyInput.UP,false);
        }

        if (k == Input.Keys.LEFT) {
            MyInput.setKey(MyInput.LEFT,false);
        }
        if (k == Input.Keys.DOWN) {
            MyInput.setKey(MyInput.DOWN,false);
        }
        if (k == Input.Keys.RIGHT) {
            MyInput.setKey(MyInput.RIGHT,false);
        }
        if (k == Input.Keys.ENTER) {
            MyInput.setKey(MyInput.ENTER,false);
        }
        if (k == Input.Keys.ESCAPE) {
            MyInput.setKey(MyInput.ESCAPE,false);
        }
        if (k == Input.Keys.SPACE) {
            MyInput.setKey(MyInput.SPACE,false);
        }
        if (k == Input.Keys.SHIFT_LEFT ||k == Input.Keys.SHIFT_RIGHT){
            MyInput.setKey(MyInput.SHIFT,false);
        }
        return true;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        MyInput.x = screenX;
        MyInput.y = screenY;
        MyInput.down = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        MyInput.x = screenX;
        MyInput.y = screenY;
        MyInput.down = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        MyInput.x = screenX;
        MyInput.y = screenY;
        MyInput.down = true;
        return true;
    }
}
