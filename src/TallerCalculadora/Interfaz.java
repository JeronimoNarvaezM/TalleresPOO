/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TallerCalculadora;
import javax.swing.*;
import java.awt.Font;

/**
 *
 * @author 57320
 */
public class Interfaz {
    public static void main(String[] args) {
        
        JFrame ventana = new JFrame("BIENVENIDO A MI CALCULADORA");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 800);
        ventana.setLayout(null);
        JLabel etiqueta = new JLabel("CALCULADORA UAM.", SwingConstants.CENTER);
        etiqueta.setBounds(270,0,200,100);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 14));
        ventana.add(etiqueta);

        
        
        
       
        JLabel etiquetanum1 = new JLabel("INGRESE EN NUMERO 1");
        etiquetanum1.setBounds(100,80,200,100);
        ventana.add(etiquetanum1);      
        
        
        JTextField camponum1 = new JTextField();
        camponum1.setBounds(99,150,132,21);
        ventana.add(camponum1);
       
        
        JLabel etiquetanum2 = new JLabel("INGRESE EN NUMERO 2");
        etiquetanum2.setBounds(510,80,200,100);
        ventana.add(etiquetanum2);      
        
        
        JTextField camponum2 = new JTextField();
        camponum2.setBounds(510,150,132,21);
        ventana.add(camponum2);
        
        
        
        
        String[] opcion = new String[5];
            opcion[0]= "SELECCIONE UNA OPCION";
            opcion[1]= "sumar";
            opcion[2]= "restar";        
            opcion[3]= "dividir";       
            opcion[4]= "multiplicar";     
            JComboBox combo1 = new JComboBox(opcion);
            combo1.setBounds(270,200,180,50);
            ventana.add(combo1);
            
          
        
        
        JButton boton1 = new JButton("1");                                                                                                                               
        boton1.setBounds(270,295,50,50);
        ventana.add(boton1);
        
        JButton boton2 = new JButton("2");                                                                                                                               
        boton2.setBounds(336,295,50,50);
        ventana.add(boton2);
        
        JButton boton3 = new JButton("3");                                                                                                                               
        boton3.setBounds(400,295,50,50);
        ventana.add(boton3);
        
        
       
        
        JButton boton4 = new JButton("4");                                                                                                                               
        boton4.setBounds(270,370,50,50);
        ventana.add(boton4);
        
        JButton boton5 = new JButton("5");                                                                                                                               
        boton5.setBounds(336,370,50,50);
        ventana.add(boton5);
        
        JButton boton6 = new JButton("6");                                                                                                                               
        boton6.setBounds(400,370,50,50);
        ventana.add(boton6);
        
        
        
        
        JButton boton7 = new JButton("7");                                                                                                                               
        boton7.setBounds(270,445,50,50);
        ventana.add(boton7);
        
        JButton boton8 = new JButton("8");                                                                                                                               
        boton8.setBounds(336,445,50,50);
        ventana.add(boton8);
        
        JButton boton9 = new JButton("9");                                                                                                                               
        boton9.setBounds(400,445,50,50);
        ventana.add(boton9);
        
        
        
         JButton boton0 = new JButton("0");                                                                                                                               
        boton0.setBounds(336,520,50,50);
        ventana.add(boton0);
        
 
        
        JButton botoncalcular = new JButton("CALCULAR");                                                                                                                               
        botoncalcular.setBounds(270,650,180,50);
        ventana.add(botoncalcular);
       

        ventana.setVisible(true);
    }
    
}


