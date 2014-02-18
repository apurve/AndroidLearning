package com.gotechy.valentinedove.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gotechy.valentinedove.adapter.fragment.TextsObjectFragment;
import com.gotechy.valentinedove.bean.T;
import com.gotechy.valentinedove.bean.TextData;
import com.gotechy.valentinedove.dao.TextsDAO;

public class TextsCollectionPagerAdapter extends FragmentStatePagerAdapter {

	Context c;
	public TextsCollectionPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	public TextsCollectionPagerAdapter(FragmentManager fm, Context c) {
	    super(fm);
	    this.c = c;
	}

	@Override
	public Fragment getItem(int i) {

        TextData texts = TextsDAO.readTexts(c);
        
		Iterator<T> itr = texts.getTexts().iterator();
		List<String> list1Strings = new ArrayList<String>();
		while(itr.hasNext()){
			T txt = itr.next();
			if(txt.getD()==i)
				list1Strings.add(txt.getM());
		}
		
		Bundle args = new Bundle();
        args.putStringArrayList(TextsObjectFragment.ARG_OBJECT, (ArrayList<String>) list1Strings);// Our object is just an integer :-P
        
        Fragment fragment = new TextsObjectFragment();
        fragment.setArguments(args);
        return fragment;
	}
	
	@Override
	public int getCount() {
		return 8;
	}
	
	@Override
    public String getPageTitle(int position) {
		switch(position){
		case 0:return "Rose Day";
		case 1:return "Propose Day";
		case 2:return "Chocolate Day";
		case 3:return "Teddy Day";
		case 4:return "Promise Day";
		case 5:return "Hug Day";
		case 6:return "Kiss Day";
		case 7:return "Valentine Day";
		default : return "Happy Day";
		}
    }
	
}