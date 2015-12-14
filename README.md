# Supermower3000
Ce projet est ma réponse à l'exercice technique proposée par Xebia et MowItNow.
Il s'agit d'une application Java 1.7 standalone.

## Enoncé
La société MotItNow souhaite faire fonctionner des tondeuses sur une pelouse en passant des instructions par fichier.
Le fichier d'entré comprend les instructions suivantes: 
- ligne 1: taille de la pelouse (ex: "5 5")
- ligne n: position initiale de la tondeuse n (ex: "1 2 N")
- ligne n+1: instructions de déplacement (ex: "GAGAGGAGGA")

## Solution
La soltion est donc une application Java 1.7, buildé grâce à l'outil Maven.
Pour l'execution, executer la commande suivante `mvn package`
Executer ensuite le jar généré avec en paramètre un fichier `java -jar mowitnow.jar fichier.txt`

## Conception
Le supermower3000 est amorcé par la classe App, prenant en paramètre un fichier d'entrée.
Le modèle est constitué d'une entité abstraite Vehicle.
L'entité Mower hérite de Vehicle et implemente ses insctructions possible (G, D, A), ainsi que les valeurs de rotation et de mouvement associés (classe Action)
L'avantage est qu'il sera aisé de créer d'autres tondeuses, ayant d'autres instructions.
Le service DescriptionReader lit parse les informations du fichier pour créer des Vehicles et une Lawn.
Il retourne une liste de VehicleRunner, chacune comportant une tondeuse et une pelouse. 
Cette classe implémente Runnable, ce qui permet de gérer le nombre de tondeuses en parallèle grâce à un executor (ici un SingleThreadExecutor)

## Dépendances
Les dépendances utilisées sont eslsuivantes:
### Logback
Ecriture des logs vers la sortie standard et vers un fichier
Utilisation de la façade SLF4J
### JUnit
Framework de test
### Hamcrest
Librarie d'écriture d'assertions pour les tests







