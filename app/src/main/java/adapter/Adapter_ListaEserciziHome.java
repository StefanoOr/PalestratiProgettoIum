package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.palestratiium.Esercizio;
import com.example.palestratiium.R;

import java.util.List;




public class Adapter_ListaEserciziHome extends  RecyclerView.Adapter<Adapter_ListaEserciziHome.ExampleViewHolder>{
    private List<Esercizio> mExampleList;





    public Adapter_ListaEserciziHome(List <Esercizio> exampleList) {
        this.mExampleList = exampleList;
    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Esercizio currentItem = mExampleList.get(position);


        holder.nEsercizio.setText(currentItem.getNome());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView nEsercizio;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            nEsercizio= itemView.findViewById(R.id.nomeEsercizio);
        }
    }
}
