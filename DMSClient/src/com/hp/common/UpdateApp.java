package com.hp.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class UpdateApp{
public static class Update extends AsyncTask<Void, Void, String> {
	Context context;
	String url;

	public Update(Context context, String url) {
		this.context = context;
		this.url = url;
	}

	ProgressDialog dialog;

	protected void onPreExecute() {
		dialog = ProgressDialog.show(context, "", "Đang tải phần mềm", true);
	}

	protected void onPostExecute(String result) {
		if (result.equals("success")) {
			// Toast.makeText(context, "Download success!",
			// Toast.LENGTH_SHORT).show();
		}
		
		dialog.dismiss();
	}

	protected String doInBackground(Void... arg0) {
		try {
			URL url = new URL(this.url);
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.setDoOutput(true);
			c.connect();

			String fileName = "dms_update.apk";
			File folder = new File(Environment.getExternalStorageDirectory(),
					"DMS");
			if (!folder.exists())
				folder.mkdir();

			/*
			 * String PATH =
			 * Environment.getExternalStoragePublicDirectory(Environment
			 * .DIRECTORY_DOWNLOADS);//"/mnt/sdcard/Download/"; File file = new
			 * File(PATH); file.mkdirs();
			 */

			File outputFile = new File(folder, fileName);
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileOutputStream fos = new FileOutputStream(outputFile);

			InputStream is = c.getInputStream();

			byte[] buffer = new byte[1024];
			int len1 = 0;
			while ((len1 = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len1);
			}
			fos.close();
			is.close();

			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(new File(folder, fileName)),
					"application/vnd.android.package-archive");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag
															// android returned
															// a intent error!
			context.startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("UpdateAPP", "Update error! " + e.getMessage());
			return "error";
		}
		return "success";
	}
}
}