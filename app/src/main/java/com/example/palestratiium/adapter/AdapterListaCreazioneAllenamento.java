package com.example.palestratiium.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.palestratiium.R;
import com.example.palestratiium.UserActivity.Home;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.EsercizioSchedaAllenamento;
import com.example.palestratiium.classi.UserFactory;

import java.util.List;

public class AdapterListaCreazioneAllenamento extends RecyclerView.Adapter<AdapterListaCreazioneAllenamento.ExampleViewHolder> {
    private  final RecycleViewInterface recycleViewInterface;
    private List<EsercizioSchedaAllenamento> esercizioAllenameto;
    private  Context context;
private Dialog dialog;




    public AdapterListaCreazioneAllenamento(List<EsercizioSchedaAllenamento> esercizioAllenameto, RecycleViewInterface recycleViewInterface, Context context) {
        this.esercizioAllenameto=esercizioAllenameto;
        this.recycleViewInterface = recycleViewInterface;
        this.context=context;
        dialog=new Dialog(context);
    }

    @NonNull
    @Override
    public AdapterListaCreazioneAllenamento.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_add_allenamento, parent, false);
        AdapterListaCreazioneAllenamento.ExampleViewHolder evh = new AdapterListaCreazioneAllenamento.ExampleViewHolder(v,recycleViewInterface);
        return  evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {





        ArrayAdapter<CharSequence> adapterRipetizione = ArrayAdapter.createFromResource(context,R.array.ripetizioni, R.layout.colored_spinner);
        adapterRipetizione.setDropDownViewResource(R.layout.colored_spinner);

        holder.ripetizioni.setAdapter(adapterRipetizione);


        ArrayAdapter<CharSequence> adapterSerie = ArrayAdapter.createFromResource(context,R.array.serie, R.layout.colored_spinner);
        adapterRipetizione.setDropDownViewResource(R.layout.colored_spinner);

        holder.serie.setAdapter(adapterSerie);


        List<Esercizio> listaEsercizi=UserFactory.getInstance().getAllEsercizi();



        AdapterSpinnerEsercizi adapterEsercizio = new AdapterSpinnerEsercizi(context, listaEsercizi);
       holder.esercizio.setAdapter(adapterEsercizio);

        holder.ripetizioni.setSelection(esercizioAllenameto.get(position).getRipetizioni());
        holder.serie.setSelection(esercizioAllenameto.get(position).getSerie());



        //holder.test.setText(esercizioAllenameto.get(position).getNome());

        holder.binData(position);
    }

    @Override
    public int getItemCount() {
        return esercizioAllenameto.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView eliminaEsercizioAllenamento;
        Spinner ripetizioni,serie,esercizio;




        public ExampleViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);


            ripetizioni=itemView.findViewById(R.id.Ripetizioni);
            serie=itemView.findViewById(R.id.Serie);
            esercizio=itemView.findViewById(R.id.iconIDpT);
            eliminaEsercizioAllenamento=itemView.findViewById(R.id.eliminaEsercizio);




        }

        public void binData(final int position) {

            eliminaEsercizioAllenamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(esercizioAllenameto.size()>1) {

                        dialog.show();

                        dialog.setContentView(R.layout.custom_dialog_elimina_esercizio_allenamento);

                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.custom_dialog));
                        }

                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.setCancelable(false);

                        TextView nome = dialog.findViewById(R.id.nome_esercizio);
                        TextView domanda= dialog.findViewById(R.id.textView);

                        domanda.setText("Vuoi eliminare dall'allenamento l'esercizio");

                        nome.setText(esercizioAllenameto.get(position).getNome());

                        Button delete = dialog.findViewById(R.id.btn_elimina_cura);
                        Button cancel = dialog.findViewById(R.id.btn_annulla_elimina_cura);

                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, "Esercizio eliminato con successo", Toast.LENGTH_SHORT).show();
                                esercizioAllenameto.remove(position);

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
                }
            });

            ripetizioni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int ripetizioni, long id) {
                    esercizioAllenameto.get(position).setRipetizioni(ripetizioni);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            serie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int serie, long id) {
                    esercizioAllenameto.get(position).setSerie(serie);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            esercizio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int esercizio, long id) {
                    esercizioAllenameto.get(position).setEsercizio(UserFactory.getInstance().getEsercizioByPosition(esercizio));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }
}
