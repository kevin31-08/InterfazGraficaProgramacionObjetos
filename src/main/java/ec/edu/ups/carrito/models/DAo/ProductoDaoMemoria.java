/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.carrito.models.DAo;

import ec.edu.ups.carrito.models.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author LAB_04
 */
public class ProductoDaoMemoria implements ProductoDAO {
    
    private List<Producto> lista;

    public ProductoDaoMemoria() {
        lista = new ArrayList<>();
    }
    
    
    @Override
    public void crear(Producto producto) {
        lista.add(producto);
        System.out.println("creo el producto");
    }

    @Override
    public Producto buscar(int codigo) {
        for(Producto producto : lista){
            if(producto.getCodigo()== codigo)
            return producto;
        }
        return null;
    }

    @Override
    public void actualizar(int codigo, Producto producto) {
        for( int i = 0; i < lista.size(); i++){
           Producto productoEncontrado = lista.get(i);
           if(productoEncontrado.getCodigo()==codigo){
               lista.set(i, producto);
               break;
           }
       }
    }

    @Override
    public void eliminar(int codigo) {
      Producto productoEliminar = buscar(codigo);
      if(productoEliminar != null){
          lista.remove(productoEliminar);
      }
      
    }

    @Override
    public List<Producto> listar() {
        return lista;
    }
    
}
