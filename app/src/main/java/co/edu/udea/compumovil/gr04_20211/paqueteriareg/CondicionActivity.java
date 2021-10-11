package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CondicionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condicion);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo_nav);
        //Activar boton back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final TextView textView = findViewById(R.id.titulo);
        textView.setText("POLÍTICA DE PRIVACIDAD APLICACIÓN PAQUETERÍA REG");
        final TextView textView1 = findViewById(R.id.parrafoUno);
        textView1.setText("La presente política de privacidad establece los términos y condiciones de la aplicación, no recopila información del usuario sin hacer uso de su " +
                "conocimiento. Para repartir los paquetes es necesario obtener la ubicación del teléfono y el numero telefónicos los cuales el usuario final si el desea brindar " +
                "los datos los agregara. Nuestra empresa esta comprometida con la seguridad de los usuarios. Cuando le pedimos llenar el formulario sobre un paquete en el" +
                "cual usted se siente identificado, lo hacemos asegurando que solo se empleara de acuerdo a los términos y condiciones de este documento. Sin embargo," +
                "esta política de privacidad puede cambiar con el tiempo o ser actualizada por lo que le recomendamosy enfatizamos revisar continuamente esta información " +
                "para asegurarse que está de acuerdo con los cambios.\n");
        final TextView textView2 = findViewById(R.id.tituloDos);
        textView2.setText("INFORMACIÓN QUE SE RECOLECTA");
        final TextView textView3 = findViewById(R.id.parrafoDos);
        textView3.setText("Nuestra aplicación Paquetería REG, solo recolecta información temporal, la cual el usuario ingresa llenando el formulario de recibir paquete, donde se le pide " +
                "enviar su ubicación, numero de paquete, teléfono, esta información se guarda de manera temporal, debido a que, una vez entregado el paquete, se elimina.\n");
        final TextView textView4 = findViewById(R.id.tituloTres);
        textView4.setText("USO DE LA INFORMACIÓN QUE SE RECOLECTA");
        final TextView textView5 = findViewById(R.id.parrafoTres);
        textView5.setText("El uso que se le da es de manera temporal, cuando el paquete aun no se ha entregado,es necesaria tener la información de ubicación y teléfono para que el " +
                "repartidor pueda guiarse y llevar el paquete, una vez entregadola información es eliminada debido a que es irrelevante, y solo ocupa espacio en nuestro " +
                "servidor de firebase.");
        final TextView textView6 = findViewById(R.id.tituloCuatro);
        textView6.setText("ENLACES A TERCEROS");
        final TextView textView7 = findViewById(R.id.parrafoCuatro);
        textView7.setText("Nuestra aplicación no utiliza enlaces a terceros.");
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}