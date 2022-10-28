# OMD_Editeur - Introduction
L'objectif de ce TP est de construire un mini-éditeur de texte offrant les concepts et fonctionnalités suivantes :

* le texte est contenu dans un buffer (zone de travail),
* il existe une notion de sélection de texte, avec des commandes utilisateur permettant de
déplacer le début et la fin de la sélection,
* copie de la sélection dans le presse-papier,
* copie de la sélection dans le presse-papier puis effacement de la sélection,
* remplacement (« collage ») de la sélection par le contenu du presse-papier,
* l’interface homme-machine est d’un type quelconque (textuelle ou graphique).

## V1_Editeur_de_Texte
Pour réaliser cet éditeur nous avons d'abord conçu une v1 comportant, les fonctionnalités citées ci-dessus.

Au lancement de l'editeur l'utilisateur est en mode écriture il peut donc écrire à l'intérieur de l'editeur à l'aide des touches d'un clavier.
Pour effectuer une sélection de caractères il doit activer le mode sélection en appuyant sur la touche **MAJ**. Il peut ensuite effectuer quelques commandes de bases sur cette sélection à l'aide de raccourci clavier. Un bouton en haut à gauche de l'editeur rappelle le raccourci pour chaque commande.

Listes des commandes :
* Copier --> CTRL
* Coller --> ENTREE
* Couper --> ALT

## V2_Editeur_de_Texte JTextArea
Ensuite, nous avons réaliser une v2 qui en plus d'implémenter les mêmes fonctionnalités que la v1, se doit de pouvoir réaliser le défaire/refaire.
Pour cela, nous utilisé la librairie JTextArea qui permet d'avoir un editeur plus propre avec des commandes plus facile à éxécuter.

## Conclusion

Ce TP nous a donc permit de réaliser un éditeur de texte nous même tout en concevant un dossier développeur.