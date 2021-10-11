package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DestinosAdapterUsuarios extends RecyclerView.Adapter<DestinosAdapterUsuarios.ViewHolder> {
    List<Destinos> destinos;
    Context context;

    public DestinosAdapterUsuarios(Context context, List<Destinos> destinos) {
        this.context = context;
        this.destinos = destinos;
    }

    @NonNull
    @Override
    public DestinosAdapterUsuarios.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destinos_usuario, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.codigo.setText(destinos.get(position).getCodigo());
        holder.telefono.setText(destinos.get(position).getTelefono());
        holder.estado.setText(destinos.get(position).getEstado());
    }


    @Override
    public int getItemCount() {
        return destinos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView codigo, telefono, estado;

        public ViewHolder(@NonNull View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView);
            codigo = (TextView) view.findViewById(R.id.codigo);
            telefono = (TextView) view.findViewById(R.id.telefono);
            estado = (TextView) view.findViewById(R.id.estado);
        }
    }
}
