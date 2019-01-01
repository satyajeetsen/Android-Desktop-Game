package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.entities.Player;
import com.mygdx.gamestate.GameOverScreen;
import com.mygdx.gamestate.MenuState;
import com.mygdx.manager.BoundedCamera;
import com.mygdx.manager.Content;
import com.mygdx.manager.GameInputProcessor;

import com.mygdx.manager.GameStateManager;
import com.mygdx.manager.MyInput;




import static com.mygdx.manager.GameStateManager.MENU;

public class MyMainGame extends Game {


	public static final int Width=300;
	public static final int Height=240
			;
	public static final int SCALE=2;
    public static final String TITLE="Jump on Colors";
	public static final float STEP= 1/60f;  //60 frames per second

	private float accumulator;
	public SpriteBatch sb;
	protected BoundedCamera cam;
	protected OrthographicCamera displayCam;
	private Texture tex;
	
	private Sprite sprite;
    private Viewport viewport;
	private GameStateManager gsm;
	private Player player;
	private boolean playerIsDead =false;
	private int level =1;

	public SpriteBatch getSb() {
		return sb;
	}

	public BoundedCamera getCam() {
		return cam;
	}   //for orthogonal tile map

	public OrthographicCamera getdisplayCam() {
		return displayCam;
	}   //for player
     public  static Content con;


	
	@Override
	public void create () {
	   //setScreen(new GameOverScreen(this));
        Gdx.input.setInputProcessor(new GameInputProcessor());
        con=new Content();
      con.loadTexture("images//boy.png","sprite");
        con.loadTexture("images//diamond.png","diamonds");
        con.loadTexture("images//spikes.png","spikes");
        con.loadTexture("images//color-blocks.png","blocks");
		con.loadTexture("images//menu.png","menu");

		con.loadSound("jump", "sfx/jump.wav");

		con.loadMusic("worldtheme", "music/Worldmap Theme.mp3");
		con.loadMusic("menu music", "music/awesomeness.wav");

		gsm=new GameStateManager(this);

		sb=new SpriteBatch();
		Gdx.gl.glClearColor(0,0,1,1);
		gsm.pushState(MENU,1);

		cam=new BoundedCamera();
   //     cam.position.set(cam.viewportWidth/2 ,
   //             cam.viewportHeight/2 , 0f);
        //cam.setToOrtho(false,Width,Height);
        displayCam=new OrthographicCamera();
        displayCam.setToOrtho(false,Width,Height);


       // cam.update();




//         sprite=new Sprite(tex);
      //   sprite.setSize(320*2,240*2);







	}

	@Override
	public void render () {
              Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.graphics.setTitle(TITLE+"FPS: "+Gdx.graphics.getFramesPerSecond());
			gsm.update(Gdx.graphics.getDeltaTime());
			gsm.render();
			MyInput.update();
		}





	public void resize(int width,int height){
		sb.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

	}

    public void dispose(){
            con.disposeTexture("sprite");
		con.disposeTexture("diamonds");
		con.disposeTexture("blocks");

		con.disposeTexture("menu");
	}
    public void pause(){

    }

    public void resume(){
		gsm.setState(GameStateManager.PLAY,level);
    }




}
