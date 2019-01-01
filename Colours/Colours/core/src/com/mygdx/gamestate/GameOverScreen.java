package com.mygdx.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyMainGame;
import com.mygdx.manager.GameStateManager;
import com.mygdx.manager.MyInput;

import static com.mygdx.manager.GameStateManager.GAMEINFO;
import static com.mygdx.manager.GameStateManager.MENU;
import static com.mygdx.manager.GameStateManager.PLAY;

public class GameOverScreen extends GameState {

    private MyMainGame game;
    private Viewport viewport;
    private Stage stage;
    private TextButton MenuInfo;
    private int level;
    private GameStateManager gsm;




    public GameOverScreen( GameStateManager gsm,int level){
        super(gsm);
        this.game=game;
        this.level=level;

        this.gsm=gsm;
        viewport=new FitViewport(MyMainGame.Width,MyMainGame.Height,new OrthographicCamera());
        sb=new SpriteBatch();

        stage=new Stage(viewport,sb);//((MyMainGame)game).sb
        Label.LabelStyle font =new Label.LabelStyle(new BitmapFont(), Color.RED);

        Table table= new Table();
        table.center();
        table.setFillParent(true);
        FreeTypeFontGenerator ftf = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Hyperspace Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter ftfp = new FreeTypeFontGenerator.FreeTypeFontParameter();
        ftfp.size = 12;
        ftfp.color = Color.BLUE;
        Label gameOverLabel = new Label("GAME OVER",font);
        table.add(gameOverLabel).expandX();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        BitmapFont font12 = ftf.generateFont(ftfp);
        textButtonStyle.font = font12;
         TextButton MenuInfo = new TextButton("Back to Main Menu", textButtonStyle);
        MenuInfo.setTouchable(Touchable.enabled);
        table.add(MenuInfo).spaceBottom(40).row();
        stage.addActor(table);


        MenuInfo.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("down");
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button ) {
                super.touchUp( event, x, y, pointer, button );
            }
        });
    }


    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {
          handleInput(dt);

    }

    @Override
    public void draw() {

    }

    @Override
    public void render(float delta) {
             Gdx.gl.glClearColor(0,0,0,1);
             Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
             stage.draw();

    }

    @Override
    public void handleInput(float dt) {
        if (Gdx.input.isTouched()) {


         // game.setScreen(new MenuState(gsm,1));
             gsm.setState(MENU,level);

        }
    }


    @Override
    public void resize(int width, int height) {

    }



    @Override
    public void dispose() {
              stage.dispose();


    }
}
