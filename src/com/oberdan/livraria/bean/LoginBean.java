package com.oberdan.livraria.bean;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.oberdan.livraria.dao.DAO;
import com.oberdan.livraria.dao.UsuarioDao;
import com.oberdan.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin () {
		System.out.println("Efetuando login: " + usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (new UsuarioDao().existe(this.usuario)) {
			//Gravar usuario logado na sessao
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			
			return "livro?faces-redirect=true";			
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário ou senha inválida"));
		return "login?faces-redirect=true";
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
		
		return "login?faces-redirect=true";
	}
}
