package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyMainGame;
import com.mygdx.gamestate.Credits;
import com.mygdx.gamestate.GameInfo;
import com.mygdx.gamestate.GameOverScreen;
import com.mygdx.gamestate.GameState;
import com.mygdx.gamestate.MenuState;
import com.mygdx.gamestate.Play;

import java.util.Stack;

public class GameStateManager {
  // private GameState gamestate;
   private MyMainGame game;
    private Stack<GameState> gamestates;
    public static final int MENU=33223;
    public static final int PLAY=892345;
    public static final int SELECT_LEVEL = -233223;
    public static final int CREDITS=894222;
    public static final int GAMEINFO=422425;
    public static final int GAMEOVER=424524;
    private int level;
    public GameStateManager(MyMainGame game){
        this.game=game;
        gamestates= new Stack<GameState>();
            pushState(MENU,level);
    }

    public void setState(int state,int level){

        popState();
        pushState(state,level);

    }

    public void update(float dt){
        gamestates.peek().update(dt);
    }

    public MyMainGame game(){
        return game;
    }
    public GameState getState(int state,int level){
       // if(gamestate !=null)
       //     gamestate.dispose();


        if(state== PLAY){
            //switch to play state

           return new Play(this,level);

        }
        if(state == MENU){
            return new MenuState(this,level);
        }



        if(state == CREDITS ){
            return new Credits(this);
        }

        if(state == GAMEINFO ){
            return new GameInfo(this);
        }

        if(state == GAMEOVER ){
            level=1;
            return new GameOverScreen(this,level);
        }

        return null;
    }

    public void pushState(int state,int level){
        gamestates.push(getState(state,level));
    }

    public void popState(){
       GameState g= gamestates.pop();
       g.dispose();
    }



    public void render(){
            gamestates.peek().render(Gdx.graphics.getDeltaTime());
    }
}
