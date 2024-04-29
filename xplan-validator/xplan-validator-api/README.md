## xPlanValidatorAPI

OpenAPI: #host#/xplan-validator-api/xvalidator/api/v1/

## Examples with curl 

### validation

```
curl -k -H "Content-Type: text/xml" \
  -d @../../xplan-tests/xplan-tests-soapui/src/main/resources/xplan-validator-api/plans/BP_5.1.gml \
  $XPLAN_VALIDATOR_API_HOST/xplan-validator-api/xvalidator/api/v1/validate
```

```
curl -k -H "Content-Type: application/zip" \
  --data-binary @../../xplan-tests/xplan-tests-soapui/src/main/resources/xplan-validator-api/plans/BP_6.0.2_valide.zip \
  $XPLAN_VALIDATOR_API_HOST/xplan-validator-api/xvalidator/api/v1/validate
```