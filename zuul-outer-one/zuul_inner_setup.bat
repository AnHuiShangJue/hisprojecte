@echo on

start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/zuul-inner/logs/one G:/his/zuul-inner/build/libs/zuul-outer-one-latest.jar --spring.profiles.active=zuul1
start javaw -Dfile.encoding=UTF-8 -jar  -Dlogging.path=G:/his/zuul-inner/logs/two G:/his/zuul-inner/build/libs/zuul-outer-one-latest.jar --spring.profiles.active=zuul2

@pause


