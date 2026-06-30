<!--
Sync Impact Report
- Version change: 1.0.0 -> 1.1.0
- Modified principles:
  - None
- Added sections:
  - VI. Concise Artifacts
- Removed sections:
  - None
- Templates requiring updates:
  - ✅ updated: .opencode/commands/speckit.plan.md (bedingte Phase-1-Artefakte)
  - ✅ updated: .specify/templates/plan-template.md
- Deferred follow-up TODOs:
  - None
-->

# 301guru Constitution

## Core Principles

### I. Domain-First Changes
Alle produktiven Aenderungen MUESSEN entlang des realen Feature-Pfads
`UrlController -> UrlService -> UrlRepository -> Url` erfolgen, ausser eine
Abweichung wird im Plan unter Complexity Tracking begruendet. Neue
Abstraktionsebenen sind nur erlaubt, wenn ein konkretes, wiederkehrendes
Problem nachgewiesen ist. Rationale: Das Projekt ist klein und historisch stark
auf den URL-Flow zentriert; zusaetzliche Schichten erhoehen sonst nur
Wartungsaufwand.

### II. API and Routing Consistency
Oeffentliche Endpunkte und Sicherheitsgrenzen MUESSEN konsistent mit der
laufenden Anwendung bleiben: geschuetzte Pfade sind `"/statistics"` und
`"/webuser"`; Statistikseiten liegen unter `"/stats"` und `"/stats/{shortToken}"`.
Jede Aenderung an Routen, Response-Formaten oder HTTP-Semantik MUSS in
Spezifikation, Tests und betroffenen Doku-Dateien nachvollziehbar aktualisiert
werden. Rationale: Der groesste Integrationsschaden entsteht hier durch stille,
inkompatible Verhaltensaenderungen.

### III. Mandatory Verification Depth
Jede Logik-Aenderung MUSS mindestens einen automatisierten Test enthalten oder
einen bestehenden Test anpassen. Fuer schnelle lokale Verifikation ist
`mvn -Dtest=UrlServiceUnitTest test` der Mindestpfad; `mvn test` ist verpflichtend,
wenn Aenderungen DynamoDB-Konfiguration, Security oder Request/Response-Flows
betreffen. Falls Volltests wegen fehlender AWS-Credentials nicht ausfuehrbar
sind, MUSS dies im Plan, in den Tasks und im Abschlussbericht explizit dokumentiert
werden. Rationale: Das Repo hat gemischte Unit-/Integrationstests mit externen
Abhaengigkeiten; Transparenz ueber Testtiefe ist nicht verhandelbar.

### IV. DynamoDB Safety and Compatibility
Kompatibilitaet der persistierten Daten hat Vorrang. Tabellennamen `url` und
`webuser`, Region `EU_CENTRAL_1` sowie Property-Schluessel
`amazon.aws.accesskey`/`amazon.aws.secretkey` DUERFEN nur mit explizitem
Migrationsplan geaendert werden. Repository-Abfragen mit `@EnableScan` MUESSEN
bewusst als Scan behandelt werden; Performance- oder Kostenfolgen sind im Plan
zu benennen. Rationale: Datenverlust oder nicht erreichbare Tabellen sind in
diesem Service die kritischsten Produktionsfehler.

### V. Operational Reproducibility
Build-, Run- und Container-Pfade MUESSEN reproduzierbar bleiben: `mvn` (ohne
Wrapper), `mvn spring-boot:run`, Docker-Build mit `Dockerfile`, und lokaler
Containerstart auf Port `7070`. Wenn sich Artifact-Name oder Version aendert,
MUSS der `Dockerfile`-Pfad (`target/301guru-0.0.1-SNAPSHOT.jar`) im selben
Aenderungspaket aktualisiert werden. Rationale: Deployments und lokale Debugging-
Sessions sollen ohne implizites Wissen funktionieren.

### VI. Concise Artifacts
Alle Artefakte (spec, plan, research, quickstart, tasks, Doku) MUESSEN so kurz
wie moeglich sein. Regeln:
- Listen und Tabellen statt aufgeblaehter Fliesstexte; nur das Notwendige.
- Keine Wiederholungen, keine Platzhalter-Prosa, keine erfundenen Pseudo-Entities,
  nur um eine Vorlage zu fuellen.
- Optionale Abschnitte/Artefakte (z. B. `data-model.md`, `contracts/`) werden
  WEGGELASSEN, wenn das Feature sie nicht braucht.
- Jeder Satz muss eine Entscheidung, einen Schritt oder eine Pruefung tragen;
  sonst streichen.
Rationale: Knappe Artefakte werden gelesen und gepflegt; aufgeblaehte nicht.

## Technical Guardrails

- Java 8 und Spring Boot `1.5.2.RELEASE` sind der verbindliche Runtime-Rahmen,
  solange kein expliziter Migrationsauftrag beschlossen wurde.
- Security-Konfiguration mit In-Memory-Credentials dient nur der aktuellen
  Betriebsrealitaet; neue sicherheitskritische Features MUESSEN diese Grenze
  deutlich kennzeichnen und mit einer Upgrade-Strategie versehen.
- Konfiguration fuer Tests liegt teilweise nichtstandardmaessig unter
  `src/main/resources/test.properties`; Aenderungen daran MUESSEN in
  quickstart-/Betriebsdokumenten gespiegelt werden.

## Delivery Workflow and Quality Gates

1. Jede Spezifikation MUSS die betroffenen Endpunkte, Datenobjekte,
   Sicherheitsauswirkungen und Testtiefe benennen.
2. Jeder Plan MUSS die Constitution Checks vor Forschung und erneut vor
   Implementierung dokumentieren.
3. Aufgabenlisten MUESSEN Testaufgaben fuer geaenderte Logik und explizite
   Dokumentationsaufgaben enthalten.
4. Vor Merge MUSS ein Review die Einhaltung aller fuenf Prinzipien bestaetigen
   oder begruendete Abweichungen mit Owner und Rueckbaupfad dokumentieren.

## Governance

Diese Constitution hat Vorrang vor lokalen Arbeitsgewohnheiten und vorlagennahen
Defaults. Aenderungen erfolgen per Pull Request mit: (a) klarer Begruendung,
(b) Versionsklassifikation nach Semantic Versioning fuer die Constitution,
(c) Sync-Pruefung der `.specify/templates/*.md` und relevanter Runtime-Dokumente.

Versionierungsregeln fuer dieses Dokument:
- MAJOR: Entfernt oder redefiniert ein Prinzip inkompatibel.
- MINOR: Fuegt ein neues Prinzip oder eine neue verpflichtende Sektion hinzu,
  oder erweitert Pflichten materiell.
- PATCH: Praezisierungen, redaktionelle Schaerfung, nicht-semantische Anpassungen.

Compliance Review ist pro Plan, pro Tasks-Datei und pro Merge verpflichtend.
Verstoesse MUESSEN in Complexity Tracking oder als explizite Follow-up-Tasks
dokumentiert und terminiert werden.

**Version**: 1.1.0 | **Ratified**: 2026-06-27 | **Last Amended**: 2026-06-30
