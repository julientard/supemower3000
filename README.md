# Supermower3000
Ce projet est ma réponse à l'exercice technique proposée par Xebia.

## Enoncé
La société MotItNow souhaite faire fonctionner des tondeuses sur une pelouse en passant des instructions par fichier.
Le fichier d'entrée comprend les instructions suivantes: 
- ligne 1: taille de la pelouse (ex: "5 5")
- ligne n: position initiale de la tondeuse n (ex: "1 2 N")
- ligne n+1: instructions de déplacement de la tondeuse n (ex: "GAGAGGAGGA")

## Solution
La solution se veut simple et ouverte aux évolutions futures.
Ainsi implémenter de nouvelles instructions ou de nouvelles tondeuses sera possible en peu de lignes.

La solution est une application Java 1.7, buildé grâce à l'outil Maven, et versionné grâce à l'outil Git.
Le jar executable est généré grâce au plugin Maven Shade.
Pour l'execution, executer la commande suivante `mvn package`
Executer ensuite le jar généré avec en paramètre un fichier d'instructions `java -jar super-mower-3000-1.0.jar fichier.txt`
Si aucun fichier n'est renseigné en argument, l'utilisateur y sera invité ultérieurement.
La restitution à l'utilisateur se fait par la console et par un fichier de log `supermower3000-log.txt` à l'emplacement d'execution du programme.

## Conception
Le supermower3000 est amorcé par la classe `App` qui lit le fichier.
Le modèle est constitué d'une entité abstraite `Vehicle`.
L'entité `Mower` hérite de `Vehicle` et implemente ses actions disponibles (G, D, A), ainsi que les valeurs de rotation et de mouvement associés (classe `Action`)
Le service `DescriptionReader` parse les informations du fichier pour créer des `Vehicle` et une `Lawn`.
Il retourne une liste de `VehicleRunner`, chacune comportant une tondeuse et une pelouse. 
Cette classe implémente `Runnable`, ce qui permet de gérer le nombre de tondeuses en parallèle grâce à un `ExecutorService` (ici un `newSingleThreadExecutor`)

## Dépendances
Les dépendances utilisées sont les suivantes:
###Commons-IO
Librairie Apache simplifiant les opérations sur fichiers
### Logback
Ecriture des logs vers la sortie standard et vers un fichier
Utilisation de la façade SLF4J
### JUnit
Framework de test
### Hamcrest
Librarie d'écriture d'assertions pour les tests










