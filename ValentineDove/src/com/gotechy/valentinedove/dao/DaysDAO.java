package com.gotechy.valentinedove.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.Context;
import android.util.Log;

import com.gotechy.valentinedove.bean.Day;
import com.gotechy.valentinedove.bean.DayData;

public class DaysDAO {

	private static final String dataFileName = "DaysData.xml";
	
	public static List<String> readDayHistory(Context c){
		Serializer s = new Persister();
		List<String> history = new ArrayList<String>();
		try {
		    BufferedReader reader = new BufferedReader(
		        new InputStreamReader(c.getAssets().open(dataFileName)));
		    DayData dayData = s.read(DayData.class, reader);
		    reader.close();
		    Iterator<Day> itr = dayData.getDays().iterator();
		    while(itr.hasNext()){
		    	history.add(itr.next().getM());
		    }
		} catch (IOException e) {
		    Log.d("dao exception", e.getMessage());
		    return null;
		} catch (Exception e) {
			Log.d("exception", e.getMessage());
			return null;
		}
		return history;
	}
	
}
