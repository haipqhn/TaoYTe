package com.volcano.taoyte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBAdapter {

	public static final String TAG ="DBAdapter";
	
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_TEST = "test";
	public static final String DATABASE_NAME = "Database_Demo";
	public static final String DATABASE_TABLE = "tests";
	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDB;
	
	private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE +" (_id integer primary key autoincrement, "
			+ KEY_TEST + " text not null, "+
			KEY_NAME+ " text not null);";
	private static final int DATABASE_VERSION = 1;
	
	private final Context mContext;
	
	private static class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.i(TAG, "Upgrading DB");
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
			onCreate(db);
		}
	}
	
	public DBAdapter(Context ctx){
		this.mContext = ctx;
	}
	
	public DBAdapter open()
	{
		mDbHelper = new DatabaseHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
		mDB = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		mDbHelper.close();
	}
	
	public void deleteAllRecords(String table) {
		String query="delete from "+table;
		mDB.execSQL(query);
	}

	public void createRecord(String Table,String key1, String name1, String key2, String name2){
		ContentValues inititalValues = new ContentValues();
		//inititalValues.put(key1, name1);
		//return mDB.insert(Table, null, inititalValues);
		String query="insert into "+ Table+ "( "+ key1 +", "+ key2 +" )"
					 +"values ( "+name1+", "+name2+" );";
		mDB.execSQL(query);
		
	}
	public long createUser(String Table,String key, String name){
		ContentValues inititalValues = new ContentValues();
		inititalValues.put(key, name);
		return mDB.insert(Table, null, inititalValues);
	}
	
	public boolean deleteUser(long rowId)
	{
		return mDB.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) >0;
	}
	public Cursor getAllUsers(String col1, String col2){
		return mDB.query(DATABASE_TABLE, new String[] {KEY_ID, col1,col2}, null, null, null, null, null);
	}
}
