package com.example.linkedtable;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

public class Main extends Application {
    //setting counter so after each click new search engine is open in clear order
    private int counter = 0;
    //image design is set in fxml file
    @FXML
    private ImageView backgroundImage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //loading fxml file with components, setting scene
        Parent root = FXMLLoader.load(getClass().getResource("Program.fxml"));
        Scene scene = new Scene(root, 700, 500);
        stage.setScene(scene);
        stage.show();

        backgroundImage = (ImageView) root.lookup("#backgroundImage");
        bindImageSizeToScene();
    }

    //search engine algorithm method
    public void openEngine(ActionEvent event) {
        //creating new SearchEngine objects with default page URL
        List<SearchEngine> searchEngines = new ArrayList<>();
        searchEngines.add(new SearchEngine("Google", "https://www.google.com/search?q="));
        searchEngines.add(new SearchEngine("Bing", "https://www.bing.com/search?q="));
        searchEngines.add(new SearchEngine("DuckDuckGo", "https://duckduckgo.com/?q="));
        searchEngines.add(new SearchEngine("Yahoo", "https://search.yahoo.com/search?p="));
        //obtain movie genre through button name which was clicked
        Button button = (Button) event.getSource();
        String buttonName = button.getText();
        //setting order of search engine and updating search text for more accurate outcome
        SearchEngine currentOne = searchEngines.get(counter);
        String fullURL = currentOne.getUrl() + buttonName + "movielist";

        //open particular browser with fullURL text and if there is an error - print error details
        try {
            Desktop.getDesktop().browse(new URI(fullURL));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        //setting new default search engine - start from first engine if we reached end of list size
        counter = (counter + 1) % searchEngines.size();
    }

    //binding for appropriate resizing of background image based on how user is resizing window
    private void bindImageSizeToScene() {
        backgroundImage.fitWidthProperty().bind(((StackPane) backgroundImage.getParent()).widthProperty());
        backgroundImage.fitHeightProperty().bind(((StackPane) backgroundImage.getParent()).heightProperty());
    }
}
