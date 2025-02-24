package com.example.consumingrest;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tienda {
    @JsonProperty("id")  // Cambié 'ID_Tienda' a 'id'
    private Integer id;  // Cambié 'ID_Tienda' a 'id'

    @JsonProperty("nombreTienda")
    private String nombreTienda;

    private String ubicacion;
    private String URL;

    // Getters y setters
    public Integer getId() {  // Cambié 'getID_Tienda' a 'getId'
        return id;
    }

    public void setId(Integer id) {  // Cambié 'setID_Tienda' a 'setId'
        this.id = id;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    // Método equals para comparar objetos Tienda
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tienda tienda = (Tienda) o;
        return Objects.equals(id, tienda.id);  // Cambié 'ID_Tienda' a 'id'
    }

    // Método hashCode para generar un hash único
    @Override
    public int hashCode() {
        return Objects.hash(id);  // Cambié 'ID_Tienda' a 'id'
    }

    // Método toString para representar el objeto Tienda como una cadena
    @Override
    public String toString() {
        return "Tienda{" +
                "id=" + id +  // Cambié 'idTienda' a 'id'
                ", nombreTienda='" + nombreTienda + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", url='" + URL + '\'' +
                '}';
    }
}
