# XPlanDokumenteAPI TestSuite
## Import Data TestCase
* POST BP 6.0 importPlan
* Property Transfer
## / TestCase
* GET XX X.X XX openAPI
* GET XX X.X XX formatNotAvailable
## /info TestCase
* GET XX X.X XX showConfig
* GET XX X.X XX formatNotAvailable
## /dokument/{planId} TestCase
* GET BP 6.0 pI getFileList
* GET BP 6.0 pI invalidInput
* GET BP 6.0 pI invalidPlanId
## /dokument/{planId}/{fileName} TestCase
* HEAD BP 6.0 pI getHeaderPDF
* HEAD BP 6.0 pI getHeaderPNG
* HEAD BP 6.0 pI getHeaderPGW
* HEAD BP 6.0 pI invalidInput
* HEAD BP 6.0 pI invalidPlanId
* HEAD BP 6.0 pI invalidFileName
* GET BP 6.0 pI getFilePDF
* GET BP 6.0 pI getFilePNG
* GET BP 6.0 pI getFilePGW
* GET BP 6.0 pI invalidInput
* GET BP 6.0 pI invalidPlanId
* GET BP 6.0 pI invalidFileName
## Delete Data TestCase
* DEL BP 6.0 pI deletePlan
## Cleanup Properties
* Property Transfer
