package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muddzdev.styleabletoast.StyleableToast;
import com.skydoves.elasticviews.ElasticCheckButton;

public class PaqueteActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ElasticCheckButton consultarLatLong, btnGuardar;
    EditText edtLat, edtLong, edtcodigo, edtbillete, edttelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paquete);
        //Titulo centrado de la app Action Bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo_nav);
        //Activar boton back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        consultarLatLong = (ElasticCheckButton) findViewById(R.id.botton);
        btnGuardar = (ElasticCheckButton) findViewById(R.id.btnEnviar);
        edtLat = findViewById(R.id.txtLat);
        edtLong = findViewById(R.id.txtLog);
        edtcodigo = findViewById(R.id.txtNo);
        edttelefono = findViewById(R.id.txtTel);
        getLocalizacion();
        getCargarLocalizacion();
        guardarDatos();
    }

    private void guardarDatos() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitud = edtLat.getText().toString().trim();
                String logitud = edtLong.getText().toString().trim();
                String codigo = edtcodigo.getText().toString().trim();
                String telefono = edttelefono.getText().toString().trim();

                if(TextUtils.isEmpty(latitud)){
                    StyleableToast.makeText(getApplicationContext(), "Por favor Pulsar el Boton Generar Ubicacion",
                            Toast.LENGTH_LONG, R.style.DemoButton).show();
                }else if(TextUtils.isEmpty(logitud)){
                    StyleableToast.makeText(getApplicationContext(), "Por favor Pulsar el Boton Generar Ubicacion",
                            Toast.LENGTH_LONG, R.style.DemoButton).show();
                }else if(TextUtils.isEmpty(codigo)){
                    StyleableToast.makeText(getApplicationContext(), "Por favor Ingresar un Codigo",
                            Toast.LENGTH_LONG, R.style.DemoButton).show();
                }else if(TextUtils.isEmpty(telefono)){
                    StyleableToast.makeText(getApplicationContext(), "Por favor Ingresar un numero de Telefono",
                            Toast.LENGTH_LONG, R.style.DemoButton).show();
                }else{
                    Destinos destinos = new Destinos(Double.valueOf(latitud),Double.valueOf(logitud), codigo, telefono);

                    databaseReference.child("destinos").child(codigo).setValue(destinos);
                    StyleableToast.makeText(getApplicationContext(), "Datos Enviados Correctamente",
                            Toast.LENGTH_LONG, R.style.DemoButton).show();
                    Intent i = new Intent(PaqueteActivity.this, MenuActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }

    private void getCargarLocalizacion() {
        consultarLatLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) PaqueteActivity.this.getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        edtLat.setText(""+location.getLatitude());
                        edtLong.setText(""+location.getLongitude());
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };
                int permiso = ContextCompat.checkSelfPermission(PaqueteActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                StyleableToast.makeText(getApplicationContext(), "Ubicacion generada Con exito",
                        Toast.LENGTH_LONG, R.style.DemoButton).show();
            }
        });
    }


    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}