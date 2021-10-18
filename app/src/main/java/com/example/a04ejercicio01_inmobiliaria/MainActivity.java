package com.example.a04ejercicio01_inmobiliaria;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.a04ejercicio01_inmobiliaria.databinding.ActivityMainBinding;
import com.example.a04ejercicio01_inmobiliaria.modelos.Piso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<Piso> pisitos;
    private ActivityResultLauncher<Intent> addPisoLauncher;
    private ActivityResultLauncher<Intent> editPisoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        pisitos = new ArrayList<>();
        addPisoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            Piso pisito = result.getData().getExtras().getParcelable("PISO");
                            pisitos.add(pisito);
                            mostrarElementos();
                        }
                    }
                });

        editPisoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData().getExtras().getParcelable("PISO") == null){
                                pisitos.remove(result.getData().getExtras().getInt("POS"));
                            }else{
                                pisitos.set(result.getData().getExtras().getInt("POS"),
                                result.getData().getExtras().getParcelable("PISO"));
                            }
                            mostrarElementos();
                        }
                    }
                });

        mostrarElementos();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPisoLauncher.launch(new Intent(MainActivity.this, AddPisoActivity.class));
            }
        });
    }

    private void mostrarElementos() {
        //Eliminar toda la información
        binding.contentMain.contenedor.removeAllViews();

        //Añadir fila con información
        for (int i = 0; i < pisitos.size(); i++) {
            Piso pisito = pisitos.get(i);
            View fila = LayoutInflater.from(this).inflate(R.layout.piso_model, null);

            TextView lblCiudad = fila.findViewById(R.id.lblCiudadPisoModel);
            TextView lblNumero = fila.findViewById(R.id.lblNumeroPisoModel);
            TextView lblCalle = fila.findViewById(R.id.lblCallePisoModel);
            TextView lblProvincia = fila.findViewById(R.id.lblProvinciaPisoModel);
            TextView lblCP = fila.findViewById(R.id.lblCPPisoModel);
            RatingBar rtbVal = fila.findViewById(R.id.rtBValoracionPisoModel);

            lblCalle.setText(pisito.getCalle());
            lblCiudad.setText(pisito.getCiudad());
            lblCP.setText(pisito.getCp());
            lblProvincia.setText(pisito.getProvincia());
            lblNumero.setText(String.valueOf(pisito.getNumero()));
            rtbVal.setRating(pisito.getValoracion());

            binding.contentMain.contenedor.addView(fila);

            int finalI = i;
            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, EditPisoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("PISO", pisito);
                    bundle.putInt("POS", finalI);
                    intent.putExtras(bundle);
                    editPisoLauncher.launch(intent);
                }
            });

        }
    }
}