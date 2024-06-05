    package gameLaby.laby;

    public abstract class Personnage{
        protected int x, y;
        protected int pv;

        public static final int PV_ENTIER = 10;
        public static final int PV_3_QUART = 7;
        public static final int PV_DEMI = 5;
        public static final int PV_1_QUART = 2;
        public static final int PV_MORT = 0;

        public Personnage(int dx, int dy, int pv) {
            this.x = dx;
            this.y = dy;
            this.pv = pv;
        }

        public boolean etrePresent(int dx, int dy) {
            return (this.x == dx && this.y == dy);
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getPv() {
            return this.pv;
        }

        public void perdrePv(int degats) {
            this.pv -= degats;
        }

    }
