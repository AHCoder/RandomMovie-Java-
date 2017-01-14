/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author Ajnol
 */
public class MainScreenController implements Initializable {
    
    @FXML
    private TextField textField1, textField2, textField3, textField4, mpcTextField, vlcTextField;
    @FXML
    private Button browseButton1, browseButton2, browseButton3, browseButton4, mpcBrowseButton, vlcBrowseButton, goButton;
    @FXML
    private RadioButton mpcRadioButton, vlcRadioButton;
    @FXML
    private CheckBox skipCheckBox, fullscreenCheckBox;
    
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private final FileChooser fileChooser = new FileChooser();
    private final List<Path> files = new ArrayList<>();
    
    // ----- Browse buttons ----- //
    @FXML
    private void handleBrowseButton1Action(ActionEvent event) {
        directoryChooser.setTitle("Choose folder");
        File dir = directoryChooser.showDialog(((Node)event.getTarget()).getScene().getWindow());
        textField1.setText(dir.getAbsolutePath());
    }
    
    @FXML
    private void handleBrowseButton2Action(ActionEvent event) {
        directoryChooser.setTitle("Choose folder");
        File dir = directoryChooser.showDialog(((Node)event.getTarget()).getScene().getWindow());
        textField2.setText(dir.getAbsolutePath());
    }
    
    @FXML
    private void handleBrowseButton3Action(ActionEvent event) {
        directoryChooser.setTitle("Choose folder");
        File dir = directoryChooser.showDialog(((Node)event.getTarget()).getScene().getWindow());
        textField3.setText(dir.getAbsolutePath());
    }
    
    @FXML
    private void handleBrowseButton4Action(ActionEvent event) {
        directoryChooser.setTitle("Choose folder");
        File dir = directoryChooser.showDialog(((Node)event.getTarget()).getScene().getWindow());
        textField4.setText(dir.getAbsolutePath());
    }
    // ----- Browse Buttons ----- //
    
    // ----- Player Browse Buttons ----- //
    @FXML
    private void handleMpcBrowseButtonAction(ActionEvent event) {
        fileChooser.setTitle("Choose player .exe");
        File exec = fileChooser.showOpenDialog(((Node)event.getTarget()).getScene().getWindow());
        mpcTextField.setText(exec.getAbsolutePath());
    }
    
    @FXML
    private void handleVlcBrowseButtonAction(ActionEvent event) {
        fileChooser.setTitle("Choose player .exe");
        File exec = fileChooser.showOpenDialog(((Node)event.getTarget()).getScene().getWindow());
        vlcTextField.setText(exec.getAbsolutePath());
    }
    // ----- Player Browse Buttons ----- //
    
    // GO! Button
    @FXML
    private void handleGoButtonAction(ActionEvent event) throws IOException {
    
        String fileName, arguments;
        
        arguments = chooseFile() + "\"";
        if (mpcRadioButton.isSelected()) {
            fileName = mpcTextField.getText();
            if (skipCheckBox.isSelected()) {
                arguments += " /startpos 00:20:00";
            }
            if (fullscreenCheckBox.isSelected()) {
                arguments += " /fullscreen";
            }
        } else {
            fileName = vlcTextField.getText();
            if (skipCheckBox.isSelected()) {
                arguments += " --start-time=1200";
            }
            if (fullscreenCheckBox.isSelected()) {
                arguments += " -f";
            }
        }
        arguments = "\"" + arguments + "\"";
        ProcessBuilder pb = new ProcessBuilder(fileName, arguments);
        pb.start();
    }
    
    // Method for choosing the random files
    private String chooseFile() throws IOException {
        
        Random rand = new Random();

        // Get the files from each directory
        listFiles(Paths.get(textField1.getText()));
        
        if(!"".equals(textField2.getText())) {
            listFiles(Paths.get(textField2.getText()));
        }
            
        if(!"".equals(textField3.getText())) {
            listFiles(Paths.get(textField3.getText()));
        }
            
        if(!"".equals(textField4.getText())) {
            listFiles(Paths.get(textField4.getText()));
        }
        
        // Choose a file at random
        return files.get(rand.nextInt(files.size())).toString();
    }
    
    // Method to recursively list and add the files in each directory
    public void listFiles(Path path) throws IOException {
        
        DirectoryStream<Path> stream;
        stream = Files.newDirectoryStream(path); //, "*.{mkv, mp4, m4p, m4v, vob, avi, mov, wmv, mpg, mpeg, m2v}");
        
        for (Path entry : stream) {
            if (Files.isDirectory(entry)) {
                listFiles(entry);
            }
            files.add(entry);
        }
        stream.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
