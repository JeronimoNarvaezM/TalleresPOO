/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {
     public static void guardarPlano(List<Persona> personas, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Persona persona : personas) {
                writer.write(persona.toString());
                writer.newLine();
            }
        }
    }

    // Leer archivo plano
    public static List<Persona> leerPlano(String filePath) throws IOException {
        List<Persona> personas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    personas.add(new Persona(parts[0], parts[1], parts[2]));
                }
            }
        }
        return personas;
    }

    // Guardar en XML
    public static void guardarXML(List<Persona> personas, String filePath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element rootElement = doc.createElement("Personas");
        doc.appendChild(rootElement);

        for (Persona persona : personas) {
            Element personaElement = doc.createElement("Persona");

            Element idElement = doc.createElement("Identificacion");
            idElement.appendChild(doc.createTextNode(persona.getIdentificacion()));
            personaElement.appendChild(idElement);

            Element nombreElement = doc.createElement("Nombre");
            nombreElement.appendChild(doc.createTextNode(persona.getNombre()));
            personaElement.appendChild(nombreElement);

            Element correoElement = doc.createElement("Correo");
            correoElement.appendChild(doc.createTextNode(persona.getCorreo()));
            personaElement.appendChild(correoElement);

            rootElement.appendChild(personaElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    // Leer XML
    public static List<Persona> leerXML(String filePath) throws Exception {
        List<Persona> personas = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(filePath));

        NodeList nodeList = doc.getElementsByTagName("Persona");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String identificacion = element.getElementsByTagName("Identificacion").item(0).getTextContent();
                String nombre = element.getElementsByTagName("Nombre").item(0).getTextContent();
                String correo = element.getElementsByTagName("Correo").item(0).getTextContent();

                personas.add(new Persona(identificacion, nombre, correo));
            }
        }
        return personas;
    }

    // Guardar en JSON
    public static void guardarJSON(List<Persona> personas, String filePath) throws IOException {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(personas, writer);
        }
    }

    // Leer JSON
    public static List<Persona> leerJSON(String filePath) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, new TypeToken<List<Persona>>() {}.getType());
        }
    }
}
