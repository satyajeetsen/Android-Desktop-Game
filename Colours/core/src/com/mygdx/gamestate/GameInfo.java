package com.mygdx.gamestate;

import com.badlogic.gdx.ApplicationListener;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyMainGame;
import com.mygdx.manager.GameStateManager;

import static com.mygdx.manager.GameStateManager.MENU;

public class GameInfo extends GameState implements ApplicationListener,Screen {
    private MyMainGame game;
    private Viewport viewport;

    private int level;
    private GameStateManager gsm;

    private Stage stage;
    BitmapFont font;
    SpriteBatch sb;
    public GameInfo(GameStateManager gsm) {
        super(gsm);
        // font=new BitmapFont(Gdx.files.internal("//fonts//Chunkfive.otf"),false);
        sb=new SpriteBatch();
        this.gsm=gsm;
        viewport=new FitViewport(MyMainGame.Width,MyMainGame.Height,new OrthographicCamera());
        stage=new Stage(viewport,sb);//((MyMainGame)game).sb
        Label.LabelStyle font =new Label.LabelStyle(new BitmapFont(), Color.RED);

        Table table= new Table();
        table.center();
        table.setFillParent(true);
        FreeTypeFontGenerator ftf = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Hyperspace Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter ftfp = new FreeTypeFontGenerator.FreeTypeFontParameter();
        ftfp.size = 6;
        ftfp.color = Color.BLACK;

        Label gameinfolabel1 = new Label("In Android version \n Tap left of screen to make the player jump \n on the right coloured \n tile .Tap right to get the colour of \n square  box  match the \n colour  of the  tile \n ",font);
        gameinfolabel1.setWrap(true);
        gameinfolabel1.setAlignment(Align.left);
        ScrollPane scrollPane1=new ScrollPane(gameinfolabel1);
        table.add(gameinfolabel1).bottom();
        table.add(scrollPane1);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        BitmapFont font12 = ftf.generateFont(ftfp);
        textButtonStyle.font = font12;
        TextButton MenuInfo = new TextButton("Back to Main Menu", textButtonStyle);
        MenuInfo.setTouchable(Touchable.enabled);
        table.add(MenuInfo).spaceBottom(40).row();

        stage.addActor(table);


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
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
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
    public void dispose() {
      sb.dispose();

    }

    @Override
    public void create() {
        sb=new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/BEBAS.ttf"),false);
        font.getData().setScale(0.2f);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.draw();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
