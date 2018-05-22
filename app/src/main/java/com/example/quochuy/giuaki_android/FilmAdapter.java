package com.example.quochuy.giuaki_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Quoc Huy on 4/14/2018.
 */

public class FilmAdapter extends ArrayAdapter<FilmClass> {

    public FilmAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public FilmAdapter(Context context, int resource, List<FilmClass> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.layout_custom, null);
        }

        FilmClass p = getItem(position);

        if (p != null) {
            // Anh xa + Gan gia tri
//            TextView tt1 = (TextView) v.findViewById(R.id.id);

            ImageView imageView=v.findViewById(R.id.id_img_poster);
            TextView tvName=v.findViewById(R.id.id_tv_name);
            TextView tvPass=v.findViewById(R.id.id_tv_time);

            tvName.setText(p.getNAME().toString());
            tvPass.setText(p.getTIME()+"");
            Picasso.with(getContext())
                    .load(p.getURL_IMAGE())
                    .into(imageView);
        }

        return v;
    }

}