@echo on

start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/his-core/build/logs/one G:/his/his-core/build/libs/his-core-latest.jar --spring.profiles.active=core1
start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/his-core/build/logs/two G:/his/his-core/build/libs/his-core-latest.jar --spring.profiles.active=core2
start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/his-core/build/logs/three G:/his/his-core/build/libs/his-core-latest.jar --spring.profiles.active=core3

@pause



