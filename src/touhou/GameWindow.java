package touhou;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Anhdd on 7/31/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;

    //window
    private Graphics2D windowGraphics;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;

    private BufferedImage background;
    private BufferedImage player;
    private int playerX = 176;
    private int playerY = 610;
    private int PlayerM = 1;
    private int backgroundY = -3109 + 730 ;
    private boolean right, left, up, down;
    private int backgroundM = 1;

    public  GameWindow(){
        background = SpriteUtils.loadImage("assets/images/background/0.png");
        player = SpriteUtils.loadImage("assets/images/players/straight/3.png");
        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(384, 730);

        this.setTitle("Touhou - Remade by 5kywa1k3r");
        //backbuffer
        this.backbufferImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        //this == window
        this.windowGraphics = (Graphics2D) this.getGraphics();

        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    right = true;
                    System.out.println("PRIGHT");
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    left = true;
                    System.out.println("PLEFT");
                }

                if (e.getKeyCode() == KeyEvent.VK_UP){
                    up = true;
                    System.out.println("PUP");
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    down = true;
                    System.out.println("PDOWN");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    right = false;
                    System.out.println("RRIGHT");
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    left = false;
                    System.out.println("RLEFT");
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    up = false;
                    System.out.println("RUP");
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    down = false;
                    System.out.println("RDOWN");
                }
            }
        });
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.currentTimeMillis();
            }
            currentTime = System.currentTimeMillis();
            if (currentTime - lastTimeUpdate > 3) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {
        if (backgroundY + backgroundM  >= 0)
            backgroundY = 0;
        else
            backgroundY += backgroundM;

        if ( right ){
            playerX += PlayerM;
            if (playerX>getWidth() - 30) playerX = getWidth() - 30;
        }
        if ( left ){
            playerX -= PlayerM;
            if (playerX<0) playerX = 0;
        }
        if ( up ){
            playerY -= PlayerM;
            if (playerY < 30) playerY = 30;
        }
        if ( down ){
            if (backgroundY == 0) playerY += PlayerM;
            else playerY += 2*PlayerM;
            if (playerY > getHeight() - 50) playerY = getHeight() -50;
        }
    }

    private void render() {
        if (windowGraphics == null) {
            windowGraphics = (Graphics2D) getGraphics();
        }
        backbufferGraphics.setColor(Color.BLACK);
        backbufferGraphics.fillRect(0,0,1024,768);
        backbufferGraphics.drawImage(background,0,backgroundY,null);
        backbufferGraphics.drawImage(player, playerX, playerY,null);
        //backbufferImage chua du hinh anh
        windowGraphics.drawImage(backbufferImage,0,0,null);
    }
}