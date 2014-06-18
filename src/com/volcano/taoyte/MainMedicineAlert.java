package com.volcano.taoyte;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

public class MainMedicineAlert extends ActionBarActivity {
	private DBAdapter mDB;
	private Cursor mCursor;
	String dBString="";
	String[] testStringArray,testStringArray2,testStringArray3;
	
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
		setContentView(R.layout.activity_main_medicine_alert);
	    createActionBar();
	    setUpButton();
	    ListView lv=(ListView) findViewById(R.id.lvDSDonThuoc);
	    Long rand, rand2;
        mDB = new DBAdapter(this);
        mDB.open();
        mDB.deleteAllRecords(DBAdapter.DATABASE_TABLE);
        int max=10;
        testStringArray2=new String[max];
        testStringArray3=new String[max];
        for (int i=0;i<max;i++){
        	rand=(long) (Math.random()*1000000000);
        	rand2=(long) (Math.random()*1000000000);
//        	testStringArray2[i]="Second line which is a long line of text and needs to scroll";
        	testStringArray3[i]="Third line which is a long line of text and needs to scroll";
	        mDB.createRecord(DBAdapter.DATABASE_TABLE, DBAdapter.KEY_TEST, rand.toString(), DBAdapter.KEY_NAME, rand2.toString());
        }
//        mDB.createRecord(DBAdapter.DATABASE_TABLE, DBAdapter.KEY_TEST, "Phạm Quang Hải");
        getData();	    
        clearListView(lv);
        updateListView(testStringArray, testStringArray2, testStringArray3, lv);
//	    TextView mainTextView=(TextView) findViewById(R.id.mainText);
//	    mainTextView.setText(dBString);
	}
	private void createActionBar(){
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getSupportActionBar().setCustomView(R.layout.abs_layout);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.abc_ab_ffbb33));
		TextView titleView = (TextView) findViewById(R.id.mytext);
		titleView.setText(getString(R.string.main_activity_name).toUpperCase());
		ActionBar actionBar = getSupportActionBar();		 
		actionBar.setDisplayShowTitleEnabled(true);
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    getSupportActionBar().setHomeButtonEnabled(true);
	    getSupportActionBar().setDisplayShowHomeEnabled(true);
	    getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.menu));
	}
	private void setAnimation(){
		
	}
	private void setUpButton(){
		ImageButton addImageButton=(ImageButton) findViewById(R.id.plusButton);
		addImageButton.setOnClickListener(addBtnOnClickListener);		
	}
    private void getData(){
    	mCursor = mDB.getAllUsers(DBAdapter.KEY_TEST, DBAdapter.KEY_NAME);
    	startManagingCursor(mCursor);
    	String[] from = new String[]{DBAdapter.KEY_TEST};
    	int[] to = new int[] {R.id.firstLine};
    	String[] temp;String cat,cat2;
    	testStringArray=new String[mCursor.getCount()];
    	for (int i=0;i<mCursor.getCount();i++) {
    		mCursor.moveToFirst();
//    	    long id = mCursor.getLong(mCursor.getColumnIndex("_id"));
    	    cat = mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_TEST));
    	    cat2 = mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_NAME));
//    	    dBString+=cat+"\n";
    		testStringArray[i]=cat;
    		testStringArray2[i]=cat2;
    	}
    }
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
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
	private void updateListView(String[] list1,String[] list2,String[] list3,ListView lv) {
		clearListView(lv);
		final String[] Flist1=list1;
		final String[] Flist2=list2;
		final String[] Flist3=list3;
		@SuppressWarnings("unchecked")
		ArrayAdapter adapter = new ArrayAdapter(this, R.layout.don_thuoc_layout, R.id.firstLine, list1) {
			  @Override
			  public View getView(int position, View convertView, ViewGroup parent) {
			    View view = super.getView(position, convertView, parent);
			    TextView text1 = (TextView) view.findViewById(R.id.firstLine);
			    TextView text2 = (TextView) view.findViewById(R.id.secondLine);
			    TextView text3 = (TextView) view.findViewById(R.id.thirdLine);
			    
			    text2.setSelected(true);
			    text3.setSelected(true);

			    text1.setText(Flist1[position]);
			    text2.setText(Flist2[position]);
			    text3.setText(Flist3[position]);
			    Log.d("position", String.valueOf(position));
			    return view;
			  }
			};
        lv.setAdapter(adapter);
		lv.invalidateViews();
	}
	private void clearListView(ListView lv){
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.don_thuoc_layout, R.id.firstLine);
		lv.setAdapter(adapter);
		lv.invalidateViews();
	}

	private void createAPLlistView(){
		ListView lv=new ListView(this);
		lv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
	}
}
