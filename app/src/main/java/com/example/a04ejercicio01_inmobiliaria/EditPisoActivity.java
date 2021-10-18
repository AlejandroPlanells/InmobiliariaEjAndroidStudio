package com.example.a04ejercicio01_inmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a04ejercicio01_inmobiliaria.databinding.ActivityEditPisoBinding;
import com.example.a04ejercicio01_inmobiliaria.modelos.Piso;

public class EditPisoActivity extends AppCompatActivity {

    private ActivityEditPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditPisoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mostrarPisoAntiguo();

        binding.btnBorrarEditPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();
                bundle.putParcelable("PISO", null);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        binding.btnCrearEditPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String calle = binding.txtCalleEditPiso.getText().toString();
                    String numero = binding.txtNumeroEditPiso.getText().toString();
                    String ciudad = binding.txtCiudadEditPiso.getText().toString();
                    String cp = binding.txtCPEditPiso.getText().toString();
                    String provincia = binding.txtProvinciaEditPiso.getText().toString();
                    if (!calle.isEmpty() && !numero.isEmpty() && !ciudad.isEmpty() && !cp.isEmpty() && !provincia.isEmpty()){
                        Piso pisito = new Piso(calle, Integer.parseInt(numero), provincia, cp, ciudad, binding.rtBValoracionEditPiso.getRating());
                        Intent intent = new Intent();
                        Bundle bundle = getIntent().getExtras();
                        bundle.putParcelable("PISO", pisito);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    }

            }
        });

    }

    private void mostrarPisoAntiguo() {
        Piso piso = getIntent().getExtras().getParcelable("PISO");

        binding.txtCalleEditPiso.setText(piso.getCalle());
        binding.txtCiudadEditPiso.setText(piso.getCiudad());
        binding.txtCPEditPiso.setText(piso.getCp());
        binding.txtProvinciaEditPiso.setText(piso.getProvincia());
        binding.txtNumeroEditPiso.setText(String.valueOf(piso.getNumero()));
        binding.rtBValoracionEditPiso.setRating(piso.getValoracion());
    }
}