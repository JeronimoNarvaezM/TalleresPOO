/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerempleados;

/**
 *
 * @author 57320
 */
public class EmpleadoPorSueldo extends Empleado {
    private double retencionFuente;
    private double fondoSolidaridad;
    private double salud;
    private double pension;

    public EmpleadoPorSueldo(String nombre, String apellido, String numeroSeguroSocial, double salarioBase, double retencionFuente, double fondoSolidaridad, double salud, double pension) {
        super(nombre, apellido, numeroSeguroSocial, salarioBase);
        this.retencionFuente= retencionFuente;
        this.fondoSolidaridad = fondoSolidaridad;
        this.salud = salud;
        this.pension = pension; 
    }
    
    @Override
    public double calcularDeducciones(){
        return retencionFuente + fondoSolidaridad + salud + pension;
    }
}
