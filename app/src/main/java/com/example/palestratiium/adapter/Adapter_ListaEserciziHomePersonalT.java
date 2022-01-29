package com.example.palestratiium.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.palestratiium.R;
import com.example.palestratiium.classi.Esercizio;

import java.util.List;

public class Adapter_ListaEserciziHomePersonalT extends  RecyclerView.Adapter<Adapter_ListaEserciziHomePersonalT.ExampleViewHolder>{
    private List<Esercizio> mExampleList;
    private  final RecycleViewInterface recycleViewInterface;


    public Adapter_ListaEserciziHomePersonalT(List <Esercizio> exampleList,RecycleViewInterface recycleViewInterface) {
        this.mExampleList = exampleList;
        this.recycleViewInterface=recycleViewInterface;
    }



    @NonNull
    @Override
    public Adapter_ListaEserciziHomePersonalT.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video, parent, false);
        Adapter_ListaEserciziHomePersonalT.ExampleViewHolder evh = new Adapter_ListaEserciziHomePersonalT.ExampleViewHolder(v,recycleViewInterface);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ListaEserciziHomePersonalT.ExampleViewHolder holder, int position) {
        Esercizio currentItem = mExampleList.get(position);


        holder.nEsercizio.setText((mExampleList.get(position).getNome()));
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView nEsercizio;
        public ExampleViewHolder(@NonNull View itemView, final RecycleViewInterface recycleViewInterface) {
            super(itemView);

            nEsercizio= itemView.findViewById(R.id.nomeEsercizio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recycleViewInterface != null){
                        int pos=getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recycleViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }

        void bindData(int position){

            nEsercizio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}


