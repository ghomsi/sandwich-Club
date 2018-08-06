package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {

        JSONObject sandwichJson = null;
        Sandwich sandwich = null;
        List<String> alsoKnownAs = new ArrayList<String>();
        List<String> ingredients =  new ArrayList<String>();




        try {
            sandwichJson = new JSONObject(json);
            Log.i("sandwichJson", "parseSandwichJson: "+sandwichJson);
            JSONObject names = sandwichJson.getJSONObject("name");


            if(names.getJSONArray("alsoKnownAs")!=null){
                for(int i=0;i<names.getJSONArray("alsoKnownAs").length();i++){
                    alsoKnownAs.add(names.getJSONArray("alsoKnownAs").getString(i));
                }

            }
            if (sandwichJson.getJSONArray("ingredients")!=null){
                for (int i=0;i<sandwichJson.getJSONArray("ingredients").length();i++){
                    ingredients.add(sandwichJson.getJSONArray("ingredients").getString(i));
                }
            }

            sandwich = new Sandwich(
                    names.getString("mainName"),
                    alsoKnownAs,
                    sandwichJson.getString("placeOfOrigin"),
                    sandwichJson.getString("description"),
                    sandwichJson.getString("image"),
                    ingredients
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
