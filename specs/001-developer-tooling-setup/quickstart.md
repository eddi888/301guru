# Quickstart Validation - Entwickler-Tooling-Setup-Dokumentation

## Ziel

Diese Validierung stellt sicher, dass die zu erstellende Dokumentation unter
`docs/tools/` einen neuen Entwickler von Null bis zu einer funktionsfaehigen
OpenCode- und Spec-Kit-Umgebung fuehrt.

## Voraussetzungen

- Frischer Clone des Repositories
- Terminal-Zugriff mit `git`, `mvn` und `docker`
- OpenCode und Spec Kit lokal installiert
- Gueltige BYOK-Zugangsdaten fuer OpenCode

## Validierungsszenario 1: OpenCode-Ende-zu-Ende

1. Einstieg in `docs/tools/README.md` und Wechsel zum OpenCode-Leitfaden.
2. Installations- und Ersteinrichtungsschritte im Repo ausfuehren.
3. BYOK-Konfiguration gemaess Leitfaden vornehmen.
4. Verifikationsschritt ausfuehren (Agent-Request im Repo).

**Erwartetes Ergebnis**:
- OpenCode laeuft im 301guru-Repo ohne Setup-Blocker.
- Modellaufrufe funktionieren mit BYOK.
- Entwickler kann mit Repo-Kontext arbeiten.

## Validierungsszenario 2: Spec-Kit-Workflow-Ende-zu-Ende

1. Wechsel zum Spec-Kit-Leitfaden aus `docs/tools/README.md`.
2. `specify init` (oder Validierung bestehender Initialisierung) durchfuehren.
3. Workflow `constitution -> specify -> plan -> tasks -> implement` fuer ein
   Beispiel-Feature folgen.
4. Pro Schritt das erzeugte Artefakt bestaetigen.

**Erwartetes Ergebnis**:
- Spec-Kit ist fuer den Team-Workflow einsatzbereit.
- Die Schrittfolge ist klar, reproduzierbar und ohne Zusatzwissen nutzbar.

## Validierungsszenario 3: Onboarding ohne implizites Vorwissen

1. Eine zweite Person ohne Projektkontext folgt nur den Doku-Schritten.
2. Probleme werden ausschliesslich ueber Troubleshooting-Abschnitte geloest.
3. Zeit bis zur arbeitsfaehigen Umgebung messen.

**Erwartetes Ergebnis**:
- Beide Setups sind in <= 60 Minuten abgeschlossen.
- Keine Rueckfrage zu implizitem Teamwissen erforderlich.

## Optionaler Repo-Sanity-Check

Nach Tooling-Setup kann zusaetzlich ein schneller Projektcheck laufen:

```bash
mvn -Dtest=UrlServiceUnitTest test
```

Dieser Check validiert die lokale Grundfaehigkeit im Repo, ist aber nicht Teil
des fachlichen Dokumentationsumfangs.
