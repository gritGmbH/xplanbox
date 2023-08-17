FROM maven:3.9-eclipse-temurin-11-alpine

RUN apk add py3-pip py3-pillow py3-cffi py3-brotli gcc musl-dev python3-dev pango pango-tools aws-cli
RUN pip install weasyprint

ADD . /xplanbox/xplan-tests/xplan-tests-soapui
ADD maven/xplanbox-pom.xml /xplanbox/pom.xml
ADD maven/xplan-tests-pom.xml /xplanbox/xplan-tests/pom.xml

WORKDIR /xplanbox/xplan-tests/xplan-tests-soapui

# pre-fill local maven repo (including  plugins)
RUN mvn validate \
	&& mvn test -Psystem-tests -fn > /dev/null 2>&1 \ 
	&& mvn surefire-report:report-only \
	&& rm -rf target

	
ENTRYPOINT ["/xplanbox/xplan-tests/xplan-tests-soapui/runAllSoapUiTests.sh"] 