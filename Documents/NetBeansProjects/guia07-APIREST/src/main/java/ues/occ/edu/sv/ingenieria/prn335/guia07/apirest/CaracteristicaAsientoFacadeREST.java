/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia07.apirest;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.CaracteristicaAsiento;
import ues.occ.edu.sv.ingenieria.prn335.guia07.apirest.resources.AbstractFacade;

/**
 *
 * @author StevenCastro
 */
@Stateless
@Path("caracteristicaAsiento")
public class CaracteristicaAsientoFacadeREST extends AbstractFacade<CaracteristicaAsiento> implements  Serializable{
    
    @EJB
    CaracteristicaAsientoFacadeREST cafr;

 @PersistenceContext(unitName="cinePU")
 private EntityManager em;
 
 public CaracteristicaAsientoFacadeREST(){
     super(CaracteristicaAsiento.class);
 }
 
  @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public String countREST() {
        return String.valueOf(super.count());
    }
 
    @GET
    @Path("{all}")
    public List<CaracteristicaAsiento> findAll( ) {

        try {
            if (cafr != null) {
                return cafr.findAll( );
            } 
        } catch (Exception e) {
             Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return Collections.EMPTY_LIST;
    }
    
    @GET
    @Path("{id}")
   
    public CaracteristicaAsiento findById(
            @PathParam("idrter") Integer id,
            @PathParam("n") Integer n){
        try {
            if (cafr != null && id != null) {
                System.out.println(n);
                return cafr.findById(id);
                
            } else {
                
            }
        } catch (Exception e) {
             Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        
      return new CaracteristicaAsiento();
   }

    @GET
    @Path("{inicio}/{tamanio}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CaracteristicaAsiento> findRange(
            @PathParam("inicio") @DefaultValue("0") int first,
            @PathParam("tamanio") @DefaultValue("10") int size) {
        try {
            if (cafr != null) {
                return cafr.findRange(first, size);
            }
        } catch (Exception e) {
            System.out.println("ex: " + e);
        }
        return Collections.EMPTY_LIST;
    }
    
    
 
    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
}
