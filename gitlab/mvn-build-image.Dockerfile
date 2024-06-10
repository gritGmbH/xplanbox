# image used for builds: pre-fill dependencies to speed up maven builds

FROM maven:3.9.6-eclipse-temurin-17
ARG BUILD_DATE=?
ARG DOCKER_IMAGE_NAME=?
ARG GIT_REVISION=?
ARG XPLANBOX_VERSION=latest

# see https://github.com/opencontainers/image-spec/blob/main/annotations.md#pre-defined-annotation-keys
LABEL "org.opencontainers.image.created"="$BUILD_DATE" \
	"org.opencontainers.image.description"="" \
	"org.opencontainers.image.licenses"="GNU Affero General Public License" \
	"org.opencontainers.image.ref.name"="$DOCKER_IMAGE_NAME" \
	"org.opencontainers.image.revision"=$GIT_REVISION \
	"org.opencontainers.image.title"="$DOCKER_IMAGE_NAME" \
	"org.opencontainers.image.vendor"="" \
	"org.opencontainers.image.version"=$XPLANBOX_VERSION



RUN mkdir /tmp/ozgxplanung
ADD . /tmp/ozgxplanung
RUN cd /tmp/ozgxplanung \
	&& mvn test -Pdocker --fail-never -Dtest=notExisting  -Ddependency-check.skip=true \
	&& mvn org.owasp:dependency-check-maven:aggregate -Ddependency-check.skip=false -Dformats=JUNIT \
	&& find $MAVEN_CONFIG/repository -name '*-SNAPSHOT' | xargs rm -rf \
	&& rm -rf /tmp/ozgxplanung