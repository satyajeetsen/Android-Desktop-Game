package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyMainGame;

public class DesktopLauncher {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="JumponColours";

		config.width=1200;
		config.height=624;
		config.useGL30=false;
		config.resizable=true;
		new LwjglApplication(new MyMainGame(), config);
	}
}
