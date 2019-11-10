/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia07.apirest.resources;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author StevenCastro
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    protected abstract EntityManager getEntityManager();
    private Class<T> EntityClass;

    public AbstractFacade(Class<T> EntityClass) {
        this.EntityClass = EntityClass;
    }

    public void crear(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.persist(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminar(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public void editar(T entity) {
        getEntityManager().merge(entity);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(EntityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public T findById(Object id) {
        return getEntityManager().find(EntityClass, id);
    }

    public List<T> findRange(int inicio, int cuantos) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(EntityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(cuantos);
        q.setFirstResult(inicio);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(EntityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    
    public List<T> findLike(String query){
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(EntityClass);
        cq.where(
                getEntityManager().getCriteriaBuilder().like(rt.<String>get("nombre"), "%"+query+"%")
        
        );
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
}
