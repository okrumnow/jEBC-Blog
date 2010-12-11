package de.jebc.examples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ListenerAsPinExample {

    private ClickListener listener = new ClickListener();
    private javax.swing.JButton btnOK = new JButton();

    public ListenerAsPinExample() {
        btnOK.addActionListener(listener);
    }

    private class ClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // mach was
        }

    }
}
