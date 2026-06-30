# Feature Specification: Entwickler-Tooling-Setup-Dokumentation

**Feature Branch**: `[001-developer-tooling-setup]`

**Created**: 2026-06-27

**Status**: Draft

**Input**: User description: "Entwickler-Tooling-Setup dokumentieren. Unter docs/tools/ entsteht eine Dokumentation, die einen neuen Entwickler befaehigt, die lokale Arbeitsumgebung fuer 301guru von Null aufzusetzen. Zwei Werkzeuge stehen im Mittelpunkt: OpenCode (terminal-basierter AI-Coding-Agent, BYOK) und GitHub Spec Kit (Spec-Driven-Development-Toolkit). Die Doku soll fuer jedes Werkzeug abdecken: Installation, Ersteinrichtung im 301guru-Repo, Konfiguration (bei OpenCode inkl. BYOK-Modellanbindung und AGENTS.md, bei Spec Kit inkl. specify init und dem constitution/specify/plan/tasks/implement-Ablauf), sowie eine Verifikation, dass das Setup funktioniert. Zielgruppe ist ein Entwickler, der das Repo frisch klont und produktiv werden will, ohne implizites Vorwissen. Erfolgskriterium: Jemand kann der Doku Schritt fuer Schritt folgen und endet mit einer lauffaehigen OpenCode+Spec-Kit-Umgebung."

## User Scenarios & Testing *(mandatory)*

### User Story 1 - OpenCode von Null einrichten (Priority: P1)

Als neuer Entwickler moechte ich OpenCode nach einem frischen Clone installieren,
im 301guru-Repo initial konfigurieren und die BYOK-Modellanbindung aktivieren,
damit ich sofort produktiv mit agentischer Entwicklung starten kann.

**Why this priority**: Ohne funktionierendes OpenCode fehlt das zentrale
Arbeitswerkzeug fuer den taeglichen Entwicklungsfluss.

**Independent Test**: Ein neuer Entwickler folgt nur dem OpenCode-Abschnitt,
fuehrt die genannten Verifikationsschritte aus und kann danach erfolgreich eine
erste Agent-Anfrage im Repo starten.

**Acceptance Scenarios**:

1. **Given** ein frisch geklontes Repo und keine lokale OpenCode-Installation,
   **When** der Entwickler die Installations- und Setup-Schritte befolgt,
   **Then** ist OpenCode lokal lauffaehig und im Repo korrekt initialisiert.
2. **Given** eine eingerichtete OpenCode-Instanz,
   **When** der Entwickler die BYOK-Konfigurationsschritte ausfuehrt,
   **Then** kann OpenCode ein Modell ueber den eigenen API-Key verwenden.
3. **Given** ein konfiguriertes OpenCode im Repo,
   **When** der Entwickler den Verifikationsablauf ausfuehrt,
   **Then** bestaetigt die Doku klar, woran ein erfolgreiches Setup erkennbar ist.

---

### User Story 2 - Spec Kit Workflow einrichten und verstehen (Priority: P1)

Als neuer Entwickler moechte ich GitHub Spec Kit installieren, im Projekt
initialisieren und den End-to-End-Ablauf mit constitution, specify, plan, tasks
und implement verstehen, damit ich Features nach dem vorgesehenen Prozess planen
und umsetzen kann.

**Why this priority**: Das Team arbeitet spezifikationsgetrieben; ohne korrektes
Spec-Kit-Setup kann der Kernprozess nicht eingehalten werden.

**Independent Test**: Ein neuer Entwickler folgt nur dem Spec-Kit-Abschnitt,
fuehrt die beschriebenen Start- und Verifikationsschritte aus und kann danach
einen vollstaendigen Spec-Kit-Ablauf fuer ein Beispiel-Feature starten.

**Acceptance Scenarios**:

1. **Given** ein frisch geklontes Repo,
   **When** der Entwickler die Spec-Kit-Installation und Initialisierung ausfuehrt,
   **Then** ist Spec Kit im Projekt einsatzbereit.
2. **Given** ein einsatzbereites Spec Kit,
   **When** der Entwickler den dokumentierten Workflow Schritt fuer Schritt folgt,
   **Then** kann er den Prozess von Verfassung bis Implementierung nachvollziehbar
   ausfuehren.
3. **Given** die abgeschlossene Einrichtung,
   **When** der Entwickler die Verifikationsschritte ausfuehrt,
   **Then** ist klar bestaetigt, dass Spec Kit korrekt arbeitet.

---

### User Story 3 - Schnell produktiv werden ohne Vorwissen (Priority: P2)

Als neuer Entwickler moechte ich eine klar strukturierte, selbsterklaerende
Tooling-Dokumentation unter `docs/tools/`, damit ich ohne Rueckfragen und ohne
implizites Teamwissen innerhalb kurzer Zeit produktiv arbeiten kann.

**Why this priority**: Die Doku soll Onboarding-Zeit und Abhaengigkeit von
muendlicher Wissensweitergabe reduzieren.

**Independent Test**: Eine Person ohne Projektkontext folgt der Dokumentation in
linearer Reihenfolge und erreicht beide funktionierenden Setups ohne Zusatzinfos.

**Acceptance Scenarios**:

1. **Given** ein Entwickler ohne 301guru-Vorerfahrung,
   **When** er die Doku von Anfang bis Ende befolgt,
   **Then** erreicht er eine funktionierende OpenCode- und Spec-Kit-Umgebung.
2. **Given** ein Fehler in einem Setup-Schritt,
   **When** der Entwickler den Troubleshooting-Teil nutzt,
   **Then** kann er den Ablauf fortsetzen, ohne den Gesamtprozess abzubrechen.

### Edge Cases

- OpenCode ist installiert, aber die Modellanbindung per BYOK ist unvollstaendig
  oder verwendet einen ungueltigen API-Key.
- Spec Kit ist vorhanden, aber das Projekt wurde noch nicht initialisiert oder
  die erwarteten Dateien fehlen.
- Ein Entwickler startet in einem teilweise vorkonfigurierten Repo und braucht
  klare Hinweise, welche Schritte er ueberspringen darf und welche verpflichtend
  erneut zu pruefen sind.
- Verifikationsschritte schlagen fehl; die Doku muss eindeutige naechste
  Diagnose-Schritte nennen.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: Die Dokumentation MUSS unter `docs/tools/` fuer beide Werkzeuge
  getrennte, klar benannte Anleitungen bereitstellen.
- **FR-002**: Die OpenCode-Anleitung MUSS alle notwendigen Voraussetzungen und
  Installationsschritte fuer einen frisch aufgesetzten Entwicklerrechner
  enthalten.
- **FR-003**: Die OpenCode-Anleitung MUSS die Ersteinrichtung im 301guru-Repo
  inklusive Nutzung von `AGENTS.md` erklaeren.
- **FR-004**: Die OpenCode-Anleitung MUSS die BYOK-Modellanbindung mit
  nachvollziehbaren Konfigurationsschritten und erwarteten Ergebnissen abdecken.
- **FR-005**: Die Spec-Kit-Anleitung MUSS Voraussetzungen, Installation und
  `specify init` im Kontext des 301guru-Repos beschreiben.
- **FR-006**: Die Spec-Kit-Anleitung MUSS den durchgaengigen Ablauf
  `constitution -> specify -> plan -> tasks -> implement` als ausfuehrbaren
  Prozess mit Ziel pro Schritt dokumentieren.
- **FR-007**: Fuer jedes Werkzeug MUSS ein separater Verifikationsablauf
  vorhanden sein, der eindeutig zwischen Erfolg und Fehler unterscheidet.
- **FR-008**: Die Gesamtstruktur MUSS so aufgebaut sein, dass ein neuer
  Entwickler die Schritte sequenziell ohne implizites Vorwissen ausfuehren kann.
- **FR-009**: Die Dokumentation MUSS typische Fehlerbilder und
  Wiederherstellungswege fuer beide Werkzeuge enthalten.
- **FR-010**: Die Dokumentation MUSS klar ausweisen, welche Schritte einmalig
  sind und welche bei neuem Clone oder neuem Feature wiederholt werden muessen.

### Key Entities *(include if feature involves data)*

- **Tooling Guide**: Ein strukturierter Abschnitt pro Werkzeug mit den Teilen
  Voraussetzungen, Installation, Repo-Setup, Konfiguration und Verifikation.
- **Verification Step**: Ein pruefbarer Schritt mit erwarteter Erfolgssignatur
  und klarem Fehlerindikator.
- **Workflow Step**: Ein einzelner Schritt im Spec-Kit-Prozess mit Ziel,
  Eingabe und erwartetem Ergebnis.
- **Troubleshooting Case**: Ein haeufiges Problem mit Ursache, Symptomen und
  konkreter Gegenmassnahme.

## Constitution Alignment *(mandatory)*

- **Domain Path Impact**: Kein Eingriff in `UrlController -> UrlService -> UrlRepository -> Url`; der Scope ist reine Tooling-Dokumentation.
- **API/Routing Impact**: Keine Aenderung an Endpunkten, Sicherheitsgrenzen oder Response-Formaten.
- **Verification Plan**: Dokumentationsaenderung wird ueber reproduzierbare Setup- und Verifikationsablaeufe validiert; kein fachlicher Laufzeitcode wird geaendert.
- **Data Compatibility**: Keine Aenderungen an DynamoDB-Tabellen, Region oder Zugangsschluesseln.
- **Operational Impact**: Entwickler-Onboarding fuer OpenCode und Spec Kit wird standardisiert; bestehende Build-/Run-Pfade der Applikation bleiben unveraendert.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: Ein neuer Entwickler kann mit der Dokumentation beide Werkzeuge
  in einer frischen Umgebung in maximal 60 Minuten betriebsbereit machen.
- **SC-002**: Bei einem Dry-Run ohne Zusatzerklaerungen werden mindestens 90 %
  der dokumentierten Schritte beim ersten Versuch korrekt ausgefuehrt.
- **SC-003**: Fuer OpenCode und Spec Kit existiert jeweils mindestens ein
  erfolgreich durchfuehrbarer Verifikationsablauf mit eindeutigem Erfolgsnachweis.
- **SC-004**: Nach Veroeffentlichung der Doku kann ein neuer Entwickler den
  Spec-Kit-Ablauf bis zur Implementierungsphase ohne Rueckfrage reproduzieren.

## Assumptions

- Neue Entwickler haben Zugriff auf die fuer OpenCode-BYOK benoetigten
  Zugangsdaten und duerfen diese lokal konfigurieren.
- Das Onboarding startet mit einem frischen Clone des 301guru-Repositories.
- Die bestehende Projektstruktur unter `.specify/` bleibt fuer den beschriebenen
  Spec-Kit-Ablauf verfuegbar.
- Die Doku richtet sich primaer an lokale Entwicklung auf ueblichen
  Entwickler-Workstations, nicht an produktive Deployment-Umgebungen.
