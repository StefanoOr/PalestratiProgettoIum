package com.example.palestratiium.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.palestratiium.classi.Esercizio;

import com.example.palestratiium.R;

import java.util.List;




public class Adapter_ListaEserciziHome extends  RecyclerView.Adapter<Adapter_ListaEserciziHome.ExampleViewHolder>{
    private List<Esercizio> mExampleList;
    private  final RecycleViewInterface recycleViewInterface;


    public Adapter_ListaEserciziHome(List <Esercizio> exampleList,RecycleViewInterface recycleViewInterface) {
        this.mExampleList = exampleList;
        this.recycleViewInterface=recycleViewInterface;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_eserciziohomeuser, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v,recycleViewInterface);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {


        if(mExampleList.get(position).getImage()!=null){
            Uri myuri= Uri.parse(mExampleList.get(position).getImage());
            holder.icona.setImageURI(myuri);
        } else {
        holder.icona.setImageResource(mExampleList.get(position).getImageDefault());
    }

       holder.nEsercizio.setText((mExampleList.get(position).getNome()));

        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView nEsercizio;
        public ImageView icona;
        public ExampleViewHolder(@NonNull View itemView, final RecycleViewInterface recycleViewInterface) {
            super(itemView);


            icona=itemView.findViewById(R.id.imgMiniaturaCarduser);
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
