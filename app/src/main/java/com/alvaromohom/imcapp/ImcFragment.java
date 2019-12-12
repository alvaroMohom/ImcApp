package com.alvaromohom.imcapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImcFragment extends Fragment {

    public static final String KEY_IMC = "com.alvaromohom.ImcApp.MainActivity.imc";
    private int imc;
    private EditText mCampoPeso, mCampoEstatura;
    private Button mBotonCalcular, mBotonLimpiar;
    private TextView mEtiquetaImc, mSituacionNutricional;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_imc,container,false);

        if (savedInstanceState == null){
            imc = 0;
        }
        else {
            imc = savedInstanceState.getInt(KEY_IMC);
        }

        mCampoPeso = (EditText) v.findViewById(R.id.campo_peso);
        mCampoEstatura = (EditText) v.findViewById(R.id.campo_estatura);
        mBotonCalcular = (Button) v.findViewById(R.id.boton_calcular);
        mBotonLimpiar = (Button) v.findViewById(R.id.boton_limpiar);
        mSituacionNutricional = (TextView) v.findViewById(R.id.etiqueta_información);
        mEtiquetaImc = (TextView) v.findViewById(R.id.etiqueta_imc);
        mBotonCalcular.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s = mCampoPeso.getText().toString();
                double peso = Double.parseDouble(s);
                s = mCampoEstatura.getText().toString();
                double estatura = Double.parseDouble(s);
                double imc = peso / (estatura * estatura);
                s = String.format("%2.2f", imc);
                mEtiquetaImc.setText(s);

                if( imc < 18.5) {
                    mSituacionNutricional.setText(R.string.texto_información_peso_bajo);
                } else  if(imc < 25.0){
                    mSituacionNutricional.setText(R.string.texto_información_peso_normal);
                }else  if(imc < 30.0) {
                    mSituacionNutricional.setText(R.string.texto_información_sobrepeso);
                } else if(imc < 40.0) {
                    mSituacionNutricional.setText(R.string.texto_información_obesidad);
                } else {
                    mSituacionNutricional.setText(R.string.texto_información_obesidad_extrema);
                }
            }
        });

        mBotonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCampoPeso.setText("");
                mCampoEstatura.setText("");
                mEtiquetaImc.setText("0.0");
                mSituacionNutricional.setText("");
            }
        });

        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_IMC, imc);
    }
}