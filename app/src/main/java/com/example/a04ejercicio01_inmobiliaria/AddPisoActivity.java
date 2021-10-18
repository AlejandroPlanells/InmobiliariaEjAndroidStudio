package com.example.a04ejercicio01_inmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a04ejercicio01_inmobiliaria.databinding.ActivityAddPisoBinding;
import com.example.a04ejercicio01_inmobiliaria.modelos.Piso;

public class AddPisoActivity extends AppCompatActivity {

    private ActivityAddPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPisoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearAddPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String calle = binding.txtCalleAddPiso.getText().toString();
                String numero = binding.txtNumeroAddPiso.getText().toString();
                String ciudad = binding.txtCiudadAddPiso.getText().toString();
                String cp = binding.txtCPAddPiso.getText().toString();
                String provincia = binding.txtProvinciaAddPiso.getText().toString();
                if (!calle.isEmpty() && !numero.isEmpty() && !ciudad.isEmpty() && !cp.isEmpty() && !provincia.isEmpty()){
                    Piso pisito = new Piso(calle, Integer.parseInt(numero), provincia, cp, ciudad, binding.rtBValoracionAddPiso.getRating());
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("PISO", pisito);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}