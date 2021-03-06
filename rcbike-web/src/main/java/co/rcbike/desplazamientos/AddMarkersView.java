package co.rcbike.desplazamientos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import co.rcbike.autenticacion.AutenticacionManager;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
public class AddMarkersView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private MapModel emptyModel;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private double lat;

    @Getter
    @Setter
    private double lng;

    @Getter
    @Setter
    @ManagedProperty(value = "#{mapaManager}")
    private MapaManager mapaManager;

    @Getter
    private List<Marker> puntosRuta = new ArrayList<>();

    @Inject
    private DesplazamientosGateway gateway;

    @PostConstruct
    public void init() {
        this.title = "";
        emptyModel = new DefaultMapModel();
    }

    public void addMarker() {
        if (puntosRuta.size() < 2) {
            Marker marker = new Marker(new LatLng(lat, lng), title);
            if (mapaManager.getOrigen() == null)
                mapaManager.setOrigen(marker);
            else
                mapaManager.setDestino(marker);

            puntosRuta.add(marker);
            emptyModel.addOverlay(marker);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Marcador Agredado", "Latitud:" + lat + ", Longitud:" + lng));
        }
    }

    public void unirse(long id) {
        gateway.guardarParticipante(id, AutenticacionManager.emailAutenticado());
    }
}