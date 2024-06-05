    package gameLaby.laby;

    /**
     * Classe abstraite pour les personnages
     */
    public abstract class Personnage{
        /**
         * Attributs: x, y, pv
         */
        protected int x, y;
        protected int pv;
        /**
         * Constantes: PV_ENTIER, PV_3_QUART, PV_DEMI, PV_1_QUART, PV_MORT
         */
        public static final int PV_ENTIER = 10;
        public static final int PV_3_QUART = 7;
        public static final int PV_DEMI = 5;
        public static final int PV_1_QUART = 2;
        public static final int PV_MORT = 0;

        /**
         * Constructeur de la classe Personnage
         * @param dx position x
         * @param dy position y
         * @param pv points de vie
         */
        public Personnage(int dx, int dy, int pv) {
            this.x = dx;
            this.y = dy;
            this.pv = pv;
        }

        /**
         * Methode pour se deplacer
         * @param dx position x
         * @param dy position y
         * @return retourne true si le deplacement est possible
         */
        public boolean etrePresent(int dx, int dy) {
            return (this.x == dx && this.y == dy);
        }

        /**
         * Guetteur de la position x
         * @return
         */
        public int getX() {
            return this.x;
        }

        /**
         * Guetteur de la position y
         * @return
         */
        public int getY() {
            return this.y;
        }
        public void setY(int y){
            this.y =y;
        }

        public void setX(int x){
            this.x = x;
        }

        /**
         * Guetteur des points de vie
         * @return
         */
        public int getPv() {
            return this.pv;
        }

        /**
         * Methode pour perdre des points de vie
         * @param degats degats subis
         */
        public void perdrePv(int degats) {
            this.pv -= degats;
        }

    }
