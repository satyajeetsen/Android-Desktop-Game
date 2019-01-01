
        package com.mygdx.gamestate;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.entities.Diamonds;
import com.mygdx.entities.Display;
import com.mygdx.entities.Player;


import com.mygdx.entities.Spikes;
import com.mygdx.game.MyMainGame;
import com.mygdx.gamestate.GameState;
import com.mygdx.manager.BoundedCamera;
import com.mygdx.manager.Box2DVariables;
import com.mygdx.manager.GameInputProcessor;
import com.mygdx.manager.GameStateManager;
import com.mygdx.manager.MyContactListener;
import com.mygdx.manager.MyInput;


import java.util.ArrayList;

import static com.mygdx.manager.Box2DVariables.BIT_BLUE;
import static com.mygdx.manager.Box2DVariables.BIT_GREEN;
import static com.mygdx.manager.Box2DVariables.BIT_PLAYER;
import static com.mygdx.manager.Box2DVariables.BIT_RED;
import static com.mygdx.manager.Box2DVariables.BIT_SPIKE;
import static com.mygdx.manager.Box2DVariables.PPM;


public class Play extends GameState {


    private World world;


    private Player player;
public static int score;

    private Box2DDebugRenderer b2d;
    private BoundedCamera B2DCAM;
    private TiledMap tileMap;
    private OrthogonalTiledMapRenderer orthomap;
    private MyContactListener cl;
    private float tileSize;
    private Sprite sprite;
    private Viewport viewport;
    private Array<Diamonds> diamondsArray;
    private Array<Spikes> spikeArray;
    private int tileMapWidth;
    private int tileMapHeight;
    private Display display;
    private OrthographicCamera displayCam;
    public static int level =1;
    private long time;
    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject () {
            return new Rectangle();
        }
    };
    private Array<Rectangle> tiles = new Array<Rectangle>();
    Texture gameovertex;



    //file name


    public Play(GameStateManager gsm,int level) {
        super(gsm);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        time = System.currentTimeMillis();



        //setup box2d
        world = new World(new Vector2(0, -7f), true);
        cl = new MyContactListener();
        world.setContactListener(cl);
        sb = new SpriteBatch();
        displayCam = new OrthographicCamera();
        // gameovertex=MyMainGame.con.getTexture("gameover");

        displayCam.translate(0, 0, 0);
        displayCam.setToOrtho(false, MyMainGame.Width, MyMainGame.Height);
        displayCam.update();

        diamondsArray = new Array<Diamonds>();
        b2d = new Box2DDebugRenderer();

        cam = new BoundedCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);

        viewport = new FillViewport(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4, cam);

        cam.translate(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);


        try {

            tileMap = new TmxMapLoader().load("maps//tilemap_"+level+".tmx");
            orthomap = new OrthogonalTiledMapRenderer(tileMap, 1);
        } catch (Exception e) {
            if(level==1)
            System.out.println("Cannot find file: maps//tilemap_"+level+".tmx");

            Gdx.app.exit();
        }

        System.out.println("Tile Size " + tileSize);
       tileMapWidth = Integer.parseInt(tileMap.getProperties().get("width").toString());
        tileMapHeight = Integer.parseInt(tileMap.getProperties().get("height").toString());
        tileSize = Integer.parseInt(tileMap.getProperties().get("tilewidth").toString());


//create player
        createPlayer();

        //create diamonds
        createDiamonds();
        //create spikes
        createSpikes();
        player.setTotalDiamonds(diamondsArray.size);

        Music musics = MyMainGame.con.getMusic("worldtheme");
        musics.setLooping(true);
        musics.play();
        createBlocks();







        //setup Box2D Cam

        B2DCAM = new BoundedCamera();
        B2DCAM.setToOrtho(false, MyMainGame.Width / PPM, MyMainGame.Height / PPM);
        B2DCAM.setBounds(0, (tileMapWidth * tileSize) / PPM, 0, (tileMapHeight * tileSize) / PPM);

//set up Display of block on left top corner
        display = new Display(player);

    }


    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        if (level <= 3 && level> 0)
            world.step(1 / 90f, 1, 1);
        else if (level > 3 && level<6) {
            world.step(1 / 60f, 1, 1);
        }
        else {
            System.exit(0);
        }

        Array<Body> bodies = cl.getBodiesToRemove();



        for (int i = 0; i < bodies.size; i++) {
            Body b = bodies.get(i);
            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////");
            System.out.println(bodies.get(i));
            diamondsArray.removeValue((Diamonds) b.getUserData(), true);
            world.destroyBody(bodies.get(i));
            player.collectDiamonds();
        }


        bodies.clear();



        player.update(dt);
        for (int i = 0; i < diamondsArray.size; i++) {
            diamondsArray.get(i).update(dt);
        }

        for (int i = 0; i < spikeArray.size; i++) {
            	spikeArray.get(i).update(dt);
            	}
        //if player completes the level and collects all the diamonds
        if (player.getBody().getPosition().x * PPM > tileMapWidth * tileSize && (player.getNumDiamonds() == player.getTotalDiamonds())) {

            level++;
            score=score+100;
            gsm.setState(GameStateManager.PLAY,level);


        }


        // if player failed to jump or fall down
        if (player.getBody().getPosition().y < 0 ) {
            //  player.getBody().setActive(false);

            level=1;
            score=0;
            gsm.setState(GameStateManager.GAMEOVER,level);
        }
        if (player.getBody().getLinearVelocity().x < 0.001f) {
            //player.getBody().setActive(false);
            level=1;
            score=0;
            gsm.setState(GameStateManager.GAMEOVER,level);

        }
        if (cl.isPlayerDead()) {
            try {
                if (System.currentTimeMillis() > time + 1500) {
                    score = 0;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }


                      level=1;
            score=0;
            gsm.setState(GameStateManager.GAMEOVER,level);


        }


    }

    @Override
    public void draw() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta) {

        //clear screen
        if(level >=3)
        delta=delta*2;    //makes the game run faster
        update(delta);
       // updatePlayer(delta);
        Gdx.gl.glClearColor(135/255f,206/255f,235/255f,1); //blue background
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.position.x = clamp(cam.position.x, Gdx.graphics.getWidth() - cam.viewportWidth, 0);
        cam.position.y = clamp(cam.position.y, Gdx.graphics.getHeight() - cam.viewportHeight, 0);
        cam.update();

        float offSetY = 35;
        cam.position.set(player.getPosition().cpy().scl(PPM).x, player.getPosition().cpy().scl(PPM).y + offSetY, 0);

        cam.update();



        if (sb == null) {
            System.out.println("Sb is null");
        }



        //draw tile map
        orthomap.setView(cam); //sets the projection matrix and viewbounds from the given camera.If camera changes you have to call this method
        orthomap.render(); //renders all layers of map
        //draw player
        sb.setProjectionMatrix(cam.combined);

        player.render(sb);

        //draw changing block
        sb.setProjectionMatrix(displayCam.combined);
        display.render(sb);
        sb.setProjectionMatrix(cam.combined);
        for (int i = 0; i < diamondsArray.size; i++) {
            diamondsArray.get(i).render(sb);
        }
        for (int i = 0; i < spikeArray.size; i++) {
            		spikeArray.get(i).render(sb);
            	}

B2DCAM.position.set(player.getPosition().x*PPM, player.getPosition().y*PPM + offSetY, 0);


    }












    public float clamp(float var, float max, float min) {
        if (var > min) {
            if (var < max) {
                return var;
            } else return max;
        } else return min;
    }


    @Override
    public void handleInput(float dt) {
        // jump only once no jumping from the air
        Gdx.input.setInputProcessor(new GameInputProcessor());
        if (MyInput.isPressed((MyInput.SPACE))) {
            playerJump();

            }

        if (MyInput.isPressed(MyInput.ENTER)) {
            //player can switch blocks


             switchBlocks();
        }
        if (MyInput.isPressed()) {
            if (MyInput.x < Gdx.graphics.getWidth() / 2) switchBlocks();
            else {
                playerJump();
            }
        }
    }

    private void createBlocks() {

        for (int i = 5; i < 8; i++) {
            for (MapObject object : tileMap.getLayers().get(i).getObjects()) {

                BodyDef bdef = new BodyDef();
                FixtureDef fdef = new FixtureDef();
                Body body;
                Shape shape;


                if (object instanceof RectangleMapObject) {
                    shape = getRectangle((RectangleMapObject) object,bdef);

                } else {
                    continue;
                }



                bdef.type = BodyDef.BodyType.StaticBody;
              //  float x = Float.parseFloat((object.getProperties().get("x").toString()))/PPM;
              //  float y = Float.parseFloat((object.getProperties().get("y").toString()))/PPM;



                body = world.createBody(bdef);

                fdef.shape = shape;



                if (i == 5) {

                    //Blue
                   fdef.filter.categoryBits =  BIT_BLUE ;

                } else if (i == 6) {

                    fdef.filter.categoryBits =  BIT_GREEN;
                           //Green

                } else if (i == 7) {
                    //Red
                 fdef.filter.categoryBits = BIT_RED ;

                }
          //      fdef.filter.maskBits = Box2DVariables.BIT_PLAYER;
                body.createFixture(fdef).setUserData("ground");
            }
        }
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject,BodyDef bdef) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
      //  bdef.position.set(rectangle.getX() + rectangle.getWidth() / 2  / PPM, rectangle.getY() + rectangle.getHeight() / 2 /PPM);
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / PPM,
                (rectangle.y + rectangle.height * 0.5f) / PPM);
        polygon.setAsBox(rectangle.width * 0.5f / PPM,
                rectangle.height * 0.5f / PPM,
                size,
                0.0f);
        return polygon;
    }


    @Override
    public void dispose() {



    }

    private void playerJump() {
        	if (cl.isPlayerOnGround()&& player.getBody().getLinearVelocity().y == 0 ) {
            // player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, 0);
            	player.getBody().applyForceToCenter(0, 400, true);
            	MyMainGame.con.getSound("jump").play();
            	}
        	}

    private void createPlayer() {
        BodyDef bodydef = new BodyDef();
        FixtureDef fixdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();


        //create player
        bodydef.position.set(60 / PPM, 200 / PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        //
        bodydef.linearVelocity.set(0.5f, 0);
        Body body = world.createBody(bodydef);

        shape.setAsBox(10 / PPM, 10 / PPM);
        fixdef.shape = shape;
        fixdef.friction=0.0f;

       fixdef.filter.categoryBits = BIT_PLAYER;
       fixdef.filter.maskBits = BIT_BLUE | Box2DVariables.BIT_DIAMOND | BIT_SPIKE;
        body.createFixture(fixdef).setUserData("PLAYER");


        //create foot sensor
        shape.setAsBox(10 / PPM, 2 / PPM, new Vector2(0, -10 / PPM), 0);
        fixdef.shape = shape;
        fixdef.filter.categoryBits = BIT_PLAYER;
       fixdef.filter.maskBits = BIT_BLUE ;

        //  fixdef.filter.maskBits=Box2DVariables.BIT_GREEN;
        fixdef.friction=0.0f;
        fixdef.isSensor = true;
        body.createFixture(fixdef).setUserData("foot");

        //create Player
        player = new Player(body);

        body.setUserData(player);
        shape.dispose();
    }


    public void createDiamonds() {
        diamondsArray = new Array<Diamonds>();
        MapLayer layer = tileMap.getLayers().get("diamonds");
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        for (MapObject mo : layer.getObjects()) {

            bdef.type = BodyDef.BodyType.StaticBody;
            float x = Float.parseFloat((mo.getProperties().get("x").toString())) / PPM;
            float y = Float.parseFloat((mo.getProperties().get("y").toString())) / PPM;

            bdef.position.set(x, y);

            CircleShape cs = new CircleShape();
            cs.setRadius(5 / PPM);
            fdef.shape = cs;
            fdef.isSensor = true;
            fdef.filter.categoryBits = Box2DVariables.BIT_DIAMOND;
            fdef.filter.maskBits = Box2DVariables.BIT_PLAYER;
            Body body = world.createBody(bdef);
            body.createFixture(fdef).setUserData("diamonds");
            Diamonds d = new Diamonds(body);
            diamondsArray.add(d);

            body.setUserData(d);
            cs.dispose();
        }
    }

    public void switchBlocks() {
        //get mask bits for player foot
        Filter filter = player.getBody().getFixtureList().get(1).getFilterData();
        short bits = filter.maskBits;
        //switch to next color
        if ((bits == BIT_BLUE)) {
            bits = BIT_GREEN;

        } else if ((bits == BIT_GREEN)) {
            bits = BIT_RED;

        } else if ((bits == BIT_RED)) {
            bits = BIT_BLUE;

        }

        //set new maskbits for player foot
        filter.maskBits = bits;
        player.getBody().getFixtureList().get(1).setFilterData(filter);

        //set new maskbits for player
       // filter = player.getBody().getFixtureList().get(0).getFilterData();
        bits |= Box2DVariables.BIT_DIAMOND | Box2DVariables.BIT_SPIKE;
        filter.maskBits = bits;
        player.getBody().getFixtureList().get(0).setFilterData(filter);
    }

    public boolean gameOver() {
        if (cl.isPlayerDead()) {
            return true;
        }
        else return false;
    }


    public void createSpikes() {
        	spikeArray = new Array<Spikes>();
        	MapLayer layer = tileMap.getLayers().get("spikes");
        	BodyDef bdef = new BodyDef();
        	FixtureDef fdef = new FixtureDef();
        	for (MapObject mo : layer.getObjects()) {

            	bdef.type = BodyDef.BodyType.StaticBody;

            	if (mo instanceof EllipseMapObject) {
                	Ellipse e = ((EllipseMapObject)mo).getEllipse();
                	float x = e.x;
                	float y = e.y;
                	bdef.position.set(x / PPM, y / PPM);
                	}
            	// float x = Float.parseFloat((mo.getProperties().get("x").toString())) / PPM;
            	// float y = Float.parseFloat((mo.getProperties().get("y").toString())) / PPM;

            	// bdef.position.set(x, y);

            	CircleShape c = new CircleShape();
            	c.setRadius(5 / PPM);
            	fdef.shape = c;
            	fdef.isSensor = true;
            	fdef.filter.categoryBits = Box2DVariables.BIT_SPIKE;
            	fdef.filter.maskBits = Box2DVariables.BIT_PLAYER;
            	Body body = world.createBody(bdef);
            	body.createFixture(fdef).setUserData("spikes");
            	Spikes s = new Spikes(body);
            	spikeArray.add(s);


            	body.setUserData(s);
            	c.dispose();
            	}
        	}

        }





