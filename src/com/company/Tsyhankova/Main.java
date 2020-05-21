package com.company.Semashko;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    static JMenuItem decreaseMenuItem;
    static JMenuItem stopMenuItem;
    private Field field = new Field();

    static double koefficient = 0;

    static public boolean decrease;

    public Main() {
        super("Программирование и синхронизация потоков");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
        setExtendedState(MAXIMIZED_BOTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if (!pauseMenuItem.isEnabled() &&
                        !resumeMenuItem.isEnabled()) {
                    pauseMenuItem.setEnabled(true);

                }
            }
        };
        ballMenu.add(addBallAction);
        menuBar.add(ballMenu);

        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Приостановить движение"){
            public void actionPerformed(ActionEvent event) {
                field.pause();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);

        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);

        JMenu decreaseMenu = new JMenu("Уменьшение");
        menuBar.add(decreaseMenu);
        Action decreaseAction = new AbstractAction("Включить уменьшение"){
            public void actionPerformed(ActionEvent event) {
                stopMenuItem.setEnabled(true);
                decreaseMenuItem.setEnabled(false);
                decrease = decreaseMenuItem.isEnabled();
            }
        };
        decreaseMenuItem = decreaseMenu.add(decreaseAction);
        decreaseMenuItem.setEnabled(true);

        Action stopAction = new AbstractAction("Выключить уменьшение") {
            public void actionPerformed(ActionEvent event) {
                stopMenuItem.setEnabled(false);
                decreaseMenuItem.setEnabled(true);
                decrease = decreaseMenuItem.isEnabled();
            }
        };
        stopMenuItem = decreaseMenu.add(stopAction);
        stopMenuItem.setEnabled(false);
        JTextField textField = new JTextField("0", 5);
        decreaseMenu.add(textField);
        JButton button = new JButton("Применить.");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    koefficient = Double.parseDouble(textField.getText());
                } catch (NumberFormatException exc) {
                    exc.printStackTrace();
                }
                textField.setText(String.valueOf(koefficient));
            }
        });
        decreaseMenu.add(button);

        getContentPane().add(field, BorderLayout.CENTER);
        decrease = decreaseMenuItem.isEnabled();
    }
    public static void main(String[] args) {
        Main frame = new Main();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}