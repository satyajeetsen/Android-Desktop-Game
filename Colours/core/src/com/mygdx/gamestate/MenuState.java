package com.mygdx.gamestate;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.entities.B2DSprite;
import com.mygdx.entities.Player;
import com.mygdx.game.MyMainGame;
import com.mygdx.manager.Animation;
import com.mygdx.manager.GameStateManager;
import com.mygdx.manager.MyInput;



import javax.swing.text.View;



import static com.mygdx.manager.Box2DVariables.PPM;
import static com.mygdx.manager.GameStateManager.CREDITS;
import static com.mygdx.manager.GameStateManager.GAMEINFO;
import static com.mygdx.manager.GameStateManager.PLAY;

public class MenuState extends GameState implements ApplicationListener,Screen {

    private Stage stage;
    private MyMainGame game;
    private String GameTitle = "JUMP ON COLOURS";
    private GlyphLayout glyphLayout;
    private BitmapFont titleFont;
    private SpriteBatch sb;
    private Player player;
    private int currentItem;
    private Animation animation;
    private OrthographicCamera cam;
    private OrthographicCamera displayCam;
    private static World world;
    private int level =1;
     TextButton PlayButton;
     TextButton GameInfoButton;
     TextButton creditButton;
     TextButton  exitButton;


    public MenuState( final GameStateManager gsm,int level) {
        super(gsm);


        MyMainGame.con.getMusic("menu music").play();
        this.game = game;
        stage = new Stage();
        world = new World(new Vector2(0, -7), true);

        sb = new SpriteBatch();
       // displayCam = new OrthographicCamera(Gdx.graphics.getWidth()/PPM,Gdx.graphics.getHeight()/PPM);
        cam=new OrthographicCamera();
        cam.setToOrtho(false, MyMainGame.Width, MyMainGame.Height);
        displayCam=new OrthographicCamera();
        titleFont = new BitmapFont();
        glyphLayout = new GlyphLayout();
        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("images//menu.png"))));

        Texture tex =MyMainGame.con.getTexture("sprite");
        TextureRegion[] texreg = new TextureRegion[6];
        for (int i = 0; i < texreg.length; i++) {
            texreg[i] = new TextureRegion(tex, i * 32, 0, 32, 32);
        }

        animation = new Animation(texreg, 1 / 12f);





        table.setFillParent(true);
        FreeTypeFontGenerator ftf = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Hyperspace Bold.ttf"));
        FreeTypeFontGenerator ftf1 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Chunkfive.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter ftfp = new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleFont = ftf1.generateFont(ftfp);
        titleFont.setColor(Color.BLACK);
        ftfp.size = 36;
        ftfp.color = Color.BLACK;

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        BitmapFont font12 = ftf.generateFont(ftfp);
        textButtonStyle.font = font12;
        TextButton.TextButtonStyle textButtonStyle1 = new TextButton.TextButtonStyle();
        BitmapFont font16 = ftf1.generateFont(ftfp);
        textButtonStyle1.font = font16;

        final TextButton Title = new TextButton("JUMP ON COLOURS", textButtonStyle1);
         PlayButton = new TextButton("Play Game", textButtonStyle);
        GameInfoButton = new TextButton("Game Info", textButtonStyle);

         creditButton = new TextButton("Credits", textButtonStyle);
         exitButton = new TextButton("Exit", textButtonStyle);

        //Layout
        table.add(Title).spaceBottom(40).row();
        table.add(PlayButton).spaceBottom(40).row();
        table.add(GameInfoButton).spaceBottom(40).row();

        table.add(creditButton).spaceBottom(40).row();
        table.add(exitButton).spaceBottom(40).row();



                stage.addActor(table);
    }


    @Override
    public void init() {

        titleFont = new BitmapFont();
        titleFont.setColor(Color.RED);

    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        animation.update(dt);
    }

    @Override
    public void draw() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(cam.combined);
        stage.act(delta);
        stage.draw();
           //draw boy
        sb.begin();
        sb.draw(animation.getFrame(), 146, 31);
        sb.end();
    }

    @Override
    public void handleInput(float dt) {
       Gdx.input.setInputProcessor(stage);

            PlayButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    gsm.setState(PLAY, 1);
                }
//            dispose();

            });

        GameInfoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gsm.setState(GAMEINFO, 1);
            }
//            dispose();

        });
        creditButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gsm.setState(CREDITS, 1);
            }
//            dispose();

        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
               Gdx.app.exit();
            }
//            dispose();

        });

       /*Gdx.input.setInputProcessor(PlayButton.getStage());
        PlayButton.addListener(new ClickListener() {


                                   @Override
                                   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                       System.out.println("down");

                                       int level=1;
                                       ;
                                       gsm.getState(GameStateManager.PLAY,level);
                                       return true;
                                   }

                                   @Override
                                   public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                                       super.touchUp( event, x, y, pointer, button );


                                   }
                               }
        );   */

        if (MyInput.isPressed(MyInput.UP)) {
            if (currentItem > 0) {
                currentItem--;
            }
        }
        if (MyInput.isPressed(MyInput.DOWN)) {
            if (currentItem < 4) {
                currentItem++;
            }
        }
        if (MyInput.isPressed(MyInput.ENTER)) {
            select();
        }
        if(MyInput.isDown(MyInput.DOWN)){
            select();
        }

    }



    private void select(){
        if(currentItem ==0){
            System.out.println("Starting game");
            MyMainGame.con.getMusic("menu music").stop();
            gsm.setState(GameStateManager.PLAY,level);

        }
        else if(currentItem ==1){
            gsm.setState(GameStateManager.GAMEINFO,level);
        }
        else if(currentItem ==2){
            gsm.setState(GameStateManager.CREDITS,level);
        }

        else if(currentItem ==3){

            Gdx.app.exit();
        }
    }


    @Override
    public void dispose() {

stage.dispose();

    }


    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().setScreenSize(width, height);
    }

    @Override
    public void render() {

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
