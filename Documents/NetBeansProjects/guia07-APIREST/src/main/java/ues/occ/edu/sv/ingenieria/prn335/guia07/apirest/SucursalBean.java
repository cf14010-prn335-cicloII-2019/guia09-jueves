/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia07.apirest;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Sucursal;
import ues.occ.edu.sv.ingenieria.prn335.guia07.apirest.resources.AbstractInterface;
import ues.occ.edu.sv.ingenieria.prn335.guia07.apirest.resources.SucursalFacade;

/**
 *
 * @author StevenCastro
 */
@Named(value = "sucursalBean")
@ViewScoped
public class SucursalBean extends AbstractManagedBean<Sucursal> implements Serializable{

    @EJB
    SucursalFacade sf;
    Sucursal su = new Sucursal();
      boolean activo=false, panel=true;
    
  
    public SucursalBean(){
    }
    
     /**
     * Este metodo carga la lista inicial de datos a la DataTable al ingresar a
     * la aplicacion
     */
     @PostConstruct
    public void init() {
        super.AbstractManagedBean();
        llenarLista();
        System.out.println("POSTCONSTRUCT");
    }
    
    @Override
    public void crear() {
        super.AbstractManagedBean();
        super.crear();
        cancelar();
    }

    /**
     * Elimina registros de la DB utilizando el metodo remove de la clase
     * AbstractFacade
     */
    @Override
    public void eliminar() {
        super.AbstractManagedBean();
        super.eliminar();
        cancelar();
    }

    /**
     * Invoca al metodo edit de la clase AbstractFacade para editar un registro
     */
    @Override
    public void editar() {
        super.AbstractManagedBean();
        super.editar();
        cancelar();
    }

    /**
     * Si el objeto Sucursal es diferente de nulo asigna una nueva instancia a este
     * para dejarlo vacio, oculta el panel de botones de edicion
     */
    public void cancelar() {
        su = new Sucursal();
        System.out.println("LIMPIAR");
        panel = true;
        activo=false;
    }
    

    /**
     * Recice un evente de tipo SelectEvent, asigna los valores del objeto
     * recibido a las propiedades del objeto rol
     *
     * @param event
     
    public void onRowSelect(SelectEvent event) {
        su= (Sucursal) event.getObject();
        panel=false;
        activo=true;
    } 
*/
    
    
    /**
     * Metodo que obtiene el facade Local de Caracteristica Asiento
     * @return Devuelve una instancia de Caracteristica Asiento Facade
     */
    
    @Override
    protected AbstractInterface<Sucursal> getFacadeLocal() {
       return (AbstractInterface<Sucursal>) su;
    }

    /**
     * Metodo que obtiene la Entity Caracteristica Asiento 
     * @return Retorna una instancia de la entity Caracteristica Asiento
     */
    @Override
    protected Sucursal getEntity() {
    return su;
    }
}
