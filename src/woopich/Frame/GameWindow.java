package woopich.Frame;

import woopich.Units.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GameWindow extends JFrame{

    //Размер окна
    public int WIDTH = 1600;
    public int HEIGHT = 1100;

    //Toolbar
    private static JToolBar toolbar = new JToolBar("Do smth with fishes");

    //Счетчики
    private Random random = new Random();
    public static int i = 0;
    public static int j = 0;

    //Массив рыб
    public static ArrayList<Sprite> blueSprites = new ArrayList<>();
    public static ArrayList<Sprite> purpleSprites = new ArrayList<>();
    public static ArrayList<Sprite> booms = new ArrayList<>();

    //Имена файлов
    private String file1 = "res/fish1.png";
    private String file3 = "res/fish3.png";

    public GameWindow(){

        JFrame frame = new JFrame("AQUA WORLD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600,1100 );
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Создание кнопок
        JButton btnPlsBlue = new JButton(new ImageIcon("res/plus_blue.png"));
        btnPlsBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blueSprites.add(new Sprite(file1, random.nextInt(1600), random.nextInt(1200)));
                i++;
            }
        });
        JButton btnPlsPurple = new JButton(new ImageIcon("res/plus_purple.png"));
        btnPlsPurple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purpleSprites.add(new Sprite(file3, random.nextInt(1600), random.nextInt(1200)));
                j++;
            }
        });
        JButton btnMnsBlue = new JButton(new ImageIcon("res/minus_blue.png"));
        btnMnsBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (i > 0) {
                    blueSprites.remove(0);
                    i--;
                }
                else
                    JOptionPane.showMessageDialog(frame, "NETU");
            }
        });
        JButton btnMnsPurple = new JButton(new ImageIcon("res/minus_purple.png"));
        btnMnsPurple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (j > 0) {
                    purpleSprites.remove(0);
                    j--;
                }
                else
                    JOptionPane.showMessageDialog(frame, "NETU");
            }
        });
        JButton btnMenu = new JButton(new ImageIcon("res/menu.png"));
        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameScreen.states = GameScreen.STATES.MENU;
            }
        });
        JButton btnFifty = new JButton(new ImageIcon("res/50.png"));
        btnFifty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i1 = 0; i1 < 50; i1++)    {
                    try {
                        blueSprites.add(new Sprite(file1, random.nextInt(1600), random.nextInt(1200)));
                        i++;
                    } catch (Exception f)
                    {
                        //
                    }
                }
            }
        });
        JButton btnFiftyf = new JButton(new ImageIcon("res/50f.png"));
        btnFiftyf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 50; i++)    {
                    try {
                        purpleSprites.add(new Sprite(file3, random.nextInt(1600), random.nextInt(1200)));
                        j++;
                    } catch (Exception f)
                    {
                        //
                    }
                }
            }
        });

        // Создание Toolbar'a
        toolbar.add(btnMenu);
        toolbar.add(btnPlsBlue);
        toolbar.add(btnMnsBlue);
        toolbar.add(btnFifty);
        toolbar.add(btnPlsPurple);
        toolbar.add(btnMnsPurple);
        toolbar.add(btnFiftyf);
        frame.getContentPane().add(toolbar, BorderLayout.PAGE_START);
        toolbar.setEnabled(false);
        toolbar.setVisible(false);

        // Добавление окна
        frame.add(new GameScreen());
        frame.setVisible(true);
    }

    public static JToolBar getToolbar() {
        return toolbar;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}