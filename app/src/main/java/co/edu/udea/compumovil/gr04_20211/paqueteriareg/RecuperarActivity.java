package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.muddzdev.styleabletoast.StyleableToast;
import com.skydoves.elasticviews.ElasticCheckButton;

public class RecuperarActivity extends AppCompatActivity {
    private TextInputEditText email;
    private ElasticCheckButton recuperar;
    private ProgressDialog progress;
    private String correo;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo_nav);
        //Activar boton back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        email = findViewById(R.id.gmail);
        recuperar = findViewById(R.id.btnRecuperar);

        auth = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);
        getRecuperar();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getRecuperar() {
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = email.getText().toString().trim();
                if(!correo.isEmpty()){
                    progress.setMessage("Espera un momento..");
                    progress.setCanceledOnTouchOutside(false);
                    progress.show();
                    getEnviarCorreo();
                }else
                {
                    StyleableToast.makeText(getApplicationContext(), "Por favor, ingrese el correo",
                            Toast.LENGTH_LONG, R.style.DemoButton).show();
                }
            }
        });
    }
    private  void getEnviarCorreo(){
        auth.setLanguageCode("es");
        auth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    StyleableToast.makeText(getApplicationContext(), "Por favor revise su correo para restaurar contraseña",
                            Toast.LENGTH_LONG, R.style.DemoButton).show();
                    Intent i = new Intent(RecuperarActivity.this, LoginClientsActivity.class);
                    startActivity(i);
                    finish();
                }else
                {
                    StyleableToast.makeText(getApplicationContext(), "El correo no se pudo Enviar",
                            Toast.LENGTH_LONG, R.style.DemoButton).show();
                }
            }
        });

    }
}