package com.example.palestratiium.adapter;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.palestratiium.R;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.UserFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class Adapter_ListaEserciziHomePersonalT extends  RecyclerView.Adapter<Adapter_ListaEserciziHomePersonalT.ExampleViewHolder>{
    private List<Esercizio> mExampleList;
    private  final RecycleViewInterface recycleViewInterface;
    public Context context;
    public Dialog dialog;



    public Adapter_ListaEserciziHomePersonalT(List <Esercizio> exampleList, RecycleViewInterface recycleViewInterface, Context context) {
        this.mExampleList = exampleList;
        this.recycleViewInterface=recycleViewInterface;
        this.context=context;
        dialog= new Dialog(context);
    }



    @NonNull
    @Override
    public Adapter_ListaEserciziHomePersonalT.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video, parent, false);
        Adapter_ListaEserciziHomePersonalT.ExampleViewHolder evh = new Adapter_ListaEserciziHomePersonalT.ExampleViewHolder(v,recycleViewInterface);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        if(mExampleList.get(position).getImage()!=null){
            Uri myuri= Uri.parse(mExampleList.get(position).getImage());
            holder.icona.setImageURI(myuri);

        }else {
            holder.icona.setImageResource(mExampleList.get(position).getImageDefault());

        }
        holder.nEsercizio.setText((mExampleList.get(position).getNome()));
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView nEsercizio;
        public ImageView icona,delete;
        public Button mButtonModifica;


        public ExampleViewHolder(@NonNull View itemView, final RecycleViewInterface recycleViewInterface) {
            super(itemView);

            mButtonModifica = itemView.findViewById(R.id.edit_esercizio);
            nEsercizio= itemView.findViewById(R.id.nomeEsercizio);
            icona = itemView.findViewById(R.id.iconIDpT);
            delete=itemView.findViewById(R.id.delete_esercizio);



            mButtonModifica.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recycleViewInterface != null){
                        int pos=getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recycleViewInterface.OnItemClickModify(pos);
                        }
                    }
                }
            });

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

       public  void bindData(final int position){

           delete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   dialog.show();

                   dialog.setContentView(R.layout.custom_dialog_elimina_esercizio_allenamento);
                   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                       dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.custom_dialog));
                   }

                   dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                   dialog.setCancelable(false);

                   Button delete = dialog.findViewById(R.id.btn_elimina_cura);
                   Button cancel = dialog.findViewById(R.id.btn_annulla_elimina_cura);

                   delete.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           UserFactory.getInstance().removeEsercizio(mExampleList.get(position));

                           dialog.dismiss();
                           notifyDataSetChanged();
                       }
                   });


                   cancel.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Toast.makeText(context, "Eliminazione annullata", Toast.LENGTH_SHORT).show();
                           dialog.dismiss();
                       }
                   });

               }
           });





            nEsercizio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}


