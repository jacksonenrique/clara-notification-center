FROM openjdk:8u111-jre-alpine
MAINTAINER Jackson E. Mosquera R. <jackson.mosquera@clara.team>

COPY build/libs/*.jar app.jar

#ENV SPRING_APPLICATION_JSON='{"spring": {"cloud": {"config": {"server": \
#  {"git": {"uri": "/var/lib/spring-cloud/config-repo", \
#  "clone-on-start": true}}}}}}'
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/app.jar"]
VOLUME /tmp
EXPOSE 8080

# docker build --file=Dockerfile --tag=clara-notification-center:latest --rm=true .
# docker run -d -p 7021:7021 --name clara-notification-center clara-notification-center

# https://runnable.com/docker/binding-docker-ports
