package F1Race;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static final int MAX_V = 100;
    public static final int MAX_TOP = 8;
    public static final int MAX_BOTTOM = 485;

    Image img_c = new ImageIcon("F1resources/car.png").getImage();
    Image img_l = new ImageIcon("F1resources/carUp.png").getImage();
    Image img_r = new ImageIcon("F1resources/carDown.png").getImage();

    Image img = img_c;

    public Rectangle getRect() {
        return new Rectangle(x+20, y+20, 128, 50);
    }

    int V = 0;
    int dV = 0;
    int s = 0;
    int x = 30;
    int y = 100;
    int dY = 0;
    int layer1 = 0;
    int layer2 = 1200 ;

    public void move() {
        s += V;
        V += dV;
        if (V <= 0) V = 0;
        if (V >= MAX_V) V = MAX_V;
        y -= dY;
        if (y <= MAX_TOP) y = MAX_TOP;
        if (y >= MAX_BOTTOM) y = MAX_BOTTOM;
        if (layer2 - V<= 0) {
            layer1 = 0;
            layer2 = 1200;
        } else {
            layer1 -= V;
            layer2 -= V;
        }
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            dV = 5;
        }
        if (key == KeyEvent.VK_LEFT) {
            dV = -5;
        }
        if (key == KeyEvent.VK_UP) {
            dY = 10;
            img = img_l;
        }
        if (key == KeyEvent.VK_DOWN) {
            dY = -10;
            img = img_r;
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            dV = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dY = 0;
            img = img_c;
        }
    }
}
