/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerempleados;

/**
 *
 * @author 57320
 */
public class EmpleadoGerente extends EmpleadoPorSueldo {
    
    private double bonoMensual;
    private double impuestoRiqueza;
    public EmpleadoGerente(String nombre, String apellido, String numeroSeguroSocial, double salarioBase, double retencionFuente, double fondoSolidaridad, double salud, double pension, double bonoMensual, double impuestoRiqueza) {
        super(nombre, apellido, numeroSeguroSocial, salarioBase, retencionFuente, fondoSolidaridad, salud, pension);
        this.bonoMensual= bonoMensual;
        this.impuestoRiqueza= impuestoRiqueza;
    }
    
    @Override
    public double calcularDevengado(){
        return super.calcularDevengado()+ bonoMensual;
    }
    
    @Override
    public double calcularDeducciones(){
        return super.calcularDeducciones()+ impuestoRiqueza;
    }
}
