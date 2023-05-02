# XPlanAPI

## Versionierung der APIs

Die Versionierung der APIs basiert auf dem Konzept von [Semantic Versioning](https://semver.org/).

Das Versionierungsschema für die XPlanManagerAPI und XPlanValidatorAPI sieht folgendermaßen aus:

    ${MAJOR_VERSION}.${MINOR_VERSION}.${BUGFIX_VERSION}

Beispiel:

    1.1.0

In den URLs der beiden APIs werden jeweils nur die Major-Versionen angegeben:

    /xmanager/api/v${MAJOR_VERSION}
    /xvalidator/api/v${MAJOR_VERSION}

Beispiel:

    /xmanager/api/v1
    /xvalidator/api/v1

### Major-Version

Hierunter fallen grundlegende Änderungen an der API:

- Änderungen sind nicht zwingend rückwärtskompatibel.
- Veränderung oder Entfernen von bestehenden Ressourcen.
- Entfernen eines HTTP-Verbs (z.B. POST, PUT, DELETE) einer bestehenden Ressource.
- Veränderung oder Entfernen von Werten von bestehenden Enumerationen.
- Hinzufügen von neuen verpflichtenden Attributen.
- Entfernen von zuvor in der Response verfügbaren Informationen.
- ...

## Minor-Version

Hierunter fallen kleine Änderungen und Ergänzungen an der API:

- Änderungen sind rückwärtskompatibel.
- Hinzufügen neuer Ressourcen.
- Hinzufügen eines HTTP-Verbs (z.B. POST, PUT, DELETE) für eine bestehende Ressource.
- Hinzufügen neuer Werte für Enumeration.
- Hinzufügen von neuen optionalen Attributen.
- Hinzufügen von weiteren in der Response verfügbaren Informationen.
- ...

## Bugfix-Version

Hierunter fallen kosmetische Änderungen an der API:

- Änderungen sind rückwärtskompatibel.
- Anpassungen an "Description" oder "Example".
- Korrektur von Rechtschreibfehlern.
- Korrektur von anderen Fehlern (z.B. falscher Datentyp).
- ...
