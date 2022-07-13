    FROM openjdk:8
    ADD target/MadfoGW-fat.jar MadfoGW-fat.jar
	ENV TZ Asia/Amman
	ENTRYPOINT ["java" , "-jar" , "MadfoGW-fat.jar"]
	EXPOSE 8080