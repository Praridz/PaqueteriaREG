package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import dmax.dialog.SpotsDialog;

public class AdminActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    AlertDialog alerta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mAuth = FirebaseAuth.getInstance();
        //Activar boton back
        alerta = new SpotsDialog.Builder().setContext(AdminActivity.this).setMessage("Por favor espere....").build();
        Button logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginClientsActivity.class));
                finish();
            }
        });
    }

    public void getRegistrar(View view) {
        Intent i = new Intent(AdminActivity.this, RegistrarActivity.class);
        startActivity(i);
    }
}