package gui;

import javax.swing.*;
import java.awt.*;

/**
 * The main control panel.
 */
public class ControlPanel {
    private final JFrame frame;

    public ControlPanel() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(800, 500);

        frame.add(new SideViewPanel(), BorderLayout.WEST);
        frame.add(new ManagePanel(), BorderLayout.EAST);
    }

    public void present() {
        frame.setVisible(true);
    }
}
