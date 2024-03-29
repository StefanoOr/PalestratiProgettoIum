package com.example.palestratiium.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.palestratiium.R;
import com.example.palestratiium.classi.EsercizioSchedaAllenamento;

import java.util.List;

public class AdapterListaEserciziAllenamento extends RecyclerView.Adapter<AdapterListaEserciziAllenamento.ExampleViewHolder> {

    List<EsercizioSchedaAllenamento> mExampleList;
    private  final RecycleViewInterface recycleViewInterface;


    public AdapterListaEserciziAllenamento(List<EsercizioSchedaAllenamento> esercizi, RecycleViewInterface recycleViewInterface) {
        this.recycleViewInterface = recycleViewInterface;
        this.mExampleList=esercizi;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_scheda_allenamento_user, parent, false);
        AdapterListaEserciziAllenamento.ExampleViewHolder evh = new AdapterListaEserciziAllenamento.ExampleViewHolder(v,recycleViewInterface);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {


        if(mExampleList.get(position).getEsercizio().getImage()!=null){
            Uri myuri= Uri.parse(mExampleList.get(position).getEsercizio().getImage());
            holder.icona.setImageURI(myuri);

        }else {
            holder.icona.setImageResource(mExampleList.get(position).getEsercizio().getImageDefault());

        }

        holder.nEsercizio.setText((mExampleList.get(position).getNome()));
        holder.nRipetizione.setText(String.valueOf(mExampleList.get(position).getRipetizioni()));

        holder.nSerie.setText(String.valueOf(mExampleList.get(position).getSerie()));
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView nEsercizio,nSerie,nRipetizione;
        public ImageView icona;

        public ExampleViewHolder(@NonNull View itemView, final RecycleViewInterface recycleViewInterface) {
            super(itemView);

            nRipetizione=itemView.findViewById(R.id.textViewRipetizioni);
            nSerie=itemView.findViewById(R.id.textViewSerie);
            nEsercizio=itemView.findViewById(R.id.textViewNesercizio);
            icona=itemView.findViewById(R.id.card_scheda_immagine);

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

        public void bindData(int position) {
        }
    }
}