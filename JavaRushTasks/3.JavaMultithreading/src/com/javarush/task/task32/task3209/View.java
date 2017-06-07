package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NazarenkoDS on 16.05.2017.
 */
public class View extends JFrame implements ActionListener {
    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        }
    }
    private Controller controller;
    private UndoManager undoManager=new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);
    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void init(){
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }
    public void exit(){
        controller.exit();
    }

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    public void initMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);

        getContentPane().add(jMenuBar, BorderLayout.NORTH);
    }
    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPane1 = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", jScrollPane1);
        JScrollPane jScrollPane2 = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", jScrollPane2);
        tabbedPane.setPreferredSize(tabbedPane.getPreferredSize());
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }
    public void selectHtmlTab() {
        //Выбирать html вкладку (переключаться на нее)
        tabbedPane.setSelectedIndex(0);
        //Сбрасывать все правки с помощью метода
        resetUndo();
    }
    public void selectedTabChanged() {
        //Метод должен проверить, какая вкладка сейчас оказалась выбранной
        //Если выбрана вкладка с индексом 0 (html вкладка)
        if (tabbedPane.getSelectedIndex() == 0) {
            //значит нам нужно получить текст из plainTextPane и установить его в контроллер с помощью метода setPlainText
            controller.setPlainText(plainTextPane.getText());
        }
        //сли выбрана вкладка с индексом 1 (вкладка с html текстом)
        else if (tabbedPane.getSelectedIndex() == 1){
            //необходимо получить текст у контроллера с помощью метода getPlainText() и установить его в панель plainTextPane
            plainTextPane.setText(controller.getPlainText());
        }
        //Сбросить правки
        resetUndo();
    }

    public boolean canUndo(){

        return undoManager.canUndo();
    }

    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(this, "HTML Editor", "About", JOptionPane.INFORMATION_MESSAGE);
    }
    public boolean canRedo(){
        return undoManager.canRedo();
    }
    public void undo(){
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }
    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }
    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex()==0;
    }
}
