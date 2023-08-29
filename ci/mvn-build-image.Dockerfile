# image used for builds: pre-fill dependencies to speed up maven builds

FROM maven:3.9.3-eclipse-temurin-11

RUN mkdir /tmp/ozgxplanung
ADD . /tmp/ozgxplanung
RUN cd /tmp/ozgxplanung \
	&& mvn test -Pdocker --fail-never -Dtest=notExisting  -Ddependency-check.skip=true \
	&& mvn org.owasp:dependency-check-maven:aggregate -Ddependency-check.skip=false -Dformats=JUNIT \
	&& find $MAVEN_CONFIG/repository -name '*-SNAPSHOT' | xargs rm -rf \
	&& rm -rf /tmp/ozgxplanung