package com.example.a04ejercicio01_inmobiliaria.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Piso implements Parcelable {
    private String calle;
    private int numero;
    private String provincia;
    private String cp;
    private String ciudad;
    private float valoracion;

    public Piso(String calle, int numero, String provincia, String cp, String ciudad, float valoracion) {
        this.calle = calle;
        this.numero = numero;
        this.provincia = provincia;
        this.cp = cp;
        this.ciudad = ciudad;
        this.valoracion = valoracion;
    }

    protected Piso(Parcel in) {
        calle = in.readString();
        numero = in.readInt();
        provincia = in.readString();
        cp = in.readString();
        ciudad = in.readString();
        valoracion = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(calle);
        dest.writeInt(numero);
        dest.writeString(provincia);
        dest.writeString(cp);
        dest.writeString(ciudad);
        dest.writeFloat(valoracion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Piso> CREATOR = new Creator<Piso>() {
        @Override
        public Piso createFromParcel(Parcel in) {
            return new Piso(in);
        }

        @Override
        public Piso[] newArray(int size) {
            return new Piso[size];
        }
    };

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }
}
