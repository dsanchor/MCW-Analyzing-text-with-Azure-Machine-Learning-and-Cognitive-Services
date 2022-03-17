# Example using MS Build of OpenJDK image directly
FROM mcr.microsoft.com/openjdk/jdk:11-ubuntu

# Continue with your application deployment
RUN mkdir /opt/app
COPY *.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]