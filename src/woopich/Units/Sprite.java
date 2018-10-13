package woopich.Units;

import woopich.Frame.GameScreen;
import woopich.Frame.GameWindow;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class Sprite
{

    //Координаты
    private int x;
    private int y;
    private int dWidth;
    private int dHeight;
    private Random random = new Random();
    private int newX = random.nextInt(1600);
    private int newY = random.nextInt(1100);

    //Изображение
    private Image image;

    //Звук
    private File eat = new File("res/eat1.wav");
    private File boomAudio = new File("res/boom.wav");
    private File plop = new File("res/Plop.wav");


    //Поворот
    private boolean turn = true;

    //Счетчики для анимации
    private short anim = 0;
    private short anim_ ;
    private short boomAnim = 0;
    private short s = 0;

    //Конструктор для создания разных рыб
    public Sprite(String fileName, int x, int y){
        image = new ImageIcon(fileName).getImage();
        this.x = x;
        this.y = y;
        dHeight = getImage().getHeight(null);
        dWidth = getImage().getWidth(null);
    }

    //Движение рыбы
    public void move() {
        if (x > newX) {
            x--;
            turn = true;
        } else if (x < newX) {
            x++;
            turn = false;
        } else {
            newX = random.nextInt(1600);
        }
        if (y > newY) {
            y--;
        } else if (y < newY) {
            y++;
        } else {
            newY = random.nextInt(1200);
        }
    }

    //Анимация рыбы
    public void animation(Graphics2D g2d, String fileName1, String fileName2) {
        if (anim < 12) {
            anim++;
            if (anim % 12 == 0) {
                setImage(new ImageIcon(fileName1).getImage());
                anim_ = 0;
            }
        } else {
            if (anim_ < 12) {
                anim_++;
                if (anim_ % 12 == 0) {
                    setImage(new ImageIcon(fileName2).getImage());
                    anim = 0;
                }
            }
        }
        if (getTurn()) {
            g2d.drawImage(getImage(), getX() + dWidth / 2, getY(), -dWidth, dHeight, null);
        } else {
            g2d.drawImage(getImage(), getX() - dWidth / 2, getY(), dWidth, dHeight, null);
        }
    }

    //Поедание рыб
    public void eat(int index) {
            for (int i = 0; i < GameWindow.blueSprites.size(); i++) {
                if (GameWindow.blueSprites.get(i).getX() > getX() && GameWindow.blueSprites.get(i).getX() + GameWindow.blueSprites.get(i).dWidth < getX() + dWidth && getY() < GameWindow.blueSprites.get(i).getY() && getY() + dHeight > GameWindow.blueSprites.get(i).getY() + GameWindow.blueSprites.get(i).dHeight) {
                    //
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(eat));
                        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-20);
                        clip.start();
                    }
                    catch (Exception e) {
                        //Never
                    }
                    //
                    if (dHeight < 280) {
                        dHeight += 10;
                        dWidth += 14;
                    }
                    else {
                        GameWindow.booms.add(new Sprite("res/boom.png",getX(),getY()));
                        try {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(boomAudio));
                            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                            gainControl.setValue(-10);
                            clip.start();
                        }
                        catch (Exception e) {
                            //never
                        }
                        try {
                            GameWindow.purpleSprites.remove(index);
                            GameWindow.j --;
                        } catch (IndexOutOfBoundsException e)  {
                            //e.getLocalizedMessage();
                        }
                    }
                    GameWindow.blueSprites.remove(i);
                    GameWindow.i--;
                }
            }

    }

    //Взрыв
    public void boom(Graphics2D g2d)  {
        if (s < 1000) {
            s++;
            g2d.drawImage(getImage(), getX() - dWidth/2, getY() - dHeight/2, null);
        } else {
            s = 0;
        }
    }

    public void boomAnim () {
        if (boomAnim < 99) {
            boomAnim++;
            if (boomAnim % 40 == 0) {
                setImage(new ImageIcon("res/bang.png").getImage());
            }
            if (boomAnim % 99 == 0) {
                setImage(new ImageIcon("res/deadFish.png").getImage());
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(plop));
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-10);
                    clip.start();
                }
                catch (Exception e) {
                    //never
                }
            }
        }
    }

    public void boomMove (int i) {
        if (boomAnim % 99 ==0) {

            if (y < 2300) {
                y++;
            }
            else {
                GameWindow.booms.remove(i);
            }
        }
    }

    //Getters
    public boolean getTurn() {
        return turn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    //Setters
    public void setImage(Image image) {
        this.image = image;
    }

}