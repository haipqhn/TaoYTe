package com.volcano.taoyte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ThemDonThuocActivity extends ActionBarActivity {

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
		setContentView(R.layout.activity_them_don_thuoc);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getSupportActionBar().setCustomView(R.layout.abs_layout);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.abc_ab_ffbb33));
		TextView titleView = (TextView) findViewById(R.id.mytext);
		titleView.setText(getString(R.string.title_activity_them_don_thuoc).toUpperCase());
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
//	    TextView mainTextView=(TextView) findViewById(R.id.mainTexttdt);
//	    mainTextView.setText(testString);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.them_don_thuoc, menu);
		return true;
	}

	private OnClickListener addBtnOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getApplicationContext(),ThemDonThuocActivity.class);
			startActivity(intent);
			finish();
		}
	};
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	        // do something on back.
	    	finish();
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
}
