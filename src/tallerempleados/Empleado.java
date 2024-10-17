/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerempleados;

/**
 *
 * @author 57320
 */
public class Empleado {
    private String nombre;
    private String apellido;
    private String numeroSeguroSocial;
    protected double salarioBase;

    public Empleado(String nombre, String apellido, String numeroSeguroSocial, double salarioBase) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroSeguroSocial = numeroSeguroSocial;
        this.salarioBase = salarioBase;
    }

    public double calcularDevengado() {
        return salarioBase;
    }

    public double calcularDeducciones() {
        // Implementar deducciones genéricas si las hubiera
        return 0;
    }

    public double calcularSalarioNeto() {
        return calcularDevengado() - calcularDeducciones();
    }

    public String getInformacion() {
        return "Nombre: " + nombre + " " + apellido + "\n" +
               "Seguro Social: " + numeroSeguroSocial + "\n" +
               "Salario Base: " + salarioBase;
    }
}
