package com.oberdan.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.oberdan.livraria.dao.UsuarioDao;
import com.oberdan.livraria.modelo.Usuario;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class LoginBean implements Serializable {

	Usuario usuario = new Usuario();
	
	@Inject
	UsuarioDao usuarioDao;
	
	@Inject
	FacesContext facesContext;

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin () {
		System.out.println("Efetuando login: " + usuario.getEmail());
		
		if (usuarioDao.existe(this.usuario)) {
			//Gravar usuario logado na sessao
			facesContext.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			
			return "livro?faces-redirect=true";			
		}

		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, new FacesMessage("Usuário ou senha inválida"));
		return "login?faces-redirect=true";
	}
	
	public String logout() {
		facesContext.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		return "login?faces-redirect=true";
	}
}
