package adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import Classi.Esercizio;

import com.example.palestratiium.Home;
import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.SignUp;

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
        holder.bindData(position);
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

        void bindData(int position){

            nEsercizio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 
                }
            });
        }
    }
}
