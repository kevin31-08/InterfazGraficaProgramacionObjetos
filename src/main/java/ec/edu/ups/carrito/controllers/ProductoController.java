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
import ec.edu.ups.carrito.views.ListarProductosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

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
    private ListarProductosView listarProductoView;


    public ProductoController ( CrearProductoView crearProductoView, ProductoDAO productoDAO, BuscarProductoView buscarProductoView,
       EliminarProductoView eliminarProductoView,  ActualizarProductoView actualizarProductoView, ListarProductosView listarProductoView    
            
    ) {
        
        this.crearProductoView = crearProductoView;
        this.productoDAO = productoDAO;
        this.buscarProductoView = buscarProductoView;
        this.eliminarProductoView = eliminarProductoView;
        this.actualizarProductoView =  actualizarProductoView;
        this.listarProductoView = listarProductoView;
        configurarEventosCrearProducto();
        configurarEventosBuscarProductos();
        configurarEventosEliminarProductos();
        configurarEventosActualizarProductos();
        configurarEventosListarProducto();
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
            
            int respuesta = JOptionPane.showConfirmDialog( null,"¿Deseas eliminar?\n" ,"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            
            if (respuesta == JOptionPane.YES_OPTION){
                 productoDAO.Eliminar(codigo);
                 JOptionPane.showMessageDialog(null, "Producto eliminado");
                 listarProducto();
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
        
        String nombre = actualizarProductoView.getTxtNombreA().getText();
        double precio = Double.parseDouble(actualizarProductoView.getTxtPrecioA().getText());
        Producto producto =  new Producto(codigoB, nombre , precio);
            
        productoDAO.actualizar( producto);
        listarProducto();
        JOptionPane.showMessageDialog(null, "Producto actualizado correctamente ");

        
    }
     public void listarProducto(){
        List<Producto> listaActualizada = productoDAO.listar();
        listarProductoView.cargarDatos(listaActualizada);
    }
     
     public void configurarEventosListarProducto(){
         listarProductoView.addInternalFrameListener(new InternalFrameAdapter(){
             @Override
             public void internalFrameActivated(InternalFrameEvent e) {
                 listarProducto();
             }
             
         });
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
