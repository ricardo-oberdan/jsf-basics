package com.oberdan.livraria.interceptador;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@SuppressWarnings("serial")
@Transacional
@Interceptor
public class GerenciadorTransacaoInterceptor implements Serializable {

	@Inject
	EntityManager em;

	@AroundInvoke
	public Object executaTransacao(InvocationContext contexto) throws Exception {
		em.getTransaction().begin();

		Object resultado = contexto.proceed();

		em.getTransaction().commit();

		return resultado;
	}

}
