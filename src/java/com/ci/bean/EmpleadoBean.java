/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ci.bean;

import com.ci.entity.Empleado;
import com.ci.services.EmpleadoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author DeveloperSr
 */
@Named(value = "empleadoBean")
@RequestScoped
public class EmpleadoBean {

    @EJB
    private EmpleadoService empleadoService;
    private Empleado empleadoSeleccionada;

    List<Empleado> empleados;
    
    public EmpleadoBean() {
    }
    @PostConstruct
    public void inicializar() {
        //Iniciamos las variables
        empleados = empleadoService.listarEmpleados();
        empleadoSeleccionada = new Empleado();
    }
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    
    public Empleado getEmpleadoSeleccionada() {
        return empleadoSeleccionada;
    }

    public void setEmpleadoSeleccionada(Empleado empleadoSeleccionada) {
        this.empleadoSeleccionada = empleadoSeleccionada;
    }
    
    public void agregarEmpleado() {
        empleadoService.registrarEmpleado(this.empleadoSeleccionada);
        this.empleadoSeleccionada = null;
    }
    public void reiniciarEmpleadoSeleccionada(){
        this.empleadoSeleccionada = new Empleado();
    }
    public void eliminarEmpleado() {
        
        empleadoService.eliminarEmpleado(this.empleadoSeleccionada);
        this.empleadoSeleccionada = null;
        this.inicializar();
    }
    
    public void editListener(RowEditEvent event) {
        Empleado empleado = (Empleado) event.getObject();
        empleadoService.modificarEmpleado(empleado);
    }
}
