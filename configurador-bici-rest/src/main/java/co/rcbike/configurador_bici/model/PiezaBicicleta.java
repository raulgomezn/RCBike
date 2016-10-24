package co.rcbike.configurador_bici.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findAll", query = "SELECT p FROM PiezaBicicleta p"),
		@NamedQuery(name = "findByIdConfiguracion", query = "SELECT p FROM PiezaBicicleta p WHERE p.idConfiguracion = :idConfiguracion") })
public class PiezaBicicleta implements Serializable {

	public static final String SQ_findAll = "findAll";
	public static final String SQ_findByIdConfiguracion = "findByIdConfiguracion";
	public static final String SQ_PARAM_ID_CONFIGURACION = "idConfiguracion";
	
	/* No es necesario llenar */
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPiezaBicicleta tipo;

	@NotNull
	@NotEmpty
	private String descripcion;

	/* No es necesario llenar */
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "idConfiguracion")
	//private ConfiguracionBicicleta configuracion;
	private Long idConfiguracion;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPiezaBicicleta getTipo() {
		return tipo;
	}

	public void setTipo(TipoPiezaBicicleta tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdConfiguracion() {
		return idConfiguracion;
	}

	public void setIdConfiguracion(Long idConfiguracion) {
		this.idConfiguracion = idConfiguracion;
	}

}