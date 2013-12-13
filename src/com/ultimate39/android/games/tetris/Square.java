package com.ultimate39.android.games.tetris;
import com.badlogic.androidgames.framework.math.Vector2;

public class Square {
   Vector2 position;
   int color;
   int newFieldX,newFieldY;
   public Square(Vector2 position,int color,int fieldX,int fieldY){
	   this.position = position;
	   this.color = color;
	   this.newFieldX = fieldX;
	   this.newFieldY = fieldY;
   }
}
