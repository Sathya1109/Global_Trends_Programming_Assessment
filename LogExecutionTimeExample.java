import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

// Rename the file to LogExecutionTimeExample.java
public class LogExecutionTimeExample {

    // Step 1: Define the Annotation
    @Target(ElementType.METHOD) // Annotation applicable to methods
    @Retention(RetentionPolicy.RUNTIME) // Annotation retained at runtime
    public @interface LogExecutionTime {
    }

    // Step 2: Implement the Annotation Processor
    public static class LogExecutionTimeProcessor {

        public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            // Example usage with user input
            MyClass obj = new MyClass();

            // Invoke method annotated with @LogExecutionTime
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(LogExecutionTime.class)) {
                    long startTime = System.nanoTime();
                    method.invoke(obj); // Invoke the method
                    long endTime = System.nanoTime();
                    long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime); // Milliseconds
                    System.out.println("Execution of " + method.getName() + " took " + duration + " ms");
                }
            }
        }
    }

    // Step 3: Example Class with Annotated Method
    public static class MyClass {
        @LogExecutionTime
        public void methodToBeLogged() {
            // Simulate some task
            for (int i = 0; i < 1000000; i++) {
                // Do some processing
            }
        }

        public void anotherMethod() {
            // Method without @LogExecutionTime annotation
        }
    }

    // Step 4: Main Method to Run the Example
    public static void main(String[] args) {
        try {
            LogExecutionTimeProcessor.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
