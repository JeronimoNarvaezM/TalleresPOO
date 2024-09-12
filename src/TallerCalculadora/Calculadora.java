/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TallerCalculadora;
import javax.swing.JOptionPane;


public class Calculadora {
    
    public static void main(String[] args) {
        
        // TODO code application logic here
        
        operaciones Operaciones= new operaciones();
        
        boolean continuar= true;
        
        while(continuar){
             String[] opciones = {"Sumar", "Restar", "Multiplicar", "Dividir", "Salir"};
             String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una operacion", "Calculadora", 
                     JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0]);
            if (seleccion.equals("Salir")){
                continuar= false;
                break;
            }
            
            String num12Str= JOptionPane.showInputDialog(null, "Ingrese el primer numero");
            double numero1= Double.parseDouble(num12Str);
            
            String num22Str= JOptionPane.showInputDialog(null, "Ingrese el segundo numero");
            double numero2= Double.parseDouble(num22Str);
            
            double resultado=0;
            
            try {
                switch(seleccion){
                    case "Sumar":
                        resultado= Operaciones.sumar(numero1, numero2);
                        break;
                    case "Restar":
                        resultado= Operaciones.restar(numero1, numero2);
                        break;
                    case "Multiplicar":
                        resultado= Operaciones.multiplicar(numero1, numero2);
                        break;
                    case "Dividir":
                        resultado= Operaciones.dividir(numero1, numero2);
                        break;
                        
                }
                JOptionPane.showMessageDialog(null, "El resultado es: " + resultado, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            }
            
            catch (ArithmeticException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}