package com.bbr0308.listviewupdate;

import java.util.ArrayList;
import java.util.List;

import com.example.testpreference.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ListView mListView;
	MyAdapter mMyAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*SharedPreferences sp = getSharedPreferences("text", this.MODE_PRIVATE);
        sp.edit().putInt("aaa", 11);
        System.out.println("zhangcheng>>>>>>>>>>>>>>>>>>>>>>>>>>begin");
        for (int i = 0; i < 1000000; i++) {
        	sp.getInt("aaa", 0);
        }
        System.out.println("zhangcheng>>>>>>>>>>>>>>>>>>>>>>>>>>end");*/
        mListView = (ListView) findViewById(R.id.lv);
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
        	l.add(i);
        }
        mMyAdapter = new MyAdapter(this, l);
        mListView.setAdapter(mMyAdapter);
        new DataThread(this, l).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private class MyAdapter extends BaseAdapter {
    	private Context mContext;
    	private List<Integer> mList;
    	
    	public MyAdapter(Context cxt, List<Integer> l) {
			// TODO Auto-generated constructor stub
    		mContext = cxt;
    		mList = l;
		}
    	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			if (null == arg1) {
				arg1 = new TextView(mContext);
			}
			((TextView)arg1).setText(mList.get(arg0).toString());
			return arg1;
		}
    	
    }
    
    private class DataThread extends Thread {
    	private List<Integer> mDataList;
    	private Context mContext;
    	
    	public DataThread(Context cxt, List<Integer> l) {
			// TODO Auto-generated constructor stub
    		mContext = cxt;
    		mDataList = l;
		}
    	
    	@Override
    	public void run() {
    		// TODO Auto-generated method stub
    		super.run();
    		int count = 1;
    		try {
    			while(count < 10) {
    				Thread.sleep(1000);
    				for (int i = 0; i < 10; i++) {
    					mDataList.add(count * 10 + i);
    				}
    				count++;
        			System.out.println("zhangcheng===================mListView firstPositioin " + mListView.getFirstVisiblePosition());
        			runOnUiThread(new Runnable() {

    					@Override
    					public void run() {
    						// TODO Auto-generated method stub
    						System.out.println("zhangcheng>>>>>>>>>>>>>run");
    						mMyAdapter.notifyDataSetChanged();
    						mListView.setSelection(6);
    					}
        				
        			});
    			}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
