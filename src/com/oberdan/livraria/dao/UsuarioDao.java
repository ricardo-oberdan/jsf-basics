package com.oberdan.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.oberdan.livraria.modelo.Usuario;

public class UsuarioDao {

	public boolean existe(Usuario usuario) {
		EntityManager em = JPAUtil.getEntityManager();

		TypedQuery<Usuario> query = em
				.createQuery("select u from Usuario u where u.email = :pEmail and u.senha=:pSenha", Usuario.class);

		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario resultado = query.getSingleResult();
			return resultado != null;
		} catch (Exception e) {
			return false;
		} finally {
			JPAUtil.closeEntityManager(em);
		}

	}

}
