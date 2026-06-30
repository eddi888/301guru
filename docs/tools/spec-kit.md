# GitHub Spec Kit Setup fuer 301guru

Zurueck zum Einstieg: `docs/tools/README.md`

## Voraussetzungen

- OpenCode laeuft bereits im Repo
- `git` installiert
- Schreibrechte im Repo

## Installation (`once`)

```bash
# 1) uv installieren (falls nicht vorhanden)
curl -LsSf https://astral.sh/uv/install.sh | sh

# 2) Spec Kit installieren
uv tool install specify-cli --from git+https://github.com/github/spec-kit.git

# 3) Version pruefen
specify --version
```

## Repo-Setup (`per-clone`)

```bash
# Im Repository-Root ausfuehren
specify init . --integration opencode
```

Erwartetes Ergebnis: `.specify/` und Speckit-Command-Dateien sind vorhanden.

## Workflow (`per-feature`)

Reihenfolge pro Feature:

1. `constitution` - Regeln/Leitplanken festlegen oder aktualisieren
2. `specify` - Problem, Stories, Requirements beschreiben
3. `plan` - Architektur- und Umsetzungsplan erstellen
4. `tasks` - konkrete Taskliste erzeugen
5. `implement` - Aufgaben umsetzen

Typische OpenCode-Kommandos im Repo:

```text
/speckit.constitution
/speckit.specify <feature-beschreibung>
/speckit.plan <kontext>
/speckit.tasks
/speckit.analyze
/speckit.implement
```

## Artefakt-Check je Schritt

- Nach `specify`: `specs/<feature>/spec.md`
- Nach `plan`: `specs/<feature>/plan.md` (+ ggf. research/quickstart)
- Nach `tasks`: `specs/<feature>/tasks.md`
- Nach `analyze`: Konsistenzreport ohne Dateischreibzugriff
- Nach `implement`: Aufgaben in `tasks.md` als `[X]` markiert

## Verifikation (`per-feature`)

1. Ein kleines Beispiel-Feature durchlaufen.
2. Pruefen, ob alle Kernartefakte vorhanden sind.

- **Erfolgssignal**: Der Ablauf ist durchgaengig reproduzierbar, naechster Schritt
  ist immer klar.
- **Fehlersignal**: Fehlende Artefakte oder unklare Schrittuebergaenge.
- **Naechster Recovery-Schritt**: letzten erfolgreichen Schritt wiederholen,
  Artefakt korrigieren, dann erneut fortsetzen.

## Troubleshooting

- **`specify` nicht gefunden**:
  - `uv tool list` pruefen
  - Shell neu starten
- **`specify init` erzeugt nichts**:
  - im Repo-Root ausfuehren
  - Schreibrechte pruefen
- **Workflow driftet**:
  - strikt Reihenfolge `constitution -> specify -> plan -> tasks -> implement`
  - vor Implementierung immer `/speckit.analyze` ausfuehren
