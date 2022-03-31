package com.example.palestratiium.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.palestratiium.R;
import com.example.palestratiium.UserActivity.ActivitySchedaAllenamentoUser;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.SchedeAllenamento;

import java.util.List;


    public class AdapterListaAllenamento extends  RecyclerView.Adapter<AdapterListaAllenamento.ExampleViewHolder>{
        private List<SchedeAllenamento> mExampleList;
        private  final RecycleViewInterface recycleViewInterface;


        public AdapterListaAllenamento(List <SchedeAllenamento> exampleList,RecycleViewInterface recycleViewInterface) {
            this.mExampleList = exampleList;
            this.recycleViewInterface=recycleViewInterface;

        }



        @NonNull
        @Override
        public AdapterListaAllenamento.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_allenamento, parent, false);
            AdapterListaAllenamento.ExampleViewHolder evh = new AdapterListaAllenamento.ExampleViewHolder(v,recycleViewInterface);
            return evh;
        }


        @Override
        public void onBindViewHolder(@NonNull AdapterListaAllenamento.ExampleViewHolder holder, int position) {


                //TODO aggiungere icona
                //holder.icona.setImageResource(mExampleList.get(position).getImageDefault());

            holder.nSchedaAllenamento.setText((mExampleList.get(position).getNomeScheda()));

            holder.bindData(position);
        }

        @Override
        public int getItemCount() {
            return mExampleList.size();
        }

        public static class ExampleViewHolder extends RecyclerView.ViewHolder {

            public TextView nSchedaAllenamento;
            public ImageView icona;
            public ExampleViewHolder(@NonNull View itemView, final RecycleViewInterface recycleViewInterface) {
                super(itemView);


                icona=itemView.findViewById(R.id.imgMiniaturaCarduser);
                nSchedaAllenamento= itemView.findViewById(R.id.nomeEsercizioInSchedaAllenamento);


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


            }
        }
    }
