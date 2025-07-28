# Aventurier Simulator

Ce projet est un simulateur de déplacements d’un aventurier sur une carte, avec gestion des obstacles, des mouvements et de la validation des entrées. Il est écrit en Java 17 et utilise Maven pour la gestion du build, des dépendances et de la qualité logicielle.

## Description
Une application console Java qui simule le déplacement d'un aventurier sur une carte, en respectant obstacles et limites.


## Structure du projet

```
src/
  main/
    java/
      com.cirilgroup.aventurier/
        app/         → Point d’entrée principal de l’application (classe `App`)
        controller/  → Contrôleur de la simulation (`SimulationController`)
        exceptions/  → Exceptions personnalisées pour la gestion des erreurs
        io/          → Chargement des fichiers (cartes, mouvements) et affichage console
        model/       → Modèles métier : carte, aventurier, position, directions, etc.
        service/     → Orchestration de la simulation (`SimulationService`)
    resources/
      maps/         → Exemples de cartes
      movements/    → Exemples de séquences de mouvements
      logback.xml   → Configuration du logging
  test/
    java/           → Tests unitaires et d’intégration
    resources/      → Fichiers de test (cartes, mouvements)
```

### Rôle des principaux packages

- **app** : Contient la classe principale [`App`](src/main/java/com/cirilgroup/aventurier/app/App.java) qui lance la simulation.
- **controller** : Coordination du flux, appel des services, gestion des erreurs et délégation à la Vue via [`SimulationController`](src/main/java/com/cirilgroup/aventurier/controller/SimulationController.java).
- **service** : Orchestration métier, chargement des données et exécution de la logique de simulation ([`SimulationService`](src/main/java/com/cirilgroup/aventurier/service/SimulationService.java)).
- **io** : Chargement des fichiers de carte et de mouvements ([`MapLoader`](src/main/java/com/cirilgroup/aventurier/io/MapLoader.java), [`MoveLoader`](src/main/java/com/cirilgroup/aventurier/io/MoveLoader.java)), abstraction de l’affichage console via ([`ConsoleView`](src/main/java/com/cirilgroup/aventurier/io/ConsoleView.java)).
- **model** : Représentation des entités du domaine : carte ([`Map`](src/main/java/com/cirilgroup/aventurier/model/Map.java)), aventurier ([`Adventurer`](src/main/java/com/cirilgroup/aventurier/model/Adventurer.java)), position, directions, séquence de mouvements, etc.
- **exceptions** : Exceptions personnalisées pour la gestion des erreurs de format et d’exécution ([`MapFormatException`](src/main/java/com/cirilgroup/aventurier/exceptions/MapFormatException.java), [`MoveFormatException`](src/main/java/com/cirilgroup/aventurier/exceptions/MoveFormatException.java), [`SimulationException`](src/main/java/com/cirilgroup/aventurier/exceptions/SimulationException.java)).

## Prérequis
- Java 8+
- Maven

## Lancer le projet

```sh
mvn clean package
java -jar target/simulator-1.0-SNAPSHOT.jar <chemin/carte.txt> <chemin/mouvements.txt>
```

## Outils d’analyse statique, style et qualité

Le projet utilise plusieurs outils de qualité logicielle configurés dans le [`pom.xml`](pom.xml) :

- **Checkstyle** : Vérifie le respect des conventions de style de code (Google Java Style).
- **PMD** : Analyse statique pour détecter les mauvaises pratiques, code mort, variables inutilisées, etc. Règles personnalisées dans [`config/pmd/ruleset.xml`](config/pmd/ruleset.xml).
- **SpotBugs** : Détection de bugs potentiels dans le code Java.
- **JaCoCo** : Génération de rapports de couverture de tests unitaires.

Ces outils sont exécutés automatiquement lors du cycle Maven (`mvn verify`).

Les rapports sont générés dans target/site :

target/site/jacoco/index.html → couverture de code
target/site/checkstyle-result.xml, spotbugs.html, pmd.html → analyses statiques

## Tests

Des tests unitaires et d’intégration sont présents dans [`src/test/java`](src/test/java). Ils couvrent les composants principaux (chargement, logique métier, contrôleur, etc.).

## CI

Une intégration continue est configurée via GitHub Actions ([`.github/workflows/ci.yml`](.github/workflows/ci.yml)) pour compiler, tester et vérifier la qualité du code à chaque push ou pull request.


## Documentation
JavaDoc généré dans target/site/apidocs (Maven) ou build/docs/javadoc (Gradle).

---

© Ciril group