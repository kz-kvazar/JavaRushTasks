package com.javarush.task.task32.task3209;

import javax.swing.text.html.HTMLDocument;
import java.io.File;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View view1 = new View();
        Controller controller= new Controller(view1);

        view1.setController(controller);
        view1.init();

        controller.init();

    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void init(){

    }
    public void exit(){
        System.exit(0);
    }

}
