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
public class Producto {

    public Producto(String codigo, String nombre, boolean importado,
            double precio, double porcentaje, double impuesto, double precioFinal, Tipo tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.importado = importado;
        this.precio = precio;
        this.tipo = tipo;
        this.porcentaje = porcentaje;
        this.impuesto = impuesto;
        this.precioFinal = precioFinal;
    }

    public Producto() {
    }

    public String getCodigo() {
        return codigo;
    }

    public double getImpuesto() {
        return impuesto = (porcentaje / 100) * precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPorcentaje() {
        switch (tipo.getNombre_tipo()) {
            case "Can. Básica":
                if (!isImportado()) {
                    porcentaje = 5;
                } else {
                    porcentaje = 5;
                    porcentaje = (porcentaje / 2) + porcentaje;
                }
                break;
            case "Popular":
                if (!isImportado()) {
                    porcentaje = 10;
                } else {
                    porcentaje = 10;
                    porcentaje = (porcentaje / 2) + porcentaje;
                }
                break;
            case "Suntuario":
                if (!isImportado()) {
                    porcentaje = 15;
                } else {
                    porcentaje = 15;
                    porcentaje = (porcentaje / 2) + porcentaje;
                }
                break;
            default:
                porcentaje = 0;
        }
        return porcentaje;
    }

    public double getPrecio() {
        return precio;
    }

    public double getPrecioFinal() {
        precioFinal = getImpuesto() + precio;
        return precioFinal;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public boolean isImportado() {
        return importado;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    String codigo;
    String nombre;
    boolean importado;
    double precio;
    Tipo tipo;
    double porcentaje;
    double impuesto;
    double precioFinal;

}
