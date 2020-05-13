package com.example.privapp;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class Adaptador extends ArrayAdapter<Modelo> {
    private List<Modelo> mList;
    private Context mContext;
    private int resourceLayout;

    public Adaptador(@NonNull Context context, int resource, @NonNull List<Modelo> objects) {
        super(context, resource, objects);
        this.mList = objects;
        this.mContext = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.list_custom, null);
        }

        Modelo modelo = mList.get(position);

        TextView name = view.findViewById(R.id.name);
        name.setText(modelo.getName());

        ImageView image = view.findViewById(R.id.image);
        image.setImageDrawable(modelo.getImage());

        return view;
    }
}
