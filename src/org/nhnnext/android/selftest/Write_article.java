package org.nhnnext.android.selftest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Write_article extends Activity implements OnClickListener{
	
	private Button wButtonWrite;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_article);
		
		wButtonWrite = (Button)findViewById(R.id.write_button_write);
		wButtonWrite.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()){
		case R.id.write_button_write:
			Intent intentWrite = new Intent(this, MainActivity.class);
			startActivity(intentWrite);
		}
	}
	
}