package com.gotechy.valentinedove.activity;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.gotechy.valentinedove.dao.DaysDAO;

public class HomeActivity extends Activity {
	
	TextView creditView;
	TextView summaryView;
	
	Button textsButton;
	Button cardsButton;
	Button aboutValentineButton;
	Button aboutUsButton;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		creditView = (TextView) findViewById(R.id.credits_lable);
		if(creditView != null)
			creditView.setMovementMethod(LinkMovementMethod.getInstance());
		
		summaryView = (TextView) findViewById(R.id.home_lable);
		if(summaryView != null){
			List<String> history = DaysDAO.readDayHistory(getApplicationContext());
			Date date = new Date();
			switch(date.getDate()){
			case 7:{summaryView.setText(history.get(0));break;}
			case 8:{summaryView.setText(history.get(1));break;}
			case 9:{summaryView.setText(history.get(2));break;}
			case 10:{summaryView.setText(history.get(3));break;}
			case 11:{summaryView.setText(history.get(4));break;}
			case 12:{summaryView.setText(history.get(5));break;}
			case 13:{summaryView.setText(history.get(6));break;}
			case 14:{summaryView.setText(history.get(7));break;}
			default:{summaryView.setText(history.get(0));break;}
			}
			summaryView.setMovementMethod(new ScrollingMovementMethod());
		}
		
		textsButton = (Button) findViewById(R.id.button_texts);
		textsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this, TextsActivity.class);
				startActivity(intent);
			}
		});
		
		cardsButton = (Button) findViewById(R.id.button_cards);
		cardsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this, CardsActivity.class);
				startActivity(intent);
			}
		});
		
		aboutValentineButton = (Button) findViewById(R.id.button_about_valentine);
		aboutValentineButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
                startActivity(intent);
			}
		});
		
		aboutUsButton = (Button) findViewById(R.id.button_about_us);
		aboutUsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, AboutActivity.class);
				startActivity(i);
			}
		});
	}

}
