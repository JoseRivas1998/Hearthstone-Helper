package com.tcg.hearthstoneHelper;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Stage window;
	
	private HashMap<String, ArrayList<Card>> cards;
	
	public static final String BOLD = "-fx-font-weight: bold";
	
	private int currentIndex;
	private int currentSet;
	private JSONObject cardsJson;
	private ImageView imgView;
	private Image currentImage;
	private Text cCardName, cCardRarity, cCardType, cCardClass, cCardCost, cCardAttack, cCardHP, cCardSet;
	
	private String[] sets = {
		"Basic"	
	};
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Hearthstone Helper");

		cards = new HashMap<String, ArrayList<Card>>();
		
		for(String s : sets) {
			cards.put(s, new ArrayList<Card>());
		}
		
		currentSet = 0;
		
		initCards();
		
		currentIndex = 0;
		
		window.setScene(initScene());
		window.getIcons().add(new Image(FileHandle.inputStreamFromFile("/icon.png")));
		window.setResizable(false);
		window.setWidth(500);
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

	private Scene initScene() {
		
		imgView = new ImageView();
		currentImage = new Image(FileHandle.inputStreamFromFile(getCurrentCard().getImgPath()));
		imgView.setImage(currentImage);
		
		StackPane img = new StackPane();
		img.getChildren().addAll(imgView);
		
		Text cardNameText = getBoldText("Name:");
		
		cCardName = new Text();
		
		Text cardRarityText = getBoldText("Rarity:");
		
		cCardRarity = new Text();
		
		Text cardTypeText = getBoldText("Type:");
		
		cCardType = new Text();
		
		Text cardClassText = getBoldText("Class:");
		
		cCardClass = new Text();
		
		Text cardCostText = getBoldText("Cost:");
		
		cCardCost = new Text();
		
		Text cardAttackText = getBoldText("Attack:");
		
		cCardAttack = new Text();
		
		Text cardHPText = getBoldText("HP:");
		
		cCardHP = new Text();
		
		Text cardSetText = getBoldText("Set:");
		
		cCardSet = new Text();
		
		updateCurrentCard();
		
		HBox[] cardBoxes = {
			getTextHBox(cardNameText, cCardName), getTextHBox(cardRarityText, cCardRarity),
			getTextHBox(cardTypeText, cCardType), getTextHBox(cardClassText, cCardClass),
			getTextHBox(cardCostText, cCardCost), getTextHBox(cardAttackText, cCardAttack),
			getTextHBox(cardHPText, cCardHP), getTextHBox(cardSetText, cCardSet)
		};
		
		Button prev = new Button();
		prev.setFont(Font.loadFont(FileHandle.inputStreamFromFile("/fontawesome.ttf"), 12));
		prev.setText("\uf060");
		prev.setOnAction(e -> {
			currentIndex--;
			if(currentIndex < 0) {
				currentSet--;
				if(currentSet < 0) {
					currentSet = sets.length - 1;
				}
				currentIndex = cards.get(sets[currentSet]).size() - 1;
			}
			updateCurrentCard();
		});
		
		Button next = new Button();
		next.setFont(Font.loadFont(FileHandle.inputStreamFromFile("/fontawesome.ttf"), 12));
		next.setText("\uf061");
		next.setOnAction(e -> {
			currentIndex++;
			if(currentIndex >= cards.get(sets[currentSet]).size()) {
				currentSet++;
				if(currentSet >= sets.length) {
					currentSet = 0;
				}
				currentIndex = 0;
			}
			updateCurrentCard();
		});
		
		HBox buttonBox = new HBox(5);
		buttonBox.getChildren().addAll(prev, next);
		
		VBox right = new VBox();
		right.getChildren().addAll(buttonBox);
		right.getChildren().addAll(cardBoxes);
		right.setPadding(new Insets(40, 20, 0, 0));
		
		HBox layout = new HBox(10);
		layout.getChildren().addAll(img, right);
		
		Scene scene = new Scene(layout);
		return scene;
	}
	
	public Card getCurrentCard() {
		return cards.get(sets[currentSet]).get(currentIndex);
	}
	
	private HBox getTextHBox(Text... texts) {
		HBox box = new HBox(5);
		box.getChildren().addAll(texts);
		return box;
	}
	
	private Text getBoldText(String text) {
		Text t = new Text(text);
		t.setStyle(BOLD);
		return t;
	}
	
	private void updateCurrentCard() {
		currentImage = new Image(FileHandle.inputStreamFromFile(getCurrentCard().getImgPath()));
		imgView.setImage(currentImage);
		cCardName.setText(getCurrentCard().getCardName());		
		cCardRarity.setText(getCurrentCard().getRarity());		
		cCardType.setText(getCurrentCard().getType());		
		cCardClass.setText(getCurrentCard().getCardClass());
		cCardCost.setText(String.valueOf(getCurrentCard().getCost()));
		cCardAttack.setText(String.valueOf(getCurrentCard().getAttack()));
		cCardHP.setText(String.valueOf(getCurrentCard().getHealth()));
		cCardSet.setText(String.valueOf(getCurrentCard().getSet()));
	}
	
}
