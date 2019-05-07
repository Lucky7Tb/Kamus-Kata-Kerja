package com.example.kamuinggris;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Welcome extends Activity {
	TextView resultDisplay;
	TextView myTextView1;
	TextView myTextView2;
	EditText formCari;
	Button translateButton;
	Button changeButton;
	DBHelper Db;
	private boolean backTwice;
	int change = 1;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Db = DBHelper.getInstance(this);
		translateButton = (Button)findViewById(R.id.translateButton1);
		changeButton = (Button)findViewById(R.id.changeButton1);
		formCari = (EditText)findViewById(R.id.findForm1);
		resultDisplay = (TextView)findViewById(R.id.resultDisplay1);
		myTextView1 = (TextView)findViewById(R.id.textView1);
		myTextView2 = (TextView)findViewById(R.id.textView2);
		viewData();
		changeTranslate();
	}
	
	public void changeTranslate(){
		changeButton.setOnClickListener(
				new View.OnClickListener(){
					@Override
					public void onClick(View view){
						change++;
						resultDisplay.setText("");
						if(change > 1){
							change = 0;
							myTextView1.setText(String.valueOf("English"));
							myTextView2.setText(String.valueOf("Indonesia"));
							resultDisplay.setHint("Result");
							translateButton.setText("Translate");
							formCari.setHint("Find Verb");
						}else if(change == 1){
							myTextView1.setText(String.valueOf("Indonesia"));
							myTextView2.setText(String.valueOf("English"));
							resultDisplay.setHint("Hasil");
							translateButton.setText("Terjemahkan");
							formCari.setHint("Cari Kata Kerja");
						}
					}
				}
		);
	}
	
	public void showMessage(String title, String Message){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(Message);
		builder.show();
	}
	
	public void viewData(){
		translateButton.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Db.setKata(formCari.getText().toString().toLowerCase());
						if(change == 0){
							Cursor res = Db.getKataIndo();
							if(res.getCount()==0){
								showMessage("Alert","Kata Tidak diTemukan");
								return;
							}
							StringBuffer buffer = new StringBuffer();
							while(res.moveToNext()){
								buffer.append(res.getString(0)+"\n");
							}
							resultDisplay.setText(buffer.toString());
						}else if(change == 1){
							Cursor res = Db.getKataInggris();
							if(res.getCount()==0){
								showMessage("Alert","Kata Tidak diTemukan");
								return;
							}
							StringBuffer buffer = new StringBuffer();
							while(res.moveToNext()){
								buffer.append(res.getString(0)+"\n");
							}
							resultDisplay.setText(buffer.toString());
						}				
					}
				}
		);
	}

	@Override
	public void onBackPressed() {
		if(backTwice == true){
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
			System.exit(0);
		}
		backTwice = true;
		 Toast.makeText(this, "Tekan Lagi Untuk Keluar", Toast.LENGTH_SHORT).show();
		 
		 new Handler().postDelayed(new Runnable(){
			 @Override
			 public void run(){
				 backTwice = false;
			 }
		 },3000);
	}
}