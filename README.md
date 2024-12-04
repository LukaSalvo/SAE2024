# 2024_Zeldiablo_salvo4u_korban2u_belaliab1u_scheffe28u
2024_Zeldiablo_salvo4u_korban2u_belaliab1u_scheffe28u
Scheffer Benjamin
Korban Ryan
Salvo Luka
Belalia Bendjafar Amin


# Documentation des Versions

## Version 1

### 1. Génération de monstres immobiles
**Descriptif :**  
- Au lancement du jeu, le jeu ajoute différents monstres dans l'environnement. Les monstres restent immobiles.

### 2. Déplacement aléatoire des monstres
**Descriptif :**  
- Régulièrement, le jeu demande aux monstres de se déplacer dans une direction aléatoire.

### 3. Cases piégées
**Descriptif :**  
- Lorsqu'une entité (héros ou monstre) arrive sur une case piégée, elle subit des dégâts.

### 4. Mort des monstres
**Descriptif :**  
- Lorsqu'un monstre n'a plus de points de vie, il meurt et disparaît du jeu.

### 5. Collision avec un monstre
**Descriptif :**  
- Lorsque le joueur demande au personnage de se déplacer vers une case contenant un monstre, le personnage ne se déplace pas.

---

## Version 2

### 1. Attaque des monstres
**Responsables :** Amin (Diagramme de séquence, Code), Luka (Diagramme de séquence, Tests)  
**Descriptif :**  
- Lorsqu'un monstre doit agir, si le monstre se trouve à côté du héros, alors il ne se déplace pas mais il attaque celui-ci.

### 2. Attaque du joueur
**Responsables :** Benjamin (Diagramme de séquence, Tests), Ryan (Diagramme de séquence, Code)  
**Descriptif :**  
- Lorsque le joueur appuie sur une touche donnée (par défaut la touche espace), il effectue une attaque contre les monstres qui se trouvent à côté de lui.

### 3. Refactorisation du code
**Responsables :** Ryan, Amin, Benjamin, Luka

### 4. Finalisation de la fonctionnalité "piège"
**Responsable :** Benjamin

---

## Version 3

### 1. Fin de jeu : mort du héros
**Responsables :** Luka (Conception, Code et Tests)  
**Descriptif :**  
- Si le héros meurt, le jeu se termine.

### 2. Mise en place de l'amulette
**Responsables :** Benjamin (Conception, Code et Tests)  
**Descriptif :**  
- Au lancement du jeu, une amulette est placée sur une case vide du labyrinthe. Le placement de l'amulette est toujours le même et dépend du niveau.

### 3. Acquisition de l'amulette
**Responsables :** Ryan (Conception, Code et Tests)  
**Descriptif :**  
- Dès que le joueur demande à déplacer le héros sur l'amulette, le héros se déplace et prend l'amulette.

### 4. Fin de jeu : victoire du héros
**Responsables :** Amin (Conception, Code et Tests)  
**Descriptif :**  
- Une fois que le héros a pris l'amulette, il peut retourner à l'entrée du labyrinthe et remporter le jeu.

### 5. Refactorisation du code
**Responsables :** Ryan, Amin, Benjamin, Luka

---

## Version 4

### 1. Génération automatique de labyrinthe
**Responsables :** Ryan (Codage, Conception), Benjamin (Test, Conception)  
**Descriptif :**  
- Les niveaux ne sont pas stockés en mémoire, mais le labyrinthe est généré automatiquement par le jeu, permettant des parties à chaque fois différentes.

### 2. Monstres au comportement intelligent
**Responsables :** Amine (Conception, Codage), Luka (Test, Conception)  
**Descriptif :**  
- À chaque fois que les monstres se déplacent, ils se rapprochent du héros en tenant compte de la présence des murs.

### 3. Barre de vie
**Responsable :** Benjamin  
**Descriptif :**  
- Une barre de vie est située au-dessus des monstres et du héros. Elle diminue et change de couleur en fonction des points de vie du personnage.

### 4. Affichage des monstres et du héros par des sprites
**Descriptif :**  
- Les monstres et le héros sont représentés par des sprites graphiques.
