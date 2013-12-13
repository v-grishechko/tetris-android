package com.ultimate39.android.games.tetris;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.helper.DebugDraw;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;

public class MainMenuScreen extends GLScreen {
 

	Camera2D  guiCam;
	SpriteBatcher batcher;
	Rectangle start;
	Rectangle instructions;
	DebugDraw dd;
	Vector2 touchPoint = new Vector2();
	public MainMenuScreen(Game game) {
		super(game);
		guiCam = new Camera2D(glGraphics,320,480);
		batcher = new SpriteBatcher(glGraphics,100);
		start = new Rectangle(58,202,210,100);
		instructions = new Rectangle(58,110,210,100);
		dd = new DebugDraw(glGraphics);
	}
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			
		    
			TouchEvent touch = touchEvents.get(i);
			
			if (touch.type == TouchEvent.TOUCH_DOWN) {
				touchPoint.set(touch.x, touch.y);
				guiCam.touchToWorld(touchPoint);
				if (OverlapTester
						.pointInRectangle(start, touchPoint)) {
				       game.setScreen(new GameScreen(game));
				}
				if(OverlapTester.pointInRectangle(instructions, touchPoint)){
					
				}
				

				}
			}
		
	}
	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glLoadIdentity();
		guiCam.setViewportAndMatrices();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		batcher.beginBatch(Assets.startScreen);
		batcher.drawSprite(160, 240, 320, 480,Assets.startScreenRegion, 1);
		batcher.endBatch();
		batcher.beginBatch(Assets.sd_0);
		Assets.font.drawText(batcher,Settings.highscore+"", 120, 385, 3);
		batcher.endBatch();
		gl.glDisable(GL10.GL_TEXTURE_2D);
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fuck() {
		// TODO Auto-generated method stub
		
	} 
	
}
