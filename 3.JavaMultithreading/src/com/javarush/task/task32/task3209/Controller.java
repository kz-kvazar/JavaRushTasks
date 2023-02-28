package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View view1 = new View();
        Controller controller = new Controller(view1);

        view1.setController(controller);
        view1.init();

        controller.init();

    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void init() {
        createNewDocument();
    }

    public void exit() {
        System.exit(0);
    }

    public void setPlainText(String text) {
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try {
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        fileChooser.setDialogTitle("Open File");

        if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION){
            currentFile = fileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try (FileReader reader = new FileReader(currentFile)){
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.read(reader,document,0);
                view.resetUndo();
            }catch (Exception e){
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile == null) {
            saveDocumentAs();
        } else {
            try (FileWriter writer = new FileWriter(currentFile)) {
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.write(writer, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        fileChooser.setDialogTitle("Save File");
        if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter writer = new FileWriter(currentFile)) {
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.write(writer, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }
}
