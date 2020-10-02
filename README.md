# gateway

This is api gateway service based on Zuul. It's main purpose is to discover services using service discovery like (https://github.com/amasiakiewicz/eureka) and forward requests to those services. It is dockerized service which can be run as standalone container. Teammanager project (https://github.com/amasiakiewicz/teammanager) uses this gateway and contains docker-compose config for it. Prior to use please build it firing command inside cloned directory:
```
./gradlew clean build
```
There is swagger endpoint which presents docs of all discovered services available under:
```
http://localhost:8762/swagger-ui/
```
