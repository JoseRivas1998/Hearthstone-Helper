package com.tcg.hearthstoneHelper;

import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		try {
			JSONObject jsonObject = new JSONObject(JSONUtils.getJsonStringFromFile(getClass().getResource("cards.json").toURI()));
			
			String[] names = JSONUtils.getNames(jsonObject);
			for(String s : names) {
				Card c = new Card(jsonObject, s);
				System.out.println(c);
				System.out.println();
			}
		} catch (JSONException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
