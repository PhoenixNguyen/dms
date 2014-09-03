package com.hp.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.hp.customer.CustomerArrayAdapter;
import com.hp.domain.Customer;
import com.hp.domain.Provider;
import com.hp.map.CustomerListActivity;
import com.hp.map.CustomerMapActivity;
import com.hp.map.R;
import com.hp.map.TakeOrder_ProductActivity;
import com.sun.jersey.api.client.ClientResponse;

public class ProviderAPI {
	public static List<Provider> providersList;

	// ///////////////// LOAD
	// /////////////////////////////////////////////////////////////////////////////
	public static class GetProviderListTask extends
			AsyncTask<Void, Void, String> {
		Context context;
		String method;
		Spinner spinner;
		
		TakeOrder_ProductActivity activity;
		
		public GetProviderListTask(Context context, String method, Spinner spinner, TakeOrder_ProductActivity activity) {
			this.context = context;
			this.method = method;
			this.spinner = spinner;
			this.activity = activity;
		}

		
		ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(context, "", "Đang tải ... ", true);
		}

		protected String doInBackground(Void... params) {
			// do something
			if (CheckingInternet.isOnline()) {
				System.out.println("Internet access!!____________________");
			} else {
				
				System.out.println("NO Internet access!!____________________");

				return "nointernet";

			}

			// Getting
			ClientResponse response = Rest.mService.path("webresources")
					.path(method).accept("application/json")
					.type("application/json").get(ClientResponse.class);
			System.out.println("________________ " + response.toString());

			if (response.getStatus() != 200) {

				return "nodata";
			} else {

				String re = response.getEntity(String.class);
				System.out.println("________________ " + re);

				// Convert
				if (ConvertStringToObjectList(re))
					
					
					return "success";
				else
					return "nodata";
			}
			// =====================================================================================

		}

		protected void onPostExecute(String result) {
			if (result.equals("success")) {
				
				spinner = (Spinner) activity.findViewById(R.id.class_id);
				
				List<String> list = new ArrayList<String>();
				for(int i = 0; i < providersList.size(); i++){
					
					//Add
					list.add(providersList.get(i).getId());
				}
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
					android.R.layout.simple_spinner_item, list);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner.setAdapter(dataAdapter);
				
				
			} else if (result.equals("nointernet")) {
				Toast.makeText(context,
						"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
						Toast.LENGTH_SHORT).show();
			} else if (result.equals("nodata")) {
				Toast.makeText(context, "Không có dữ liệu!", Toast.LENGTH_SHORT)
						.show();
			} else {
				
				Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

			}
			
			dialog.dismiss();
		}

		public boolean ConvertStringToObjectList(String input) {
			// pair to object
			ObjectMapper mapper = new ObjectMapper();

			try {
				providersList = mapper.readValue(
						input,
						TypeFactory.defaultInstance().constructCollectionType(
								List.class, Provider.class));
				// System.out.println("++++++++++++++ mdt "+customerList.get(0).getmMaDoiTuong());
			} catch (JsonGenerationException e) {
				e.printStackTrace();
				return false;
			} catch (JsonMappingException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

			return true;
		}

	}
}
