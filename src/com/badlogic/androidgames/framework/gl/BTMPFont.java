package com.badlogic.androidgames.framework.gl;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.badlogic.androidgames.framework.FileIO;
import com.badlogic.androidgames.framework.impl.*;
public class BTMPFont {
	BufferedReader in;
    public final TextureRegion[] glyphs = new TextureRegion[96];  
	public BTMPFont(GLGame game,String cfg,Texture texture){
		FileIO fileIO = game.getFileIO();
		String str;
		Matcher matcher = null;
	   	try {
			in = new BufferedReader(new InputStreamReader(fileIO.readAsset(cfg)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    StringBuilder b = new StringBuilder();
	    Pattern pattern = Pattern.compile("y=");
	    Pattern patternX = Pattern.compile("x=");
	    Pattern  patternY = Pattern.compile("y=");
	    Pattern patternWidth = Pattern.compile("width=");
	    Pattern patternHeight = Pattern.compile("height=");
	    
	    while(true){
	    try {
			b.append(in.readLine());
			matcher = pattern.matcher(b);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	    

	    if(matcher.find()){

	    	break;
	    }
		 b.delete(0, b.length()-1);
		 
	    }
	     for(int i = 0; i <= 94;i++){
	    	
	    	 matcher = patternX.matcher(b.toString());
	    	 matcher.find();
	    	 str = b.substring(matcher.end(), matcher.end()+3);
	    	 str =  str.replaceAll("\\s", "");
	    	 float x = Integer.parseInt(str);

	    	 matcher = patternY.matcher(b.toString());
	    	 matcher.find();
	    	 str = b.substring(matcher.end(), matcher.end()+3);
	    	 str =  str.replaceAll("\\s", "");
	    	 float y = Integer.parseInt(str); 


	    	 matcher = patternWidth.matcher(b.toString());
	    	 matcher.find();
	    	 str = b.substring(matcher.end(), matcher.end()+3);
	    	 str =  str.replaceAll("\\s", "");
	    	 float width = Integer.parseInt(str);

	         
	    	 matcher = patternHeight.matcher(b.toString());
	    	 matcher.find();
	    	 str = b.substring(matcher.end(), matcher.end()+3);
	    	 str =  str.replaceAll("\\s", "");
	    	 float height = Integer.parseInt(str);
	    	 b.delete(0, b.length()-1);
	    	 try {
				b.append(in.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 glyphs[i] = new TextureRegion(texture,x,y,width,height);
	     }
	     try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	
public void drawText(SpriteBatcher batcher, String text, float x, float y,float size) {
    int len = text.length();
    for(int i = 0; i < len; i++) {
        int c = text.charAt(i) - ' ';
        if(c < 0 || c > glyphs.length - 1) 
            continue;
        
        TextureRegion glyph = glyphs[c];
        batcher.drawSprite(x, y,glyph.width*size, glyph.height*size,glyph,1,1,0,1);
        x +=glyphs[c].width*size;
    }
}
}


