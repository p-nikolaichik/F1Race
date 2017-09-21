package F1Race;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nikolaichik on 04.08.2017.
 */
public class Enemy {
    int x;
    int y;
    int V;
    Image img = new ImageIcon("F1resources/Enemycar.png").getImage();
    Road road;
    public Rectangle getRect() {
        return new Rectangle(x+20, y+20, 128, 50);
    }

    public Enemy(int x, int y, int V, Road road) {
        this.x = x;
        this.y = y;
        this.V = V;
        this.road = road;
    }
    public void move() {
        x = x - road.p.V + V;
    }

}
