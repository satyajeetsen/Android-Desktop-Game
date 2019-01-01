package com.mygdx.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyMainGame;
import com.mygdx.manager.GameStateManager;

import static com.mygdx.manager.GameStateManager.MENU;

public class Credits extends GameState implements Screen {
    private int level;
    private GameStateManager gsm;
    private MyMainGame game;
    private Viewport viewport;
    private Stage stage;
    BitmapFont font;
    SpriteBatch sb;
    public Credits(GameStateManager gsm) {
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
        ftfp.size = 12;
        ftfp.color = Color.BLACK;
        Label creditlabel = new Label("Created by Satyajeet Sen,\n Inspired by MarioBros",font);
        table.add(creditlabel).expandX();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        BitmapFont font12 = ftf.generateFont(ftfp);

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

            gsm.setState(MENU,level);

        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {

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
