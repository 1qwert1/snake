



public class Food {

    int x;
    int y;

    public Food() {
        this.x = (int)Math.round(Math.random()*(Panel.WIDTH_FRAME-1));
        this.y = (int)Math.round(Math.random()*(Panel.HEIGHT_FRAME-1));
    }


}
