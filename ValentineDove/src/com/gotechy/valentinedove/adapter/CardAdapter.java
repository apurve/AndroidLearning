package com.gotechy.valentinedove.adapter;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.gotechy.valentinedove.activity.R;


public class CardAdapter extends BaseAdapter {
    private Context mContext;
    
    // references to our images
    private List<String> thumbUris;
    
    public CardAdapter(Context c, List<String> thumbUris) {
    	super();
        mContext = c;
        this.thumbUris = thumbUris;
    }

    // create a new View for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView card;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	// inflate the layout for each item of listView
            convertView = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.simple_card, parent, false);
        }
            // get the reference of imageViews
            card = (ImageView) convertView.findViewById(R.id.card);
           
            // Set the image respective imageViews
            card.setImageURI(Uri.parse(mContext.getExternalFilesDir(null)+File.separator+thumbUris.get(position)));
        return convertView;
    }
    
    public int getCount() {
    	if(thumbUris != null)
    		return thumbUris.size();
    	else
    		return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

}
