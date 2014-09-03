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

package com.hp.order_manager;

import com.hp.map.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * A widget that describes an activity that demonstrates a feature.
 */
public final class FeatureViewDetail extends FrameLayout {

    /**
     * Constructs a feature view by inflating layout/feature.xml.
     */
    public FeatureViewDetail(Context context) {
        super(context);

        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.feature_orders_manager_detail, this);
    }

    /**
     * Set the resource id of the title of the demo.
     *
     * @param titleId the resource id of the title of the demo
     */
    public synchronized void setName(String name) {
        ((TextView) (findViewById(R.id.name))).setText(name);
    }

    /**
     * Set the resource id of the description of the demo.
     *
     * @param descriptionId the resource id of the description of the demo
     */
    public synchronized void setId(String id) {
        ((TextView) (findViewById(R.id.id))).setText(id);
    }
    
    public synchronized void setNumber(String value) {
        ((TextView) (findViewById(R.id.number_value))).setText(value);
    }

    
    public synchronized void setValue(String value) {
        ((TextView) (findViewById(R.id.total_value))).setText(value);
    }
    
    public synchronized void setOriginalPrice(String value) {
        ((TextView) (findViewById(R.id.origin))).setText(value);
    }
    
    public synchronized void setDiscount(String value) {
        ((TextView) (findViewById(R.id.discount))).setText(value);
    }
    
    public synchronized void setPromoionalProduct(String value) {
        ((TextView) (findViewById(R.id.promotional_product_title))).setVisibility(View.VISIBLE);
        TextView promotional_product = (TextView) (findViewById(R.id.promotional_product));
        promotional_product.setVisibility(View.VISIBLE);
        promotional_product.setText(value);
    }
}
