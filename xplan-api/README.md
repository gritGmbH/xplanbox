# Beschreibung der Schnittstellen von XPlanManagerAPI und XPlanValidatorAPI

Änderungen an den REST-Schnittstellen der Komponenten XPlanManagerAPI und XPlanValidatorAPI werden über Versionsnummern abgebildet.  

## Aufbau der Versionsnummer

Die Versionierung der API basiert auf dem Konzept von [Semantic Versioning](https://semver.org/).

Die Versionsnummer für die XPlanManagerAPI und XPlanValidatorAPI setzt sich aus folgenden Teilen zusammen:

    MAJOR_VERSION.MINOR_VERSION.BUGFIX_VERSION

Beispiel:

    1.1.0
    | | +-- Bugfix-Version (Revisionsnummer)
    | +---- Minor-Version (Nebenversionsnummer)
    +------ Major-Version (Hauptversionsnummer)

In den Ressourcenpfaden der beiden Komponenten wird nur die Major-Version angegeben:

    /xmanager/api/v1
    /xvalidator/api/v1

In den OpenAPI-Dokumenten wird die vollständige Versionsnummer im Element `version` ausgegeben:

```json
"info": {
...
"version": "1.1.0"
}
```

### Änderungen in einer Major-Version

Mit Einführung einer neuen Major-Version wird ein neuer Ressourcenpfad mit z. B. `/api/v2` eingeführt.
Änderungen an der API, die nicht abwärts-/rückwärtskompatibel sind, können sein:

- Entfernen von bestehenden Ressourcen
- Entfernen eines HTTP-Verbs (z.B. POST, PUT, DELETE) zu einer bestehenden Ressource
- Entfernen von Werten von bestehenden Enumerationen
- Entfernen von zuvor verfügbaren Typen
- Entfernen von Datentypen, die in einer vorherigen Version als deprecated markiert worden sind
- Entfernen von Content-Types
- Hinzufügen von neuen verpflichtenden Attributen
- Ändern eines Datentyps
- Ändern einer "operationId"


## Änderungen in einer Minor-Version

Hierunter fallen abwärts-/rückwärtskompatible Änderungen und Ergänzungen an der API:

- Hinzufügen neuer Ressourcen
- Hinzufügen eines HTTP-Verbs (z.B. POST, PUT, DELETE) für eine bestehende Ressource
- Hinzufügen neuer Werte für Enumerationen
- Hinzufügen von neuen optionalen Parametern
- Hinzufügen von neuen optionalen Attributen
- Hinzufügen von neuen Content-Types
- Hinzufügen eines neuen Datentyps (Schema)
- Hinzufügen eines neuen Properties zu einem Datentyp (Schema)
- Hinzufügen weiterer Status-Codes zu einer Antwort
- Hinzufügen von weiteren Informationen

## Änderungen in einer Bugfix-Version

Hierunter fallen abwärts-/rückwärtskompatible Änderungen an der API:

- Hinzufügen, Entfernen und Ändern von "Summary", "Description" oder "Example" Elementen
- Hinzufügen, Entfernen und Ändern von Tags
- Korrektur von Tipp- und Rechtschreibfehlern im OpenAPI-Dokument
