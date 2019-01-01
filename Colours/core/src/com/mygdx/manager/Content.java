package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class Content {
    private HashMap<String,Texture>  textures ;
    private HashMap<String,Sound>  sounds ;
    private HashMap<String,Music>  musics ;

    public Content(){
        textures=new HashMap<String, Texture>();

        sounds = new HashMap<String, Sound>();
        musics = new HashMap<String, Music>();
    }

    public void loadTexture(String path,String key){
Texture tex=new Texture(Gdx.files.internal(path));
textures.put(key,tex);

    }

    public Texture getTexture(String key){
              return textures.get(key);
    }

    public void disposeTexture(String key){
Texture tex=textures.get(key);
if(tex != null){
    tex.dispose();
}
    }


    public void loadMusic(String key, String path) {
        musics.put(key, Gdx.audio.newMusic(Gdx.files.internal(path)));
    }

    public Music getMusic(String key) {
        return musics.get(key);
    }

    public void removeMusic(String key) {
        Music music = musics.get(key);
        musics.remove(key);
        music.dispose();
    }
    public void loadSound(String key, String path) {
        sounds.put(key, Gdx.audio.newSound(Gdx.files.internal(path)));
    }

    public Sound getSound(String key) {
        return sounds.get(key);
    }

    public void removeSound(String key) {
        Sound sound = sounds.get(key);
        sounds.remove(key);
        sound.dispose();
    }

}
