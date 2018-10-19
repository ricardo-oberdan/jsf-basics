package com.oberdan.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.oberdan.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		//Preciso recuperar a arvore de componentes
		FacesContext context = event.getFacesContext();
		
		String nomePagina = context.getViewRoot().getViewId();
		
		if("/login.xhtml".equals(nomePagina)) {
			return;
		}
		
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(usuarioLogado != null) {
			return;
		}
		
		//redirecionamento para o login
		
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login?faces-redirect=true");
		context.renderResponse();
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {		
	}

	@Override
	public PhaseId getPhaseId() {
		//Só executar na primeira fase do ciclo de vida
		return PhaseId.RESTORE_VIEW;
	}

}
