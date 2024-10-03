import java.lang.reflect.Method;

public class ClassModifierTest {

    public static void main(String[] args) {
        // Lista de clases y métodos a verificar
        String[][] targets = {
                { "java.lang.System", "exit" },
                { "java.lang.Runtime", "exec" },
                { "java.io.FileInputStream", "<init>" },
                { "java.io.FileWriter", "<init>" },
                { "java.io.FileReader", "<init>" },
                { "java.io.PrintStream", "<init>" },
                { "java.io.OutputStream", "<init>" }
        };

        for (String[] target : targets) {
            checkMethodRemoval(target[0], target[1]);
        }
    }

    public static void checkMethodRemoval(String className, String methodName) {
        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();
            boolean methodFound = false;

            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    methodFound = true;
                    break;
                }
            }

            if (methodFound) {
                System.out.println("Method " + methodName + " in class " + className + " was NOT removed.");
            } else {
                System.out.println("Method " + methodName + " in class " + className + " was successfully removed.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + className + " not found.");
        } catch (Exception e) {
            System.out.println("An error occurred while checking method removal: " + e.getMessage());
        }
    }
}