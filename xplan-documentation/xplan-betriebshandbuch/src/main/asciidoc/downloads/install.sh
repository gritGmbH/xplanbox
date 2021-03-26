#!/bin/bash

# CONFIGURATION
#Die Version und ggf. die Pfade muessen vor dem Ausfuehren des Skriptes angepasst werden
VERSION=2.2.1
TOMCAT_SERVICES_ROOT=~/tomcat7-services
TOMCAT_APPLICATIONS_ROOT=~/tomcat7-applications
DEEGREE_DIR=~/.deegree

# No change!
WORK_DIR=`pwd`

################# Cleanup #################
function cleantomcat () {
  rm -rf ${TOMCAT_APPLICATIONS_ROOT}/webapps/xplan-manager-web
  rm -rf ${TOMCAT_APPLICATIONS_ROOT}/webapps/xplan-manager-web.war
  rm -rf ${TOMCAT_APPLICATIONS_ROOT}/webapps/xplan-validator-web
  rm -rf ${TOMCAT_APPLICATIONS_ROOT}/webapps/xplan-validator-web.war
  rm -rf ${TOMCAT_SERVICES_ROOT}/webapps/xplan-wms
  rm -rf ${TOMCAT_SERVICES_ROOT}/webapps/xplan-wms.war
  rm -rf ${TOMCAT_SERVICES_ROOT}/webapps/xplansyn-wfs
  rm -rf ${TOMCAT_SERVICES_ROOT}/webapps/xplansyn-wfs.war
  rm -rf ${TOMCAT_SERVICES_ROOT}/webapps/xplan-wfs
  rm -rf ${TOMCAT_SERVICES_ROOT}/webapps/xplan-wfs.war
  rm -rf ${TOMCAT_APPLICATIONS_ROOT}/webapps/doc/xPlanBox-Benutzerhandbuch.pdf
  rm -rf ${TOMCAT_APPLICATIONS_ROOT}/webapps/doc/xPlanBox-Betriebshandbuch.pdf
  rm -rf ${TOMCAT_APPLICATIONS_ROOT}/webapps/doc/xPlanBox-Benutzerhandbuch
  rm -rf ${TOMCAT_APPLICATIONS_ROOT}/webapps/doc/xPlanBox-Betriebshandbuch
  rm -rf ${TOMCAT_APPLICATIONS_ROOT}/webapps/ROOT*
}

function cleanconfig () {
  rm -rf ${DEEGREE_DIR}/xplan-manager-workspace
  rm -rf ${DEEGREE_DIR}/xplan-wfs-workspace
  rm -rf ${DEEGREE_DIR}/xplansyn-wfs-workspace
  rm -rf ${DEEGREE_DIR}/xplansyn-wms-workspace
  rm -rf ${DEEGREE_DIR}/xplan-manager-config
  rm -rf ${DEEGREE_DIR}/xplan-validator-config
}

# Main function to be called in execution
function clean () {
  cleantomcat
  cleanconfig
}

function copywarstotomcat () {
  cp web/xplan-manager-web.war ${TOMCAT_APPLICATIONS_ROOT}/webapps/xplan-manager-web.war
  cp web/xplan-validator-web.war ${TOMCAT_APPLICATIONS_ROOT}/webapps/xplan-validator-web.war
  cp web/xplan-wms.war ${TOMCAT_SERVICES_ROOT}/webapps/xplan-wms.war
  cp web/xplansyn-wfs.war ${TOMCAT_SERVICES_ROOT}/webapps/xplansyn-wfs.war
  cp web/xplan-wfs.war ${TOMCAT_SERVICES_ROOT}/webapps/xplan-wfs.war
  cp web/xplan-root.war ${TOMCAT_APPLICATIONS_ROOT}/webapps/ROOT.war
}

function unzipdocumentationtotomcat () {
  unzip -d ${TOMCAT_APPLICATIONS_ROOT}/webapps/ ${WORK_DIR}/doc/xPlanBox-Benutzerhandbuch-html.zip
  unzip -d ${TOMCAT_APPLICATIONS_ROOT}/webapps/ ${WORK_DIR}/doc/xPlanBox-Benutzerhandbuch-pdf.zip
  unzip -d ${TOMCAT_APPLICATIONS_ROOT}/webapps/ ${WORK_DIR}/doc/xPlanBox-Betriebshandbuch-html.zip
  unzip -d ${TOMCAT_APPLICATIONS_ROOT}/webapps/ ${WORK_DIR}/doc/xPlanBox-Betriebshandbuch-pdf.zip
}

function installconfiguration () {
  cd ${DEEGREE_DIR} && unzip -o ${WORK_DIR}/config/xplan-manager-workspace-${VERSION}.zip
  cd ${DEEGREE_DIR} && unzip -o ${WORK_DIR}/config/xplan-wfs-workspace-${VERSION}.zip
  cd ${DEEGREE_DIR} && unzip -o ${WORK_DIR}/config/xplansyn-wfs-workspace-${VERSION}.zip
  cd ${DEEGREE_DIR} && unzip -o ${WORK_DIR}/config/xplansyn-wms-workspace-${VERSION}.zip
  cd ${DEEGREE_DIR} && unzip -o ${WORK_DIR}/config/xplan-manager-config-${VERSION}.zip
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
install
