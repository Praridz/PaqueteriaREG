package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.List;

public class ListaPaquetesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Destinos> destinosList;
    FirebaseRecyclerAdapter<Destinos, DestinosAdapter.ViewHolder> adapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paquetes);
        //Titulo centrado de la app Action Bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo_nav);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //Activar boton back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.reciclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new FirebaseRecyclerAdapter<Destinos, DestinosAdapter.ViewHolder>(
                Destinos.class,
                R.layout.destinos,
                DestinosAdapter.ViewHolder.class,
                databaseReference.child("destinos")
        ) {
            @Override
            protected void populateViewHolder(DestinosAdapter.ViewHolder viewHolder, Destinos destinos, final int i) {
                viewHolder.codigo.setText(String.valueOf(destinos.getCodigo()));
                viewHolder.telefono.setText(String.valueOf(destinos.getTelefono()));
                viewHolder.cambiarEstado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        destinos.setEstado("En camino");
                        adapter.getRef(i).setValue(destinos);
                        StyleableToast.makeText(getApplicationContext(), "Estado de la entrega cambiado a: en camino",
                                Toast.LENGTH_LONG, R.style.DemoButton).show();
                    }
                });
                viewHolder.eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.getRef(i).removeValue();
                        StyleableToast.makeText(getApplicationContext(), "La entrega ha sido borrada exitosamente",
                                Toast.LENGTH_LONG, R.style.DemoButton).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}