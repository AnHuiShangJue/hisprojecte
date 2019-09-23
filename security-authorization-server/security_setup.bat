@echo on

start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/security/logs/one G:/his/security/build/libs/security-authorization-server-latest.jar --spring.profiles.active=s1
start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/security/logs/two G:/his/security/build/libs/security-authorization-server-latest.jar --spring.profiles.active=s2

@pause


