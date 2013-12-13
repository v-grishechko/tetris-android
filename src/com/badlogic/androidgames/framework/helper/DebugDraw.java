package com.badlogic.androidgames.framework.helper;


import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.gl.Vertices;
import com.badlogic.androidgames.framework.impl.GLGraphics;
import com.badlogic.androidgames.framework.math.Rectangle;

public class DebugDraw {
 final Vertices vertices;
 float[] verticesBuffer;
 public DebugDraw(GLGraphics glGraphics){
	this.vertices = new Vertices(glGraphics,4,0,true,false);
	
	
 }
 public void draw(Rectangle rec){
	 verticesBuffer = new float[24];
	 float x1 = rec.lowerLeft.x;
	 float y1 = rec.lowerLeft.y;
	 float x2 = x1+rec.width;
	 float y2 = y1;
	 float x3 = x2;
	 float y3 = y2+rec.height;
	 float x4 = x1;
	 float y4 = y3;
	 verticesBuffer[0] = x1;
	 verticesBuffer[1] = y1;
	 verticesBuffer[2] = 1;
	 verticesBuffer[3] = 0;
	 verticesBuffer[4] = 0;
	 verticesBuffer[5] = 1;
	 

	 verticesBuffer[6] = x2;
	 verticesBuffer[7] = y2;
	 verticesBuffer[8] = 1;
	 verticesBuffer[9] = 0;
	 verticesBuffer[10] = 0;
	 verticesBuffer[11] = 1;
	 

	 verticesBuffer[12] = x3;
	 verticesBuffer[13] = y3;
	 verticesBuffer[14] = 1;
	 verticesBuffer[15] = 0;
	 verticesBuffer[16] = 0;
	 verticesBuffer[17] = 1;
	 

	 verticesBuffer[18] = x4;
	 verticesBuffer[19] = y4;
	 verticesBuffer[20] = 1;
	 verticesBuffer[21] = 0;
	 verticesBuffer[22] = 0;
	 verticesBuffer[23] = 1;
	 
	 vertices.setVertices(verticesBuffer, 0, verticesBuffer.length);
	 vertices.bind();
	 vertices.draw(GL10.GL_LINE_LOOP, 0, 4);
	 vertices.unbind();
 }
}
