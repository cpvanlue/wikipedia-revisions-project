package edu.bsu.cs222;

import com.sun.javafx.fxml.FXMLLoaderHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;

import static edu.bsu.cs222.UI.collectRecentEditors;
import static edu.bsu.cs222.UI.collectRecentRevisions;

public class Controller {

    public TextField searchField;
    public RadioButton revisionsButton;
    public RadioButton editorsButton;
    public Button searchButton;
    public TextArea resultsTextArea;


    public String collectSearchTermFromView(ActionEvent actionEvent) {
        return searchField.getText();
    }

    public void collectWikiData(ActionEvent actionEvent) {
        String searchTerm = collectSearchTermFromView(actionEvent);
        String results = null;
        if (revisionsButton.isSelected()) {
            results = collectRecentRevisions(searchTerm);
        }
        if (editorsButton.isSelected()) {
            results = collectRecentEditors(searchTerm);
        }
        resultsTextArea.setText(results);
    }
}
