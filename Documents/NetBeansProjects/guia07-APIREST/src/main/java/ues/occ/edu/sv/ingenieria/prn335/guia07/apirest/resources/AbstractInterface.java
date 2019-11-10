/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia07.apirest.resources;

import java.util.List;

/**
 *
 * @author StevenCastro
 * @param <T>
 */
public interface AbstractInterface<T> {
    void crear(T Entity);
    void eliminar(T Entity);
    void editar(T Entity);
    List<T> findAll();
    List<T> findRange(int inicio, int cuantos);
    T findById(Object id);
    List<T> findLike(String query);
    int count();
}
