
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClassModifier {

    public static void main(String[] args) {
        // Ruta donde están las clases extraídas
        String baseDir = "/Users/German/java-base-content/classes/java";

        // Lista de clases y métodos a eliminar/Library/Java/JavaVirtualMachines/jdk-21.0.4.jdk/Contents/Home/bin/jlink --module-path /Users/German/custom-modules --add-modules java.base --output /Users/German/custom-jre
        String[][] targets = {
                { baseDir + "/lang/System.class", "exit", "(I)V" },
                { baseDir + "/lang/Runtime.class", "exec", "(Ljava/lang/String;)Ljava/lang/Process;" },
                { baseDir + "/io/FileInputStream.class", "<init>", "(Ljava/lang/String;)V" },
                { baseDir + "/io/FileWriter.class", "<init>", "(Ljava/lang/String;)V" },
                { baseDir + "/io/FileReader.class", "<init>", "(Ljava/lang/String;)V" },
                { baseDir + "/io/PrintStream.class", "<init>", "(Ljava/lang/String;)V" },
                { baseDir + "/io/OutputStream.class", "<init>", "()V" }
        };

        for (String[] target : targets) {
            try {
                modifyClass(target[0], target[1], target[2]);
            } catch (IOException e) {
                System.err.println("Error modifying class " + target[0] + ": " + e.getMessage());
            }
        }
    }

    public static void modifyClass(String inputClass, String methodName, String methodDescriptor) throws IOException {
        byte[] modifiedClass;

        // Lee el bytecode del archivo de clase
        try (FileInputStream fis = new FileInputStream(inputClass)) {
            ClassReader reader = new ClassReader(fis);
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);

            // Usa un visitor para modificar la clase
            ClassVisitor modifier = new RemoveMethodVisitor(writer, methodName, methodDescriptor);
            reader.accept(modifier, 0);

            // Obtiene el nuevo bytecode
            modifiedClass = writer.toByteArray();
        }

        // Ahora escribe el nuevo bytecode a un archivo
        try (FileOutputStream fos = new FileOutputStream(inputClass)) {
            fos.write(modifiedClass);
            System.out.println("Modified: " + inputClass + ", removed method: " + methodName);
        }
    }

    // Elimina un método de la clase
    static class RemoveMethodVisitor extends ClassVisitor {
        private final String methodName;
        private final String methodDescriptor;

        public RemoveMethodVisitor(ClassVisitor cv, String methodName, String methodDescriptor) {
            super(Opcodes.ASM9, cv);
            this.methodName = methodName;
            this.methodDescriptor = methodDescriptor;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            if (name.equals(methodName) && descriptor.equals(methodDescriptor)) {
                System.out.println("Removing method: " + name + " with descriptor: " + descriptor);
                return null; // Omitir el método (lo elimina)
            }
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
    }
}
