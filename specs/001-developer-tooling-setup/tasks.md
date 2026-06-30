# Tasks: Entwickler-Tooling-Setup-Dokumentation

**Input**: Design documents from `/specs/001-developer-tooling-setup/`

**Prerequisites**: `plan.md` (required), `spec.md` (required), `research.md`, `quickstart.md`

**Tests**: Fuer dieses Doku-Feature keine automatisierten Code-Tests; Verifikation erfolgt ueber die unabhaengigen Szenarien in `specs/001-developer-tooling-setup/quickstart.md`.

**Organization**: Tasks sind pro User Story gruppiert, damit jede Story unabhaengig umgesetzt und geprueft werden kann.

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Ausgangslage und Doku-Einstieg vorbereiten

- [X] T001 Bestehende Inhalte in `docs/tools/opencode.md` und `docs/tools/spec-kit.md` gegen Anforderungen aus `specs/001-developer-tooling-setup/spec.md` abgleichen
- [X] T002 Neue Einstiegsseite `docs/tools/README.md` mit Ziel, Zielgruppe und Navigationsstruktur anlegen
- [X] T003 Strukturvorgaben (Abschnitte, Notation, Wiederholbarkeit) in `docs/tools/README.md` festlegen

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Gemeinsame Leitplanken, die alle Stories brauchen

**⚠️ CRITICAL**: Keine Story-Arbeit vor Abschluss dieser Phase

- [X] T004 Einheitliches Schrittformat (Voraussetzungen, Installation, Repo-Setup, Konfiguration, Verifikation, Troubleshooting) in `docs/tools/README.md` verbindlich definieren
- [X] T005 Matrix fuer Wiederholbarkeit (`once`, `per-clone`, `per-feature`) in `docs/tools/README.md` aufnehmen
- [X] T006 Verifikationskriterien aus `specs/001-developer-tooling-setup/quickstart.md` als Referenzrahmen in `docs/tools/README.md` verankern

**Checkpoint**: Foundation fertig - Story-Umsetzung kann starten

---

## Phase 3: User Story 1 - OpenCode von Null einrichten (Priority: P1) 🎯 MVP

**Goal**: Neuer Entwickler kann OpenCode inkl. BYOK im Repo lauffaehig machen

**Independent Test**: Nur `docs/tools/opencode.md` befolgen und Validierungsszenario 1 aus `specs/001-developer-tooling-setup/quickstart.md` erfolgreich ausfuehren

### Implementation for User Story 1

- [X] T007 [US1] OpenCode-Voraussetzungen und Installationspfad in `docs/tools/opencode.md` fuer frischen Clone vollstaendig beschreiben
- [X] T008 [US1] Repo-Ersteinrichtung inkl. Nutzung von `AGENTS.md` in `docs/tools/opencode.md` dokumentieren
- [X] T009 [US1] BYOK-Konfiguration mit klaren Eingaben/Erwartungen in `docs/tools/opencode.md` ausformulieren
- [X] T010 [US1] Verifikationsblock mit Erfolgssignal, Fehlersignal und naechstem Recovery-Schritt in `docs/tools/opencode.md` ergaenzen
- [X] T011 [US1] OpenCode-Troubleshooting-Faelle (Install, Auth/BYOK, Kontext) in `docs/tools/opencode.md` ergaenzen

**Checkpoint**: US1 ist allein nutzbar und pruefbar

---

## Phase 4: User Story 2 - Spec Kit Workflow einrichten und verstehen (Priority: P1)

**Goal**: Neuer Entwickler kann Spec Kit lokal nutzen und den Workflow sicher ausfuehren

**Independent Test**: Nur `docs/tools/spec-kit.md` befolgen und Validierungsszenario 2 aus `specs/001-developer-tooling-setup/quickstart.md` erfolgreich ausfuehren

### Implementation for User Story 2

- [X] T012 [US2] Spec-Kit-Voraussetzungen, Installation und `specify init` in `docs/tools/spec-kit.md` fuer 301guru dokumentieren
- [X] T013 [US2] Workflow `constitution -> specify -> plan -> tasks -> implement` mit Ziel, Input, Output je Schritt in `docs/tools/spec-kit.md` beschreiben
- [X] T014 [US2] Klaren Artefakt-Check pro Workflow-Schritt in `docs/tools/spec-kit.md` ergaenzen
- [X] T015 [US2] Verifikationsblock mit Erfolg/Fehler/Recovery in `docs/tools/spec-kit.md` ergaenzen
- [X] T016 [US2] Spec-Kit-Troubleshooting fuer fehlende Initialisierung und Artefaktdrift in `docs/tools/spec-kit.md` ergaenzen

**Checkpoint**: US2 ist allein nutzbar und pruefbar

---

## Phase 5: User Story 3 - Schnell produktiv werden ohne Vorwissen (Priority: P2)

**Goal**: Linearer, selbsterklaerender Onboarding-Pfad fuer beide Werkzeuge

**Independent Test**: Person ohne Projektkontext folgt `docs/tools/README.md` und erreicht beide Setups gemaess Validierungsszenario 3 in `specs/001-developer-tooling-setup/quickstart.md`

### Implementation for User Story 3

- [X] T017 [US3] Sequenziellen Onboarding-Ablauf (Start, Reihenfolge, Exit-Kriterien) in `docs/tools/README.md` festlegen
- [X] T018 [P] [US3] Ruecksprung zum zentralen Einstieg in `docs/tools/opencode.md` einbauen
- [X] T019 [P] [US3] Ruecksprung zum zentralen Einstieg in `docs/tools/spec-kit.md` einbauen
- [X] T020 [US3] Skip-/Repeat-Regeln fuer teilweise vorkonfigurierte Repos in `docs/tools/README.md` dokumentieren
- [X] T021 [US3] Gemeinsamen Troubleshooting-Einstieg und Eskalationshinweis in `docs/tools/README.md` ergaenzen

**Checkpoint**: US3 ermoeglicht Onboarding ohne implizites Wissen

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Konsistenz, finale Verifikation, Abschluss

- [X] T022 Konsistenz-Pass (Begriffe, Befehlsstil, Abschnittsreihenfolge) ueber `docs/tools/README.md`, `docs/tools/opencode.md`, `docs/tools/spec-kit.md`
- [X] T023 End-to-End-Dry-Run nach `specs/001-developer-tooling-setup/quickstart.md` durchfuehren und Abweichungen in `docs/tools/README.md` nachziehen
- [X] T024 Optionalen Repo-Sanity-Check `mvn -Dtest=UrlServiceUnitTest test` als Abschlusskontrolle in `docs/tools/README.md` klar kennzeichnen

---

## Dependencies & Execution Order

### Phase Dependencies

- **Phase 1 (Setup)**: Startet sofort
- **Phase 2 (Foundational)**: Haengt von Phase 1 ab und blockiert alle Stories
- **Phase 3 (US1)**: Startet nach Phase 2
- **Phase 4 (US2)**: Startet nach Phase 2
- **Phase 5 (US3)**: Startet nach US1 und US2, da beide Guides integriert werden
- **Phase 6 (Polish)**: Startet nach allen Stories

### User Story Dependencies

- **US1 (P1)**: Keine Abhaengigkeit auf andere Stories
- **US2 (P1)**: Keine Abhaengigkeit auf andere Stories
- **US3 (P2)**: Haengt von US1 und US2 ab (Integration im Einstiegspfad)

### Parallel Opportunities

- US1 und US2 koennen nach Phase 2 parallel umgesetzt werden
- In US3 koennen T018 und T019 parallel laufen (verschiedene Zieldateien)

---

## Parallel Example: User Story 3

```bash
# Parallel moeglich, da unterschiedliche Zieldateien
Task: "Ruecksprung zum Einstieg in docs/tools/opencode.md"
Task: "Ruecksprung zum Einstieg in docs/tools/spec-kit.md"
```

---

## Implementation Strategy

### MVP First (US1 + US2)

1. Phase 1 und Phase 2 abschliessen
2. US1 und US2 umsetzen
3. Gegen `specs/001-developer-tooling-setup/quickstart.md` Szenario 1+2 validieren
4. Erst danach US3 integrieren

### Incremental Delivery

1. Foundation herstellen
2. US1 liefern und pruefen
3. US2 liefern und pruefen
4. US3 als integrierten Onboarding-Pfad liefern
5. Polish + finaler Dry-Run
