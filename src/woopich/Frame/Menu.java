package woopich.Frame;

import javax.swing.*;
import java.awt.*;

public class Menu {

    //Положение и стороны кнопки
    private int x;
    private int y;
    private double width;
    private double height;

    //Путь к картинке
    private Image img= new ImageIcon("res/button.png").getImage();
    Image image = new ImageIcon("res/aquarium.jpg").getImage();
    //Массив надписей
    String[] list = new String[2];

    public Menu() {

        x = 1600 / 2 - 200;
        y = 20;
        width = 400;
        height = 200;

        list[0] = "Новая игра";
        list[1] = "Выход";
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, 0, 0, 1600, 1100, null);
        if (GameScreen.mouseX > getX() && GameScreen.mouseX < getX() + getWidth() && GameScreen.mouseY > (getY() + getHeight())  && GameScreen.mouseY < (getY() + getHeight()) + 93) {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            int l = (int) g.getFontMetrics().getStringBounds(list[0], g).getWidth();
            g.drawImage(img, x + 10, (int) (y + height + 10), 380, 73, null);
            g.drawString(list[0], (int) (x + width / 2) - l / 2, (int) ((y + 200) + (height / 7) * 2));
        }
        else   {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
            int l = (int) g.getFontMetrics().getStringBounds(list[0], g).getWidth();
            g.drawImage(img, x, (int) (y + height), null);
            g.drawString(list[0], (int) (x + width / 2) - l / 2, (int) ((y + 200) + (height / 7) * 2));

        }
        if (GameScreen.mouseX > getX() && GameScreen.mouseX < getX() + getWidth() && GameScreen.mouseY > (getY() + getHeight())*2 && GameScreen.mouseY < (getY() + getHeight())*2 + 93) {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            int l = (int) g.getFontMetrics().getStringBounds(list[1], g).getWidth();
            g.drawImage(img, x + 10, (int) (y + height + 10)*2, 380, 73, null);
            g.drawString(list[1], (int) (x + width / 2) - l / 2, (int) ((y + 200)*2 + (height / 3) * 1));
        }
        else   {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
            int l = (int) g.getFontMetrics().getStringBounds(list[1], g).getWidth();
            g.drawImage(img, x, (int) (y + height)*2, null);
            g.drawString(list[1], (int) (x + width / 2) - l / 2, (int) ((y + 200)*2 + (height / 10) * 3));
        }

    }

    public void checkMouse()    {
        //
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
