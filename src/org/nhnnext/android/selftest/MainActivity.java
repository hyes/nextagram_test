package org.nhnnext.android.selftest;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity implements OnClickListener, OnItemClickListener{

	private ArrayList<Article> articleList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button mButtonWrite =  (Button)findViewById(R.id.main_button_write);
		Button mButtonRefresh = (Button)findViewById(R.id.main_button_refresh);
		
		mButtonWrite.setOnClickListener(this);
		mButtonRefresh.setOnClickListener(this);

		ListView listView = (ListView)findViewById(R.id.custom_list_listView);
		
		/* 
		 * Dao생성시 해당 context에 DB 생성
		 * DB 연동 Json데이터를 getJsonData()로 불러 String 변수에 지정
		 * 지정한 변수를 insertJsonData()로 레코드 생성 
		 */
		Dao dao = new Dao(getApplicationContext());
		String testJsonData = dao.getJsonTestData();
		dao.insertJsonData(testJsonData);
		
		articleList = dao.getArticleList();
		CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, articleList);
		/* 
		 * context를 넘기는 자리에 activity의 this를 넘길 수 있는 이유는 
		 * activity가 context를 상속받았기 때문.
		 */
		listView.setAdapter(customAdapter);
		listView.setOnItemClickListener(this);

	
	}
	@Override
	public void onClick(View arg0) {
		
		switch(arg0.getId()){
		case R.id.main_button_write:
			Intent intentWrite = new Intent(this, Write_article.class);
			startActivity(intentWrite);
		case R.id.main_button_refresh:

			break;
		}
		}
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
	
		/*
		 * intent는 component(Activity)간의 메시지   
		 * putExtra() intent에 데이터를 저장할 때 사
		 * getExtra() intent에서 데이터를 가져올 때 사용 
		 */
		Intent intent = new Intent(this, View_article.class);
		
		intent.putExtra("ArticleNumber", articleList.get(position).getArticleNumber() + "");
		startActivity(intent);
		
	}


}
