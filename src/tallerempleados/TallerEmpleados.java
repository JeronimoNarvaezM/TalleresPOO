/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tallerempleados;

/**
 *
 * @author 57320
 */
public class TallerEmpleados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Empresa empresa = new Empresa();

        EmpleadoPorHoras empHoras = new EmpleadoPorHoras("Jeronimo", "Narvaez", "121423114", 0, 90, 120);
        EmpleadoPorSueldo empSueldo = new EmpleadoPorSueldo("Andrea", "Giraldo", "987654321", 2000, 100, 50, 150, 200);
        EmpleadoGerente empGerente = new EmpleadoGerente("Camilo", "Ramoz", "192837465", 3000, 150, 75, 200, 250, 500, 100);

        empresa.agregarEmpleado(empHoras);
        empresa.agregarEmpleado(empSueldo);
        empresa.agregarEmpleado(empGerente);

        empresa.calcularNominaEmpleados();
    }
    
}
