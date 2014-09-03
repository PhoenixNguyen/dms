package com.hp.map;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.hp.datahandle.DataConvert;
import com.hp.domain.DataInfo;
import com.hp.rest.CustomerAPI.UploadCustomerImageTask;
import com.hp.rest.Rest;
import com.hp.rest.CustomerAPI.ModifyCustomerTask;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.messaging.saaj.packaging.mime.internet.MimeBodyPart;
import com.sun.xml.messaging.saaj.packaging.mime.internet.MimeMultipart;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.MediaStore.Files;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class TakeImagesActivity extends MainMenuActivity {

	protected Button _button;
	protected Button _send;
	protected ImageView _image;
	protected TextView _field;
	protected String _path;
	protected boolean _taken;

	protected static final String PHOTO_TAKEN = "photo_taken";

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		super.onCreate(savedInstanceState);

		setContentView(R.layout.take_images);

		_image = (ImageView) findViewById(R.id.image);
		_field = (TextView) findViewById(R.id.field);
		_send = (Button) findViewById(R.id.send);
		_button = (Button) findViewById(R.id.button);
		_button.setOnClickListener(new ButtonClickHandler());

		_path = Environment.getExternalStorageDirectory()
				+ "/make_machine_example.jpg";
	}

	public class ButtonClickHandler implements View.OnClickListener {
		public void onClick(View view) {
			Log.i("MakeMachine", "ButtonClickHandler.onClick()");
			startCameraActivity();
		}
	}

	protected void startCameraActivity() {
		Log.i("MakeMachine", "startCameraActivity()");
		File file = new File(_path);
		Uri outputFileUri = Uri.fromFile(file);

		Intent intent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

		startActivityForResult(intent, 0);
		
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("MakeMachine", "resultCode: " + resultCode);
		System.out.println("____________ ACTION");
		switch (resultCode) {
		case 0:
			System.out.println("_____  Cancelled");
			Log.i("MakeMachine", "User cancelled");
			break;

		case -1:
			onPhotoTaken();
			
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 4;
			Bitmap bitmap = BitmapFactory.decodeFile(_path, options);
			
			//RESIZE and SAVE
			savePhoto(bitmap);

			_send.setVisibility(View.VISIBLE);
			break;
		}
	}

	protected void onPhotoTaken() {
		Log.i("MakeMachine", "onPhotoTaken");
		System.out.println("_____  Taken");

		_taken = true;

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;

		Bitmap bitmap = BitmapFactory.decodeFile(_path, options);

		_image.setImageBitmap(bitmap);

		_field.setVisibility(View.GONE);

		
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		System.out.println("_____  Restore");
		Log.i("MakeMachine", "onRestoreInstanceState()");
		if (savedInstanceState.getBoolean(TakeImagesActivity.PHOTO_TAKEN)) {
			onPhotoTaken();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		System.out.println("_____  save");
		
		
		
		//Put image
		outState.putBoolean(TakeImagesActivity.PHOTO_TAKEN, _taken);
		
		//Turn off dialog
//		if(UploadCustomerImageTask.dialog != null)
//			UploadCustomerImageTask.dialog.dismiss();
	}

	public static String pathSave;
	public void savePhoto(Bitmap bmp) {
		File imageFileFolder = new File(
				Environment.getExternalStorageDirectory(), "DMS");
		imageFileFolder.mkdir();
		FileOutputStream out = null;
		Calendar c = Calendar.getInstance();
		String date = fromInt(c.get(Calendar.MONTH))
				+ fromInt(c.get(Calendar.DAY_OF_MONTH))
				+ fromInt(c.get(Calendar.YEAR))
				+ fromInt(c.get(Calendar.HOUR_OF_DAY))
				+ fromInt(c.get(Calendar.MINUTE))
				+ fromInt(c.get(Calendar.SECOND)) + "-hp";
		File imageFileName = new File(imageFileFolder, date.toString() + ".jpg");
		pathSave = Environment.getExternalStorageDirectory()+ "/DMS/" + imageFileName.getName();
		try {
			out = new FileOutputStream(imageFileName);
			
			final int BUFFER_SIZE = 1024 * 8;
			final BufferedOutputStream bos = new BufferedOutputStream(out, BUFFER_SIZE);
			bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
			
			bos.flush();
			bos.close();
			out.close();
			
			scanPhoto(imageFileName.toString());
			out = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String fromInt(int val) {
		return String.valueOf(val);
	}

	private MediaScannerConnection msConn = null;
	public void scanPhoto(final String imageFileName) {
		
		File file = new File(imageFileName);
		Uri contentUri = Uri.fromFile(file);
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE); 
		mediaScanIntent.setData(contentUri);
		sendBroadcast(mediaScanIntent);
		
		msConn = new MediaScannerConnection(TakeImagesActivity.this,
				new MediaScannerConnectionClient() {
					public void onMediaScannerConnected() {
						msConn.scanFile(imageFileName, null);
						Log.i("msClient obj  in Photo Utility",
								"connection established");
					}

					public void onScanCompleted(String path, Uri uri) {
						msConn.disconnect();
						Log.i("msClient obj in Photo Utility", "scan completed");
					}
				});
		msConn.connect();
	}

	public void upload(View view) {
		// PUT infomations
		if((pathSave == null)  || (pathSave.compareTo("") == 0)){
			Toast.makeText(context, "Hãy chụp một bức ảnh", Toast.LENGTH_SHORT).show();
			return;
		}
		UploadCustomerImageTask uploadData = new UploadCustomerImageTask(context, "putImage", pathSave);
		uploadData.execute();
//		
	}
	
	
}