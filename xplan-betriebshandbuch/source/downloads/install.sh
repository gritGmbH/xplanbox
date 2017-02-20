#!/bin/bash

# CONFIGURATION
#Die Version und ggf. die Pfade muessen vor dem Ausfuehren des Skriptes angepasst werden
VERSION=2.2.1
DEEGEE_DIR=~/.deegree
TOMCAT_ROOT=~/tomcat7
TOMCAT_PORTALS_ROOT=~/tomcat7-portals

# No change!
WORK_DIR=`pwd`

################# Cleanup #################
function cleantomcat () {
  rm -rf ${TOMCAT_ROOT}/webapps/xplanmanager
  rm -rf ${TOMCAT_ROOT}/webapps/xplanmanager.war
  rm -rf ${TOMCAT_ROOT}/webapps/xplanvalidator
  rm -rf ${TOMCAT_ROOT}/webapps/xplanvalidator.war
  rm -rf ${TOMCAT_ROOT}/webapps/xplan-wms
  rm -rf ${TOMCAT_ROOT}/webapps/xplan-wms.war
  rm -rf ${TOMCAT_ROOT}/webapps/xplansyn-wfs
  rm -rf ${TOMCAT_ROOT}/webapps/xplansyn-wfs.war
  rm -rf ${TOMCAT_ROOT}/webapps/xplan-wfs
  rm -rf ${TOMCAT_ROOT}/webapps/xplan-wfs.war
  rm -rf ${TOMCAT_ROOT}/webapps/userdocumentation
  rm -rf ${TOMCAT_ROOT}/webapps/operationmanual
  rm -rf ${TOMCAT_ROOT}/webapps/ROOT*
  rm -rf ${TOMCAT_PORTALS_ROOT}/webapps/portal-bplan
  rm -rf ${TOMCAT_PORTALS_ROOT}/webapps/portal-bplan.war
  rm -rf ${TOMCAT_PORTALS_ROOT}/webapps/portal-fplan
  rm -rf ${TOMCAT_PORTALS_ROOT}/webapps/portal-fplan.war
  rm -rf ${TOMCAT_PORTALS_ROOT}/webapps/portal-lplan
  rm -rf ${TOMCAT_PORTALS_ROOT}/webapps/portal-lplan.war
}

function cleanconfig () {
  rm -rf ${DEEGEE_DIR}/xplan-manager-workspace
  rm -rf ${DEEGEE_DIR}/xplan-wfs-workspace
  rm -rf ${DEEGEE_DIR}/xplansyn-wfs-workspace
  rm -rf ${DEEGEE_DIR}/xplansyn-wms-workspace
  rm -rf ${DEEGEE_DIR}/portal-configuration
  rm -rf ${DEEGEE_DIR}/manager-configuration
}

# Main function to be called in execution
function clean () {
  cleantomcat
  cleanconfig
}

################# Prepare #################
function setupconfigurationfolders () {
  mkdir -p ${DEEGEE_DIR}/portal-configuration
}

# Main function to be called in execution
function prepare () {
  setupconfigurationfolders
}

function copywarstotomcat () {
  cp web/xplanmanager.war ${TOMCAT_ROOT}/webapps/xplanmanager.war
  cp web/xplanvalidator.war ${TOMCAT_ROOT}/webapps/xplanvalidator.war
  cp web/xplan-wms.war ${TOMCAT_ROOT}/webapps/xplan-wms.war
  cp web/xplansyn-wfs.war ${TOMCAT_ROOT}/webapps/xplansyn-wfs.war
  cp web/xplan-wfs.war ${TOMCAT_ROOT}/webapps/xplan-wfs.war
  cp web/xplan-root.war ${TOMCAT_ROOT}/webapps/ROOT.war
  cp web/portal-bplan.war ${TOMCAT_PORTALS_ROOT}/webapps/portal-bplan.war
  cp web/portal-fplan.war ${TOMCAT_PORTALS_ROOT}/webapps/portal-fplan.war
  cp web/portal-lplan.war ${TOMCAT_PORTALS_ROOT}/webapps/portal-lplan.war
}

function unzipdocumentationtotomcat () {
  unzip -d ${TOMCAT_ROOT}/webapps/doc/benutzerdokumentation.zip
  unzip -d ${TOMCAT_ROOT}/webapps/doc/betriebshandbuch.zip
}

function installconfiguration () {
  cd ${DEEGEE_DIR} && unzip -o ${WORK_DIR}/config/xplan-manager-workspace-${VERSION}.zip
  cd ${DEEGEE_DIR} && unzip -o ${WORK_DIR}/config/xplan-wfs-workspace-${VERSION}.zip
  cd ${DEEGEE_DIR} && unzip -o ${WORK_DIR}/config/xplansyn-wfs-workspace-${VERSION}.zip
  cd ${DEEGEE_DIR} && unzip -o ${WORK_DIR}/config/xplansyn-wms-workspace-${VERSION}.zip
  cd ${DEEGEE_DIR} && unzip -o ${WORK_DIR}/config/xplan-manager-config-${VERSION}.zip
  cp ${WORK_DIR}/config/xplan-bplan-config-${VERSION}.jar ${DEEGEE_DIR}/portal-configuration/xplan-bplan-config.jar
  cp ${WORK_DIR}/config/xplan-fplan-config-${VERSION}.jar ${DEEGEE_DIR}/portal-configuration/xplan-fplan-config.jar
  cp ${WORK_DIR}/config/xplan-lplan-config-${VERSION}.jar ${DEEGEE_DIR}/portal-configuration/xplan-lplan-config.jar
}

function unzipclis () {
  unzip -d ${WORK_DIR}/cli/ ${WORK_DIR}/cli/xplan-manager-cli.zip
  unzip -d ${WORK_DIR}/cli/ ${WORK_DIR}/cli/xplan-validator-cli.zip
}

# Main function to be called in execution
function install () {
  copywarstotomcat
  unzipdocumentationtotomcat
  installconfiguration
  unzipclis
}

####################
####  EXECUTION ####
####################
clean
prepare
install
