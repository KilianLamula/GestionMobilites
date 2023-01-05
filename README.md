# Projet Tuteuré sur la gestion des mobilités internationales ISIS

## Résumé et contexte du projet

Ce projet s'inscrit dans le cadre de la première année du cycle ingénieur ISIS (Informatique et Système d’Information pour la Santé). 
S'adressant à notre client, M. Laurent GREGOIRE, il consiste en la création d’une application web pour la gestion des mobilités internationales à ISIS. 

L’application permet aux administrateurs de gérer : 
- une base de données contenant des informations concernant : 
    •	Les mobilités des étudiants
    •	Les organisations étrangères qui les accueillent
- un tableau de bord permettant de suivre rapidement les obligations de séjours internationaux des étudiants.

Les administrateurs peuvent gérer les destinations proposées c'est-à-dire en ajouter, les modifier, les supprimer.

Cette application est également dédiée aux étudiants. 
Ces derniers peuvent se renseigner sur les destinations proposées par l’école pour effectuer un semestre, une année, un stage à l’étranger ou une mission humanitaire. 
Sont renseignés :
-	le type de mobilité
- la liste et les détails des campus
- une photo du campus (facultative)
- le nombre de places disponibles par semestre ou par an (dans le cas d’une mobilité d’étude)
- le nombre de candidatures par offre de stage (dans le cas d’un stage)

Ils devront également pouvoir y retrouver l’ensemble des documents administratifs afin de préparer leur départ. 



## Documents annexes


Le rapport technique, le support de présentation, le manuel d'utilisation ainsi que les documents générés tout au long de ce projet (outils de gestion de projet, otuils techniques et méthodologiques d'ingénierie) sont disponibles dans le dossier doc, à la racine de notre dépôt GIT.

## Architecture Logicielle

```
gestionDesMobilites
├─┬ backend     → backend module with Spring Boot code
│ ├── src
│ └── pom.xml
├─┬ frontend    → frontend module with Vue.js code
│ ├── src
│ └── pom.xml
└── pom.xml     → Maven parent pom managing both modules
```

## Pour exécuter le projet

A la racine du projet, sur la branche authentification: 

```
mvn clean install
```
Va construire le frontend, recopier les fichiers dans le backend.
Le clean install risque d'échouer. Si c'est le cas, renommez le dossier "Accueil" en "accueil". Ce dossier est situé dans :
/frontend/src/views/admin/accueil

Exécution de l'application "fullstack":

```
mvn --projects backend spring-boot:run
```

Ouvrir http://localhost:8989/ pour accéder à l'application.

## Utilisation des outils de développement "front-end"

On peut lancer webpack-dev-server, qui "rafraîchit" automatiquement le front-end à chaque changement dans le code ! Pour celà, se positionner dans le répertoire `frontend` et lancer :

```
npm run serve
```

## Accès à Swagger

http://localhost:8989/swagger-ui/#/ 
