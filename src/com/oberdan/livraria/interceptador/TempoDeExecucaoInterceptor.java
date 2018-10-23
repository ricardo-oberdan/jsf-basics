package com.oberdan.livraria.interceptador;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("serial")
@Log
@Interceptor
public class TempoDeExecucaoInterceptor implements Serializable{
	
	@AroundInvoke
	public Object duracaoExecucao(InvocationContext contexto) throws Exception {
		
		long antes = System.currentTimeMillis();
		
		String metodo = contexto.getTarget().getClass().getName() + "/" + contexto.getMethod().getName();
		Object objeto = contexto.proceed();
		
		long depois = System.currentTimeMillis();
		
		System.out.println("Duracao Execucao Metodo " + metodo + ": " + (depois - antes) + "ms");
		
		return objeto;
	}

}
