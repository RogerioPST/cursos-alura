package br.com.stapait.anotacao;

import java.lang.reflect.Proxy;

public class MainTesteAnotacao {
    public static void main(String[] args) {
		
		  Examples examples = (Examples) Proxy.newProxyInstance(
		  Examples.class.getClassLoader(), new Class[]{Examples.class}, new
		  ExamplesInvocationHandler());
		 

		/*
		 * ExamplesInvocationHandler examplesInvocationHandler = new
		 * ExamplesInvocationHandler(); examplesInvocationHandler.invoke(proxy, method,
		 * args) Examples examples = examplesInvocationHandler.getExamples();
		 */
    	
        examples.thisIsAMethod();
        examples.thisIsAnotherMethod("");
        examples.thisIsALongRunningMethod();
    }
}