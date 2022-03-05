package com.example.palestratiium.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.palestratiium.R;
import com.example.palestratiium.classi.MyEnum;

import java.util.ArrayList;

public class GruppoMuscolareAdapter extends ArrayAdapter<MyEnum>{




        public GruppoMuscolareAdapter(Context context, ArrayList<MyEnum> countryList){
            super(context,0,countryList);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        private View initView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.item_lista_coach, parent, false
                );
            }

            TextView textViewName = convertView.findViewById(R.id.text_view_name);

            MyEnum currentItem = getItem(position);

            if (currentItem != null) {

                textViewName.setText(currentItem.toString());
            }

            return convertView;
        }
    }


