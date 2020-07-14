FROM java:8
ADD chuxi-1.0.jar /app.jar
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-Dspring.profiles.active=test","-jar","/app.jar"]