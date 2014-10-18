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

package com.hp.set.lunch;

import com.hp.map.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A widget that describes an activity that demonstrates a feature.
 */
public final class FeatureView extends FrameLayout {

    /**
     * Constructs a feature view by inflating layout/feature.xml.
     */
    public FeatureView(Context context) {
        super(context);

        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.feature_view_calendar, this);
    }

    /**
     * Set the resource id of the title of the demo.
     *
     * @param titleId the resource id of the title of the demo
     */
    public synchronized void setTitleId(String titleId) {
        ((TextView) (findViewById(R.id.title))).setText(titleId);
    }

    /**
     * Set the resource id of the description of the demo.
     *
     * @param descriptionId the resource id of the description of the demo
     */
    public synchronized void setProvince(String province) {
        ((TextView) (findViewById(R.id.province))).setVisibility(View.GONE);
    }
    
    public synchronized void setDescription(String description) {
        ((TextView) (findViewById(R.id.description))).setText(description);
    }
    
    public synchronized void setStatus(int status) {
    	((ImageView) (findViewById(R.id.status))).setVisibility(View.GONE);
    	//Init
//    	if(status == 0)
//    		((ImageView) (findViewById(R.id.status))).setImageResource(R.drawable.exclamation);
//    	
//    	//Request
//    	if(status == 1)
//    		((ImageView) (findViewById(R.id.status))).setImageResource(R.drawable.request);
//    	
//    	//Approve
//    	if(status == 2)
//    		((ImageView) (findViewById(R.id.status))).setImageResource(R.drawable.completed);
    }
    
    public synchronized void setStaff(String value) {
        ((TextView) (findViewById(R.id.staff))).setText(value);
    }

}
