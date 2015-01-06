package com.hp.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.hp.rest.Rest;

import android.util.Log;

public class HttpHelper {
	public static int TIME_OUT_LONG = 10000;
	public static int TIME_OUT_SHORT = 3000;
	public static String makeRequest(String url){
		// Making HTTP request
		InputStream is = null;
		String json = "";
		
        try {
            // defaultHttpClient
        	/*HttpParams httpParameters = new BasicHttpParams();
        	ConnManagerParams.setTimeout(httpParameters, 10000);
            HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
            HttpConnectionParams.setSoTimeout(httpParameters, 10000);*/
            
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();           

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //parse to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        
		return json;
	}
	
	public static boolean checkServer(){
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse response;
			HttpGet httpGet = new HttpGet(Rest.mURL);
			response = httpClient.execute(httpGet);
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return true;
			}else{
				return false;
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
