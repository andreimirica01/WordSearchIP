package decorator;


import javax.swing.JLabel;
import java.awt.Color;

interface Label {
    void setColor(Color color);
}

public class LabelDecorator implements Label {
    protected JLabel label;

    public LabelDecorator(JLabel label) {
        this.label = label;
    }

    @Override
    public void setColor(Color color) {
        label.setForeground(color);
    }
}

