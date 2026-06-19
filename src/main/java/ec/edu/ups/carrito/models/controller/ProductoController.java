package ec.edu.ups.carrito.models.controller;

import ec.edu.ups.carrito.models.DAo.ProductoDAO;
import ec.edu.ups.carrito.models.Producto;
import ec.edu.ups.carrito.views.ActualizarProducto;
import ec.edu.ups.carrito.views.BuscarProducto;
import ec.edu.ups.carrito.views.CrearProductoView;
import ec.edu.ups.carrito.views.EliminarProductoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductoController {

    private Producto producto;
    private CrearProductoView crearProductoView;
    private BuscarProducto buscarProductoView;
    private EliminarProductoView eliminarProductoView;
    private ActualizarProducto actualizarProductoView;
    private ProductoDAO productoDAO;

   
    
    

    public ProductoController(CrearProductoView crearProductoView,ProductoDAO productoDAO, BuscarProducto buscarProductoView, EliminarProductoView eliminarProductoView, ActualizarProducto actualizarProductoView) {

        this.crearProductoView = crearProductoView;
        configurarEventosCrearProducto();
        this.productoDAO = productoDAO;
        this.buscarProductoView = buscarProductoView;
        configurarEventosBuscarProductos();
        this.eliminarProductoView = eliminarProductoView;
        configurarEventosEliminarProductos();
        this.actualizarProductoView = actualizarProductoView;
        configurarEventosActualizarProducto();
    }

    public void crearProducto() {
        //Este metodo debe acceder a las cajas de las vistas o View 
        int codigo = Integer.parseInt(crearProductoView.getTxtCodigo().getText());
        String nombre = crearProductoView.getTxtNombre().getText();
        double precio = Double.parseDouble(crearProductoView.getTxtPrecio().getText());

        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.crear(producto);
        crearProductoView.mostrarMnesaje("Producto creado exitosamente");

    }

    public void configurarEventosCrearProducto() {
        crearProductoView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearProducto();
            }
        });

    }

    public void buscarProducto() {
        int codigo = Integer.parseInt(buscarProductoView.getTxtMostrar().getText());
        productoDAO.buscar(codigo);
    }

    public void configurarEventosBuscarProductos() {
        buscarProductoView.getBjnMostrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }

        });
    }

    public void eliminarProducto() {
        int codigo = Integer.parseInt(eliminarProductoView.getTxtEliminarProducto().getText());
        productoDAO.eliminar(codigo);
    }

    public void configurarEventosEliminarProductos() {
        eliminarProductoView.getBtnAceptarEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                eliminarProducto();
            }

        });
    }

    public void actualizarProducto() {
        int codigo = Integer.parseInt(actualizarProductoView.getTxtCodigoProducto().getText());
        String nombre = actualizarProductoView.getTxtNombreProducto().getText();
        double precio = Double.parseDouble(actualizarProductoView.getTxtPrecioProducto().getText());
        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.actualizar(codigo, producto);
        
    }

    public void configurarEventosActualizarProducto() {

        actualizarProductoView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                actualizarProducto();
            }
        });
    }
}
