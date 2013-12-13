package com.badlogic.androidgames.framework.helper;

import android.util.Log;

public class Logger {
	String TAG = "LOGGER";
	public void logArray(int[][] array,int maxX, int maxY){
		StringBuilder buffer = new StringBuilder();
		buffer.append("START LOG(Array)");
		for(int i = 0; i < maxX;i++){
			buffer.append("\n");
			for(int j = 0; j < maxY;j++){
				buffer.append(array[i][j]+" ");
			}
		}
	    buffer.append("\n"+"FINISH LOG(Array)");
	    Log.d(TAG,buffer.toString());
	}
	public void logArray(float[][] array,int maxX, int maxY){
		StringBuilder buffer = new StringBuilder();
		buffer.append("START LOG(Array)");
		for(int i = 0; i < maxX;i++){
			buffer.append("\n");
			for(int j = 0; j < maxY;j++){
				buffer.append(array[i][j]+" ");
			}
		}
	    buffer.append("\n"+"FINISH LOG(Array)");
	    Log.d(TAG,buffer.toString());
	}
	public void logArray(Object[][] array,int maxX, int maxY){
		StringBuilder buffer = new StringBuilder();
		buffer.append("START LOG(Array)");
		for(int i = 0; i < maxX;i++){
			buffer.append("\n");
			for(int j = 0; j < maxY;j++){
				if(array[i][j]!= null){
				buffer.append(array[i][j].getClass().getSimpleName()+" ");
				}else{
				buffer.append(array[i][j]+" ");
				}
			}
		}
	    buffer.append("\n"+"FINISH LOG(Array)");
	    Log.d(TAG,buffer.toString());
	}
    
}
