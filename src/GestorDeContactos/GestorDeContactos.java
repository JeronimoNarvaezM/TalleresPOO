/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestorDeContactos;

import java.util.ArrayList;
import javax.swing.JOptionPane;


public class GestorDeContactos {
    private ArrayList<Contacto> listaContactos;

    public GestorDeContactos() {
        listaContactos = new ArrayList<>();
    }

    public void agregarContacto(Contacto contacto) {
        if (esContactoValido(contacto)) {
            listaContactos.add(contacto);
            JOptionPane.showMessageDialog(null, "Contacto agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "El contacto ya existe o tiene datos inválidos.");
        }
    }

    public void editarContacto(Contacto contacto, String nuevoNombre, String nuevoTelefono, String nuevoEmail, String nuevaDireccion, String nuevoEstadoCivil) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de editar este contacto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            contacto.setNombre(nuevoNombre);
            contacto.setTelefono(nuevoTelefono);
            contacto.setEmail(nuevoEmail);
            contacto.setDireccion(nuevaDireccion);
            contacto.setEstadoCivil(nuevoEstadoCivil);
            JOptionPane.showMessageDialog(null, "Contacto editado correctamente.");
        }
    }

    private boolean esContactoValido(Contacto contacto) {
        for (Contacto c : listaContactos) {
            if (c.getNombre().equals(contacto.getNombre()) || c.getTelefono().equals(contacto.getTelefono())) {
                return false;
            }
        }
        return contacto.getTelefono().matches("\\d+") && contacto.getEmail().contains("@") && contacto.getEmail().endsWith(".com");
    }

    public void eliminarContacto(Contacto contacto) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este contacto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            listaContactos.remove(contacto);
            JOptionPane.showMessageDialog(null, "Contacto eliminado.");
        }
    }

    public Contacto buscarContacto(String nombre) {
        for (Contacto contacto : listaContactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                return contacto;
            }
        }
        return null;
    }

    public ArrayList<Contacto> getListaContactos() {
        return listaContactos;
    }
}
