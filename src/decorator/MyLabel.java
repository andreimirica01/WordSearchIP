/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decorator;

import javax.swing.JLabel;

/**
 *
 * @author Andrei
 */
 public class MyLabel extends LabelDecorator {
    public MyLabel(JLabel label) {
        super(label);
    }
}