FROM nimmis/java-centos:oracle-8-jdk

MAINTAINER prashastsaxena1301@gmail.com

CMD mkdir /apps

COPY target/spring-boot-sample-0.0.1-SNAPSHOT.jar /apps/

CMD ["java", "-Dspring.profiles.active=live", "-jar", "/apps/spring-boot-sample-0.0.1-SNAPSHOT.jar"]

EXPOSE 9999