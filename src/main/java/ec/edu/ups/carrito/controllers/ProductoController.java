/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.carrito.controllers;

import ec.edu.ups.carrito.dao.ProductoDAO;
import ec.edu.ups.carrito.models.Producto;
import ec.edu.ups.carrito.views.ActualizarProductoView;
import ec.edu.ups.carrito.views.BuscarProductoView;
import ec.edu.ups.carrito.views.CrearProductoView;
import ec.edu.ups.carrito.views.EliminarProductoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Maza
 */
public class ProductoController {
    
    private ProductoDAO productoDAO;
    private CrearProductoView crearProductoView;
    private BuscarProductoView buscarProductoView;
    private EliminarProductoView eliminarProductoView;
    private ActualizarProductoView actualizarProductoView;


    public ProductoController ( CrearProductoView crearProductoView, ProductoDAO productoDAO, BuscarProductoView buscarProductoView,
       EliminarProductoView eliminarProductoView,  ActualizarProductoView actualizarProductoView    
            
    ) {
        
        this.crearProductoView = crearProductoView;
        this.productoDAO = productoDAO;
        this.buscarProductoView = buscarProductoView;
        this.eliminarProductoView = eliminarProductoView;
        this.actualizarProductoView =  actualizarProductoView;
        configurarEventosCrearProducto();
        configurarEventosBuscarProductos();
        configurarEventosEliminarProductos();
        configurarEventosActualizarProductos();
        
    }
 
    public void crearProducto(){
        //Este metodo debe acceder a las cajas de las vistas o View 
        int codigo = Integer.parseInt(crearProductoView.getTxtCodigo().getText());
        String nombre = crearProductoView.getTxtNombre().getText();
        double precio = Double.parseDouble(crearProductoView.getTxtPrecio().getText());
        Producto producto = new Producto(codigo , nombre , precio);
            
        productoDAO.crear(producto);
        
        crearProductoView.mostrarInformacion("Producto creado exitosamente");
        
    }
    public void configurarEventosCrearProducto(){
        crearProductoView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hola");
                crearProducto();
            }
        });
        
    }
    
    public void buscarProducto (){
        int codigo = Integer.parseInt(buscarProductoView.getTxtBuscarCodigo().getText());
        Producto p = productoDAO.buscar(codigo);
        if ( p != null){
            buscarProductoView.mostrarProducto(p);
        }else{
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
        
        
       
    }
    public void configurarEventosBuscarProductos(){
        buscarProductoView.getBtnBuscar().addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                buscarProducto();
            }
            
        });
    }
    public void eliminarProducto(){
        int codigo = Integer.parseInt(eliminarProductoView.getTxtEliminarProducto().getText());
        
        Producto p = productoDAO.buscar(codigo);
        if (p != null){
            eliminarProductoView.mostrarProducto(p);
            int respuesta = JOptionPane.showConfirmDialog(
            null,
            "¿Deseas eliminar?\n" ,
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION){
                 productoDAO.Eliminar(codigo);
                 JOptionPane.showMessageDialog(null, "Producto eliminado");
            }
        
        }
        
        } 
    public void configurarEventosEliminarProductos(){
        eliminarProductoView.getBtnAceptarEliminar().addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                eliminarProducto();
            }
            
        });
    }
    
     public void actualizarProducto(){
        int codigoB = Integer.parseInt(actualizarProductoView.getTxtCodigoBuscar().getText());
        int codigo = Integer.parseInt(actualizarProductoView.getTxtActualizarProducto().getText());
        String nombre = actualizarProductoView.getTxtNombreA().getText();
        double precio = Double.parseDouble(actualizarProductoView.getTxtPrecioA().getText());
        Producto producto =  new Producto(codigo , nombre , precio);
            
        productoDAO.actualizar(codigoB, producto);
        JOptionPane.showMessageDialog(null, "Producto actualizado correctamente ");

        
    }
     public void configurarEventosActualizarProductos(){
        actualizarProductoView.getBtnActualizarP().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
            
        });
    }




    
    
    
   
}
