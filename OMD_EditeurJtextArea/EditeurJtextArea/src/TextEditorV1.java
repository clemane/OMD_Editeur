import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public final class TextEditorV1 {
    
    private static JFrame frame;
    private static JTextArea area;
    private static JButton copy;
    private String pressepapier;
    private static JButton cut;
    private static JButton paste;
    private static JButton startLeft;
    private static JButton startRight;
    private static JButton endLeft;
    private static JButton endRight;
    private static int width = 800;
    private static int height = 600;

    public TextEditorV1() { 
        // Init the variables
        frame = new JFrame("Editeur de texte");
        // Text area
        area = new JTextArea();
        area.setBounds(20,60,740,480);
        // Copy button
        copy = new JButton(new AbstractAction("Copier"){
            @Override
            public void actionPerformed(ActionEvent e){
                pressepapier = area.getSelectedText();
            }
        });
        copy.setBounds(20,20,80,20);

        // Cut button
        cut = new JButton(new AbstractAction("Couper"){
            @Override
            public void actionPerformed(ActionEvent e){
                String tmp = area.getSelectedText();
                pressepapier = tmp;
                area.replaceSelection("");
            }
        });
        cut.setBounds(120,20,80,20);

        // Paste button
        paste = new JButton(new AbstractAction("Coller"){
            @Override
            public void actionPerformed(ActionEvent e){
                area.replaceSelection(pressepapier);
            }
        });
        paste.setBounds(220,20,80,20);

        // Selection buttons
        startLeft = new JButton(new AbstractAction("<Début"){
            @Override
            public void actionPerformed(ActionEvent e){
                area.setSelectionStart(area.getSelectionStart()-1);
                area.grabFocus();
                area.setSelectionColor(Color.GREEN);
            }
        });
        startLeft.setBounds(320,20,80,20);

        startRight = new JButton(new AbstractAction("Début>"){
            @Override
            public void actionPerformed(ActionEvent e){
                area.setSelectionStart(area.getSelectionStart()+1);
                area.grabFocus();
                area.setSelectionColor(Color.GREEN);
            }
        });
        startRight.setBounds(420,20,80,20);

        endLeft = new JButton(new AbstractAction("<Fin"){
            @Override
            public void actionPerformed(ActionEvent e){
                area.setSelectionEnd(area.getSelectionEnd()-1);
                area.grabFocus();
                area.setSelectionColor(Color.GREEN);
            }
        });
        endLeft.setBounds(520,20,80,20);

        endRight = new JButton(new AbstractAction("Fin>"){
            @Override
            public void actionPerformed(ActionEvent e){
                area.setSelectionEnd(area.getSelectionEnd()+1);
                area.grabFocus();
                area.setSelectionColor(Color.GREEN);
            }
        });
        endRight.setBounds(620,20,80,20);

        // App window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(width, height);
        
        frame.add(area);
        frame.add(copy);
        frame.add(cut);
        frame.add(paste);
        frame.add(startLeft);
        frame.add(startRight);
        frame.add(endLeft);
        frame.add(endRight);
        frame.setVisible(true);
    }

} 

