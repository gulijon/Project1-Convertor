        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.EmptyStackException;
        import java.util.Stack;

        //**
        // *UMGC:CMSC 350
        // * Author: Guli Atakuzieva
        // * Date: 11-01-2020
        // */


public class PrefixPostfix {

    public static class GUI extends JFrame {

        JFrame frame = new JFrame("frame");
        private JTextField expressionTextField, resultTextField;
        private JLabel expressionLabel, resultLabel;
        private JButton prefixButton, postfixButton;


        public GUI() {

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(450, 200);
            frame.setTitle("Guli's Expression Converter");
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            setLayout(new GridBagLayout());
            GridBagConstraints window = new GridBagConstraints();
            window.insets = new Insets(5, 5, 5, 5);

            expressionLabel = new JLabel("Enter Expression");
            expressionLabel.setHorizontalAlignment(JLabel.RIGHT);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 0;
            add(expressionLabel, window);

            expressionTextField = new JTextField(20);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 0;
            add(expressionTextField, window);

            prefixButton = new JButton("Prefix to Postfix");
            window.gridx = 0;
            window.gridy = 1;
            add(prefixButton, window);

            postfixButton = new JButton("Postfix to Prefix");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 1;
            add(postfixButton, window);

            resultLabel = new JLabel("Enter Expression");
            resultLabel.setHorizontalAlignment(JLabel.RIGHT);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 3;
            add(resultLabel, window);

            resultTextField = new JTextField(20);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 1;
            window.gridy = 3;
            add(resultTextField, window);

            prefixButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setSize(100, 100);
                    resultTextField.setText("");
                    //grab the text from prefix
                    String prefixExp = expressionTextField.getText();
                    //convert to postfix
                    ExpressionConvertor prefix = new ExpressionConvertor();
                    String postfixExp = prefix.postfixPrefixConvertor(prefixExp, false);
                    if (postfixExp != null) {
                        JOptionPane.showMessageDialog(null, "The postfix expression for prefix " + prefixExp + " is: \n" + postfixExp);
                        //update the info
                        resultTextField.setText(postfixExp);
                    }
                }
            });

            postfixButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //grab the text from prefix
                    String postfixExp = expressionTextField.getText();
                    //convert to postfix
                    ExpressionConvertor postfix = new ExpressionConvertor();
                    String prefixExp = postfix.postfixPrefixConvertor(postfixExp, true);
                    if (prefixExp != null) {
                        JOptionPane.showMessageDialog(null, "The prefix expression for postfix " + postfixExp + " is: \n" + prefixExp);
                        //update the info
                        resultTextField.setText(prefixExp);
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        GUI myUI = new GUI();
        // Establish basic parameters for the GUI
        myUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myUI.setSize(450, 200);
        myUI.setTitle("Guli's Expression Converter");
        myUI.setLocationRelativeTo(null);
        myUI.setVisible(true);
    }
}