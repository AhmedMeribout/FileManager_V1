# Manuel Technique - [gestion des fichiers]

## Introduction
Ce manuel technique vise à fournir une documentation détaillée sur l'architecture, la mise en œuvre et les fonctionnalités du projet [Nom du Projet].



## Architecture du Projet
L'architecture du projet consiste en une application simple de navigation de fichiers avec une interface en ligne de commande. Voici une description de l'architecture :

Structure générale du projet
Classe principale (App.java) : 
>Cette classe contient la logique principale de l'application. Elle gère les opérations de navigation dans les fichiers et répertoires, la visualisation du contenu des fichiers, ainsi que les opérations de copie, déplacement et suppression de fichiers.

Classes utilitaires : Le projet utilise plusieurs classes utilitaires pour des fonctionnalités spécifiques :

>DirectoryFunction, DeleteFunction, CsvReader, CopyPaste, VisuReader, FileFunctions: Ces classes fournissent des fonctionnalités pour les opérations sur les fichiers et les répertoires, telles que la création de répertoires, la suppression de fichiers, la lecture de fichiers CSV, la copie, le déplacement, la visualisation du contenu des fichiers, etc.
Principales fonctionnalités et composants
Exploration des fichiers et répertoires : L'application permet à l'utilisateur de naviguer à travers les fichiers et répertoires présents dans le système de fichiers. Elle offre des fonctionnalités de déplacement vers le répertoire parent, de changement de répertoire, ainsi que de création et suppression de répertoires.

Gestion des éléments : 
>L'application permet de sélectionner des éléments (fichiers ou répertoires) pour effectuer des opérations telles que la copie, le déplacement, la suppression, l'affichage du contenu et la recherche de fichiers.

>Interaction utilisateur : L'interface en ligne de commande est gérée via la bibliothèque JLine, offrant des fonctionnalités telles que la lecture de lignes, la complétion automatique et l'exécution des commandes saisies par l'utilisateur.

Résumé
>Le projet est conçu pour être un explorateur de fichiers simplifié avec des fonctionnalités de base pour naviguer, manipuler et visualiser le contenu des fichiers et répertoires via une interface en ligne de commande. Les fonctionnalités principales comprennent la gestion des répertoires, la manipulation des fichiers, la visualisation du contenu des fichiers texte, et la facilitation des opérations courantes sur les fichiers et les répertoires.

## Dépendances
Les dépendances du projet Maven sont spécifiées dans la section <dependencies> du fichier pom.xml. Voici les dépendances répertoriées dans ce fichier :

Dépendances du projet :

>commons-io : Version 2.13.0 de la bibliothèque Apache Commons IO, utilisée pour des opérations sur les fichiers.
org.junit.jupiter : Version 5.9.1 de JUnit Jupiter pour les tests unitaires.
org.jline : Version 3.24.1 de JLine, utilisée pour l'interaction avec l'interface en ligne de commande.
org.fusesource.jansi : Version 2.4.1 de JAnsi, utilisée pour la coloration dans le terminal.

Dépendances des plugins Maven :

>maven-checkstyle-plugin : Utilisé pour vérifier le style de code avec Checkstyle.
exec-maven-plugin : Exécute une classe Java spécifiée lors du build Maven.
maven-assembly-plugin : Permet de créer un assemblage (par exemple, un JAR exécutable avec toutes les dépendances).
jacoco-maven-plugin : Plugin pour la génération de rapports de couverture de code.
Ces dépendances et plugins définissent les bibliothèques externes utilisées dans le projet ainsi que les outils Maven supplémentaires pour la génération, les tests et la vérification du code.


# Manuel technique

## Compiler le projet

### .Sous Linux

>$ ./mvnw package
----

### .Sous Windows
----
> mvn package
----

=== Exécuter l'application
----
> java -jar target/explorer-1.0-SNAPSHOT-jar-with-dependencies.jar
----


### Comment consulter le rapport de couverture de code par les tests ?
en excutant cette commande "mvn test" puis on doit ouvrir le fichier index.html 
qui se trouve le fichier target/site/jacoco/index.html (l'ouvrir dans un navigateur) pour qu'on peut voir le pourcentage des rapports.


### Quelles bibliothèques ont été utilisées et pourquoi ?

>les bibliothèques que j'ai intégrées sont:

        "java.io" et "java.nio.file" : Ces bibliothèques standard de Java sont utilisées pour la manipulation des fichiers
    et la gestion des répertoires et des chemins.

        "org.apache.commons.io" : Cette bibliothèque est utilisée pour extraire des informations supplémentaires des fichiers,
    telles que les extensions de fichier.
    
          "org.jline" : Cette bibliothèque est employée pour créer une interface interactive en ligne de commande. 
    Elle propose des fonctionnalités telles que la complétion automatique, 
    la lecture de lignes et d'autres fonctionnalités d'interaction avec l'utilisateur.

          "JAnsi" : Utilisée pour la coloration dans un terminal, 
    cette bibliothèque permet d'ajouter des couleurs et des styles au texte affiché dans un terminal, 
    améliorant ainsi la lisibilité et l'expérience utilisateur.

          "JLine" : Cette bibliothèque est spécifiquement utilisée pour la gestion des saisies dans le terminal. 
    Elle offre des fonctionnalités avancées pour gérer les entrées utilisateur, 
    la complétion automatique et d'autres interactions pour faciliter l'utilisation de l'interface en ligne de commande.

    
> Quel est le rôle des différentes classes ?
App.java : lancement d'application et contient les variables du programme.
ConsoleColors.java : Coloration d'application.
CopyPaste.java : copier et couper et coller un element.
CsvReader.java : lire et afficher les notes.
DeleteFunction.java : supprimer un element.
DirectoryFunction.java : créer un répertoire
Element.java : contient les elements de la liste.
FileFunctions.java : trouver le chemin d'un fichier.
Functions.java : contient des methodes simple pour la fonctionalité d'application.
VisuReader.java : affichier le contenu d'un fichier texte sinon afficher sa taille.

### Quels traitements sont réalisés pour gérer une commande saisie par l'utilisateur ?

>le programme met en place un lecteur de ligne à l'aide de la bibliothèque JLine. Il lit les commandes de l'utilisateur depuis le terminal et les traite en appelant la méthode runCommand() pour chaque commande saisie. Cette méthode peut contenir la logique de traitement spécifique à chaque commande. Le programme continue de lire les commandes jusqu'à ce que l'utilisateur saisisse "exit" pour quitter le programme

 Quelles améliorations peut-on envisager ?

>1- Gestion avancée des fichiers : Intégrer des opérations avancées telles que le renommage de fichiers, la compression/décompression.

>2- Interface utilisateur améliorée : Implémenter une interface utilisateur plus conviviale et informative en utilisant des menus interactifs, des messages plus clairs pour guider l'utilisateur, ou encore une aide contextuelle pour expliquer les commandes.

>3- Options de tri et de filtrage : Ajouter des fonctionnalités de tri et de filtrage pour permettre à l'utilisateur de trier ou filtrer les fichiers en fonction de différents critères comme le nom, la taille, la date, etc.

>4- Support de l'édition de fichiers : Intégrer la possibilité d'éditer des fichiers texte directement depuis l'interface en ligne de commande.


---

Ceci est un modèle de base pour un manuel technique en Markdown. Vous pouvez ajouter, supprimer ou adapter les sections en fonction des besoins spécifiques de votre projet.
