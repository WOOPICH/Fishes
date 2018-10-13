package woopich.Frame;

import woopich.Units.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel implements ActionListener
{
    //Состояние
    public enum STATES {MENU,GAME}
    public static STATES states = STATES.MENU;

    //Буферное изображение
    public BufferedImage image;
    private Graphics2D g;

    //Создание объекта Меню и Заднего фона
    private Menu menu = new Menu();

    //Имена файлов
    private String file1 = "res/fish1.png";
    private String file2 = "res/fish2.png";
    private String file3 = "res/fish3.png";
    private String file4 = "res/fish4.png";

    //Положение мыши
    public static double mouseX;
    public static double mouseY;

    GameScreen()
    {
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        image = new BufferedImage(1600, 1100, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        Timer timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        Image image = new ImageIcon("res/aquarium.jpg").getImage();
        g2d.drawImage(image, 0, 0, 1600, 1100, this);
        //Анимация
        for (Sprite sprite: GameWindow.blueSprites) {
            if (sprite.getImage() == new ImageIcon(file1).getImage() || sprite.getImage() == new ImageIcon(file2).getImage()) {
                sprite.animation(g2d, file1, file2);
            }
        }
        for (Sprite sprite: GameWindow.purpleSprites) {
            if (sprite.getImage() == new ImageIcon(file3).getImage() || sprite.getImage() == new ImageIcon(file4).getImage())  {
                sprite.animation(g2d,file3,file4);
            }
        }
        for (int i = 0; i < GameWindow.booms.size(); i++)  {
            GameWindow.booms.get(i).boom(g2d);
            try {
                GameWindow.booms.get(i).boomAnim();
            }
            catch (IndexOutOfBoundsException e) {
                //Maybe
            }
        }
        //Синхронизация изображения и очистка мусора
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        //MENU
        if (states.equals(STATES.MENU)) {
            GameWindow.getToolbar().setEnabled(false);
            GameWindow.getToolbar().setVisible(false);
            try {
                mouseX = getMousePosition().getX();
                mouseY = getMousePosition().getY();
            }
            catch (NullPointerException f) {
                //WELL YOU F*CKED UP
            }
            //Отрисовка меню и заднего фона
            menu.draw(g);
            //Положение мыши относительно кнопок
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (mouseX > menu.getX() && mouseX < menu.getX() + menu.getWidth() && mouseY > (menu.getY() + menu.getHeight())  && mouseY < (menu.getY() + menu.getHeight()) + 93) {
                        states = STATES.GAME;
                        menu.list[0] = "Продолжить";
                    }
                    if (mouseX > menu.getX() && mouseX < menu.getX() + menu.getWidth() && mouseY > (menu.getY() + menu.getHeight())*2 && mouseY < (menu.getY() + menu.getHeight())*2 + 93) {
                        System.exit(0);
                    }
                }

            });
            //Отрисовка буферных изображений
            gameDraw();
        }

        //GAME
        if (states.equals(STATES.GAME)) {
            GameWindow.getToolbar().setEnabled(true);
            GameWindow.getToolbar().setVisible(true);
            for (Sprite sprite : GameWindow.blueSprites) {
                    sprite.move();
            }
            for (int i = 0; i < GameWindow.purpleSprites.size(); i++) {
                GameWindow.purpleSprites.get(i).move();
                GameWindow.purpleSprites.get(i).eat(i);
            }
            for (int i = 0; i < GameWindow.booms.size(); i++)   {
                GameWindow.booms.get(i).boomMove(i);
            }
            repaint();
        }
    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0,null);
        g2.dispose();
    }
}