package com.ultimate39.android.games.tetris;

import com.badlogic.androidgames.framework.math.Vector2;

public abstract class Shape {
	int color;
	abstract Square[] getArray();
	void update(float speedX, float speedY) {
		Square[] sq = getArray();
		for (int i = 0; i < sq.length; i++) {
			sq[i].position.add(speedX, speedY);
			sq[i].newFieldY += speedY;
			sq[i].newFieldX += speedX;

		}
	}

 abstract int getType();
}

class Shape1 extends Shape {
	Square[] a = new Square[4];
	int color;
	public Shape1(int color) {
		a[0] = new Square(new Vector2(7.5f, 23.5f), color, 7+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
		a[1] = new Square(new Vector2(8.5f, 23.5f), color, 8+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
		a[2] = new Square(new Vector2(7.5f, 22.5f), color, 7+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
		a[3] = new Square(new Vector2(8.5f, 22.5f), color, 8+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
		this.color = color;
	}

	public Square[] getArray() {
		Square[] square = a;
		return square;
	}

	@Override
	int getType() {
		return 1;
	}
}


	class Shape2 extends Shape {
		Square[] a = new Square[4];
        int color;
		public Shape2(int color) {
			a[0] = new Square(new Vector2(6.5f, 23.5f), color, 6+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
			a[1] = new Square(new Vector2(7.5f, 23.5f), color, 7+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
			a[2] = new Square(new Vector2(8.5f, 23.5f), color, 8+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
			a[3] = new Square(new Vector2(9.5f, 23.5f), color, 9+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
			this.color = color;
		}

		public Square[] getArray() {
			return a;
		}
		int getType() {
			return 2;
		}
	}

	class Shape3 extends Shape {
		Square[] a = new Square[4];

		public Shape3(int color) {
			a[0] = new Square(new Vector2(8.5f, 22.5f), color, 8+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[1] = new Square(new Vector2(7.5f, 22.5f), color, 7+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[2] = new Square(new Vector2(6.5f, 22.5f), color, 6+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[3] = new Square(new Vector2(6.5f, 23.5f), color, 6+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
		}

		public Square[] getArray() {
			return a;
		}
		int getType() {
			return 3;
		}
	}

	class Shape4 extends Shape {
		Square[] a = new Square[4];

		public Shape4(int color) {
			a[0] = new Square(new Vector2(6.5f, 22.5f), color, 6+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[1] = new Square(new Vector2(7.5f, 22.5f), color, 7+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[2] = new Square(new Vector2(8.5f, 22.5f), color, 8+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[3] = new Square(new Vector2(8.5f, 23.5f), color, 8+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
		}

		public Square[] getArray() {
			return a;
		}
		int getType() {
			return 4;
		}
	}
	class Shape5 extends Shape {
		Square[] a = new Square[4];
		int color;
		public Shape5(int color) {
			a[0] = new Square(new Vector2(6.5f, 22.5f), color, 6+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[1] = new Square(new Vector2(7.5f, 22.5f), color, 7+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[2] = new Square(new Vector2(7.5f, 23.5f), color, 7+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
			a[3] = new Square(new Vector2(8.5f, 23.5f), color, 8+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
			this.color = color;
		}

		public Square[] getArray() {
			return a;
		}

		@Override
		int getType() {
			return 5;
		}
	}
	class Shape6 extends Shape {
		Square[] a = new Square[4];
		int color;
		public Shape6(int color) {
			a[0] = new Square(new Vector2(6.5f, 22.5f), color, 6+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[1] = new Square(new Vector2(7.5f, 22.5f), color, 7+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[2] = new Square(new Vector2(7.5f, 23.5f), color, 7+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
			a[3] = new Square(new Vector2(8.5f, 22.5f), color, 8+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			this.color = color;
		}

		public Square[] getArray() {
			return a;
		}

		@Override
		int getType() {
			return 6;
		}
	}
	class Shape7 extends Shape {
		Square[] a = new Square[4];
		int color;
		public Shape7(int color) {
			a[0] = new Square(new Vector2(6.5f, 23.5f), color, 6+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
			a[1] = new Square(new Vector2(7.5f, 23.5f), color, 7+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
			a[2] = new Square(new Vector2(7.5f, 22.5f), color, 7+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			a[3] = new Square(new Vector2(8.5f, 22.5f), color, 8+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
			this.color = color;
		}

		public Square[] getArray() {
			return a;
		}

		@Override
		int getType() {
			return 7;
		}
	}
		
	

