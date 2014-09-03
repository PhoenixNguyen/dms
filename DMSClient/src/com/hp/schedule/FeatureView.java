package com.hp.schedule;

import com.hp.map.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
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
        layoutInflater.inflate(R.layout.schedule_view, this);
    }

    
    public synchronized void setCustomerID(String id) {
        ((TextView) (findViewById(R.id.ma_kh))).setText(id);
    }

    public synchronized void setTime(String time) {
        ((TextView) (findViewById(R.id.thoi_gian))).setText(time);
    }
    
    public synchronized void setCustomerName(String name) {
        ((TextView) (findViewById(R.id.ten_kh))).setText(name);
    }

    public synchronized void setStaffID(String staff) {
        ((TextView) (findViewById(R.id.nhan_vien))).setText(staff);
    }

}
