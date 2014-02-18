package com.gotechy.valentinedove.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class InitializeActivity extends Activity {
	
	ImageView ivAppLogo;
	ImageView ivOurLogo;
	
	TextView tv;
	
	ProgressBar p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initialize);
		
		ivOurLogo = (ImageView) findViewById(R.id.ourLogo_ini);
		ivAppLogo = (ImageView) findViewById(R.id.appLogo_ini);
		
		p = (ProgressBar) findViewById(R.id.progressBar_ini);
		tv = (TextView) findViewById(R.id.progress_status);
		
		InitializeDAO idao = new InitializeDAO();
		idao.execute("");
		
	}
	
	class InitializeDAO extends AsyncTask<String, Integer, String> {
		int x = 0;
		
		@Override
		protected void onPreExecute() {
			tv.setText("Initializing...");
			String state = Environment.getExternalStorageState();
		    if (!Environment.MEDIA_MOUNTED.equals(state)) {
		    	Toast.makeText(getApplicationContext(), "Insert your SD Card, Dove cards needs to use it.", Toast.LENGTH_LONG).show();
		    	cancel(true);
		    }
		}
		
		@Override
		protected String doInBackground(String... params) {
			AssetManager assetManager = getAssets();            
            InputStream in = null;
            OutputStream out = null;
            try {
            	String[] files = assetManager.list("thumbs");
            	int count = 0;
            	int size = files.length;
            	for(String file: files){
            		File f = new File(getApplicationContext().getExternalFilesDir(null)+File.separator+file);
            		try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
            		if(!f.exists()){
            			out = new FileOutputStream(f);
                        in = assetManager.open("thumbs"+File.separator+file);
                        copyFile(in, out);
                        in.close();
                        in = null;
                        out.flush();
                        out.close();
                        out = null;
            		}
                    count++;
                    publishProgress((count*100)/size);
            	}
            }catch (IOException e) {
              	Log.d("error", e.getMessage());
              	cancel(true);
              	}
			return null;
		}
		
		private void copyFile(InputStream in, OutputStream out) throws IOException {
		    byte[] buffer = new byte[1024];
		    int read;
		    while((read = in.read(buffer)) != -1){
		      out.write(buffer, 0, read);
		    }
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			p.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			tv.setText("Initialization Completed...");
			Intent i = new Intent(InitializeActivity.this, HomeActivity.class);
			startActivity(i);
			finish();
		}
	}

}

