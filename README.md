# GRAMA--GRAph-Map-Analysis
Projet de SAE en C, réalisé en binôme, durant tout le semestre 2 de la 1ère année de BUT Informatique à l'IUT de Lyon 1.  
[Le sujet](https://github.com/nabilesall/GRAMA--GRAph-Map-Analysis/blob/main/SAE-MAP%20Analysis%20SUJET.pdf)

## Utilisation
L'application permet de visualiser une carte map sous format d'un graphe en symbolisant les liens entre les nœuds.  
L'utilisation est assez simple, après avoir lancé l'application vous pouvez utiliser le menu "fichier" pour choisir votre fichier csv ( Raccourci : Ctrl + N).  
Les différents écrans de l'application permettent d'avoir des informations plus détaillées sur le graphe, sur un nœud en particulier ou bien comparer deux nœuds.  
Ces informations vont de savoir quel nœud est plus ouvert jusqu'à celui qui est plus gastronomique.. D'autres fonctionnalités permettent d'en apprendre un peu plus sur votre graphe, comme par exemple avoir des statistiques sur un nœud sélectionné, et savoir les autres nœuds auxquels il est lié et le genre du nœud ( ville, restaurant, loisir).
Vous pouvez utiliser le fichier qui vous est fourni pour votre jeu de test(voir Pré-requis ci-dessous).  
Vous retrouverez aussi tous les dossiers liés au projet: Cahier de charges, dossiers de spécifications fonctionnelles etc [dossiers ici](https://github.com/nabilesall/GRAMA--GRAph-Map-Analysis/tree/main/Dossier%20de%20gestion)

### Installation 
Vous pouvez simplement télécharger le dossier en zip et le mettre dans un dossier ou vous pourrez l'ouvrir après avec votre IDE.

### Pré-requis 
Vous aurez probablement besoin d'un IDE comme NetBeans.  
Ensuite vous serez amené à installer java(jdk 8 utilisé ici).  
Les données sont stockées dans un fichier csv, vous pouvez donc mettre vos propres fichiers à condition de respecter le format suivant :  
```csv
V,Dakar:A,8034::V,Antananarivo;D,20::V,Guédiawaye;D,10::R,Planet
```
Vous pouvez consulter le [sujet](https://github.com/nabilesall/GRAMA--GRAph-Map-Analysis/blob/main/SAE-MAP%20Analysis%20SUJET.pdf) pour plus d'informations sur le format du fichier csv.  
Vous pouvez utiliser [ce fichier test](https://github.com/nabilesall/GRAMA--GRAph-Map-Analysis/blob/main/SaeGrama/SAE%20GRAMA.csv) mis à votre disposition.

## Auteurs 
Idrissa SALL et Aro RANDRIAMANANTENA
