package com.ultimate39.android.games.tetris;

import java.util.Random;


import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.helper.Logger;

public class FactoryShape {

	static Logger log = new Logger();
	static Random rand = new Random();
	// Ёто переменна€ нужна дл€ особого перевората фигуры "Ћини€" или Shape2
	static int positionLine = 1;

	public static Shape newShape(int shapeType,int color) {
    
		switch (shapeType) {
		case 0:
			return new Shape1(color);
		case 1:
			return new Shape2(color);
		case 2:
			return new Shape3(color);
		case 3:
			return new Shape4(color);
		case 4:
			return new Shape5(color);
		case 5:
			return new Shape6(color);
		case 6:
			return new Shape7(color);
		}
		

		return new Shape1(color);
	}

	public static TextureRegion setColor(int color) {

		switch (color) {
		case 0:
			return Assets.blue;
		case 1:
			return Assets.green;
		case 2:
			return Assets.red;
		case 3:
			return Assets.blueLight;
		}
		return null;
	}

	public static void turnShapeLine(Shape shape) {
		Square square[] = shape.getArray();

		if (positionLine == 1) {
			turnSquare(square[1], square[2]);
			turnSquare(square[3], square[2]);
			turnAroundSquareLine(square[0], square[2]);
		} else {
			turnSquare(square[0], square[1]);
			turnSquare(square[2], square[1]);
			turnAroundSquareLine(square[3], square[1]);
		}

	}

	public static void turnShape(Shape shape, Square field[][]) {
		Square[] square = shape.getArray();
		Square squareCent = square[1];
		int type = shape.getType();
		boolean disable = false;
		if (disable != true) {
			switch (type) {
			case 1:
				return;
			case 2:
				if (positionLine == 1) {
					if (isNearSquare(square[1], field) == false
							&& isNearSquare(square[3], field) == false
							&& isNearAroundSquareLine(square[0], field) == false) {
						moveIfNearWallLine(shape);
						turnShapeLine(shape);
						positionLine = 2;
						return;
					}
				}
				if (positionLine == 2) {
					if (isNearSquare(square[0], field) == false
							&& isNearSquare(square[2], field) == false
							&& isNearAroundSquareLine(square[3], field) == false) {

						moveIfNearWallLine(shape);
						turnShapeLine(shape);
						positionLine = 1;
						return;
					}
				}

				return;
			case 3:
				if (isNearSquare(square[0], field) == false
						&& isNearSquare(square[2], field) == false
						&& isNearAroundSquare(square[3], field) == false) {
					moveIfNearWall(shape, 1);
					turnSquare(square[0], squareCent);
					turnSquare(square[2], squareCent);
					turnAroundSquare(square[3], squareCent);
				}
				return;
			case 4:
				if (isNearSquare(square[0], field) == false
						&& isNearSquare(square[2], field) == false
						&& isNearAroundSquare(square[3], field) == false) {
					moveIfNearWall(shape, 1);
					turnSquare(square[0], squareCent);
					turnSquare(square[2], squareCent);
					turnAroundSquare(square[3], squareCent);
				}
				return;
			case 5:
				if (isNearSquare(square[0], field) == false
						&& isNearSquare(square[2], field) == false
						&& isNearAroundSquare(square[3], field) == false) {
					moveIfNearWall(shape, 1);
					turnSquare(square[0], squareCent);
					turnSquare(square[2], squareCent);
					turnAroundSquare(square[3], squareCent);
				}
				return;
			case 6:

				if (isNearSquare(square[0], field) == false
						&& isNearSquare(square[2], field) == false
						&& isNearAroundSquare(square[3], field) == false) {
					moveIfNearWall(shape, 1);
					turnSquare(square[0], squareCent);
					turnSquare(square[2], squareCent);
					turnSquare(square[3], squareCent);
				}
				return;
			case 7:

				if (isNearSquare(square[0], field) == false
						&& isNearSquare(square[2], field) == false
						&& isNearAroundSquare(square[3], field) == false) {
					moveIfNearWall(shape, 1);
					turnSquare(square[0], squareCent);
					turnSquare(square[2], squareCent);
					turnAroundSquare(square[3], squareCent);
				}
				return;
			}
		}

	}

	static void turnSquare(Square square, Square squareCent) {
		if (square.newFieldX - 1 == squareCent.newFieldX
				&& square.newFieldY == squareCent.newFieldY) {
			square.newFieldX -= 1;
			square.newFieldY -= 1;
			square.position.add(-1, -1);
		} else if (square.newFieldX == squareCent.newFieldX
				&& square.newFieldY + 1 == squareCent.newFieldY) {
			square.newFieldX -= 1;
			square.newFieldY += 1;
			square.position.add(-1, +1);
		} else if (square.newFieldX + 1 == squareCent.newFieldX
				&& square.newFieldY == squareCent.newFieldY) {
			square.newFieldX += 1;
			square.newFieldY += 1;
			square.position.add(+1, +1);
		} else if (square.newFieldX == squareCent.newFieldX
				&& square.newFieldY - 1 == squareCent.newFieldY) {
			square.newFieldX += 1;
			square.newFieldY -= 1;
			square.position.add(+1, -1);
		}

	}

	static void turnAroundSquare(Square square, Square squareCent) {
		// ѕоворачиваем 4 квадрат
		if (square.newFieldX + 1 == squareCent.newFieldX
				&& square.newFieldY - 1 == squareCent.newFieldY) {
			square.newFieldX += 2;
			square.newFieldY -= 0;
			square.position.add(+2, 0);
		} else if (square.newFieldX - 1 == squareCent.newFieldX
				&& square.newFieldY - 1 == squareCent.newFieldY) {
			square.newFieldX -= 0;
			square.newFieldY -= 2;
			square.position.add(0, -2);
		} else if (square.newFieldX - 1 == squareCent.newFieldX
				&& square.newFieldY + 1 == squareCent.newFieldY) {
			square.newFieldX -= 2;
			square.newFieldY += 0;
			square.position.add(-2, 0);
		} else if (square.newFieldX + 1 == squareCent.newFieldX
				&& square.newFieldY + 1 == squareCent.newFieldY) {
			square.newFieldX += 0;
			square.newFieldY += 2;
			square.position.add(0, +2);
		}

	}

	static void turnAroundSquareLine(Square square, Square squareCent) {
		if (square.newFieldX - 2 == squareCent.newFieldX
				&& square.newFieldY == squareCent.newFieldY) {
			square.newFieldX -= 2;
			square.newFieldY -= 2;
			square.position.add(-2, -2);
		} else if (square.newFieldX == squareCent.newFieldX
				&& square.newFieldY + 2 == squareCent.newFieldY) {
			square.newFieldX -= 2;
			square.newFieldY += 2;
			square.position.add(-2, +2);
		} else if (square.newFieldX + 2 == squareCent.newFieldX
				&& square.newFieldY == squareCent.newFieldY) {
			square.newFieldX += 2;
			square.newFieldY += 2;
			square.position.add(+2, +2);
		} else if (square.newFieldX == squareCent.newFieldX
				&& square.newFieldY - 2 == squareCent.newFieldY) {
			square.newFieldX += 2;
			square.newFieldY -= 2;
			square.position.add(+2, -2);
		}

	}

	static boolean isNearAroundSquare(Square square, Square field[][]) {
		synchronized (square) {
			if (field[square.newFieldY + 2][square.newFieldX - 0] != null) {
				return true;
			} else if (field[square.newFieldY - 0][square.newFieldX - 2] != null) {
				return true;
			} else if (field[square.newFieldY - 2][square.newFieldX] != null) {
				return true;
			} else if (field[square.newFieldY][square.newFieldX - 2] != null) {
				return true;
			}
			return false;
		}
	}

	static boolean isNearAroundSquareLine(Square square, Square field[][]) {
		synchronized (square) {
			if (field[square.newFieldY - 2][square.newFieldX - 2] != null) {
				return true;
			} else if (field[square.newFieldY - 2][square.newFieldX + 2] != null) {
				return true;
			} else if (field[square.newFieldY + 2][square.newFieldX + 2] != null) {
				return true;
			} else if (field[square.newFieldY + 2][square.newFieldX - 2] != null) {
				return true;
			}

			return false;
		}
	}

	static boolean isNearSquare(Square square, Square field[][]) {
		synchronized (square) {
			if (field[square.newFieldY - 1][square.newFieldX - 1] != null) {
				return true;
			}
			if (field[square.newFieldY - 1][square.newFieldX + 1] != null) {
				return true;
			}
			if (field[square.newFieldY + 1][square.newFieldX + 1] != null) {
				return true;
			}
			if (field[square.newFieldY + 1][square.newFieldX - 1] != null) {
				return true;
			}
			return false;
		}
	}

	static void moveLeftShape(Shape shape, Square field[][]) {
		synchronized (shape) {
			Square square[] = shape.getArray();
			for (Square element : square) {
				if (element.newFieldX == 2) {
					return;
				} else if (field[element.newFieldY][element.newFieldX - 1] != null) {
					return;
				}
			}
			shape.update(-1, 0);
		}
	}

	static void moveRightShape(Shape shape, Square field[][]) {
		synchronized (shape) {
			Square square[] = shape.getArray();
			for (Square element : square) {
				if (element.newFieldX == 14) {
					return;
				} else if (field[element.newFieldY][element.newFieldX + 1] != null) {
					return;
				}
			}
			shape.update(1, 0);
		}
	}

	static void moveIfNearWall(Shape shape, int x) {
		Square square[] = shape.getArray();
		if (square[1].newFieldX == 2) {
			shape.update(x, 0);
		}
		if (square[1].newFieldX == 14) {
			shape.update(-x, 0);
		}
	}

    
	static void moveIfNearWallLine(Shape shape) {

		Square square[] = shape.getArray();

		if (square[2].newFieldX == 2) {
			shape.update(2, 0);
		}
		if (square[2].newFieldX == 3) {
			shape.update(1, 0);
		}
		if (square[2].newFieldX == 14) {
			shape.update(-2, 0);
		}
		if (square[2].newFieldX == 13) {
			shape.update(-1, 0);
		}
	}

	
}


