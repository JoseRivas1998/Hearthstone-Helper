package com.tcg.hearthstoneHelper;

import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

public class TestMain {

	public static void main(String[] args) {
		new TestMain();
	}
	
	public TestMain() {
		try {
			JSONObject jsonObject = JSONUtils.getJsonObjectFromFile("/cards.json");
			
			String[] names = JSONUtils.getNames(jsonObject);
			for(String s : names) {
				Card c = new Card(jsonObject, s);
				System.out.println(c);
				System.out.println();
			}
		} catch (JSONException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
