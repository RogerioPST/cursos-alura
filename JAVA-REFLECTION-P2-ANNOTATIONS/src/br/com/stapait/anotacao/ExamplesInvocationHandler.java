package br.com.stapait.anotacao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class ExamplesInvocationHandler implements InvocationHandler {
    // ******************************
    // Fields
    // ******************************
//    private Examples examples = new ExamplesImpl();
	private Examples examples = new TesteImplementacaoExamples();

	public Examples getExamples() {
		return examples;
	}
    // ******************************
    // Public methods
    // ******************************
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // If the annotation is not present, just redirect the method call to its origin...
        if(!method.isAnnotationPresent(Clocking.class)) {
            return method.invoke(examples, args);
        }

        // ... otherwise log the execution time of it.
        //Instant start = Instant.now();
        long start = System.currentTimeMillis();
        Object returnObj = method.invoke(examples, args);
        //Instant end = Instant.now();

        // TODO: This is for demonstration purpose only and should use the application's logging system.
        long end = System.currentTimeMillis();
        //System.out.println("Method " + method.getName() + " executed in " + Duration.between(end, start) + ".");
        System.out.println("Method " + method.getName() + " executed in " + (end - start) + " ms.");

        return returnObj;
    }

    // ******************************
    // Inner classes
    // ******************************
    private static class ExamplesImpl implements Examples {
        @Override
        public void thisIsAMethod() {
            System.out.println("thisIsAMethod called!");
        }

        @Override
        public void thisIsAnotherMethod(String something) {
            System.out.println("thisIsAnotherMethod called!");
        }

        @Override
        public void thisIsALongRunningMethod() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("thisIsALongRunningMethod called!");
        }
    }
}
