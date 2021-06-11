package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DestinosAdapter extends RecyclerView.Adapter<DestinosAdapter.ViewHolder> {
    List<Destinos> destinos;
    Context context;
    public DestinosAdapter(Context context, List<Destinos> destinos){
        this.context = context;
        this.destinos = destinos;
    }

    @NonNull
    @Override
    public DestinosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destinos, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DestinosAdapter.ViewHolder holder, int position) {
        holder.codigo.setText(destinos.get(position).getCodigo());
        holder.telefono.setText(destinos.get(position).getTelefono());

    }

    @Override
    public int getItemCount() {
        return destinos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView codigo, telefono;
        ImageView eliminar;

        public ViewHolder(@NonNull View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView);
            codigo = (TextView) view.findViewById(R.id.codigo);
            telefono = (TextView) view.findViewById(R.id.telefono);
            eliminar = (ImageView) view.findViewById(R.id.eliminar);
        }
    }
}
