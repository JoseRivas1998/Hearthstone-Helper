package com.tcg.hearthstoneHelper;

import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

public class Card {

	private String cardName;
	private String rarity;
	private int cost;
	private int health;
	private int attack;
	
	public Card(JSONObject jsonObject,String card) throws JSONException, URISyntaxException {
		JSONObject cardObject = jsonObject.getJSONObject(card);
		this.cardName = cardObject.getString("name");
		this.cost = cardObject.getInt("cost");
		this.attack = cardObject.getInt("attack");
		this.health = cardObject.getInt("HP");
		this.rarity = cardObject.getString("rarity");
	}

	@Override
	public String toString() {
		return String.format("%s is a %d cost %d - %d card.\nRarity: %s", this.cardName, this.cost, this.attack, this.health, this.rarity);
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
	
	
	
}
