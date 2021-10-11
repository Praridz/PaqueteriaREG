package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ListaPaquetesUsuarioActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Destinos> destinosList;
    FirebaseRecyclerAdapter<Destinos, DestinosAdapterUsuarios.ViewHolder> adapter;
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

        adapter = new FirebaseRecyclerAdapter<Destinos, DestinosAdapterUsuarios.ViewHolder>(
                Destinos.class,
                R.layout.destinos_usuario,
                DestinosAdapterUsuarios.ViewHolder.class,
                databaseReference.child("destinos").orderByChild("correoUser").equalTo(FirebaseAuth.getInstance().getCurrentUser().getEmail())
        ) {
            @Override
            protected void populateViewHolder(DestinosAdapterUsuarios.ViewHolder viewHolder, Destinos destinos, final int i) {
                    viewHolder.codigo.setText(String.valueOf(destinos.getCodigo()));
                    viewHolder.telefono.setText(String.valueOf(destinos.getTelefono()));
                    viewHolder.estado.setText(destinos.getEstado());
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

