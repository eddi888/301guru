# Implementation Plan: Entwickler-Tooling-Setup-Dokumentation

**Branch**: `master` | **Date**: 2026-06-27 | **Spec**: `specs/001-developer-tooling-setup/spec.md`

**Input**: Feature specification from `/specs/001-developer-tooling-setup/spec.md`

**Note**: This template is filled in by the `/speckit.plan` command. See `.specify/templates/plan-template.md` for the execution workflow.

## Summary

Wir erstellen unter `docs/tools/` eine Schritt-fuer-Schritt-Dokumentation, mit der
ein neuer Entwickler OpenCode (inkl. BYOK und Repo-spezifischer `AGENTS.md`-Nutzung)
und GitHub Spec Kit (inkl. `specify init` und dem Workflow
`constitution -> specify -> plan -> tasks -> implement`) ohne Vorwissen lokal
betriebsbereit bekommt. Die Umsetzung fokussiert auf klar trennbare Leitfaeden,
verifizierbare Abschlusschecks pro Werkzeug und Troubleshooting fuer typische
Setup-Fehler.

## Technical Context

<!--
  ACTION REQUIRED: Replace the content in this section with the technical details
  for the project. The structure here is presented in advisory capacity to guide
  the iteration process.
-->

**Language/Version**: Markdown-Dokumentation im bestehenden Java-8/Spring-Boot-1.5.2-Repo

**Primary Dependencies**: OpenCode CLI (BYOK-faehig), GitHub Spec Kit CLI, Git, Maven, Docker

**Storage**: Dateibasiert (`docs/tools/`, `.specify/`, `specs/001-developer-tooling-setup/`)

**Testing**: Dokumentations-Validierung ueber reproduzierbare CLI-Verifikationsschritte;
minimaler Projekt-Check via `mvn -Dtest=UrlServiceUnitTest test`

**Target Platform**: Lokale Entwicklerumgebung (Linux/macOS/WSL) fuer Terminal-basiertes Arbeiten

**Project Type**: Dokumentations- und Onboarding-Feature fuer bestehendes Web-Service-Repository

**Performance Goals**: Erstsetup eines neuen Entwicklers in <= 60 Minuten; klarer Erfolgspfad ohne Rueckfragen

**Constraints**: Keine Aenderung an produktivem URL-Flow; keine API-/DynamoDB-Schema-Aenderungen;
Schritte muessen ohne implizites Teamwissen reproduzierbar sein

**Scale/Scope**: Zwei Hauptleitfaeden (OpenCode, Spec Kit), je Installation/Setup/Konfiguration/Verifikation,
plus gemeinsames Troubleshooting und Wiederholungslogik (einmalig vs. pro Feature)

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- [x] Domain-First: Kein Eingriff in `UrlController -> UrlService -> UrlRepository -> Url`.
- [x] API/Routing: Keine Aenderung an Endpunkten oder Sicherheitsgrenzen.
- [x] Verification Depth: Dokumentations-Setup wird per Verifikationsschritte geprueft;
      `mvn -Dtest=UrlServiceUnitTest test` wird als optionaler Repo-Sanity-Check dokumentiert.
- [x] DynamoDB Safety: Keine Aenderung an Tabellen, Region oder Credential-Keys.
- [x] Operational Reproducibility: Build-/Run-Pfade bleiben unveraendert; Doku beschreibt
      reproduzierbare lokale Tooling-Einrichtung.

**Gate Status (pre-design)**: PASS

## Project Structure

### Documentation (this feature)

```text
specs/001-developer-tooling-setup/
├── plan.md              # This file (/speckit.plan command output)
├── research.md          # Phase 0 output (/speckit.plan command)
├── quickstart.md        # Phase 1 output (/speckit.plan command)
└── tasks.md             # Phase 2 output (/speckit.tasks command - NOT created by /speckit.plan)
```

### Source Code (repository root)
<!--
  ACTION REQUIRED: Replace the placeholder tree below with the concrete layout
  for this feature. Delete unused options and expand the chosen structure with
  real paths (e.g., apps/admin, packages/something). The delivered plan must
  not include Option labels.
-->

```text
src/
└── main/java/guru/threezeroone/
    ├── url/
    └── webuser/

docs/
└── tools/
    ├── opencode.md
    ├── spec-kit.md
    └── README.md (neu geplant als Einstieg)

.specify/
├── memory/constitution.md
└── templates/
```

**Structure Decision**: Single-project-Struktur bleibt erhalten. Diese Arbeit
liefert Dokumentationsaenderungen unter `docs/tools/` und Planungsartefakte unter
`specs/001-developer-tooling-setup/`.

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

Keine Verstosse gegen die Constitution identifiziert.

## Phase 0 - Research Output

Research-Ergebnisse sind in `specs/001-developer-tooling-setup/research.md`
dokumentiert und alle Plan-Unsicherheiten aufgeloest.

## Phase 1 - Design Output

- Validierungsleitfaden: `specs/001-developer-tooling-setup/quickstart.md`

Hinweis: `data-model.md` und `contracts/` entfallen, da dieses Feature reine
Dokumentation ohne persistierte Daten oder externe Schnittstellen liefert.

## Constitution Check (Post-Design)

- [x] Domain-First: Design bleibt auf Dokumentations- und Onboarding-Ebene.
- [x] API/Routing: Keine API-/Routing-Aenderung vorgesehen.
- [x] Verification Depth: Pro Werkzeug klarer Validierungspfad im Quickstart.
- [x] DynamoDB Safety: Keine Datenpersistenz-Aenderung.
- [x] Operational Reproducibility: Wiederholbare Setup-Schritte und erwartete
      Erfolgsindikatoren sind als Vertrag und Quickstart definiert.

**Gate Status (post-design)**: PASS
