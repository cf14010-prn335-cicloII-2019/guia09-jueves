/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia07.apirest;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import ues.occ.edu.sv.ingenieria.prn335.guia07.apirest.resources.AbstractInterface;

/**
 *
 * @author StevenCastro
 */
public abstract class AbstractManagedBean<T> implements Serializable {
    
public void AbstractManagedBean( ) {
         this.ai = getFacadeLocal();
         this.entity = getEntity();
    }
    
    private AbstractInterface ai;
    private T entity;
    protected List<T> listaRegistros;
    
    protected abstract AbstractInterface<T> getFacadeLocal();
    protected abstract T getEntity();

    /**
     * Este metodo sirve para crear un nuevo un registro
     *
     */
    public void crear() {

        if (ai != null && entity != null) {
            try {
                ai.crear(entity);
                llenarLista();
                System.out.println("CREAR");
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    /**
     * Este metodo sirve para editar un registro
     *
     */
    public void editar() {
        if (ai != null && entity != null) {
            try {
                ai.editar(entity);
                llenarLista();
                System.out.println("EDITAR");
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    /**
     * Este metodo sirve para eliminar un registro
     *
     */
    public void eliminar() {
        if (ai != null && entity != null) {
            try {
                ai.eliminar(entity);
                llenarLista();
                System.out.println("ELIMINAR");
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    /**
     * Invoca el metodo findAll de la clase abstractFacade que retorna una lista
     * de registros de la base de datos, posteriormente se asigna una lista
     * propiedad de esta clase
     */
    public void llenarLista() {
        if (ai.findAll() != null) {
            this.listaRegistros = ai.findAll();
        } else {
            this.listaRegistros = Collections.EMPTY_LIST;
        }
    }
}