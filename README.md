README
Description
This project contains a tool to modify Java classes using the ASM library. The tool allows removing specific methods from compiled classes (.class).  
Project Structure
src/main/java/ClassModifier.java: Contains the main logic for modifying the classes.
module-info.java: Module file for project compilation.
build.gradle: Gradle configuration file for building the project.
Requirements
JDK 21.0.4
Gradle
ASM Library
Usage
Compile the Project:  Navigate to the project directory and run the following command to compile all classes:  <pre>cd /Users/German/java-base-content/classes /Library/Java/JavaVirtualMachines/jdk-21.0.4.jdk/Contents/Home/bin/javac module-info.java $(find . -name "*.java") </pre>
Modify Classes:  Run the ClassModifier class to remove the specified methods from the target classes:  <pre>java -cp build/classes/java/main ClassModifier </pre>
Create a JMOD File:  Create a java.base.jmod file with the modified classes:  <pre>/Library/Java/JavaVirtualMachines/jdk-21.0.4.jdk/Contents/Home/bin/jmod create --class-path . /Users/German/custom-modules/java.base.jmod </pre>
Example
The following example shows how to remove the exit method from the System class:
String baseDir = "/Users/German/java-base-content/classes/java";
String[][] targets = {
    { baseDir + "/lang/System.class", "exit", "(I)V" }
};


Contributions
Contributions are welcome. Please open an issue or a pull request to discuss any changes.  
License
This project is licensed under the MIT License.