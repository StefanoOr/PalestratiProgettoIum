package com.example.palestratiium.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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





    public AdapterListaCreazioneAllenamento(List<EsercizioSchedaAllenamento> esercizioAllenameto, RecycleViewInterface recycleViewInterface, Context context) {
        this.esercizioAllenameto=esercizioAllenameto;
        this.recycleViewInterface = recycleViewInterface;
        this.context=context;
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


        ArrayAdapter<CharSequence> adapterRipetizione = ArrayAdapter.createFromResource(context,R.array.ripetizioni, android.R.layout.simple_dropdown_item_1line);
        adapterRipetizione.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.ripetizioni.setAdapter(adapterRipetizione);


        ArrayAdapter<CharSequence> adapterSerie = ArrayAdapter.createFromResource(context,R.array.serie, android.R.layout.simple_dropdown_item_1line);
        adapterRipetizione.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.serie.setAdapter(adapterSerie);


        List<Esercizio> listaEsercizi=UserFactory.getInstance().getAllEsercizi();



        AdapterSpinnerEsercizi adapterEsercizio = new AdapterSpinnerEsercizi(context, listaEsercizi);
      holder.esercizio.setAdapter(adapterEsercizio);

        //holder.test.setText(esercizioAllenameto.get(position).getNome());

        holder.binData(position);
    }

    @Override
    public int getItemCount() {
        return esercizioAllenameto.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public Button eliminaEsercizioAllenamento;
        Spinner ripetizioni,serie,esercizio;



        public ExampleViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);

            eliminaEsercizioAllenamento=itemView.findViewById(R.id.button_elimina);
            ripetizioni=itemView.findViewById(R.id.Ripetizioni);
            serie=itemView.findViewById(R.id.Serie);
            esercizio=itemView.findViewById(R.id.iconIDpT);


        }

        public void binData(final int position) {

            eliminaEsercizioAllenamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    esercizioAllenameto.remove(position);
                    notifyDataSetChanged();

                }
            });


        }
    }
}
