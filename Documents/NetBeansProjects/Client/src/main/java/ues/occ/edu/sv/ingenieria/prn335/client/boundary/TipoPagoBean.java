/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.client.boundary;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.event.SelectEvent;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Pago;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.TipoPago;

/**
 *
 * @author StevenCastro
 */
@Named(value = "tipoPagoBean")
@ViewScoped
public class TipoPagoBean implements Serializable {

    private List<TipoPago> listaTipoPago;
    Client cliente;
    WebTarget wt;
    Integer first, range;
    private final static String URL = "http://localhost:8080/server/webresource/";
    private Integer id;
    private String tipopago, descripcion;
    private boolean activo;
    TipoPago selectTipoPago = new TipoPago();
    private List<Pago> pagosSelect;

    public TipoPagoBean() {
        this.cliente = ClientBuilder.newClient();
        this.wt = cliente.target(URL);
    }

    /**
     * METODO INIT para cargar los datos de la tabla Pago
     */
    @PostConstruct
    public void init() {
        if (range == null) {
            range = 100;
        }
        if (first == null) {
            first = 0;
        }
        Response response = this.wt.path("tipoPago").queryParam("first", first).queryParam("pagesize", range).request().get();
        listaTipoPago = response.readEntity(new GenericType<List<TipoPago>>() {
        });
        if (response.getStatus() == 404) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay datos o no se establecio la conexion"));
        }
    }

    /**
     * Se  busca el rango que se ingresa
     * @param ae
     */
    public void btnRangeHandler(ActionEvent ae) {
        init();
    }

    /**
     * en este metodo se persiste gracias a que se manda a llamar la entidad REST
     *
     * @param ae Accion de hacer click
     */
    public void btnAgregarHandler(ActionEvent ae) {
        FacesContext context = FacesContext.getCurrentInstance();
        Response respuesta = wt.path("/tipoPago").request().post(Entity.json(new TipoPago(id, tipopago, descripcion, activo)));
        if (respuesta.getStatus() == 201) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "El registro ha sido creado con exito"));
        } else if (respuesta.getStatus() == 500) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El registro no ha sido creado"));
        }
        borrarCampos();
        init();
    }

    /**
     * Se cancelan los procesos.
     *
     * @param ae
     */
    public void btnCancelarHandler(ActionEvent ae) {
        borrarCampos();
        init();
    }

    public void borrarCampos() {
        this.id = null;
        this.tipopago = null;
        this.descripcion = null;
        this.activo = false;
    }

    /**
     * Select event para cuando se seleccione una sucursal muestre la sala
     *
     * @param se
     */
    public void salasSucursal(SelectEvent se) {
        FacesContext context = FacesContext.getCurrentInstance();;
        try {
            Response response = wt.path("sucursal/" + selectTipoPago.getIdTipoPago()+ "/tipoPago").request().get();
            pagosSelect = response.readEntity(new GenericType<List<Pago>>() {
            });
            if (response.getStatus() == 404) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay datos o no se establecio la conexion"));
            }
        } catch (Exception e) {
            pagosSelect = Collections.EMPTY_LIST;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay datos o no se establecio la conexion"));
        }

    }

    public List<TipoPago> getListaTipoPago() {
        return listaTipoPago;
    }

    public void setListaTipoPago(List<TipoPago> listaTipoPago) {
        this.listaTipoPago = listaTipoPago;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public WebTarget getWt() {
        return wt;
    }

    public void setWt(WebTarget wt) {
        this.wt = wt;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TipoPago getSelectTipoPago() {
        return selectTipoPago;
    }

    public void setSelectTipoPago(TipoPago selectTipoPago) {
        this.selectTipoPago = selectTipoPago;
    }

    public List<Pago> getPagosSelect() {
        return pagosSelect;
    }

    public void setPagosSelect(List<Pago> pagosSelect) {
        this.pagosSelect = pagosSelect;
    }

   

}
