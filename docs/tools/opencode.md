# OpenCode Setup fuer 301guru

Zurueck zum Einstieg: `docs/tools/README.md`

## Voraussetzungen

- Frischer Clone des Repos
- Terminal mit `git`, `mvn`, `docker`
- API-Key/BYOK-Zugang fuer den gewuenschten Modell-Provider

## Installation (`once`)

1. OpenCode lokal installieren (offizieller Team-/Installer-Pfad).
2. Sicherstellen, dass `opencode` im PATH liegt (typisch: `$HOME/.opencode/bin`).
3. Version pruefen:

```bash
opencode --version
```

## Repo-Setup (`per-clone`)

1. Ins Repo wechseln.
2. `AGENTS.md` lesen (Projektregeln, Sprache, wichtige Kommandos).
3. OpenCode im Repo starten.

```bash
opencode .
```

## BYOK-Konfiguration (`once`, bei Key-Rotation erneut)

1. Provider konfigurieren:

```bash
opencode providers
```

2. Gewuenschtes Modell waehlen (im OpenCode-UI oder CLI).
3. Optional verfuegbare Modelle pruefen:

```bash
opencode models
```

## Verifikation (`per-clone`)

1. OpenCode im Repo starten.
2. Kurze Anfrage im Repo-Kontext ausfuehren:

```bash
opencode run "Lies AGENTS.md und nenne die wichtigsten Regeln in 5 Punkten."
```

- **Erfolgssignal**: Antwort referenziert konkrete Repo-Regeln aus `AGENTS.md`.
- **Fehlersignal**: Auth-/Model-Fehler oder fehlender Repo-Kontext.
- **Naechster Recovery-Schritt**: `opencode providers` erneut ausfuehren,
  Modell neu waehlen, danach Verifikation wiederholen.

## Troubleshooting

- **Installationsproblem**: `opencode` nicht gefunden.
  - PATH auf `$HOME/.opencode/bin` pruefen
  - Shell neu starten
- **BYOK/Auth-Problem**: Modellaufruf scheitert.
  - API-Key/Provider in `opencode providers` neu setzen
  - mit `opencode models` pruefen, ob Modell erreichbar ist
- **Kontextproblem**: Antwort ohne Repo-Bezug.
  - im Repo-Root starten: `opencode .`
  - Anfrage explizit auf `AGENTS.md` beziehen
