# XPlanValidatorAPI TestSuite
## / TestCase
* GET XX X.X XX openAPI
## /info TestCase
* GET XX X.X XX showConfig
## /validate TestCase
* POST BP 5.1 XX validatePlan
* POST BP 5.1 sF validatePlan
* POST BP 5.1 sG validatePlan
* POST BP 5.1 sGeo validatePlan
* POST BP 5.1 sS validatePlan
* POST BP 5.1 nV validatePlan
* POST BP 5.1 nV,sF,sG,sGeo,sS validatePlan
* POST BP 5.1 XX validatePlanRequestBodyText/XML
* POST BP 5.1 XX validatePlanRequestBodyApplication/GML+XML
* POST BP 5.1 XX validatePlanRequestBodyApplication/ZIP
* POST BP 5.1 XX validatePlanRequestBodyApplication/ZIPExpectError
* POST BP 5.1 XX validatePlanAcceptHeaderApplication/JSON
* POST BP 5.1 XX validatePlanAcceptHeaderApplication/XML
* POST BP 5.1 XX validatePlanAcceptHeaderText/XML
* POST BP 5.1 XX validatePlanAcceptHeaderApplication/PDF
* POST BP 5.1 XX validatePlanAcceptHeaderApplication/ZIP
* POST BP 5.3 XX validatePlan
* POST BP 6.0 sGeo validatePlan
* POST BP 6.0 XX validatePlanExpectError
* POST BP 6.0 pf validatePlanExpectError
# Profiles TestSuite
## /info TestCase
* GET XX X.X XX showConfig
* Property Transfer
## /validate TestCase
* POST BP 6.0 pf validatePlanWithProfil
* POST BP 6.0 pf validatePlanWithMultipleProfile
