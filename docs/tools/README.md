# Entwickler-Tooling fuer 301guru

## Ziel

Mit diesen drei Seiten richtest du OpenCode und Spec Kit in einem frischen Clone
ein und kannst danach direkt im Repo arbeiten.

## Reihenfolge (empfohlen)

1. `docs/tools/opencode.md`
2. `docs/tools/spec-kit.md`
3. Validierung nach `specs/001-developer-tooling-setup/quickstart.md`

## Verbindliches Schrittformat

Jeder Leitfaden nutzt dieselben Bloecke:

1. Voraussetzungen
2. Installation
3. Repo-Setup
4. Konfiguration
5. Verifikation (Erfolg, Fehler, naechster Recovery-Schritt)
6. Troubleshooting

## Wiederholbarkeit

| Typ | Bedeutung |
|---|---|
| `once` | Einmal pro Entwicklerrechner |
| `per-clone` | Pro neuem Repository-Clone |
| `per-feature` | Pro Feature-Start/Iteration |

## Sequenzieller Onboarding-Ablauf

1. Frischen Clone erstellen.
2. OpenCode-Leitfaden komplett ausfuehren.
3. Spec-Kit-Leitfaden komplett ausfuehren.
4. Quickstart-Szenarien 1-3 als End-to-End-Check durchlaufen.

## Skip-/Repeat-Regeln fuer teilweise vorkonfigurierte Repos

- Wenn `opencode --version` und `specify --version` funktionieren, kannst du
  reine Installationsschritte ueberspringen.
- Repo-bezogene Schritte (`AGENTS.md` lesen, `specify init` pruefen,
  Workflow-Verstaendnis) trotzdem ausfuehren.
- Bei Unsicherheit immer Verifikationsblock aus dem jeweiligen Leitfaden laufen
  lassen.

## Gemeinsames Troubleshooting

- **Symptom**: Command nicht gefunden.
  **Recovery**: Installation pruefen, Shell neu starten, PATH pruefen.
- **Symptom**: Auth/BYOK Fehler.
  **Recovery**: Provider-Login/API-Key erneut setzen, Model-Zugriff pruefen.
- **Symptom**: Repo-Kontext fehlt.
  **Recovery**: OpenCode/Speckit im Repo-Root starten, `AGENTS.md` lesen.
- **Eskalation**: Wenn ein Schritt nach 2 Versuchen scheitert, Fehlerausgabe,
  OS und ausgefuehrte Commands sammeln und an das Team weitergeben.

## Abschlusskontrolle (optional)

```bash
mvn -Dtest=UrlServiceUnitTest test
```

Dieser Check bestaetigt die lokale Grundfaehigkeit des Repos, ist aber nicht
Teil des fachlichen Tooling-Setups.
