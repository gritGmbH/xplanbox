# XPlanManagerAPI TestSuite
## / TestCase
* GET XX X.X XX openAPI
## /info TestCase
* GET XX X.X XX showConfig
## /plan TestCase
* POST BP 4.0 sF,sG,sS importPlanInvalidLaufrichtung
* POST BP 4.1 sF,sS,pf importPlanInvalidProfil
* POST BP 4.1 sF,sS importPlan
* POST BP 4.1 sF,sS importPlanMimeTypeV2
* POST BP 4.1 XX importInvalidPlan
* POST BP 5.0 sF,sG,Ss importPlan
* POST BP 5.0 XX importPlanWithAenderung/Dokument
* POST BP 5.1 sF,sS importPlanInvalidText/XML
* POST BP 5.2 sF importInvalidPlanMultiplePlaene
* POST BP 5.2 sF,sG,Ss importPlanWIthoutBereich
* POST BP 5.2 sF,sL,Ss importPlan
* POST BP 5.2 sF,sL,sS importPlanInvalidLaufrichtung
* POST BP 5.2 sF,Ss importPlanWithTwoBereiche
* POST BP 5.3 XX importPlan
* POST BP 5.3 XX importPlanWithText
* POST BP 5.4 sF,sG,Ss importPlanMultiplePlaene
* POST BP 6.0 XX importPlan
* POST FP 5.0 sF,sG,Ss importPlan
* POST LP 6.0 sF,sG,Ss importPlan
* POST RP 5.1 sF,sG importPlan
* POST SO 5.3 sF,sG,Ss importPlan
* Property Transfer
## /plan/name/{planName} TestCase
* GET BP 4.1 pN currentStatus
## /plans TestCase
* GET BP 4.1 pN exactMatch
* GET BP 4.1 pN lowercaseExactMatch
* GET BP 5.4 pI findById
* GET BP 5.4 pI,pN invalidPlanNameExpectError
## /plan/{planId}/aenderungen/ TestCase
* GET BP 5.0 pI currentStatus
* PUT BP 5.0 pI modifyStatus
* GET BP 5.0 pI verifyStatus
* PUT BP 5.0 pI modifyStatusEmpty
* GET BP 5.0 pI verifyStatusEmpty
* GET FP 5.0 pI currentStatus
* PUT FP 5.0 pI modifyStatus
* GET FP 5.0 pI verifyStatus
* GET LP 6.0 pI currentStatus
* PUT LP 6.0 pI modifyStatus
* GET LP 6.0 pI verifyStatus
* GET RP 5.1 pI currentStatus
* PUT RP 5.1 pI modifyStatus
* GET RP 5.1 pI verifyStatus
* GET SO 5.3 pI currentStatus
* PUT SO 5.3 pI modifyStatus
* GET SO 5.3 pI verifyStatus
## /plan/{planId}/basisdaten/ TestCase
* GET BP 4.1 pI currentStatus
* PUT BP 4.1 pI modifyStatus
* GET BP 4.1 pI verifyStatus
* GET FP 5.0 pI currentStatus
* PUT FP 5.0 pI modifyStatus
* GET FP 5.0 pI verifyStatus
* GET LP 6.0 pI currentStatus
* PUT LP 6.0 pI modifyStatus
* GET LP 6.0 pI verifyStatus
* GET RP 5.1 pI currentStatus
* PUT RP 5.1 pI modifyStatus
* GET RP 5.1 pI verifyStatus
* GET SO 5.3 pI currentStatus
* PUT SO 5.3 pI modifyStatus
* GET SO 5.3 pI verifyStatus
## /plan/{planId}/dokument/ TestCase
* GET BP 5.0 pI currentStatus
* POST BP 5.0 pI modifyStatus
* GET BP 5.0 pI verifyStatus
* POST BP 5.0 pI verifyStatusExpectError
* GET FP 5.0 pI currentStatus
* POST FP 5.0 pI modifyStatus
* GET FP 5.0 pI verifyStatus
* GET LP 6.0 pI currentStatus
* POST LP 6.0 pI modifyStatus
* GET LP 6.0 pI verifyStatus
* GET RP 5.1 pI currentStatus
* POST RP 5.1 pI modifyStatus
* GET RP 5.1 pI verifyStatus
* GET SO 5.3 pI currentStatus
* POST SO 5.3 pI modifyStatus
* GET SO 5.3 pI verifyStatus
## /plan/{planId}/dokument/{id} TestCase
* GET BP 5.3 pI,id currentStatus
* PUT BP 5.3 pI,id modifyStatus
* PUT BP 5.3 pI,id modifyStatusExpectError
* DEL BP 5.3 pI,id modifyStatus
* GET BP 5.3 pI,id verifyStatus
* PUT FP 5.0 pI,id modifyStatus
* GET FP 5.0 pI,id currentStatus
* DEL FP 5.0 pI,id modifyStatus
* GET FP 5.0 pI,id verifyStatusExpectError
* PUT LP 6.0 pI,id modifyStatus
* GET LP 6.0 pI,id currentStatus
* DEL LP 6.0 pI,id modifyStatus
* GET LP 6.0 pI,id verifyStatusExpectError
* PUT RP 5.1 pI,id modifyStatus
* GET RP 5.1 pI,id currentStatus
* DEL RP 5.1 pI,id modifyStatus
* GET RP 5.1 pI,id verifyStatusExpectError
* PUT SO 5.3 pI,id modifyStatus
* GET SO 5.3 pI,id currentStatus
* DEL SO 5.3 pI,id modifyStatus
* GET SO 5.3 pI,id verifyStatusExpectError
## /plan/{planId}/gueltigkeit/ TestCase
* GET BP 4.1 pI currentStatus
* PUT BP 4.1 pI modifyStatus
* GET BP 4.1 pI verifyStatus
* PUT BP 4.1 pI modifyStatusNull
* GET BP 4.1 pI verifyStatusNull
## /plan/{planId}/rasterbasis TestCase
* GET BP 5.3 pI currentStatus
* GET BP 5.2 pI currentStatusWithoutBereich
* POST BP 5.3 pI modifyStatus
* POST BP 5.2 pI modifyStatusWithoutBereichExpectError
* GET BP 5.3 pI verifyStatus
* POST BP 5.3 pI modifyStatusWithoutBereichNummerExpectError
* POST XX X.X XX modifyStatusWithInvalidPlanIdExpectError
* GET BP 5.3 pI currentStatusWithBereiche
* POST BP 5.3 pI modifyStatusWithBereiche
* GET BP 5.3 pI verifyStatusBereiche
* GET FP 5.0 pI currentStatus
* POST FP 5.0 pI modifyStatus
* GET FP 5.0 pI verifyStatus
* GET LP 6.0 pI currentStatus
* POST LP 6.0 pI modifyStatus
* GET LP 6.0 pI verifyStatus
* GET RP 5.1 pI currentStatus
* POST RP 5.1 pI modifyStatus
* GET RP 5.1 pI verifyStatus
* GET SO 5.3 pI currentStatus
* POST SO 5.3 pI modifyStatus
* GET SO 5.3 pI verifyStatus
## /plan/{planId}/rasterbasis/{id} TestCase
* GET BP 5.3 pI,id currentStatus
* PUT BP 5.3 pI,id modifyStatus
* GET BP 5.3 pI,id verifyStatus
* DEL BP 5.3 pI,id modifyStatus
* GET BP 5.3 pI,id verifyStatusExpectError
* GET BP 5.3 pI,id verifyStatusExpectError2
* PUT BP 5.3 pI,id modifyStatusWithoutBereichNummerExpectError
* PUT BP 5.3 pI,id modifyStatusWithInvalidPlanIdExpectError
* GET BP 5.2 pI,id currentStatusWithBereiche
* DEL BP 5.2 pI,id modifyStatusWithBereiche
* GET BP 5.2 pI currentStatusWithBereiche
* PUT BP 5.2 pI,id modifyStatusWithBereiche
* GET BP 5.2 pI,id verifyStatusWithBereicheBereicheExpectError
* GET BP 5.2 pI,id verifyStatusWithBereicheBereicheExpectError2
* PUT FP 5.0 pI,id modifyStatus
* GET FP 5.0 pI,id currentStatus
* DEL FP 5.0 pI,id modifyStatus
* GET FP 5.0 pI,id verifyStatusExpectError
* PUT LP 6.0 pI,id modifyStatus
* GET LP 6.0 pI,id currentStatus
* DEL LP 6.0 pI,id modifyStatus
* GET LP 6.0 pI,id verifyStatusExpectError
* PUT RP 5.1 pI,id modifyStatus
* GET RP 5.1 pI,id currentStatus
* DEL RP 5.1 pI,id modifyStatus
* GET RP 5.1 pI,id verifyStatusExpectError
* PUT SO 5.3 pI,id modifyStatus
* GET SO 5.3 pI,id currentStatus
* DEL SO 5.3 pI,id modifyStatus
* GET SO 5.3 pI,id verifyStatusExpectError
## /plan/{planId}/text/ TestCase
* GET BP 5.3 pI currentStatus
* POST BP 5.3 pI modifyStatus
* GET BP 5.3 pI verifyStatus
* GET BP 5.4 pI currentStatusMultiplePlaene
* GET BP 6.0 pI currentStatus
* POST BP 6.0 pI modifyStatus
* GET BP 6.0 pI verifyStatus
* GET FP 5.0 pI currentStatus
* POST FP 5.0 pI modifyStatus
* GET FP 5.0 pI verifyStatus
* GET LP 6.0 pI currentStatus
* POST LP 6.0 pI modifyStatus
* GET LP 6.0 pI verifyStatus
* GET RP 5.1 pI currentStatus
* POST RP 5.1 pI modifyStatus
* GET RP 5.1 pI verifyStatus
* GET SO 5.3 pI currentStatus
* POST SO 5.3 pI modifyStatus
* GET SO 5.3 pI verifyStatus
* Property Transfer
## /plan/{planId}/text/{id} TestCase
* GET BP 5.3 pI,id currentStatus
* POST BP 5.3 pI,id modifyStatus
* GET BP 5.3 pI,id verifyStatus
* GET BP 5.3 pI,id verifyStatusExpectError
* PUT FP 5.0 pI,id modifyStatus
* GET FP 5.0 pI,id currentStatus
* PUT LP 6.0 pI,id modifyStatus
* GET LP 6.0 pI,id currentStatus
* PUT RP 5.1 pI,id modifyStatus
* GET RP 5.1 pI,id currentStatus
* PUT SO 5.3 pI,id modifyStatus
* GET SO 5.3 pI,id currentStatus
## /plan/{planId} TestCase
* GET BP 4.1 pI currentStatus
* GET BP 4.1 pI CurrentStatusAcceptApplication/XML
* GET BP 4.1 pI CurrentStatusAcceptApplication/ZIP
* GET BP 4.1 pI getById currentStatusInvalidPlanIdExpectError
* DEL BP 4.1 pI deletePlan
* DEL BP 4.1 pI deletePlanMimeTypeV2
* DEL BP 5.0 pI deletePlan
* DEL BP 5.0 pI deletePlanWithAenderung
* DEL BP 5.0 pI deletePlanWithDokument
* DEL BP 5.2 pI deletePlanWithBereiche
* DEL BP 5.2 pI deletePlanWithoutBereich
* DEL BP 5.2 pI deletePlan
* DEL BP 5.3 pI deletePlanWithText
* DEL BP 5.3 pI deletePlan
* DEL BP 5.4 pI deletePlanWithMultiplePlaeneHafen11
* DEL BP 5.4 pI deletePlanWithMultiplePlaeneHafen14
* DEL BP 6.0 pI deletePlan
* DEL FP 5.0 pI deletePlan
* DEL LP 6.0 pI deletePlan
* DEL RP 5.1 pI deletePlan
* DEL SO 5.3 pI deletePlan
# Profiles TestSuite
## /info TestCase
* GET XX X.X XX showConfig
* Property Transfer
## /plan TestCase
* POST BP 4.1 sF,sS,pf importPlanWithProfil
* POST BP 4.1 sF,sS,pf importPlanWithMultipleProfile
* Property Transfer
## /plan/{planId} TestCase
* DEL BP 4.1 pI deletePlanWithProfil
* DEL BP 4.1 pI deletePlanWithMultipleProfile
