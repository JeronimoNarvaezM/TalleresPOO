package TallerCalculadora;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Interfaz {
    private static boolean focusOnNum1 = true; // Para determinar en qué campo se ingresa el número

    public static void main(String[] args) {

        // Crear ventana principal
        JFrame ventana = new JFrame("BIENVENIDO A MI CALCULADORA");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 800);
        ventana.setLayout(null);

        // Etiqueta de la calculadora
        JLabel etiqueta = new JLabel("CALCULADORA UAM.", SwingConstants.CENTER);
        etiqueta.setBounds(270, 0, 200, 100);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 14));
        ventana.add(etiqueta);

        // Etiquetas y campos de texto
        JLabel etiquetanum1 = new JLabel("INGRESE EL NÚMERO 1");
        etiquetanum1.setBounds(100, 80, 200, 100);
        ventana.add(etiquetanum1);

        JTextField camponum1 = new JTextField();
        camponum1.setBounds(99, 150, 132, 21);
        ventana.add(camponum1);

        JLabel etiquetanum2 = new JLabel("INGRESE EL NÚMERO 2");
        etiquetanum2.setBounds(510, 80, 200, 100);
        ventana.add(etiquetanum2);

        JTextField camponum2 = new JTextField();
        camponum2.setBounds(510, 150, 132, 21);
        ventana.add(camponum2);

        // Añadir focus listener a los campos para saber cuál tiene el foco
        camponum1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                focusOnNum1 = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                // No es necesario implementar esta parte
            }
        });

        camponum2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                focusOnNum1 = false;
            }

            @Override
            public void focusLost(FocusEvent e) {
                // No es necesario implementar esta parte
            }
        });

        // ComboBox para seleccionar la operación
        String[] opcion = {"SELECCIONE UNA OPCIÓN", "Sumar", "Restar", "Dividir", "Multiplicar"};
        JComboBox<String> combo1 = new JComboBox<>(opcion);
        combo1.setBounds(270, 200, 180, 50);
        ventana.add(combo1);

        // Instancia de la clase Calculadora
        Calculadora calculadora = new Calculadora();

        // Botones de la calculadora
        JButton boton0 = crearBoton("0", 336, 520, camponum1, camponum2);
        JButton boton1 = crearBoton("1", 270, 295, camponum1, camponum2);
        JButton boton2 = crearBoton("2", 336, 295, camponum1, camponum2);
        JButton boton3 = crearBoton("3", 400, 295, camponum1, camponum2);
        JButton boton4 = crearBoton("4", 270, 370, camponum1, camponum2);
        JButton boton5 = crearBoton("5", 336, 370, camponum1, camponum2);
        JButton boton6 = crearBoton("6", 400, 370, camponum1, camponum2);
        JButton boton7 = crearBoton("7", 270, 445, camponum1, camponum2);
        JButton boton8 = crearBoton("8", 336, 445, camponum1, camponum2);
        JButton boton9 = crearBoton("9", 400, 445, camponum1, camponum2);

        // Botón para realizar el cálculo
        JButton botoncalcular = new JButton("CALCULAR");
        botoncalcular.setBounds(270, 650, 180, 50);
        ventana.add(botoncalcular);

        // Acción del botón calcular
        botoncalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(camponum1.getText());
                    double num2 = Double.parseDouble(camponum2.getText());
                    String operacion = (String) combo1.getSelectedItem();
                    double resultado = 0;

                    switch (operacion) {
                        case "Sumar":
                            resultado = calculadora.sumar(num1, num2);
                            break;
                        case "Restar":
                            resultado = calculadora.restar(num1, num2);
                            break;
                        case "Multiplicar":
                            resultado = calculadora.multiplicar(num1, num2);
                            break;
                        case "Dividir":
                            resultado = calculadora.dividir(num1, num2);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Seleccione una operación válida");
                            return;
                    }

                    JOptionPane.showMessageDialog(null, "El resultado es: " + resultado);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese números válidos.");
                } catch (ArithmeticException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // Añadir botones a la ventana
        ventana.add(boton0);
        ventana.add(boton1);
        ventana.add(boton2);
        ventana.add(boton3);
        ventana.add(boton4);
        ventana.add(boton5);
        ventana.add(boton6);
        ventana.add(boton7);
        ventana.add(boton8);
        ventana.add(boton9);

        ventana.setVisible(true);
    }

    private static JButton crearBoton(String texto, int x, int y, JTextField camponum1, JTextField camponum2) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, 50, 50);
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (focusOnNum1) {
                    camponum1.setText(camponum1.getText() + texto);
                } else {
                    camponum2.setText(camponum2.getText() + texto);
                }
            }
        });
        return boton;
    }
}
