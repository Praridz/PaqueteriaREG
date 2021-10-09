package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {
    CardView paquete, admin, ofertas, condicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);

        //Iniciar
        paquete = (CardView) findViewById(R.id.idcard1);
        admin = (CardView) findViewById(R.id.idcard2);
        ofertas = (CardView) findViewById(R.id.idcard3);
        condicion = (CardView) findViewById(R.id.idcard4);

        getPaquete();
        getAdmin();
        getOferta();
        getCondicion();
        Button logout = findViewById(R.id.btnLogout1);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginClientsActivity.class));
                finish();
            }
        });
    }

    private void getPaquete() {
        paquete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, PaqueteActivity.class);
                startActivity(i);
            }
        });
    }
    private void getAdmin() {
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, AdminActivity.class);
                startActivity(i);
            }
        });
    }
    private void getOferta() {
        ofertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, OfertaActivity.class);
                startActivity(i);
            }
        });
    }
    private void getCondicion() {
        condicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, CondicionActivity.class);
                startActivity(i);
            }
        });
    }


}