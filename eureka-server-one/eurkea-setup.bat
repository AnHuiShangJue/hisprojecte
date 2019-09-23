@echo on

start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/eureka-server/logs/one G:/his/eureka-server/build/libs/eureka-server-one-latest.jar --spring.profiles.active=e1
start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/eureka-server/logs/two G:/his/eureka-server/build/libs/eureka-server-one-latest.jar --spring.profiles.active=e2

@pause

