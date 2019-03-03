/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ci.controller;

import com.ci.entity.Empleado;
import com.ci.model.EmpleadoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author DeveloperSr
 */
@Named(value = "empleadoController")
@RequestScoped
public class EmpleadoController implements Serializable {

    @EJB
    private EmpleadoFacade empleadoFacade;
    private Empleado empleado=new Empleado();
    
    public EmpleadoController() {
        
    }
     public void reiniciarEmpleadoSeleccionada(){
        this.empleado = new Empleado();
    }
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public List<Empleado> findAll(){
    return this.empleadoFacade.findAll();
    }
    
    public String insert(){
        this.empleadoFacade.create(empleado);
        this.empleado = new Empleado();
    return "viewEmpleado";
    }
    
    public String delete(Empleado empleado){
        this.empleadoFacade.remove(empleado);
    return "index";
    }
}
