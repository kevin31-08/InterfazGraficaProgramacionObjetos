/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.carrito.controllers;

import ec.edu.ups.carrito.models.Producto;
import ec.edu.ups.carrito.views.ActualizarProductoView;
import ec.edu.ups.carrito.views.BuscarProductoView;
import ec.edu.ups.carrito.views.CrearProductoView;
import ec.edu.ups.carrito.views.EliminarProductoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Maza
 */
public class ProductoController {
    
    private Producto producto;
    private CrearProductoView crearProductoView;
    private BuscarProductoView buscarProductoView;
    private EliminarProductoView eliminarProductoView;
    private ActualizarProductoView actualizarProductoView;
    private static ArrayList<Producto> listaProductos = new ArrayList<>();

    public ProductoController ( CrearProductoView crearProductoView ) {
        
        this.crearProductoView = crearProductoView;
        configurarEventosCrearProducto();
        
       
        
        
    }
    public ProductoController(BuscarProductoView buscarProductoView){
         this.buscarProductoView = buscarProductoView;
         configurarEventosBuscarProductos();
    }
    public ProductoController(EliminarProductoView  eliminarProductoView){
        this.eliminarProductoView = eliminarProductoView;
        configurarEventosEliminarProductos();
    }
    
    public ProductoController(ActualizarProductoView actualizarProductoView){
        this.actualizarProductoView = actualizarProductoView;
        configurarEventosActualizarProductos();
        
    }
    public void crearProducto(){
        //Este metodo debe acceder a las cajas de las vistas o View 
        int codigo = Integer.parseInt(crearProductoView.getTxtCodigo().getText());
        String nombre = crearProductoView.getTxtNombre().getText();
        double precio = Double.parseDouble(crearProductoView.getTxtPrecio().getText());
        
        producto = new Producto (codigo,nombre,precio);
        listaProductos.add(producto);
        System.out.println("Producto creado exitosamente");
        
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
        
        for ( int i = 0 ; i< listaProductos.size(); i++ ){
            Producto producto = listaProductos.get(i);
            if ( producto.getCodigo() == codigo ){
                System.out.println("Producto encontrado:" + producto.getNombre());
                return;
          }
        }
        System.out.println("Erro no se encontro un producto");
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
        int codigoE = Integer.parseInt(eliminarProductoView.getTxtEliminarProducto().getText());
        for ( int i = 0 ; i< listaProductos.size(); i++ ){
            Producto producto = listaProductos.get(i);
            if ( producto.getCodigo() == codigoE ){
                System.out.println("Producto eliminado: " + producto.getNombre());
                return;
          }
        }
        System.out.println("Erro no se encontro un producto");
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
            int codigoActualizado = Integer.parseInt(actualizarProductoView.getTxtActualizarProducto().getText());
            String nuevoN = actualizarProductoView.getTxtNombreA().getText(); 
            double nuevoP = Double.parseDouble(actualizarProductoView.getTxtPrecioA().getText()); 
        
        for(int i = 0; i < listaProductos.size(); i++){
            Producto producto = listaProductos.get(i);
            if(producto.getCodigo() == codigoActualizado){
                producto.setNombre(nuevoN);
                producto.setPrecio(nuevoP);
                System.out.println("El producto se actualizo correctamente");
                return ; 
            }
        }
        
        System.out.println("No se encontro el producto por el codigo inexistente");
        
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
