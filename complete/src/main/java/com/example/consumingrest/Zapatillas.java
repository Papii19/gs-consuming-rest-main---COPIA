package com.example.consumingrest;

import java.math.BigDecimal;

public class Zapatillas {

    private String idZapatilla;
    private String nombreModelo;  // Corregido el nombre
    private BigDecimal precio;    // Corregido el tipo de dato
    private Integer talla;
    private Integer idTienda;      // Corregido el nombre

    // Constructor con todos los parámetros
    public Zapatillas(String idZapatilla, String nombreModelo, BigDecimal precio, Integer talla, Integer idTienda) {
        this.idZapatilla = idZapatilla;
        this.nombreModelo = nombreModelo;
        this.precio = precio;
        this.talla = talla;
        this.idTienda = idTienda;
    }

    // Getters y Setters
    public String getIdZapatilla() {
        return idZapatilla;
    }

    public void setIdZapatilla(String idZapatilla) {
        this.idZapatilla = idZapatilla;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getTalla() {
        return talla;
    }

    public void setTalla(Integer talla) {
        this.talla = talla;
    }

    public Integer getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }
}
