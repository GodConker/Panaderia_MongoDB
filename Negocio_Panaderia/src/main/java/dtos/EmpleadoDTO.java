/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

public class EmpleadoDTO {

    private String id;  // Usamos String en lugar de ObjectId
    private int idEmpleado;
    private String nombre;
    private String cargo;
    private Double salario;  // Tipo Double para alinearse con la clase Empleado

    public EmpleadoDTO() {
    }
    
     // Constructor con parámetros
    public EmpleadoDTO(String nombre, String cargo, Double salario) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }   
    
    public EmpleadoDTO(String id, /*int idEmpleado,*/ String nombre, String cargo, Double salario) {
        this.id = id;
        /*this.idEmpleado = idEmpleado;*/
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{"
                + "id='" + id + '\''
                + ", nombre='" + nombre + '\''
                + ", cargo='" + cargo + '\''
                + ", salario=" + salario
                + '}';
    }

    public String obtenerNombre() {
        return this.nombre;
    }
}
