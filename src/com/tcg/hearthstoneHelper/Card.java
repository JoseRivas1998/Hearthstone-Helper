package com.tcg.hearthstoneHelper;

import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

public class Card {

	private String cardName;
	private String rarity;
	private String cardClass;
	private String type;
	private int cost;
	private int health;
	private int attack;
	private String set;
	private String imgPath;
	
	public Card(JSONObject jsonObject,String card) throws JSONException, URISyntaxException {
		JSONObject cardObject = jsonObject.getJSONObject(card);
		this.cardName = cardObject.getString("name");
		this.rarity = cardObject.getString("rarity");
		this.type = cardObject.getString("type");
		this.cost = cardObject.getInt("cost");
		this.attack = cardObject.getInt("attack");
		this.health = cardObject.getInt("HP");
		if(JSONUtils.objectExists(cardObject, "class")) {
			this.cardClass = cardObject.getString("class");
		} else {
			this.cardClass = "Neutral";
		}
		this.set = cardObject.getString("set");
		this.imgPath = cardObject.getString("imgPath");
	}

	@Override
	public String toString() {
		return String.format("Name: %s\nRarity: %s\nClass: %s\nType: %s\nCost: %d\nAttack: %d\nHealth: %d\nSet: %s"
				, this.cardName, this.rarity, this.cardClass,this.type, this.cost, this.attack, this.health, this.set);
	}

	public String getCardName() {
		return cardName;
	}

	public String getRarity() {
		return rarity;
	}

	public int getCost() {
		return cost;
	}

	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public String getCardClass() {
		return cardClass;
	}

	public String getType() {
		return type;
	}

	public String getSet() {
		return set;
	}

	public String getImgPath() {
		return imgPath;
	}
	
	
	
}
