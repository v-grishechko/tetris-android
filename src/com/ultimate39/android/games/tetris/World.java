package com.ultimate39.android.games.tetris;

import java.util.Random;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;

public class World {

	Square[][] field;
	Shape shape;
	Square[] miniShape;
	Square[] shadowShape;
	Square[] shapeAr;
	Square sq1;
	int score;
	int level;
	int levelScore;
	static final int STATE_GAME_OVER = 1;
	static final int STATE_GAME_RUNNING = 2;
	static final int FIELD_WIDTH = 17;
	static final int FIELD_HEIGHT = 26;
	static final int SQUARE_CAPACITY = FIELD_WIDTH * FIELD_HEIGHT;
	static final float TICK_INITIAL = 0.8f;
	static final int MINUS_HEIGHT = 1;
	static final int PLUS_WIDTH = 2;
	Vector2 speed = new Vector2();
	float tick = 0;
	float tickLevel = 0;
	float tickTimeLevel = 160;
	float tickTime = TICK_INITIAL;
	int capacity = SQUARE_CAPACITY;
	static int state = 0;
	Rectangle turnButton;
	Rectangle moveRightButton;
	Rectangle moveLeftButton;
	Rectangle fieldScreen;
	Rectangle downButton;
	Rectangle yesBtn;
	Rectangle noBtn;
	static Random rand = new Random();
	private  int shapeType = rand.nextInt(7);
	private int color = rand.nextInt(4);

	
	float tickTimeShape = 0;
	float tickTimeShapeInitial =  0.17f;
	boolean shapeFall = false;
	public World() {
		field = new Square[FIELD_HEIGHT][FIELD_WIDTH];
		shape = FactoryShape.newShape(rand.nextInt(7),rand.nextInt(3));
		score = 0;
	    level = 1;
	    levelScore = 0;
		speed = new Vector2(0, -1);
		turnButton = new Rectangle(6.1f, 0, 4, 3);
		moveLeftButton = new Rectangle(-2, 0, 8, 3);
		moveRightButton = new Rectangle(10, 0, 7, 3);
		fieldScreen = new Rectangle(0, 3.05f, 13, 22);
	    downButton = new Rectangle(13.5f,4,3,3);
	    noBtn = new Rectangle(7.3f,11f,4,3);
	    yesBtn = new Rectangle(2.3f,11f,4,3);
		shadowShape = new Square[4];
		shapeAr = shape.getArray();
		state = STATE_GAME_RUNNING;
		getShadowShape();
		newMiniShape();

	}

	void update(float deltaTime) {
		updateCollision();
		getShadowShape();
		moveShape(deltaTime, speed);
		updateLevel(deltaTime);
		if(shapeFall){
			tickTimeShape +=deltaTime;
			if(tickTimeShape > tickTimeShapeInitial){
				shapeFall = false;
				tickTimeShape = 0;
				insertShape();
				newShape();
				shapeType = rand.nextInt(7);
				color = rand.nextInt(4);
				newMiniShape();
				deleteFullLines();
			}
		}

	}

	void moveShape(float deltaTime, Vector2 speed) {
		tick += deltaTime;
		if (tick > tickTime) {
			tick -= tickTime;
			shape.update(speed.x, speed.y);
		}
	}

	void insertShape() {

		for (int i = 0; i < shapeAr.length; i++) {
			field[shapeAr[i].newFieldY][shapeAr[i].newFieldX] = shapeAr[i];
		}

	}

	void updateCollision() {
		for (int i = 0; i < shapeAr.length; i++) {
			if (shapeAr[i].newFieldY == 2
					|| field[shapeAr[i].newFieldY - 1][shapeAr[i].newFieldX] != null) {
				stopShape();
				if (shapeAr[i].newFieldY == 21) {
					state = STATE_GAME_OVER;
					break;
				}
				stopShape();
				shapeFall = true;
				break;
			}else{
				shapeFall = false;
				startShape();
			}
		}
	}

	void stopShape() {
		speed.set(0, 0);
	}
    void startShape(){
    	speed.set(0,-1);
    }
	void newShape() {
		shape = FactoryShape.newShape(shapeType,color);
		speed.set(0, -1);
		shapeAr = shape.getArray();
		FactoryShape.positionLine = 1;
		
	}

	void fallShape() {
		boolean fall = false;
		int y = shapeAr[1].newFieldY;
		synchronized (shape) {
			while (true) {
				Square[] shapeAr = shape.getArray();
				for (int i = 0; i < shapeAr.length; i++) {
					if (shapeAr[i].newFieldY != 2
							&& field[shapeAr[i].newFieldY - 1][shapeAr[i].newFieldX] == null) {

					} else {
						stopShape();
						fall = true;
						break;
					}

				}

				if (fall) {
					score+=y/3+levelScore;
					break;
				}
				shape.update(0, -1);
			}
		}
	}

	void deleteFullLines() {
		// Находим первую заполненную линию
		int firstFullLine = 0;
		boolean isFull = true;
		for (int i = 2; i <= 23; i++) {
			for (int j = 2; j <= 14; j++) {
				if (field[i][j] == null) {
					isFull = false;
					break;
				}
			}
			if (isFull) {
				firstFullLine = i;
				break;
			}
			isFull = true;
		}
		// Проверяем есть ли заполненные линии
		int lastFullLine = 0;

		for (int i = 23; i >= 2; i--) {
			for (int j = 14; j >= 2; j--) {
				if (field[i][j] == null) {
					isFull = false;
					break;
				}
			}
			// Если есть то удаляем квадраты на этой линии
			if (isFull) {
				for (int b = 14; b >= 2; b--) {
					field[i][b] = null;
				}
				lastFullLine = i;
				score+=20+levelScore;
			}
			isFull = true;

		}
		boolean fill = false;
		int fillLine = firstFullLine;
		// Cмещаем квадраты
		if (lastFullLine > 0) {
			for (int i = lastFullLine + 1; i < 23; i++) {
				for (int j = 2; j <= 14; j++) {
					if (field[i][j] != null) {
						field[fillLine][j] = field[i][j];
						field[i][j] = null;
						fill = true;

					}
				}
				if (fill == true) {
					fillLine += 1;
				}
				fill = false;
			}
		}

	}

	void getShadowShape() {
		Square squareCopy[] = new Square[4];
		Square squareMain[] = shape.getArray();
		squareCopy[0] = new Square(new Vector2(squareMain[0].position),
				squareMain[0].color, squareMain[0].newFieldX,
				squareMain[0].newFieldY);
		squareCopy[1] = new Square(new Vector2(squareMain[1].position),
				squareMain[1].color, squareMain[1].newFieldX,
				squareMain[1].newFieldY);
		squareCopy[2] = new Square(new Vector2(squareMain[2].position),
				squareMain[2].color, squareMain[2].newFieldX,
				squareMain[2].newFieldY);
		squareCopy[3] = new Square(new Vector2(squareMain[3].position),
				squareMain[3].color, squareMain[3].newFieldX,
				squareMain[3].newFieldY);
		boolean b = false;
		while (true) {
			for (int i = 0; i < squareCopy.length; i++) {
				if (squareCopy[i].newFieldY != 2
						&& field[squareCopy[i].newFieldY - 1][squareCopy[i].newFieldX] == null) {

				} else {
					b = true;
					break;
				}

			}

			if (b) {
				break;
			}
			for (int j = 0; j < squareCopy.length; j++) {
				squareCopy[j].position.add(0, -1);
				squareCopy[j].newFieldY += -1;
				squareCopy[j].newFieldX += 0;

			}
		}
		shadowShape = squareCopy;
	}

	void reload() {
		for (int i = 1; i < FIELD_HEIGHT-1; i++) {
			for (int j = 1; j < FIELD_WIDTH-1; j++) {
				field[i][j] = null;
			}
			newShape();
			state = STATE_GAME_RUNNING;
			score = 0;
			level = 1;
			levelScore =0;
			tickTime = TICK_INITIAL;

		}
	}

	void newMiniShape() {
		int type = shapeType+1;
		int color = this.color;
		miniShape = new Square[4];
		switch (type) {
		case 0:
			return;
		case 1:
			miniShape[0] = new Square(new Vector2(14.2f, 19.0f), color, 0, 0);
			miniShape[1] = new Square(new Vector2(14.9f, 19.0f), color, 0, 0);
			miniShape[2] = new Square(new Vector2(14.2f, 19.7f), color, 0, 0);
			miniShape[3] = new Square(new Vector2(14.9f, 19.7f), color, 0, 0);
			break;

		case 2:
			miniShape[0] = new Square(new Vector2(14.5f, 20.3f), color, 0, 0);
			miniShape[1] = new Square(new Vector2(14.5f, 19.6f), color, 0, 0);
			miniShape[2] = new Square(new Vector2(14.5f, 18.9f), color, 0, 0);
			miniShape[3] = new Square(new Vector2(14.5f, 18.2f), color, 0, 0);
			break;
		case 3:
			miniShape[0] = new Square(new Vector2(14.9f, 19.9f), color, 0, 0);
			miniShape[1] = new Square(new Vector2(14.2f, 19.9f), color, 0, 0);
			miniShape[2] = new Square(new Vector2(14.2f, 19.2f), color, 0, 0);
			miniShape[3] = new Square(new Vector2(14.2f, 18.5f), color, 0, 0);
			break;
		case 4:
			miniShape[0] = new Square(new Vector2(14.2f, 19.9f), color, 0, 0);
			miniShape[1] = new Square(new Vector2(14.9f, 19.9f), color, 0, 0);
			miniShape[2] = new Square(new Vector2(14.9f, 19.2f), color, 0, 0);
			miniShape[3] = new Square(new Vector2(14.9f, 18.5f), color, 0, 0);
			break;
		case 5:
			miniShape[0] = new Square(new Vector2(13.9f, 19.0f), color, 0, 0);
			miniShape[1] = new Square(new Vector2(14.6f, 19.0f), color, 0, 0);
			miniShape[2] = new Square(new Vector2(14.6f, 19.7f), color, 0, 0);
			miniShape[3] = new Square(new Vector2(15.3f, 19.7f), color, 0, 0);
			break;
		case 6 :
			miniShape[0] = new Square(new Vector2(13.9f, 19.0f), color, 0, 0);
			miniShape[1] = new Square(new Vector2(14.6f, 19.0f), color, 0, 0);
			miniShape[2] = new Square(new Vector2(14.6f, 19.7f), color, 0, 0);
			miniShape[3] = new Square(new Vector2(15.3f, 19.0f), color, 0, 0);
			break;
		case 7 :
			miniShape[0] = new Square(new Vector2(13.9f, 19.7f), color, 0, 0);
			miniShape[1] = new Square(new Vector2(14.6f, 19.0f), color, 0, 0);
			miniShape[2] = new Square(new Vector2(14.6f, 19.7f), color, 0, 0);
			miniShape[3] = new Square(new Vector2(15.3f, 19.0f), color, 0, 0);
			break;
			

		}
	}
	public void fasterDown(){
		if(shapeFall!=true){
		shape.update(0, -1);
		score+=1+levelScore;
		}
	}
	public void updateLevel(float deltaTime){
		
			tickLevel += deltaTime;
			if (tickLevel > tickTimeLevel) {
				tickLevel -= tickTimeLevel;
				level +=1;
				levelScore+=1;
				tickTime -=0.08;
			}
		}
	}

