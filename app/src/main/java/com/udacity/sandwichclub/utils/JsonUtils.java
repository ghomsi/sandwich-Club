package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String JSON_KEY="name";


    public static Sandwich parseSandwichJson(String json) {

        JSONObject sandwichJson = null;
        Sandwich sandwich = null;
        List<String> alsoKnownAs = new ArrayList<String>();
        List<String> ingredients =  new ArrayList<String>();




        try {
            sandwichJson = new JSONObject(json);
            Log.i("sandwichJson", "parseSandwichJson: "+sandwichJson);
            JSONObject names = sandwichJson.optJSONObject(JSON_KEY);


            if(names.optJSONArray("alsoKnownAs")!=null){
                for(int i=0;i<names.optJSONArray("alsoKnownAs").length();i++){
                    alsoKnownAs.add(names.optJSONArray("alsoKnownAs").getString(i));
                }

            }
            if (sandwichJson.getJSONArray("ingredients")!=null){
                for (int i=0;i<sandwichJson.optJSONArray("ingredients").length();i++){
                    ingredients.add(sandwichJson.optJSONArray("ingredients").getString(i));
                }
            }

            sandwich = new Sandwich(
                    names.optString("mainName"),
                    alsoKnownAs,
                    sandwichJson.optString("placeOfOrigin"),
                    sandwichJson.optString("description"),
                    sandwichJson.optString("image"),
                    ingredients
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
