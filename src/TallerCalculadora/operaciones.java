/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TallerCalculadora;

/**
 *
 * @author 57320
 */
public class operaciones {
    
    public double sumar(double num1, double num2){
            return num1+num2;
        }
        
        public double restar(double num1, double num2){
            return num1-num2;
        }
        public double multiplicar(double num1, double num2){
            return num1*num2;
        }
        public double dividir(double num1, double num2){
            if(num2 !=0){
                return num1/num2;
            }
            else{
                throw new ArithmeticException ("la división por cero no es permitida");
            }
                
        }
}
