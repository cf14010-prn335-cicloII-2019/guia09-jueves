/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia07.apirest;

import java.util.ArrayList;
import java.util.List;
import ues.occ.edu.sv.ingenieria.prn335.guia07.apirest.resources.AbstractInterface;

/**
 *
 * @author StevenCastro
 * @param <T>
 */
public abstract class BeanGenerico<T> {

    T e = getEntity();
    List<T> lista = new ArrayList<>();

    public BeanGenerico() {
    }

    public T getE() {
        return e;
    }

    public void setE(T e) {
        this.e = e;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public void crear() {
        if (getfacadelocal() != null) {
            try {
                getfacadelocal().crear(getEntity());
                setE(getEntity());
            } catch (Exception ex) {
                System.out.println("Error" + ex.getMessage());
            }
        }
    }

    public void editar() {
        try {
            getfacadelocal().editar(getEntity());
            System.out.println("Editado con exito");
            setE(getEntity());
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void eliminar() {
        try {
             getfacadelocal().eliminar(getEntity());
            System.out.println("Elimacion satisfactoria");
            setE(getEntity());
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Error a la hora de eliminar los datos.");
        }
    }

    public abstract AbstractInterface<T> getfacadelocal();

    public abstract T getEntity();

}
