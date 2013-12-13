package com.ultimate39.android.games.tetris;

import com.badlogic.androidgames.framework.gl.BTMPFont;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGame;

public class Assets {
 static	Texture atlas;
 static Texture sd_0;
 static Texture startScreen;
 
 static BTMPFont font;
 static Texture backgroundGameOver;
 static TextureRegion startScreenRegion;
 static TextureRegion backgroundGameOverRegion;
 static	TextureRegion background;
 static TextureRegion red;
 static TextureRegion green;
 static TextureRegion blue;
 static TextureRegion blueLight;
 static TextureRegion gameOver;
 static TextureRegion exit;
 public static void load(GLGame game){
	 atlas = new Texture(game,"atlas.png");
	 backgroundGameOver = new Texture(game,"gameover.png");
	 sd_0 = new Texture(game,"sd_0.png");
	 startScreen = new Texture(game,"startScreen2.png");
	
	 startScreenRegion = new TextureRegion(startScreen,0,0,320,480); 
	 backgroundGameOverRegion = new TextureRegion(backgroundGameOver,0,0,320,480);
	 background = new TextureRegion(atlas,0,0,320,480);
	 red = new TextureRegion(atlas,340,0,20,20);
	 green = new TextureRegion(atlas,380,0,20,20);
	 blue = new TextureRegion(atlas,420,0,20,20);
	 blueLight = new TextureRegion(atlas,460,0,20,20);
	 gameOver = new TextureRegion(atlas,330,41,172,60);	
	 exit = new TextureRegion(atlas,324,40,180,105);
	 font = new BTMPFont(game,"sd.fnt",sd_0);
	 
 }
public static void reload(){
	atlas.reload();
	backgroundGameOver.reload();
	sd_0.reload();
	startScreen.reload();
}
 

}
