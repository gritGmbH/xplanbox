FROM maven:3.9.5-eclipse-temurin-11-alpine
ARG BUILD_DATE=?
ARG DOCKER_IMAGE_NAME=?
ARG GIT_REVISION=?
ARG XPLANBOX_VERSION=latest

# see https://github.com/opencontainers/image-spec/blob/main/annotations.md#pre-defined-annotation-keys
LABEL "org.opencontainers.image.created"="$BUILD_DATE" \
	"org.opencontainers.image.description"="ozgxplanung xPlanBox component" \
	"org.opencontainers.image.licenses"="GNU Affero General Public License & others" \
	"org.opencontainers.image.ref.name"="$DOCKER_IMAGE_NAME" \
	"org.opencontainers.image.revision"="$GIT_REVISION" \
	"org.opencontainers.image.title"="ozgxplanung - $DOCKER_IMAGE_NAME" \
	"org.opencontainers.image.url"="https://gitlab.opencode.de/diplanung/ozgxplanung" \
	"org.opencontainers.image.vendor"="lat/lon GmbH" \
	"org.opencontainers.image.version"="$XPLANBOX_VERSION"

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