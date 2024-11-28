/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistroProductosJSON;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegistroProductosGson {
    private static final String ARCHIVO_JSON = "C:\\Users\\57320\\Desktop\\Programación\\TalleresPOO\\src\\RegistroProductosJSON\\productos.json" ;
    private List<Producto> productos;
    private Gson gson;
    
    public RegistroProductosGson(){
        gson= new Gson();
        productos = cargarProductos();
    }
    
    public void guardarProductos(){
        try(Writer writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(productos, writer);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public List<Producto> cargarProductos(){
        try(Reader reader= new FileReader(ARCHIVO_JSON)){
            Type listType = new TypeToken<List<Producto>>(){}.getType();
            return gson.fromJson(reader, listType);
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public void agregarProductos(Producto producto){
        productos.add(producto);
        guardarProductos();
        
    }
    
    public void eliminarProductos(String codigo){
        productos.removeIf(p->p.getCodigo().equals(codigo));
        guardarProductos();
    }
    
    public void modificarProducto(String codigoOriginal, Producto productoModificado) {
    for (int i = 0; i < productos.size(); i++) {
        if (productos.get(i).getCodigo().equals(codigoOriginal)) {
            productos.set(i, productoModificado); // Reemplazar el producto
            guardarProductos(); // Guardar cambios
            break;
        }
    }
}

    public List<Producto> getProductos(){
        return productos;
    }

    
}
