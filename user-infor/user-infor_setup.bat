@echo on

start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/user-infor/logs/one G:/his/user-infor/build/libs/user-infor-latest.jar --spring.profiles.active=u1
start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/user-infor/logs/two G:/his/user-infor/build/libs/user-infor-latest.jar --spring.profiles.active=u2
start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/user-infor/logs/three G:/his/user-infor/build/libs/user-infor-latest.jar --spring.profiles.active=u3

@pause


