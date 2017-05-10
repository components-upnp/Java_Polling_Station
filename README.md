# Java_Bureau_De_Vote
Bureau de vote basé sur le protocole UPnP

<strong>Description: </strong>

Ce composant permet de soumettre un vote à un ensemble de personne qui pourront répondre en utilisant des applications spécifiques,
le tout connecté par UPnP.

Om peut imaginer que ce composant soit utilisé lors d'un cours par un professeur afin de rendre le cours plus interactif, en
faisant participer les élèves par le biais de QCM. 



<strong>Lancement de l'application: </strong>

Pour lancer l'application il suffit de lancer le .jar se situant dans le répertoire build. Voici un exemple d'exécution 
de l'application:

![alt tag]()

<strong>Spécicfications UPnP:</strong>

Ce composant présente les services suivant:

  1) MasterCommandController
  2) VoteController
  3) ReportController
 
VoteService qui offre l'accès aux méthodes:

  1) getState(): permet de récupérer l'état du vote ( en cours ou pas).
  2) inscription(String udn): permet à un composant de s'inscrire au bureau de vote
  3) commande(String com): permet à un composant inscrit de voter (com est ici un document XML DOM envoyé par le composant votant, qui contient l'UDN de l'envoyeur et son choix).
  
RapportService n'offre accès à aucune méthode, mais envoie un événement Reponses lorsque le vote est terminé. Il envoie alors un document XML DOM contenant le nombre de vote par réponses ( dans un String). 

Voici le schéma représentant le composant:

![alt tag](https://github.com/components-upnp/Java_Bureau_De_Vote/blob/master/BureauDeVoteSch%C3%A9ma.png)




