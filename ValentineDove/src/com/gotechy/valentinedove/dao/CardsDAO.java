package com.gotechy.valentinedove.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.Context;
import android.util.Log;

import com.gotechy.valentinedove.bean.C;
import com.gotechy.valentinedove.bean.CardData;

public class CardsDAO {
	//TODO for thumb prefix "t_" to the filename
	
	private static final String aFileName = "AssetCardsData.xml";
	private static final String dwFileName = "DownloadableCardsData.xml";
	//TODO
	//private final String serverDirectory = "http://www/.gotechy.com/images";
	
	public static CardData readAvailableCards(Context c){
		Serializer s = new Persister();
		CardData cardData = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(c.getAssets().open(aFileName)));
		    cardData = s.read(CardData.class, reader);
		    reader.close();
		    reader = new BufferedReader(new InputStreamReader(c.getAssets().open(dwFileName)));
		    CardData temp = s.read(CardData.class, reader);
		    Iterator<C> itr = temp.getCards().iterator();
		    File f;
		    C crd;
		    while(itr.hasNext()){
		    	crd = itr.next();
		    	f = new File(c.getExternalFilesDir(null), crd.getN());
		    	if(f.exists()){
		    		cardData.getCards().add(crd);
		    	}
		    }
		    
		} catch (IOException e) {
		    Log.d("dao exception", e.getMessage());
		    return null;
		} catch (Exception e) {
			Log.d("exception", e.getMessage());
			return null;
		}
		return cardData;
	}

	public static List<String> readDownloadableCards(Context c, List<String> exists, int x){
		List<String> dwList = new ArrayList<String>();
		Serializer s = new Persister();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(c.getAssets().open(dwFileName)));
			s = new Persister();
			CardData temp = s.read(CardData.class, reader);
			Iterator<C> itr = temp.getCards().iterator();
		    C crd;
		    while(itr.hasNext()){
		    	crd = itr.next();
		    	if((!exists.contains(crd.getN())) && crd.getD()==x){
		    		//TODO
		    		dwList.add(crd.getN());
		    	}
		    	// only 3 new cards should be added..
		    	if(dwList.size()==3){
		    		break;
		    	}
		    }
		} catch (IOException e) {
		    Log.d("dao exception", e.getMessage());
		    return null;
		} catch (Exception e) {
			Log.d("exception", e.getMessage());
			return null;
		}
		return dwList;
	}
}
