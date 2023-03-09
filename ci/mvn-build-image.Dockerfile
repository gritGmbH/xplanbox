# image used for builds: pre-fill dependencies to speed up maven builds

FROM maven:3.8.6-jdk-11

RUN mkdir /tmp/ozgxplanung
ADD . /tmp/ozgxplanung
RUN cd /tmp/ozgxplanung \
	&& mvn test -Pdocker --fail-never -Dtest=notExisting  -Dskip.owasp.check=true \
	&& mvn org.owasp:dependency-check-maven:aggregate -Dskip.owasp.check=false -Dformats=JUNIT -Dskip.owasp.check=false \
	&& find $MAVEN_CONFIG/repository -name '*-SNAPSHOT' | xargs rm -rf \
	&& rm -rf /tmp/ozgxplanung