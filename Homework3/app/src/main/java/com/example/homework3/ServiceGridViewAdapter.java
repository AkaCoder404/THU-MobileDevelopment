package com.example.homework3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class ServiceGridViewAdapter extends ArrayAdapter<CourseModel> {

    public ServiceGridViewAdapter(@NonNull Context context, ArrayList<CourseModel> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.service_grid_card, parent, false);
        }


        CourseModel courseModel = getItem(position);
        TextView courseTV = listItemView.findViewById(R.id.service_card_text);
        ImageView courseIV = listItemView.findViewById(R.id.service_card_image);

        courseTV.setText(courseModel.getCourse_name());
        courseIV.setImageResource(courseModel.getImgId());
        return listItemView;
    }
}