/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hp.map;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hp.domain.Customer;
import com.hp.domain.Schedule;
import com.hp.domain.RoadManagement;
import com.hp.menu.DetailListData;
import com.hp.menu.DetailsList;
import com.hp.menu.DialogArrayAdapter;
import com.hp.rest.Rest;
import com.hp.rest.CustomerAPI;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.hp.rest.CustomerAPI.ModifyCustomerTask;
import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.transition.Scene;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

/**
 * This shows how to place markers on a map.
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class CustomerMapActivity extends FragmentActivity
										        implements
										        OnMarkerClickListener,
										        OnInfoWindowClickListener,
										        OnMarkerDragListener,
										        OnSeekBarChangeListener,
										        
										        ConnectionCallbacks,
										        OnConnectionFailedListener,
										        LocationListener,
										        OnMyLocationButtonClickListener{
    
    private GoogleMap mMap;
    private LocationClient mLocationClient;
      
    private Context context = this;
    private ListView lv;
    
 // These settings are the same as the settings for the map. They will in fact give you updates
    // at the maximal rates currently possible.
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    
    private final List<Marker> mMarkerRainbow = new ArrayList<Marker>();

    private TextView customer_name, customer_id, customer_phone, customer_address;
    private CheckBox mFlatBox;

    private final Random mRandom = new Random();
    
    private int positionClick = 0;
    public static String customerSelected = new String();
    public static Customer mSelectedCustomer;
    //current location
    public static double mX;
    public static double mY;
    
    String mUrl = "http://masterpro02.hosco.com.vn:8080/DMSProject/webresources/putJourney"; 
    
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	if (android.os.Build.VERSION.SDK_INT > 9) {
    	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    	    StrictMode.setThreadPolicy(policy);
    	}
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_map);

        Button sendLocation = (Button)findViewById(R.id.send_location);
        if(Rest.mStaff.getPermission() == 1 || Rest.mStaff.getPermission() == 3)
        	sendLocation.setVisibility(View.VISIBLE);
        
        //getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setIcon(R.drawable.ic_drawer);
        
        //Get POSITION
        Intent i = getIntent();
        customerSelected = null;
        customerSelected  = i.getStringExtra("POSITION_CLICK");
        
        for(int j = 0; j < CustomerAPI.customerList.size(); j++){
        	if(customerSelected.compareTo(CustomerAPI.customerList.get(j).getMaDoiTuong()) == 0){
        		positionClick = j;
        		mSelectedCustomer = CustomerAPI.customerList.get(j);
        	}
        }
        
        customer_name = (TextView) findViewById(R.id.customer_name);
        customer_id = (TextView) findViewById(R.id.customer_id);
        customer_phone = (TextView) findViewById(R.id.customer_phone);
        customer_address = (TextView) findViewById(R.id.customer_address);

        customer_name.setText(CustomerAPI.customerList.get(positionClick).getDoiTuong());
        customer_id.setText(CustomerAPI.customerList.get(positionClick).getMaDoiTuong());
        customer_phone.setText(CustomerAPI.customerList.get(positionClick).getDienThoai());
        customer_address.setText(CustomerAPI.customerList.get(positionClick).getDiaChi());
        
        setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customer_menu, menu);
 
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.init_order);
        if(!compareLocation())
        	item.setEnabled(false);
        else
        	item.setEnabled(true);
        return true;
        
    }
    
    public boolean compareLocation(){
    	double x = CustomerAPI.customerList.get(positionClick).getCoordinateX();
    	double y = CustomerAPI.customerList.get(positionClick).getCoordinateY();
    	System.out.println("mX va (x - 0.000099): " + mX + " " + (x - 0.000199));
    	System.out.println("mX va (x + 0.000099): " + mX + " " + (x + 0.000199));
    	
    	System.out.println("mY va (y - 0.000099): " + mY + " " + (y - 0.000199));
    	System.out.println("mY va (y + 0.000099): " + mY + " " + (y + 0.000199));
    	if(mX > (x - 0.001099) && mX < (x + 0.000199) && mY > (y - 0.000199) && mX < (y + 0.000199))
    		return true;
    	else
    		return false;
    	
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
//        case android.R.id.home:
//	    	menuDialog();
//
//	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
//	    	return true;
        case R.id.init_order:
            initTakeOrder();
            return true;
        case R.id.take_picture:
            takePicture();
            return true;
            
        case R.id.sale:
            saleOrder();
            return true;
            
        case R.id.inventory_manager:
            inventoryManager();
            return true;
       
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    //Left menu ============================================
    public boolean onKeyUp(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_MENU) {
	        // ........
	    	//Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
	    	menuDialog();
	    	
	        return true;
	    }
	    return super.onKeyUp(keyCode, event);
	}
	
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//
//	    int itemId = item.getItemId();
//	    switch (itemId) {
//	    case android.R.id.home:
//	    	menuDialog();
//
//	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
//	        break;
//	    case R.id.init_order:
//            initTakeOrder();
//            return true;
//        case R.id.take_picture:
//            takePicture();
//            return true;
//        case R.id.inventory_manager:
//            inventoryManager();
//            return true;
//       
//        default:
//            return super.onOptionsItemSelected(item);    
//	    }
//
//	    return true;
//	}
	
	public void menuDialog(){
		final Dialog dialog = new Dialog(this);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.menu_dialog, null, false);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(v);
		
		dialog.setTitle("Danh mục chính");
		
		Display display = getWindowManager().getDefaultDisplay();
		
		dialog.getWindow().setLayout(2*display.getWidth()/3, LayoutParams.FILL_PARENT);
		dialog.getWindow().getAttributes().gravity = Gravity.LEFT|Gravity.CENTER_VERTICAL;
		
		lv = (ListView)dialog.findViewById(R.id.menu_list_view);
		
		lv.setAdapter(new DialogArrayAdapter(context, android.R.layout.simple_list_item_1, DetailListData.MENU_LIST));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				DetailsList selectedValue = (DetailsList)lv.getAdapter().getItem(arg2);
				if(selectedValue.activityClass != null){
					//if sigout
					if(selectedValue.activityClass == LoginActivity.class){
						LoginActivity.threadLooper.quit();
					}
					startActivity(new Intent(context, selectedValue.activityClass));
				}
			}
		});
		
		dialog.show();
		
//		ImageView iv = (ImageView)dialog.findViewById(R.id.menu_list_view);
//		iv.setImageResource(1);
	}
	//////////////////////////////////////////==========================================
	
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        setUpLocationClientIfNeeded();
        mLocationClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mLocationClient != null) {
            mLocationClient.disconnect();
        }
    }

    public void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
             // Check if we were successful in obtaining the map.
                if (mMap != null) {
                    mMap.setMyLocationEnabled(true);
                    mMap.setOnMyLocationButtonClickListener(this);
                }
            }
        }
    } 
    
    //=========================== My Location =======================================
    private void setUpLocationClientIfNeeded() {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(
                    getApplicationContext(),
                    this,  // ConnectionCallbacks
                    this); // OnConnectionFailedListener
        }
    }
    
    /**
     * Button to get current Location. This demonstrates how to get the current Location as required
     * without needing to register a LocationListener.
     */
    public void showMyLocation(View view) {
        if (mLocationClient != null && mLocationClient.isConnected()) {
            String msg = "Location = " + mLocationClient.getLastLocation();
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
    
    /**
     * Implementation of {@link LocationListener}.
     */
    @Override
    public void onLocationChanged(Location location) {
        //mMessageView.setText("Location = " + location);
    	mX = (float)location.getLatitude();
    	mY = (float)location.getLongitude();
    	
    	//refresh menu
    	invalidateOptionsMenu();
    }
    
    /**
     * Callback called when connected to GCore. Implementation of {@link ConnectionCallbacks}.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        mLocationClient.requestLocationUpdates(
                REQUEST,
                this);  // LocationListener
    }

    /**
     * Callback called when disconnected from GCore. Implementation of {@link ConnectionCallbacks}.
     */
    @Override
    public void onDisconnected() {
        // Do nothing
    }

    /**
     * Implementation of {@link OnConnectionFailedListener}.
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Do nothing
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "Updating your location ...", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
    
  //=========================== End My Location =======================================
    
    public void setUpMap() {
        // Hide the zoom controls as the button panel will cover it.
        mMap.getUiSettings().setZoomControlsEnabled(false);

        // Add lots of markers to the map.
        addMarkersToMap(1);

        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerDragListener(this);

        // Pan to see all markers in view.
        // Cannot zoom to bounds until the map has a size.
        final View mapView = getSupportFragmentManager().findFragmentById(R.id.map).getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation") // We use the new method when supported
                @SuppressLint("NewApi") // We check which build version we are using.
                @Override
                public void onGlobalLayout() {
                    Builder builder = new LatLngBounds.Builder();
                    //for(int i = 0; i< Rest.customerList.size(); i++){
                    		
                    	builder.include(new LatLng(CustomerAPI.customerList.get(positionClick).getCoordinateX(), CustomerAPI.customerList.get(positionClick).getCoordinateY()));
                        	
                    //}
                    
                    LatLngBounds  bounds = builder.build();
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                      mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                      mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
                }
            });
        }
    }

    //If show only a customer clicked: pView = 1
    //If show all customers: pView = 0
    public void addMarkersToMap(int pView) {
    	
    	//Add Markers
    	if(pView == 1){
    		mMap.addMarker(new MarkerOptions()
            .position(new LatLng(CustomerAPI.customerList.get(positionClick).getCoordinateX(), CustomerAPI.customerList.get(positionClick).getCoordinateY()))
            .title(CustomerAPI.customerList.get(positionClick).getDoiTuong())
            .snippet(CustomerAPI.customerList.get(positionClick).getMaDoiTuong()+":"+CustomerAPI.customerList.get(positionClick).getDiaChi())
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    	}
    	else
	    	for(int i = 0; i< CustomerAPI.customerList.size(); i++){
	    		mMap.addMarker(new MarkerOptions()
	            .position(new LatLng(CustomerAPI.customerList.get(i).getCoordinateX(), CustomerAPI.customerList.get(i).getCoordinateY()))
	            .title(CustomerAPI.customerList.get(i).getDoiTuong())
	            .snippet(CustomerAPI.customerList.get(i).getMaDoiTuong()+":"+CustomerAPI.customerList.get(i).getDiaChi())
	            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	    		            	
	        }

    }

    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this, R.string.map_not_ready, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /** Called when the Clear button is clicked. */
    public void onClearMap(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.clear();
        // Add a marker to the map.
        addMarkersToMap(1);
    }

    /** Called when the Reset button is clicked. */
    public void onResetMap(View view) {
        if (!checkReady()) {
            return;
        }
        // Clear the map because we don't want duplicates of the markers.
        mMap.clear();
        //Add lots of makers to the map
        addMarkersToMap(0);
    }

    /** Called when the sendDemo button is clicked. */
    public void onSendDemo(View view) {
    	    	
    	if(mX <= 0.0 || mY <= 0.0)
    		return;
    	
        System.out.println("SEND DEMO LOCATION: _______");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        
        //Post
//        RoadManagement track = new RoadManagement(""
//        		,RestAPI.customerList.get(positionClick).getMaDoiTuong()
//        		,Timestamp.valueOf(dateFormat.format(date))
//        		,mX
//        		,mY
//        		,"");
        
        Customer customer = CustomerAPI.customerList.get(positionClick);
        customer.setCoordinateX(mX);
        customer.setCoordinateY(mY);
        
        ModifyCustomerTask insertData = new ModifyCustomerTask(context, "updateCustomer", customer, true);
		insertData.execute();
		
		//loading
		GetCustomerListTask getData = new GetCustomerListTask(context, "getCustomersListStart", Rest.mStaff.getId(),
			    true, this);
        getData.execute();
        		
    }
    
    public enum MIMETypes {
		 
		  APPLICATION_XML("application/xml");
		  
		  private final String name;
		   
		  private MIMETypes(String name) {
		    this.name = name;
		  }
		   
		  public String getName() {
		    return name;
		  }
	}
    
    /** Called when the Reset button is clicked. */
    public void onToggleFlat(View view) {
        if (!checkReady()) {
            return;
        }
        boolean flat = mFlatBox.isChecked();
        for (Marker marker : mMarkerRainbow) {
            marker.setFlat(flat);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!checkReady()) {
            return;
        }
        float rotation = seekBar.getProgress();
        for (Marker marker : mMarkerRainbow) {
            marker.setRotation(rotation);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Do nothing.
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Do nothing.
    }

    //
    // Marker related listeners.
    //

    @Override
    public boolean onMarkerClick(final Marker marker) {

            // This causes the marker at Adelaide to change color and alpha.
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(mRandom.nextFloat() * 360));
            marker.setAlpha(mRandom.nextFloat());
        
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getBaseContext(), "Click Info Window", Toast.LENGTH_SHORT).show();
    }

	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerDragEnd(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	@SuppressLint("NewApi")
	static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
	
	public boolean isOnline() { 
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); 
		return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting(); 
	}
	
	public void initTakeOrder(){
		//Reset values and init it
		TakeOrder_ProductActivity.mProductsMap.clear();
		TakeOrder_ReViewActivity.takeOrderDetailList.clear();
		TakeOrder_ProductActivity.timeLine = true;
		TakeOrder_ProductActivity.add_take_order_detail = false;
		
		startActivity(new Intent(context, TakeOrder_TabActivity.class));
	}
	public void takePicture(){
		startActivity(new Intent(context, TakeImagesActivity.class));
	}
	
	public void saleOrder(){
		//Reset values and init it
		SaleManager_ProductActivity.mProductsMap.clear();
		SaleManager_ReviewActivity.takeOrderDetailList.clear();
		TakeOrder_ProductActivity.timeLine = true;
		TakeOrder_ProductActivity.add_take_order_detail = false;
		
		startActivity(new Intent(context, SaleManager_TabActivity.class));
	}
	
	public void inventoryManager(){
		//Reset values and init it
		InventoryManager_ProductActivity.mProductsMap.clear();
		InventoryManager_ReviewActivity.takeOrderDetailList.clear();
		TakeOrder_ProductActivity.timeLine = true;
		TakeOrder_ProductActivity.add_take_order_detail = false;
		
		startActivity(new Intent(context, InventoryManager_TabActivity.class));
		
		
	}
										       

}
