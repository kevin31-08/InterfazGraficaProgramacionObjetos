/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.ups.carrito.dao;

import ec.edu.ups.carrito.models.Producto;

/**
 *
 * @author Miguel Maza
 */
public interface ProductoDAO {
    
    // Las interfaces tienen metodos publicos abstractos
    
    void crear(Producto producto );
    Producto buscar(int codigo);
    void actualizar(int codigo, Producto producto);
    Producto Eliminar(int codigo);
    
}
