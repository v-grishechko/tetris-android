package com.ultimate39.android.games.tetris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.androidgames.framework.FileIO;
import com.badlogic.androidgames.framework.FileIOInternal;

;

public class Settings {
	public static int highscore = 0;

	public static void load(FileIOInternal files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readFile(".tetris")));

			highscore = Integer.parseInt(in.readLine());

		} catch (IOException e) {
			// :( It's ok we have defaults
		} catch (NumberFormatException e) {
			// :/ It's ok, defaults save our day
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
	}

	public static void save(FileIOInternal files) {
		BufferedWriter out = null;
		try {
			
				out = new BufferedWriter(new OutputStreamWriter(
						files.writeFile(".tetris")));
			
			out.write(Integer.toString(highscore));
		}catch (IOException e) {
			//Бебе
		} catch (Exception e) {
			//Бебе
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}

	public static void addScore(int score) {
           if(score > highscore){
        	   highscore = score;
           }
	}
	public static void getHighScore(FileIOInternal files){
	   try {
		BufferedReader in = new BufferedReader(new InputStreamReader(files.readFile(".tetris")));
		String str =  in.readLine();
		int score = Integer.parseInt(str);
	    System.out.println(score+"HighScore");
	} catch (IOException e) {
		//
	}
		
	}

}
