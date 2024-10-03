## README

### Description

This project contains a tool to modify Java classes using the ASM library. The tool allows removing specific methods from compiled classes (`.class`).

### Project Structure

- `src/main/java/ClassModifier.java`: Contains the main logic for modifying the classes.
- `module-info.java`: Module file for project compilation.
- `build.gradle`: Gradle configuration file for building the project.

### Requirements

- JDK 21.0.4
- Gradle
- ASM Library

### Usage

1. **Compile the Project:**

   Navigate to the project directory and run the following command to compile all classes:

   ```sh
   cd /path/to/your/java-base-content/classes
   /Library/Java/JavaVirtualMachines/jdk-21.0.4.jdk/Contents/Home/bin/javac module-info.java $(find . -name "*.java")censed under the MIT License.