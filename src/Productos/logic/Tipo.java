/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Productos.logic;

/**
 *
 * @author steve
 */
public class Tipo {

    public Tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public Tipo() {
        nombre_tipo = "vacio";
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    @Override
    public String toString() {
        return nombre_tipo;
    }

    String nombre_tipo;
    
}
