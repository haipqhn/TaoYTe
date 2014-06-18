package com.volcano.taoyte;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.TextView;

public class DonThuocActivity extends ActionBarActivity {

	@Override
	public void onDestroy(){
		try {
			super.onDestroy();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_don_thuoc);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getSupportActionBar().setCustomView(R.layout.abs_layout);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.abc_ab_ffbb33));
		TextView titleView = (TextView) findViewById(R.id.mytext);
		titleView.setText(getString(R.string.title_activity_don_thuoc).toUpperCase());
		ActionBar actionBar = getSupportActionBar();		 
		actionBar.setDisplayShowTitleEnabled(true);
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    getSupportActionBar().setHomeButtonEnabled(true);
	    getSupportActionBar().setDisplayShowHomeEnabled(true);
	    getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.menu));
//	    String testString="";
//	    for(int i=0;i<100;i++) {
//	    	testString+=i+"\n";
//	    }
//	    TextView mainTextView=(TextView) findViewById(R.id.mainTextdt);
//	    mainTextView.setText(testString);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.don_thuoc, menu);
		return true;
	}
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	        // do something on back.
	    	finish();
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}

}
