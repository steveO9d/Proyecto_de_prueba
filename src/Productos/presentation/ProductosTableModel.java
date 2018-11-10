/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Productos.presentation;

import Productos.logic.Producto;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author steve
 */
public class ProductosTableModel extends AbstractTableModel implements TableModel {

    List<Producto> rows;
    int[] cols;

    public ProductosTableModel(int[] cols, List<Producto> rows) {
        this.cols = cols;
        this.rows = rows;
        initColNames();
    }

    public Producto getRowAt(int row) {
        return rows.get(row);
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int col) {
        return colNames[cols[col]];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (cols[col]) {
            case IMPORTADO:
                return Boolean.class;
            default:
                return super.getColumnClass(col);
        }
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        Producto producto = rows.get(row);
        switch (cols[col]) {
            case CODIGO:
                return producto.getCodigo();
            case NOMBRE:
                return producto.getNombre();
            case IMPORTADO:
                return producto.isImportado();
            case PRECIO:
                return producto.getPrecio();
            case TIPO:
                return producto.getTipo();
            case PORCENTAJE:
                return producto.getPorcentaje();
            case IMPUESTO:
                return producto.getImpuesto();
            case PRECIO_FINAL:
                return producto.getPrecioFinal();
            default:
                return "";
        }
    }

    public JCheckBox importado(Producto p) {
        JCheckBox nuevo = new JCheckBox();
        if (p.isImportado()) {
            nuevo.setSelected(true);
        } else {
            nuevo.setSelected(false);
        }
        return nuevo;
    }

    public static final int CODIGO = 0;
    public static final int NOMBRE = 1;
    public static final int IMPORTADO = 2;
    public static final int PRECIO = 3;
    public static final int TIPO = 4;
    public static final int PORCENTAJE = 5;
    public static final int IMPUESTO = 6;
    public static final int PRECIO_FINAL = 7;

    String[] colNames = new String[11];

    private void initColNames() {
        //colNames[CODIGO] = "Codigo";
        colNames[NOMBRE] = "Nombre";
        colNames[IMPORTADO] = "Importado";
        colNames[PRECIO] = "Precio";
        colNames[TIPO] = "Tipo";
        colNames[PORCENTAJE] = "Porcentaje";
        colNames[IMPUESTO] = "Impuesto";
        colNames[PRECIO_FINAL] = "Precio final";
    }
}
