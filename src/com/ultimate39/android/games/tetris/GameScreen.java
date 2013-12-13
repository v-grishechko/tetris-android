package com.ultimate39.android.games.tetris;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.Input.KeyEvent;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.helper.DebugDraw;
import com.badlogic.androidgames.framework.helper.FPSCounter;
import com.badlogic.androidgames.framework.helper.Logger;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Vector2;

public class GameScreen extends GLScreen {

	static final int GAME_RUNNING = 1;
	static final int GAME_READY = 2;
	static final int GAME_PAUSE = 3;
	static final int GAME_OVER = 4;

	private SpriteBatcher batcher;
	Camera2D guiCam;
	World world;
	FPSCounter fps = new FPSCounter();
	Logger log = new Logger();
	TextureRegion text;
	DebugDraw dd;
	Vector2 touchPoint = new Vector2();
	int state;

	public GameScreen(Game game) {
		super(game);
		batcher = new SpriteBatcher(glGraphics, 360);
		guiCam = new Camera2D(glGraphics, 16, 24);
		world = new World();
		state = GAME_RUNNING;
		dd = new DebugDraw(glGraphics);

	}

	@Override
	public void update(float deltaTime) {
		switch (state) {
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_READY:
			updateReady();
		case GAME_PAUSE:
			updatePause();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}
	}

	void updateRunning(float deltaTime) {
		if (World.state == World.STATE_GAME_OVER) {
			state = GAME_OVER;
			return;
		}
		world.update(deltaTime);
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent touch = touchEvents.get(i);

			if (touch.type == TouchEvent.TOUCH_DOWN) {
				touchPoint.set(touch.x, touch.y);
				guiCam.touchToWorld(touchPoint);
				if (OverlapTester
						.pointInRectangle(world.turnButton, touchPoint)) {
					FactoryShape.turnShape(world.shape, world.field);
					world.getShadowShape();

				}
				if (OverlapTester.pointInRectangle(world.moveRightButton,
						touchPoint)) {
					FactoryShape.moveRightShape(world.shape, world.field);
					world.getShadowShape();
				}
				if (OverlapTester.pointInRectangle(world.moveLeftButton,
						touchPoint)) {

					FactoryShape.moveLeftShape(world.shape, world.field);
					world.getShadowShape();
				}
				if (OverlapTester.pointInRectangle(world.fieldScreen,
						touchPoint)) {
					world.fallShape();
				}
				if (OverlapTester
						.pointInRectangle(world.downButton, touchPoint)) {
					world.fasterDown();

				}

			}
		}

	}

	void updatePause() {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent touch = touchEvents.get(i);
			if (touch.type == TouchEvent.TOUCH_UP) {
				touchPoint.set(touch.x, touch.y);
				guiCam.touchToWorld(touchPoint);
				if (OverlapTester
						.pointInRectangle(world.noBtn, touchPoint)) {
					state = GAME_RUNNING;
				}
				if(OverlapTester.pointInRectangle(world.yesBtn, touchPoint)){
					state = GAME_OVER;
					Settings.addScore(world.score);
					game.setScreen(new MainMenuScreen(game));
				}
				

				}
			}
		}
	

	void updateGameOver() {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		Settings.addScore(world.score);
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent touch = touchEvents.get(i);
			touchPoint.set(touch.x, touch.y);
			guiCam.touchToWorld(touchPoint);
			if (touch.type == TouchEvent.TOUCH_DOWN) {
				if (OverlapTester.pointInRectangle(world.fieldScreen,
						touchPoint))
					;
				state = GAME_READY;
				break;

			}
		}
	}

	void updateReady() {
		world.reload();
		state = GAME_RUNNING;
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
		switch (state) {
		case GAME_RUNNING:
			batcher.beginBatch(Assets.atlas);
			batcher.drawSprite(8f, 12, 16, 24, Assets.background, 1);
			drawWorld();
			batcher.endBatch();
			batcher.beginBatch(Assets.sd_0);
			Assets.font.drawText(batcher, "" + world.score, 13.7f, 9.1f, 0.1f);
			Assets.font.drawText(batcher, "" + world.level, 14.4f, 22, 0.15f);
			batcher.endBatch();
			break;
		case GAME_OVER:
			batcher.beginBatch(Assets.backgroundGameOver);
			batcher.drawSprite(8f, 12, 16, 24, Assets.backgroundGameOverRegion,
					1);
			batcher.endBatch();
			batcher.beginBatch(Assets.sd_0);
			Assets.font.drawText(batcher, world.score + "", 7, 14.1f, 0.1f);
			batcher.endBatch();
			break;
		case GAME_READY:
			batcher.beginBatch(Assets.atlas);
			batcher.drawSprite(8f, 12, 16, 24, Assets.background, 1);
			batcher.endBatch();
			break;
		case GAME_PAUSE:
			batcher.beginBatch(Assets.atlas);
			batcher.drawSprite(8f, 12, 16, 24, Assets.background, 1);
			drawWorld();
			batcher.drawSprite(7.0f, 15, 8, 8, Assets.exit, 1);
			batcher.endBatch();
			batcher.beginBatch(Assets.sd_0);
			Assets.font.drawText(batcher, "" + world.score, 13.7f, 9.1f, 0.1f);
			Assets.font.drawText(batcher, "" + world.level, 14.4f, 22, 0.15f);
			batcher.endBatch();
			break;

		}
	
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		fps.logFrame();

	}

	@Override
	public void pause() {
	

	}

	@Override
	public void resume() {
		// Assets.reload();
		state = GAME_RUNNING;

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void drawWorld() {
		for (int i = 0; i < World.FIELD_HEIGHT; i++) {
			for (int j = 0; j < World.FIELD_WIDTH; j++) {
				if (world.field[i][j] != null) {
					text = FactoryShape.setColor(world.field[i][j].color);
					batcher.drawSprite(j - 2 + 0.5f, i + 0.5f + 1, 1, 1, text,
							1f);
				}
			}
		}

		Square[] shape = world.shape.getArray();
		text = FactoryShape.setColor(shape[0].color);
		for (int i = 0; i < shape.length; i++) {
			batcher.drawSprite(shape[i].position.x, shape[i].position.y, 1, 1,
					text, 1f);
		}
		if (world.shadowShape[0] != null) {
			Square[] sh = world.shadowShape;
			text = FactoryShape.setColor(sh[0].color);
			for (int i = 0; i < sh.length; i++) {
				batcher.drawSprite(sh[i].position.x, sh[i].position.y, 1, 1,
						text, 0.5f);
			}
		}
		Square[] miniShape = world.miniShape;
		text = FactoryShape.setColor(miniShape[0].color);
		for (int i = 0; i < 4; i++) {
			batcher.drawSprite(miniShape[i].position.x,
					miniShape[i].position.y, 0.7f, 0.7f, text, 1);
		}

	}

	@Override
	public void fuck() {
		if (state == GAME_RUNNING) {
			state = GAME_PAUSE;
		}
		
	}

}
