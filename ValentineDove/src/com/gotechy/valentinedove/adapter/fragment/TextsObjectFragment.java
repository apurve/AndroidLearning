package com.gotechy.valentinedove.adapter.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gotechy.valentinedove.activity.R;

public class TextsObjectFragment extends Fragment {
	public static final String ARG_OBJECT = "list-item";
	
    @Override
    public ListView onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ListView view = (ListView) inflater.inflate(R.layout.fragment_collection_list, container, false);
        Bundle args = getArguments();
        List<String> list1Strings = args.getStringArrayList(ARG_OBJECT);

        view.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list1Strings));
        
        
        view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				TextView tv = (TextView) v;
            	Intent i = new Intent(Intent.ACTION_SEND);
            	i.setType("text/*");
            	i.putExtra(Intent.EXTRA_TEXT,(String) tv.getText());
            	startActivity(i);
			}
		});
        return view;
}
}