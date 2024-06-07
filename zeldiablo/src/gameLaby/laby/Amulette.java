package gameLaby.laby;

public class Amulette extends Coordonnees{
    public Amulette(int x , int y ){
        super(x,y);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void deplacer(int x , int y){
        this.x=x;
        this.y=y;
    }

}
