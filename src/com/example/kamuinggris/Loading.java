package com.example.kamuinggris;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;

public class Loading extends Activity {
	private static int splashInterval = 5000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		 new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent i = new Intent(Loading.this, Welcome.class);
					startActivity(i);
				}
			}, splashInterval);
	}; 
	
    
}
