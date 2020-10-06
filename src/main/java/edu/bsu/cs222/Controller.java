package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.ParseException;

import static edu.bsu.cs222.UI.collectRecentEditors;
import static edu.bsu.cs222.UI.collectRecentRevisions;

public class Controller {

    public TextField searchField;
    public RadioButton revisionsButton;
    public RadioButton editorsButton;
    public Button searchButton;
    public TextArea resultsTextArea;


    public String collectSearchTermFromView() {
        return searchField.getText();
    }

    public void collectWikiData(ActionEvent actionEvent) throws ParseException {
        String searchTerm = collectSearchTermFromView();
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
