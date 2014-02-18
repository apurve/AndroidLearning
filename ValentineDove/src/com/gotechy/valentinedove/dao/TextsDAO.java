package com.gotechy.valentinedove.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.Context;
import android.util.Log;

import com.gotechy.valentinedove.bean.TextData;

public class TextsDAO {
	
	private static final String dataFileName = "TextsData.xml";
	
	public static TextData readTexts(Context c){
		Serializer s = new Persister();
		TextData textData = null;
		try {
		    BufferedReader reader = new BufferedReader(
		        new InputStreamReader(c.getAssets().open(dataFileName)));
		    s = new Persister();
		    textData = s.read(TextData.class, reader);
		    reader.close();
		} catch (IOException e) {
		    Log.d("dao exception", e.getMessage());
		    return null;
		} catch (Exception e) {
			Log.d("exception", e.getMessage());
			return null;
		}
		return textData;
	}
}
