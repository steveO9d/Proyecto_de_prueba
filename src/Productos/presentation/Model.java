/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Productos.presentation;

import Productos.data.DaoProducto;
import Productos.data.GlobalException;
import Productos.data.NoDataException;
import Productos.logic.Producto;
import Productos.logic.Tipo;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.TableModel;

/**
 *
 * @author Escinf
 */
public class Model extends Observable {

    public Model() {
        this.tableProductos = new ProductosTableModel(cols, new ArrayList<>());
        daoProducto = new DaoProducto();
    }

    public Producto getFilter() {
        return filter;
    }

    public TableModel getTableProductoas() {
        return tableProductos;
    }

    public Producto getRowAt(int row) {
        return tableProductos.getRowAt(row);
    }

    public void setFilter(Producto filter) {
        this.filter = filter;
    }

    public void setTableProductos(List<Producto> personas) {
        this.tableProductos = new ProductosTableModel(cols, personas);
        actualizar();
    }

    public void insertarProducto(Producto nuevoProd) throws GlobalException, NoDataException /*throws GlobalException, NoDataException*/ {
        daoProducto.insertarProducto(nuevoProd);
        actualizar();
    }

    public void listarProductos() throws GlobalException, NoDataException {
        List<Producto> rows = new ArrayList<>();
        rows = daoProducto.listarProducto();
        setTableProductos(rows);
    }

    public List<Tipo> listarTipos() throws GlobalException, NoDataException {
        List<Tipo> rows = daoProducto.listarTipos();
        return rows;
    }

    public void listarProductoPorNombre(String nombre) throws GlobalException, NoDataException {
        List<Producto> rows = daoProducto.buscarProductoPorNombre(nombre);
        setTableProductos(rows);
    }

    public void listarPorTipo(String nombre) throws GlobalException, NoDataException {
        List<Producto> rows = daoProducto.listarPorTipo(nombre);
        setTableProductos(rows);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o); //To change body of generated methods, choose Tools | Templates.
        actualizar();
    }

    private void actualizar() {
        setChanged();
        notifyObservers();
    }

    DaoProducto daoProducto;

    Producto filter = new Producto();
    ProductosTableModel tableProductos;
    int[] cols = {/*ProductosTableModel.CODIGO,*/ProductosTableModel.NOMBRE,
        ProductosTableModel.IMPORTADO, ProductosTableModel.PRECIO, ProductosTableModel.TIPO,
        ProductosTableModel.PORCENTAJE, ProductosTableModel.IMPUESTO, ProductosTableModel.PRECIO_FINAL};
}
