/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistroProductos;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ArchivoProducto {
    
     private static final String FILE_NAME = "C:\\Users\\57320\\Desktop\\Programación\\TalleresPOO\\src\\RegistroProductos\\productos.txt";

    // Método para escribir un producto en el archivo
    public void escribirProducto(Producto producto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(producto.getCodigo() + ", "+producto.getNombre() + ", "+producto.getPrecio() + ", "+producto.getCategoria());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer productos desde el archivo
    public List<Producto> leerProductos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String codigo = partes[0];
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    String categoria = partes[3];
                    productos.add(new Producto(codigo, nombre, precio, categoria));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
