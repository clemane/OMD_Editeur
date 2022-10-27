import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public final class TextEditorV2 {

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
    private static JButton save;
    private static JButton open;
    private static JButton undo;
    private static JButton redo;

    private static int width = 870;
    private static int height = 600;

    public TextEditorV2() {
        // Init the variables
        frame = new JFrame("Text editor");

        // Text area
        area = new JTextArea();
        area.setBounds(20, 60, 810, 480);
        area.setLineWrap(true);

        // Copy button
        copy = new JButton(new AbstractAction("Copy") {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressepapier = area.getSelectedText();
            }
        });
        copy.setBounds(20, 20, 70, 20);

        // Cut button
        cut = new JButton(new AbstractAction("Cut") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = area.getSelectedText();
                pressepapier = tmp;
                area.replaceSelection("");
            }
        });
        cut.setBounds(100, 20, 60, 20);

        // Paste button
        paste = new JButton(new AbstractAction("Paste") {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.replaceSelection(pressepapier);
            }
        });
        paste.setBounds(170, 20, 70, 20);

        // Selection buttons
        startLeft = new JButton(new AbstractAction("<Start") {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setSelectionStart(area.getSelectionStart() - 1);
                area.grabFocus();
                area.setSelectionColor(Color.GREEN);
            }
        });
        startLeft.setBounds(250, 20, 70, 20);

        startRight = new JButton(new AbstractAction("Start>") {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setSelectionStart(area.getSelectionStart() + 1);
                area.grabFocus();
                area.setSelectionColor(Color.GREEN);
            }
        });
        startRight.setBounds(330, 20, 70, 20);

        endLeft = new JButton(new AbstractAction("<End") {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setSelectionEnd(area.getSelectionEnd() - 1);
                area.grabFocus();
                area.setSelectionColor(Color.GREEN);
            }
        });
        endLeft.setBounds(410, 20, 65, 20);

        endRight = new JButton(new AbstractAction("End>") {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setSelectionEnd(area.getSelectionEnd() + 1);
                area.grabFocus();
                area.setSelectionColor(Color.GREEN);
            }
        });
        endRight.setBounds(485, 20, 65, 20);

        // Save button
        save = new JButton(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileWriter script = new FileWriter("filename.txt")) {
                    try {
                        area.write(script);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        });
        save.setBounds(680, 20, 70, 20);

        // Open button
        open = new JButton(new AbstractAction("Open") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileReader script = new FileReader("filename.txt")) {
                    try {
                        area.read(script, null);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        });
        open.setBounds(760, 20, 70, 20);

        // Document settings
        UndoManager manager = new UndoManager();
        area.getDocument().addUndoableEditListener(manager);

        // Undo button
        undo = new JButton(new AbstractAction("<=") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.undo();
                } catch (CannotUndoException ex) {

                }
            }
        });
        undo.setBounds(560, 20, 50, 20);

        // Redo button
        redo = new JButton(new AbstractAction("=>") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.redo();
                } catch (CannotRedoException ex) {

                }
            }
        });
        redo.setBounds(620, 20, 50, 20);

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
        frame.add(undo);
        frame.add(redo);
        frame.add(save);
        frame.add(open);
        frame.setVisible(true);
    }

}
