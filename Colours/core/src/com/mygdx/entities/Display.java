package com.mygdx.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.MyMainGame;
import com.mygdx.gamestate.Play;
import com.mygdx.manager.Box2DVariables;

public class Display {
    Player player;
    TextureRegion[] blocks;
    BitmapFont font;


    public Display(Player player){
        FileHandle handle = Gdx.files.internal("fonts/BEBAS.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(handle);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 10;

        parameter.color= Color.BLACK;
        font = generator.generateFont(parameter);
        this.player=player;
        Texture blocktex= MyMainGame.con.getTexture("blocks");
        blocks =new TextureRegion[3];
        for(int i=0;i<blocks.length;i++){
          blocks =  TextureRegion.split(blocktex,32,32)[0];
        }
        if(blocks[1] == null){
            System.out.println("green block is null");
        }
       else if(blocks[2] == null){
            System.out.println("blue block is null");
        }
    }

    public void render(SpriteBatch sb){
        int scre=Play.score;
        int levl=Play.level;

        short bits=player.getBody().getFixtureList().first().getFilterData().maskBits;
      sb.begin();
      if( (bits & Box2DVariables.BIT_BLUE) !=0){
          sb.draw(blocks[2],40,180);
      }
      else  if( (bits & Box2DVariables.BIT_GREEN) !=0){
        sb.draw(blocks[1],40,180);
        }
      else  if( (bits & Box2DVariables.BIT_RED) !=0){
            sb.draw(blocks[0],40,180);
        }

        font.draw(sb,"Score:"+scre,100,220);
      font.draw(sb,"Diamonds "+ player.getNumDiamonds()+" / "+ player.getTotalDiamonds(),170,220);
      font.draw(sb,"Level "+levl,250,220);

              sb.end();
    }
}
