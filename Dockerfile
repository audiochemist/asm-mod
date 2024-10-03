# Usamos Alpine como base con OpenJDK
FROM openjdk:21-alpine

# Instalamos bash, wget y unzip para gestionar los archivos
RUN apk update && apk add bash wget unzip

# Creamos los directorios de trabajo
WORKDIR /app

# Copiamos el código Java al contenedor
COPY ClassModifier.java /app/ClassModifier.java

# Descargamos la librería ASM y la colocamos en el contenedor
RUN wget https://repo1.maven.org/maven2/org/ow2/asm/asm/9.2/asm-9.2.jar -P /app/libs/
RUN wget https://repo1.maven.org/maven2/org/ow2/asm/asm-commons/9.2/asm-commons-9.2.jar -P /app/libs/

# Compilamos el modificador de clases con ASM
RUN javac -cp "/app/libs/asm-9.2.jar:/app/libs/asm-commons-9.2.jar" /app/ClassModifier.java

# Definimos el directorio donde estarán los módulos extraídos y modificados
RUN mkdir -p /app/java-base-content

# Mantiene el contenedor activo para usarlo en modo interactivo
CMD ["/bin/bash"]
