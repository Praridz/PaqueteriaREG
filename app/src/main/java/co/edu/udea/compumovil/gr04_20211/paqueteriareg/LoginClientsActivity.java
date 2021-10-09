package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muddzdev.styleabletoast.StyleableToast;
import com.skydoves.elasticviews.ElasticCheckButton;

import dmax.dialog.SpotsDialog;

public class LoginClientsActivity extends AppCompatActivity {
    ElasticCheckButton btnEnviar,recuperar;
    TextInputEditText email, password;
    private FirebaseAuth mAuth;
    AlertDialog alerta;
    FirebaseFirestore store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_clients);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo_nav);
        mAuth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        //Activar boton back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recuperar = (ElasticCheckButton) findViewById(R.id.recuperar);
        btnEnviar = (ElasticCheckButton) findViewById(R.id.btnEnviar);
        email = findViewById(R.id.gmail);
        password = findViewById(R.id.password);

        alerta = new SpotsDialog.Builder().setContext(LoginClientsActivity.this).setMessage("Por favor espere....").build();
        loginAdmin();
    }
    private void loginAdmin() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userE = email.getText().toString().trim();
                String passE = password.getText().toString().trim();
                if(TextUtils.isEmpty(userE)){
                    StyleableToast.makeText(getApplicationContext(), "Ingrese un correo",
                            Toast.LENGTH_LONG, R.style.DemoButton).show();
                }else if(TextUtils.isEmpty(passE)){
                    StyleableToast.makeText(getApplicationContext(), "Ingrese una contraseña",
                            Toast.LENGTH_LONG, R.style.DemoButton).show();
                }else{
                    alerta.show();
                    mAuth.signInWithEmailAndPassword(userE, passE)
                            .addOnCompleteListener(LoginClientsActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        StyleableToast.makeText(getApplicationContext(), "Usuario o contraseña Incorrecta",
                                                Toast.LENGTH_LONG, R.style.DemoButton).show();
                                    }else{
                                        StyleableToast.makeText(getApplicationContext(), "Ingreso Exitoso!!",
                                                Toast.LENGTH_LONG, R.style.DemoButton).show();
                                        checkUserRole(task.getResult().getUser().getUid());
                                    }
                                    alerta.dismiss();
                                }
                            });
                    email.setText("");
                    password.setText("");
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void getRecuperar(View view) {
        Intent i = new Intent(LoginClientsActivity.this, RecuperarActivity.class);
        startActivity(i);
    }

    public void getRegistrar(View view) {
        Intent i = new Intent(LoginClientsActivity.this, RegisterClientsActivity.class);
        startActivity(i);
    }

    private void checkUserRole(String uid){
        DocumentReference df = store.collection("Users").document(uid);
        //extract data
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess: "+ documentSnapshot.getData());
                //identificando rol
                if(documentSnapshot.getString("isClient") != null){
                    //user is client
                    startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                    finish();
                }
                if(documentSnapshot.getString("isEmployee") != null){
                    //user is employee
                    startActivity(new Intent(getApplicationContext(),MenuAdminActivity.class));
                    finish();
                }
                if(documentSnapshot.getString("isAdmin") != null){
                    //user is employee
                    startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                    finish();
                }
            }
        });
    }


 // comprueba si ya esta logueado un usuario
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            checkUserRole(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
    }
}