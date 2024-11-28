package RegistroProductosXML;

import RegistroProductos.Producto;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class ArchivoXML {
    private final String archivoXML = "productos.xml";

    // Método para agregar un producto al archivo XML
    public void agregarProducto(Producto producto) throws IOException {
        List<Producto> productos = leerProductos();
        productos.add(producto);
        escribirProductos(productos);
    }

    // Método para leer todos los productos del archivo XML
    public List<Producto> leerProductos() throws IOException {
        List<Producto> productos = new ArrayList<>();
        File archivo = new File(archivoXML);

        if (archivo.exists()) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(archivo);
                NodeList listaProductos = doc.getElementsByTagName("producto");

                for (int i = 0; i < listaProductos.getLength(); i++) {
                    Node nodo = listaProductos.item(i);

                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) nodo;
                        String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                        String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                        double precio = Double.parseDouble(elemento.getElementsByTagName("precio").item(0).getTextContent());
                        String categoria = elemento.getElementsByTagName("categoria").item(0).getTextContent();

                        productos.add(new Producto(codigo, nombre, precio, categoria));
                    }
                }
            } catch (Exception e) {
                throw new IOException("Error al leer el archivo XML: " + e.getMessage());
            }
        }

        return productos;
    }

    // Método para escribir todos los productos al archivo XML
    public void escribirProductos(List<Producto> productos) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("productos");
            doc.appendChild(root);

            for (Producto producto : productos) {
                Element elementoProducto = doc.createElement("producto");

                Element elementoCodigo = doc.createElement("codigo");
                elementoCodigo.appendChild(doc.createTextNode(producto.getCodigo()));
                elementoProducto.appendChild(elementoCodigo);

                Element elementoNombre = doc.createElement("nombre");
                elementoNombre.appendChild(doc.createTextNode(producto.getNombre()));
                elementoProducto.appendChild(elementoNombre);

                Element elementoPrecio = doc.createElement("precio");
                elementoPrecio.appendChild(doc.createTextNode(String.valueOf(producto.getPrecio())));
                elementoProducto.appendChild(elementoPrecio);

                Element elementoCategoria = doc.createElement("categoria");
                elementoCategoria.appendChild(doc.createTextNode(producto.getCategoria()));
                elementoProducto.appendChild(elementoCategoria);

                root.appendChild(elementoProducto);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(archivoXML));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new IOException("Error al escribir en el archivo XML: " + e.getMessage());
        }
    }
    
    public void eliminarProductoPorCodigo(String codigo) throws IOException {
        List<Producto> productos = leerProductos();
        productos.removeIf(producto -> producto.getCodigo().equals(codigo));
        escribirProductos(productos);
    }
    
    public void modificarProductoPorCodigo(String codigoActual, Producto productoModificado) throws IOException {
        List<Producto> productos = leerProductos();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(codigoActual)) {
                productos.set(i, productoModificado);
                break;
            }
    }
    escribirProductos(productos);
}
        
        
}