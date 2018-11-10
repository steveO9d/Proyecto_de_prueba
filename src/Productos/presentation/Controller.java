/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Productos.presentation;

import Productos.data.GlobalException;
import Productos.data.NoDataException;
import Productos.logic.Producto;
import Productos.logic.Tipo;
import java.util.List;

/**
 *
 * @author Escinf
 */
public class Controller {

    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.setController(this);
    }

    public void listarProductos() throws GlobalException, NoDataException {
        model.listarProductos();
    }

    public void insertarProducto(Producto nuevoProd) throws GlobalException, NoDataException {
        model.insertarProducto(nuevoProd);
        model.listarProductos();
    }

    public List<Tipo> listarTipos() throws GlobalException, NoDataException {
        return model.listarTipos();
    }

    public void listarProductoPorNombre(String nombre) throws GlobalException, NoDataException {
        model.listarProductoPorNombre(nombre);
    }
    
    public void listarProductoPorTipo(String nombre) throws GlobalException, NoDataException {
        model.listarPorTipo(nombre);
    }

}
