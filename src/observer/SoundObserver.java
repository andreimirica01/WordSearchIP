package observer;

import adapter.MusicPlayer;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;




 interface Observer {
    void update();
}

public class SoundObserver implements Observer {
    @Override
    public void update() {
        System.out.println("Playing sound...");
  MusicPlayer musicPlayer = new MusicPlayer("src\\resources\\nice.wav");
  musicPlayer.play();
   //   Image image = null;
  
        // creaza frame-ul
        JFrame frame = new JFrame();
        frame.setSize(505,656);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // adauga imaginea la frame
        JLabel label = new JLabel(new ImageIcon("src\\resources\\niceimage.jpeg"));
        frame.add(label);
        // afiseaza frame-ul
        frame.setVisible(true);
        // creaza un timer pentru a inchide frame-ul dupa o secunda
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
  
  
    }
}