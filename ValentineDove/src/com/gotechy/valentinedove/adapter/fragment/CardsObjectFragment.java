package com.gotechy.valentinedove.adapter.fragment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gotechy.valentinedove.activity.R;
import com.gotechy.valentinedove.adapter.CardAdapter;
import com.gotechy.valentinedove.dao.CardsDAO;

public class CardsObjectFragment extends Fragment {

    public static final String ARG_OBJECT = "list-item";
    public static final String ITEM = "item";
    public static final String download_folder = "http://www.gotechy.in/wp-content/uploads/2013/08/";
    List<String> uriList;
    CardAdapter ca;
    ListView view;
    Button btnLoadMore;
    ProgressDialog pDialog;
    Bundle args;
    
    @Override
    public ListView onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	view = (ListView) inflater.inflate(R.layout.fragment_collection_list, container, false);
    	args = getArguments();
        uriList = args.getStringArrayList(ARG_OBJECT);

        ca = new CardAdapter(getActivity(),  uriList); 
        //pass list of ids which you want to display
        view.setAdapter(ca);
        
        btnLoadMore = new Button(container.getContext());
        btnLoadMore.setText("Load More");
        
        view.addFooterView(btnLoadMore);
        
        btnLoadMore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DownloadFileFromURL download = new DownloadFileFromURL();
				download.execute(download_folder, Integer.toString(args.getInt(ITEM)));
			}
		});
        
        view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				Intent imgi = new Intent (Intent.ACTION_SEND, Uri.parse(getActivity().getExternalFilesDir(null)+File.separator+uriList.get(position)));
        		imgi.setType("image/*");
        		imgi.putExtra(Intent.EXTRA_STREAM, Uri.parse(getActivity().getExternalFilesDir(null)+File.separator+uriList.get(position)));
        		startActivity(imgi);
			}
		});
        return view;
    }
    
    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {
    	List<String> dwList;
        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if(!(activeNetworkInfo != null && activeNetworkInfo.isConnected())){
            	Toast.makeText(getActivity().getApplicationContext(), "Your internet is disabled, turn in on and then try again.", Toast.LENGTH_SHORT).show();
            	cancel(true);
            }
			
            pDialog = new ProgressDialog(
                    getActivity());
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
            dwList = CardsDAO.readDownloadableCards(getActivity().getApplicationContext(), uriList, args.getInt(ITEM));
        	//check if any more files to download
            if(dwList.size()==0){
        		Toast.makeText(getActivity().getApplicationContext(), "No more cards to download.", Toast.LENGTH_SHORT).show();
        		pDialog.dismiss();
        		cancel(true);
        	}
            pDialog.setMessage("Please wait, starting download..");
        	
        	//check if any more files to download
        }
 
        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... params) {
        	int count;
        	try {
            	//start download
            	for(String str :dwList){
            		URL url = new URL(params[0]+str);
                    URLConnection conection = url.openConnection();
                    conection.connect();
                    // this will be useful so that you can show a tipical 0-100% progress bar
                    int lenghtOfFile = conection.getContentLength();
     
                    // download the file
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);
     
                    // Output stream
                    OutputStream output = new FileOutputStream(getActivity().getApplicationContext().getExternalFilesDir(null)+File.separator+str);
     
                    byte data[] = new byte[1024];
     
                    long total = 0;
     
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        // publishing the progress....
                        // After this onProgressUpdate will be called
                        publishProgress("Downloading "+str+" "+(int)((total*100)/lenghtOfFile)+"% done.");
     
                        // writing data to file
                        output.write(data, 0, count);
                    }
                    // flushing output
                    output.flush();
     
                    // closing streams
                    output.close();
                    input.close();
                    
                    // after image download, add the downloaded card to cards            
            	}
            	
            } catch (Exception e) {
            	Log.d("error", e.getMessage());
            	publishProgress("dwf");
            	pDialog.dismiss();
            	cancel(true);
            }
            return "Download Completed";
        }
 
        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
    		Log.d("progress update", progress[0]);
        	if(progress[0].equals("dwf"))
            	Toast.makeText(getActivity().getApplicationContext(), "Download failed, check your internet conection and try again later.", Toast.LENGTH_LONG).show();
        	else
            	pDialog.setMessage(progress[0]);
       }
 
        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        
		@Override
        protected void onPostExecute(String file_url) {
			Iterator<String> itr = dwList.iterator();
    		while(itr.hasNext()){
    			uriList.add(itr.next());
    		}
    		//TODO add cards to cards
    		ca.notifyDataSetChanged();
    		Toast.makeText(getActivity().getApplicationContext(), file_url, Toast.LENGTH_SHORT).show();
        	pDialog.dismiss();
        }
    }
}