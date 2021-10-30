package com.crud.entities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobraysol.mobashshir.raysol.R;

import java.util.List;

public class BookedhistoryAdapter extends ArrayAdapter<Servicesvm> {

    private Context context;
    private List<Servicesvm> servicesvms;

    public BookedhistoryAdapter(Context context, List<Servicesvm> servicesvms){
        super(context, R.layout.bookinghistory_layout, servicesvms);
        this.context = context;
        this.servicesvms = servicesvms;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.bookinghistory_layout, parent, false);
        Servicesvm servicesvm = servicesvms.get(position);
        TextView textViewbooked = (TextView) convertView.findViewById(R.id.rowbookinghistoryorder);
        TextView textViewdatetime = (TextView) convertView.findViewById(R.id.rowbookinghistorydatetime);
        TextView textViewstatus = (TextView) convertView.findViewById(R.id.rowbookinghistorystatus);

        String serviceorderdata = servicesvm.getServicesOrdered();
        String[] data = serviceorderdata.split(",");
        String servicetypebooking = "";
        servicetypebooking = data[0].split(":")[1];
        servicetypebooking = servicetypebooking + data[1].split(":")[1];

        String[] datetimedataarr = servicesvm.getServicesOrdered().split("DateTime");
        String datetimedata = datetimedataarr[1].split(",")[0];
        datetimedata = datetimedata.replace(":", "");



        textViewbooked.setText("Bookings type: " + servicetypebooking);
        textViewdatetime.setText("Date Of Booking: " + datetimedata);
        textViewstatus.setText("Status: " + servicesvm.getUsername());

        return convertView;
    }
}
