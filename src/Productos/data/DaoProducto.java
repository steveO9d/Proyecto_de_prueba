/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Productos.data;

import Productos.logic.Producto;
import Productos.logic.Tipo;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author steve
 */
public class DaoProducto extends Servicio {

    //FUNCIONES ALMACENADAS
    public List<Producto> listarProducto() throws GlobalException, NoDataException {
        List<Producto> r = new ArrayList<>();
        try {
            conectar();
            CallableStatement stm = conexion.prepareCall(LISTARPRODUCTOS);
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.execute();
            ResultSet rs = (ResultSet) stm.getObject(1);

            while (rs.next()) {
                r.add(new Producto(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getBoolean("importado"),
                        rs.getDouble("precio"),
                        rs.getDouble("porcentaje"),
                        rs.getDouble("impuesto"),
                        rs.getDouble("precioFinal"),
                        toTipo(rs)));
            }
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        return r;
    }

    private Tipo toTipo(ResultSet rs) throws SQLException {
        Tipo nuevo = new Tipo();
        nuevo.setNombre_tipo(rs.getString("tipo_nombretipo"));
        return nuevo;
    }

    // PROCEDIMIENTOS ALMACENADOS
    public void insertarProducto(Producto producto) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement pstmt = conexion.prepareStatement(INSERTARPRODUCTO);
            pstmt.setString(1, producto.getCodigo());
            pstmt.setString(2, producto.getNombre());
            int num = (producto.isImportado()) ? 1 : 0;
            pstmt.setInt(3, num);
            pstmt.setDouble(4, producto.getPrecio());
            pstmt.setDouble(5, producto.getPorcentaje());
            pstmt.setDouble(6, producto.getImpuesto());
            pstmt.setDouble(7, producto.getPrecioFinal());
            pstmt.setString(8, producto.getTipo().getNombre_tipo());
            int exito = pstmt.executeUpdate();

            if (exito != 1) {
                throw new Exception("Solicitud no ingresada");
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    public List<Tipo> listarTipos() throws GlobalException, NoDataException {
        List<Tipo> r = new ArrayList<>();
        try {
            conectar();
            CallableStatement stm = conexion.prepareCall(LISTARTIPOS);
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.execute();
            ResultSet rs = (ResultSet) stm.getObject(1);

            while (rs.next()) {
                r.add(new Tipo(
                        rs.getString("nombretipo")));
            }
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        return r;
    }

    public List<Producto> buscarProductoPorNombre(String nombre) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        List<Producto> coleccion = new ArrayList();
        Producto elProducto = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(BUSCAR_PRODUCTO_POR_NOMBRE);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, nombre);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elProducto = new Producto(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getBoolean("importado"),
                        rs.getDouble("precio"),
                        rs.getDouble("porcentaje"),
                        rs.getDouble("impuesto"),
                        rs.getDouble("precioFinal"),
                        toTipo(rs));
                coleccion.add(elProducto);
            }
        } catch (SQLException e) {

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    public List<Producto> listarPorTipo(String tipo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        List<Producto> coleccion = new ArrayList();
        Producto elProducto = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTAR_POR_TIPO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, tipo);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elProducto = new Producto(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getBoolean("importado"),
                        rs.getDouble("precio"),
                        rs.getDouble("porcentaje"),
                        rs.getDouble("impuesto"),
                        rs.getDouble("precioFinal"),
                        toTipo(rs));
                coleccion.add(elProducto);
            }
        } catch (SQLException e) {

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
    
    
    private static final String LISTAR_POR_TIPO
            = "{? = call listarPorTipo(?)}";

    private static final String LISTARTIPOS
            = "{? = call listarTipos()}";

    private static final String LISTARPRODUCTOS
            = "{? = call listarProductos()}";

    private static final String BUSCAR_PRODUCTO_POR_NOMBRE
            = "{? = call buscarProductoPorNombre(?)}";

    private static final String INSERTARPRODUCTO
            = "{call insertarProductos(?,?,?,?,?,?,?,?)}";

}
