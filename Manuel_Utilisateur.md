# Manuel Utilisateur - Explorateur de fichiers

## Introduction

L'Explorateur de fichiers est une application permettant de naviguer à travers les répertoires, d'effectuer diverses opérations sur les fichiers (comme la création, la copie, la suppression), et de visualiser le contenu des fichiers.

## Utilisation de base

1-  Lancer l'Application : l'utilisateur doit exécuter cette insctruction :
>java -jar target/explorer-1.0-SNAPSHOT-jar-with-dependencies.jar

2-  Sélectionnez l'Élément suivi par la commande souhaité : Pour effectuer une opération sur un élément particulier du répertoire (fichier ou répertoire)  
## Commandes disponibles

  A - vous devez utiliser son NER associé à côté de celui-ci suivi par un de ces commande suivant : 

    - Pour copier un élément désigné par le numéro 1 saisissez " 1 copy ".
  
    - Pour coller un élément qui a été Sélectionner pour copier, selectionnez le repertoire apres saisissez "paste".
  
    - Pour voir et lire le contenu d'un fichier texte désigné par le numéro 3 , saisissez "3 visu".
    
    - Pour couper un élément désigné par le numéro 5, saisissez "5 cut". (copier et supprimer le de la liste en meme temps)
    
    - Pour entrer dans un répertoire à condition que le NER désigne un répertoire, saisissez "6 .".
    
    - Pour sortir d'un fichier au fichier precedant , saisissez "7 ..".
    
    - Pour ajouter une note à un élement, saisissez " 8 + "<Note>" ".
     
    - Pour supprimer une note d'un élement, saisissez " 9 - ".
    
    - Pour supprimer un élément désigné par le numéro 10, saisissez "remove 10".
     
    - Pour activer l'auto-complétion, commencez à taper la première lettre de la commande souhaitée, puis appuyez sur la touche TAB.
    
  ## Autres Commandes

    - pour créer un nouveau répertoire, saisissez "mkdir <nom du répertoire>".
    
    - pour trouver le repertoire d'un élement, saisissez " find <nom du element> ".

3-Quitter l'Application : pour quitter l'Application saisissez 
>exit 

## Exemples
>copy hi.txt

>mkdir hachem

>hachem .

>paste 

Result : hi.txt est coller dans fichier hachem 

### Quelles sont les mises à jours du fichier des annotations à effectuer en fonction des types de commandes ?

    le fichier des annotations sera effectuer seulement avec la commande "+" ou "-" 
    "+": cette commande va ajouter une note dans le fichier des Annotations
    "-": cette commande va supprimer une note dans le fichier des Annotations

### Y a t il des bibliothèques Java qui permettront de prendre en charge la visualisation d'une image png si l'utilisateur veut l'afficher ?

>Java Advanced Imaging (JAI) est une extension de la plateforme Java 
qui fournit des fonctionnalités avancées pour le traitement d'images. 
Cette bibliothèque offre un ensemble d'outils et de fonctionnalités pour manipuler,
traiter et afficher des images dans des applications Java.


### Quelles sont les commandes qui seraient utiles de rajouter ?

   Commandes de suppression. 
   Commandes pour renommer les fichiers.
   Commandes pour ouvrir certain fichier avec l'app souhaité.


### Quelles améliorations peut on envisager pour rendre l'usage de l'interface clavier plus souples/efficaces pour l'utilisateur ?
----------
>1. Auto-complétion et suggestions :
Utiliser une bibliothèque comme JLine qui offre des fonctionnalités d'auto-complétion et de suggestions. 
Cela permet à l'utilisateur de naviguer dans les options disponibles en appuyant sur la touche TAB, 
ce qui rend l'interface plus interactive et conviviale.
-----------
>2. Historique des commandes :
Intégrer un mécanisme de gestion de l'historique des commandes. 
L'utilisateur peut naviguer dans l'historique (flèches haut/bas) pour revoir et réexécuter les commandes précédentes.
------------
>3. Instructions contextuelles :
Fournir des informations contextuelles en temps réel lors de la saisie des commandes. 
Cela peut inclure l'affichage d'aide ou de suggestions en fonction du contexte de la commande en cours de saisie.
------------
>4. Gestion des erreurs :
Gérer les erreurs de manière conviviale en fournissant des messages clairs et informatifs pour guider 
l'utilisateur lorsqu'une commande n'est pas reconnue ou lorsqu'une erreur se produit.

---

Ceci conclut le manuel utilisateur pour l'Explorateur de fichiers. N'hésitez pas à consulter les commandes disponibles ou les astuces pour une meilleure utilisation de l'application.