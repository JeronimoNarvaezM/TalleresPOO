/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Eventos;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/**
 *
 * @author 57320
 */
public class Main {
    public static void main(String[] args){
        
        JFrame ventana= new JFrame ("Ejemplo MouseClick");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800,600);
        
        JLabel labelNumero1= new JLabel("Ingrese el numero 1");
        JTextField campoNumero1= new JTextField();
        labelNumero1.setBounds(100, 50, 150, 30);
        campoNumero1.setBounds(100, 50, 150, 30);
        
        
        JLabel labelNumero2= new JLabel("Ingrese el numero 2");
        JTextField campoNumero2= new JTextField();
        labelNumero2.setBounds(300, 50, 150, 30);
        campoNumero2.setBounds(350, 50, 150, 30);
        
        JButton boton1= new JButton("Sumar");
        boton1.setBounds(300, 100, 100, 30);
        
        ventana.add(labelNumero1);
        ventana.add(campoNumero1);
        ventana.add(labelNumero2);
        ventana.add(campoNumero2);
        ventana.add(boton1);
        
        boton1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int numero1= Integer.parseInt(campoNumero1.getText());
                int numero2= Integer.parseInt(campoNumero2.getText());
                JOptionPane.showInputDialog(null, "INFO", "Se ha presionado el boton");
            }
        });
        
    }
}
