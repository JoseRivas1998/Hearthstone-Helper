package com.tcg.hearthstoneHelper;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Stage window;
	
	private HashMap<String, ArrayList<Card>> cards;
	
	private String currentHash;
	private int currentIndex;
	private JSONObject cardsJson;
	private ImageView imgView;
	private Image currentImage;
	private Text cCardName;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Hearthstone Helper");

		cards = new HashMap<String, ArrayList<Card>>();
		
		cards.put("Basic", new ArrayList<Card>());
		
		initCards();
		
		currentHash = "Basic";
		currentIndex = 0;
		
		imgView = new ImageView();
		currentImage = new Image(FileHandle.inputStreamFromFile(getCurrentCard().getImgPath()));
		imgView.setImage(currentImage);
		
		StackPane img = new StackPane();
		img.getChildren().addAll(imgView);
		
		Text cardNameText = new Text("Name:");
		cardNameText.setStyle("-fx-font-weight: bold");
		
		cCardName = new Text(getCurrentCard().getCardName());
		
		HBox cardNameHBox = new HBox(5);
		cardNameHBox.getChildren().addAll(cardNameText, cCardName);
		
		VBox right = new VBox();
		right.getChildren().addAll(cardNameHBox);
		
		HBox layout = new HBox(10);
		layout.getChildren().addAll(img, right);
		
		Scene scene = new Scene(layout);
		
		window.setScene(scene);
		window.getIcons().add(new Image(FileHandle.inputStreamFromFile("/icon.png")));
		window.show();
	}
	
	private void initCards() throws JSONException, URISyntaxException {
		
		cardsJson = JSONUtils.getJsonObjectFromFile("/cards.json");
		
		String[] names = JSONUtils.getNames(cardsJson);
		
		for(String s : names) {
			Card c = new Card(cardsJson, s);
			if(cards.get(c.getSet()) != null) {
				cards.get(c.getSet()).add(c);
			} else {
				System.out.println("Card: " + c.getCardName() + " could not be added, its set: " + c.getSet() + " does not exist");
			}
		}
	}

	public Card getCurrentCard() {
		return cards.get(currentHash).get(currentIndex);
	}
	
}
