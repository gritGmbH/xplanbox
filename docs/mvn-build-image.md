# Docker Maven Build Image

Um die ozgxplanung Builds zu beschleunigen wird ein Build Image verwendet, das schon die meisten benötigten Maven Artefekate enthält, die für das Build benötigt werden. Dieses Image sollte regelmäßig neu erzeugt werden, sodass es synchron zu den benötigten Dependencies bleibt (oder fast). Mit diesem Image ist das normale Maven Build bei den ersten Versuchen eine bis zwei Minuten schneller und das OWASP Dependency Checks Build mehr als 6 Minuten schneller.

Die automatische Erzeugung auf OpenCoDE klappt noch nicht (Speicherprobleme).

## Manuelle Erzeugung

### Docker Image lokal bauen

```
docker build -t registry.opencode.de/diplanung/ozgxplanung/mvn-build-image:latest -f ci/mvn-build-image.Dockerfile .
```

### GitLab personal access token erstellen

Auf OpenCoDE unter [User Settings / Access Tokens](https://gitlab.opencode.de/-/profile/personal_access_tokens) ein neues Personal Access Token mit `read_registry` and `write_registry` Scopes erzeugen.

### Im OpenCoDE docker registry einloggen

Mit dem üblichen Benutzername und das _personal access token_ als Passwort:

```
docker login registry.opencode.de
```

### Image pushen

```
docker push registry.opencode.de/diplanung/ozgxplanung/mvn-build-image:latest
```
