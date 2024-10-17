/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerempleados;

/**
 *
 * @author 57320
 */
public class EmpleadoPorHoras extends Empleado {
    private int horasTrabajadas;
    private double tarifaHora;

    public EmpleadoPorHoras(String nombre, String apellido, String numeroSeguroSocial, double salarioBase, int horasTrabajadas, double tarifaHora) {
        super(nombre, apellido, numeroSeguroSocial, salarioBase);
        this.horasTrabajadas= horasTrabajadas;
        this.tarifaHora= tarifaHora;
    }
    
    @Override
    public double calcularDevengado(){
        return horasTrabajadas * tarifaHora;
    }
    
    @Override
    
    public double calcularDeducciones() {
        double fondoSolidaridad = calcularDevengado() * 0.02;
        double retencionFuente = calcularDevengado() * 0.05;
        return fondoSolidaridad + retencionFuente;
    }
}
