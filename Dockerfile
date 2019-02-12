FROM openjdk:11-jre

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/todo/todo.jar"]

# Add the service itself
ADD target/todo-service.jar /usr/share/todo/todo.jar