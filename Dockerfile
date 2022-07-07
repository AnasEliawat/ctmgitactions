    FROM openjdk:8
    ADD target/MadfoGW-1.2.1-SNAPSHOT-fat.jar MadfoGW-1.2.1-SNAPSHOT-fat.jar
	ENV TZ Asia/Amman
	ENTRYPOINT ["java" , "-jar" , "MadfoGW-1.2.1-SNAPSHOT-fat.jar"]
	EXPOSE 8080