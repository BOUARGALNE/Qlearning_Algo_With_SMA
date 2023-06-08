# Qlearning_Algo_With_SMA

Avant de commencer l’implémentation, il nous faut décrire les paramètres et les variables nécessaires pour implémenter cet Algorithme :
*	ALPHA : Il s'agit du taux d'apprentissage, qui contrôle dans quelle mesure les nouvelles informations influencent les valeurs Q existantes \n. 
*	GAMMA : Il représente le facteur de remise, qui influence la valeur de la récompense future. 
*	MAX_EPOCH : C'est le nombre maximal d'itérations (ou époques) pendant lesquelles l'algorithme Q-learning sera exécuté. 
* GRID_SIZE : Il définit la taille de la grille de l'environnement dans lequel les agents évoluent. Dans cet exemple, la grille est carrée et a une taille de 4x4.
*	ACTIONS_SIZE : Il représente le nombre d'actions possibles pour chaque agent. Dans ce cas, il y a 4 actions possibles : haut, bas, gauche et droite.
*	grid : C'est un tableau 2D qui représente l'environnement dans lequel les agents se déplacent. Chaque case de la grille contient une valeur entière.
*	qTable : Il s'agit de la table Q qui enregistre les valeurs Q pour chaque état et action possibles. 
*	actions : C'est un tableau 2D qui définit les déplacements possibles pour chaque action. Chaque action est représentée par un couple de coordonnées (delta i, delta j) correspondant au déplacement vertical et horizontal dans la grille.
*	stateI et stateJ : Ce sont les coordonnées de l'état actuel dans la grille. Les agents se déplacent dans la grille et ces variables sont mises à jour en conséquence.
 
![image](https://github.com/BOUARGALNE/Qlearning_Algo_With_SMA/assets/120935020/8ab64997-4405-40be-87c9-35f70270b947)

####	Méthode ChooseAction :
La méthode chooseAction est utilisée pour sélectionner une action à prendre par l'agent, en fonction d'un paramètre epsilon (eps) qui contrôle l'exploration par rapport à l'exploitation.
Cette méthode permet à l'agent d'effectuer une exploration aléatoire avec une probabilité eps ou d'exploiter la meilleure valeur Q disponible pour prendre une décision

 ![image](https://github.com/BOUARGALNE/Qlearning_Algo_With_SMA/assets/120935020/e96c9c8d-d829-4046-9be0-3c7a666b6db5)

####	Méthode resetState :
La méthode resetState est utilisée pour réinitialiser l'état de l'agent à une position de départ dans la grille
 ![image](https://github.com/BOUARGALNE/Qlearning_Algo_With_SMA/assets/120935020/6d5ab6ad-99aa-4230-9ed7-d924aa674ba0)

####	Méthode executeAction :
La méthode executeAction met à jour la position de l'agent en fonction de l'action choisie, en vérifiant que la nouvelle position reste dans les limites de la grille. Elle retourne ensuite l'indice correspondant à la nouvelle position dans la table qtable.
 ![image](https://github.com/BOUARGALNE/Qlearning_Algo_With_SMA/assets/120935020/c1dc05d7-0be2-4b3d-a4ec-42fd70f70f0f)

#### Méthode finished :
La méthode finished permet de vérifier si l'agent a atteint l'état final dans la grille en comparant la valeur de la case correspondant à sa position avec la valeur 1.
 
####	Méthode showResult :
La méthode showResult affiche les résultats de la table Q et les actions prises en parcourant les états et en sélectionnant les actions appropriées, il faut réinitialiser la position de l'agent à l'état initial avant de commencer à afficher les actions.
 ![image](https://github.com/BOUARGALNE/Qlearning_Algo_With_SMA/assets/120935020/8f1fedf8-2446-4a3e-8a76-7aa6a32b7a14)

#### Méthode run :
La méthode run est la méthode principale qui exécute l'algorithme Q-learning en mettant à jour la table Q à chaque itération jusqu'à ce que l'agent atteigne l'état final, puis affiche les résultats, À chaque début d'époque, la position de l'agent est réinitialisée à l'état initial à l'aide de la méthode resetState().puis une boucle while est utilisée pour continuer l'apprentissage jusqu'à ce que l'agent atteigne l'état final.
À chaque itération, la méthode chooseAction(0.4) est appelée pour choisir une action à prendre avec un paramètre d'exploration (epsilon) de 0.4, ce qui signifie que l'agent effectuera une exploration avec une probabilité de 0.4.
 ![image](https://github.com/BOUARGALNE/Qlearning_Algo_With_SMA/assets/120935020/b8b229db-ee69-4474-a4e7-70eec418209a)
####	Execution :
      Pour la gride suivante 
      ![image](https://github.com/BOUARGALNE/Qlearning_Algo_With_SMA/assets/120935020/e0cb3cda-fc8a-4151-b0f5-f6653f80be1e)
  #### resultat
![image](https://github.com/BOUARGALNE/Qlearning_Algo_With_SMA/assets/120935020/e193d604-2511-4cf3-bc1d-019ad5626f5f)




