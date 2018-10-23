package com.oberdan.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.oberdan.livraria.modelo.Usuario;

@SuppressWarnings("serial")
public class UsuarioDao implements Serializable {

	@Inject
	private EntityManager em;

	public boolean existe(Usuario usuario) {

		TypedQuery<Usuario> query = em
				.createQuery("select u from Usuario u where u.email = :pEmail and u.senha=:pSenha", Usuario.class);

		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario resultado = query.getSingleResult();
			return resultado != null;
		} catch (Exception e) {
			return false;
		}
	}

}
