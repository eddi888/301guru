# Phase 0 Research - Entwickler-Tooling-Setup-Dokumentation

## Entscheidung 1: Dokumentationsstruktur unter `docs/tools/`

- **Decision**: Drei Dokumente als Zielstruktur nutzen:
  1) `docs/tools/opencode.md`,
  2) `docs/tools/spec-kit.md`,
  3) `docs/tools/README.md` als Einstieg und Reihenfolge.
- **Rationale**: Getrennte Leitfaeden reduzieren kognitive Last und machen
  Wiederverwendung einfacher; ein Einstiegspunkt verhindert Navigationsprobleme
  fuer neue Entwickler.
- **Alternatives considered**:
  - Ein monolithisches Dokument: verworfen wegen schlechter Scanbarkeit.
  - Verteilung auf Root-README + Teilseiten: verworfen wegen Kontextwechseln.

## Entscheidung 2: Verifikation als verbindlicher Abschluss pro Werkzeug

- **Decision**: Jeder Leitfaden endet mit einem expliziten Verifikationsblock
  (Befehle, erwartete Ausgabe, typische Fehlerindikatoren).
- **Rationale**: Onboarding ist nur dann belastbar, wenn Erfolg objektiv erkennbar
  ist und nicht vom subjektiven Eindruck abhaengt.
- **Alternatives considered**:
  - Nur "sollte funktionieren"-Hinweise: verworfen, nicht testbar.
  - Zentrale Gesamtverifikation ohne Werkzeugtrennung: verworfen, erschwert
    Fehlerlokalisierung.

## Entscheidung 3: Spec-Kit-Workflow-Darstellung

- **Decision**: Workflow als lineare Kette
  `constitution -> specify -> plan -> tasks -> implement` mit Ziel, Input,
  Output und naechstem Schritt pro Phase dokumentieren.
- **Rationale**: Neue Entwickler verstehen schneller, warum ein Schritt existiert
  und welches Artefakt jeweils entsteht.
- **Alternatives considered**:
  - Nur Command-Liste ohne Kontext: verworfen, da hoher Fehlgebrauchsanteil.
  - Tiefes internals-first Tutorial: verworfen, zu viel Vorwissen erforderlich.

## Entscheidung 4: OpenCode-BYOK-Darstellung

- **Decision**: BYOK als eigener Unterabschnitt mit klaren Voraussetzungen,
  Konfigurationsschritten, Sicherheits-Hinweisen und Verifikation aufnehmen.
- **Rationale**: BYOK ist der haeufigste Stolperstein beim Erstsetup und entscheidet
  direkt ueber Arbeitsfaehigkeit.
- **Alternatives considered**:
  - BYOK im Troubleshooting verstecken: verworfen, zu spaete im Fluss.
  - Nur Link auf externe Dokumentation: verworfen, fehlender Repo-Kontext.

## Entscheidung 5: Scope-Abgrenzung

- **Decision**: In diesem Feature nur Tooling-Dokumentation und Planungsartefakte;
  keine Aenderung an Java-Code, Routen, Security oder DynamoDB-Verhalten.
- **Rationale**: Verfassung fordert Domain- und Datensicherheit; diese Arbeit ist
  ein reines Enablement-Feature.
- **Alternatives considered**:
  - Gleichzeitige Anpassung von Runtime-Konfiguration: verworfen, waere eigenes
    Feature mit hoehren Risiken.
