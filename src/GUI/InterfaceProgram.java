package GUI;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import dictionary.Dictionary;

public class InterfaceProgram {
    protected static Dictionary dic;
    protected static JFrame jframe; // them static
    protected static JPanel app;

    public InterfaceProgram() {
        dic = new Dictionary();
        dic.initData();
    }

    public JPanel control_panel() {
        app = new JPanel();
        app.setLayout(new CardLayout());

        Introduction_Interface introduction_panel = new Introduction_Interface();
        Functions_Interface function_panel = new Functions_Interface();
        app.add("intro", introduction_panel.ui_introduction());
        app.add("function", function_panel.ui_function());

        return app;
    }


    public JPanel getAPP() {
        return this.app;
    }

    public void prepareUi() {
        jframe = new JFrame();
        jframe.setTitle("App dictionary");

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(control_panel());

        jframe.setLocationRelativeTo(null);
        jframe.pack();

        jframe.setVisible(true);
    }




}
