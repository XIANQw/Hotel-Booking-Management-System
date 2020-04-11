DIR_SERV = /Library/Tomcat/webapps/microproject
DIR_SERV_CLASS = $(DIR_SERV)/WEB-INF/classes
DIR_CLASS = target/classes/jar
DIR_STATIC = static

install:
	
	cp -r $(DIR_CLASS) $(DIR_SERV_CLASS)
	cp -r $(DIR_STATIC) $(DIR_SERV)
	cp web.xml $(DIR_SERV)/WEB-INF/

clean:
	rm $(DIR_SERV_CLASS)/jar/*