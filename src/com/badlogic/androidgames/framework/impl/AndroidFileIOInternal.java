package com.badlogic.androidgames.framework.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

import com.badlogic.androidgames.framework.FileIOInternal;

public class AndroidFileIOInternal implements FileIOInternal {
     Context context;
	public AndroidFileIOInternal(Context context){
	    this.context = context;
	}
	@Override
	public InputStream readFile(String fileName) throws IOException {
		return 	context.openFileInput(fileName); 
	}
	@Override
	public OutputStream writeFile(String fileName) throws IOException {
		return  context.openFileOutput(fileName, Context.MODE_PRIVATE);
	}

}
