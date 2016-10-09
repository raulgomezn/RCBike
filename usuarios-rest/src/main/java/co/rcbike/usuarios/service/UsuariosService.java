package co.rcbike.usuarios.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import co.rcbike.autenticacion.service.AutenticacionService;
import co.rcbike.usuarios.model.RegistroUsuario;
import co.rcbike.usuarios.model.Usuario;

@Stateless
public class UsuariosService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private AutenticacionService autenticacion;

    public Usuario findUsuario(String email) {
        TypedQuery<Usuario> q = em.createNamedQuery(Usuario.SQ_findByEmail, Usuario.class);
        q.setParameter(Usuario.SQ_PARAM_EMAIL, email);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Listar todos los usuarios del sistema
     * 
     * @return lista con todos los usuarios del sistema (no null)
     */
    public List<Usuario> listUsuario() {
        TypedQuery<Usuario> q = em.createNamedQuery(Usuario.SQ_listByNombre, Usuario.class);
        return q.getResultList();
    }

    public void crearUsuario(RegistroUsuario registroUsuario) {
        Usuario usuario = new Usuario();
        usuario.setEmail(registroUsuario.getEmail());
        usuario.setNombres(registroUsuario.getNombres());
        usuario.setApellidos(registroUsuario.getApellidos());
        usuario.setFoto(registroUsuario.getFoto());
        try {
            em.persist(usuario);
            autenticacion.registrar(registroUsuario.getEmail(), registroUsuario.getClave());
        } catch (Exception e) {
            // TODO usuario ya existe
            e.printStackTrace();
        }
    }

    public void editarUsuario(Usuario usuario) {

    }

    public void agregarAmigo(String emailUsuario, String emailAmigo) {

    }

    public void removerAmigo(String emailUsuario, String emailAmigo) {

    }

    public List<Usuario> listAmigos(String email) {
        List<Usuario> amigos = findUsuario(email).getAmigos();
        amigos.size();
        return amigos;
    }
}
