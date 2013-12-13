package com.ultimate39.android.games.tetris;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.GLGame;

public class Tetris extends GLGame {
	boolean firstTimeCreate = false;

	@Override
	public Screen getStartScreen() {
		return new MainMenuScreen(this);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		if (firstTimeCreate == false) {
			Assets.load(this);
			Settings.load(getFileIOIn());
			firstTimeCreate = true;
			Settings.getHighScore(getFileIOIn());
		} else {
			Settings.save(getFileIOIn());
			Assets.reload();
			Settings.getHighScore(getFileIOIn());
		}
	}
}