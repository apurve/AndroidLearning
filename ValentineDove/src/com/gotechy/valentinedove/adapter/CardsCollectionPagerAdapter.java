package com.gotechy.valentinedove.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gotechy.valentinedove.adapter.fragment.CardsObjectFragment;
import com.gotechy.valentinedove.bean.C;
import com.gotechy.valentinedove.bean.CardData;
import com.gotechy.valentinedove.dao.CardsDAO;

public class CardsCollectionPagerAdapter extends FragmentStatePagerAdapter {

	Context c;
	
	public CardsCollectionPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	
	public CardsCollectionPagerAdapter(FragmentManager fm, Context c) {
		super(fm);
		this.c = c;
	}

	@Override
	public Fragment getItem(int i) {
		CardData cards = CardsDAO.readAvailableCards(c);
        
		Iterator<C> itr = cards.getCards().iterator();
		List<String> list1Strings = new ArrayList<String>();
		while(itr.hasNext()){
			C crd = itr.next();
			if(crd.getD()==i)
				list1Strings.add(crd.getN());
		}
		
		Bundle args = new Bundle();
		args.putStringArrayList(CardsObjectFragment.ARG_OBJECT, (ArrayList<String>) list1Strings);
		args.putInt(CardsObjectFragment.ITEM, i);
        Fragment fragment = new CardsObjectFragment();
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
		default : return "Rose Day";
		}
    }
	
}