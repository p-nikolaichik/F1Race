package F1Race;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable{
    Timer mainTimer = new Timer(20, this);
    Image img = new ImageIcon("F1resources/Road.png").getImage();
    Player p = new Player();
    Thread enemyFactory = new Thread(this);
    ArrayList<Enemy> enemies = new ArrayList();
    public Road() {
        mainTimer.start();
        enemyFactory.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }
    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2000));
                enemies.add(new Enemy(1200, random.nextInt(500), random.nextInt(60), this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }
        public void keyReleased(KeyEvent e)
        {
            p.keyReleased(e);
        }
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, p.layer1,0,null);
        g2d.drawImage(img, p.layer2,0,null);
        g.drawImage(p.img, p.x, p.y,null);
        double V = (200/Player.MAX_V) * p.V;
        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Скорость: " + V + "км/ч", 100, 30);
        Iterator<Enemy> i = enemies.iterator();
        while(i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            } else {
                e.move();
                g.drawImage(e.img, e.x, e.y,null);
                System.out.println(enemies.size());
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testCollisionWithEnemies();
        testWin();
    }

    private void testWin() {
        if (p.s > 20000) {
            JOptionPane.showMessageDialog(null, "Вы выйграли!!!");
            System.exit(0);
        }
    }

    private void testCollisionWithEnemies() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())) {
                JOptionPane.showMessageDialog(null, "Вы проиграли!!!");
                System.exit(1);
            }
        }
    }
}
