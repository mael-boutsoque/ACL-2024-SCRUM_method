How to launch:

1) Make sure "Apache Maven 3.9.9" and "Java version: 21.0.2" are installed.

- HC: pourquoi 21 (et pas 17)?

2) Open cmd and go to the directory ACL-2024-SCRUM_method\jeu
   Execute the command : mvn exec:java

- HC: non, il faut faire mvn package d'abord.
- HC: il faut supprimer target du dépôt git, seulement le code source doit être présent (pas ce qui est généré)

3) The program should print("Hello from SCRUM_method team!") and is located in ACL-2024-SCRUM_method\jeu\target as jeu-0

- HC: ca marche mais seulement parce que target est dans le dépôt. 
