package edu.sjsu.cmpe275.lab2.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.sjsu.cmpe275.lab2.Phone;
import edu.sjsu.cmpe275.lab2.User;
import org.json.JSONException;

public class Helper {

    /**
     * Returns JSON string for phone entity
     * @param phone
     * @return json string
     */
    public static String phoneJsonBuilder(Phone phone) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":\"" + phone.getId() + "\",");
        sb.append("\"number\":\"" + phone.getNumb() + "\",");
        sb.append("\"description\":\"" + phone.getDescription() + "\",");
        sb.append("\"address\":{");
        sb.append("\"street\":\"" + phone.getAddress().getStreet() + "\",");
        sb.append("\"city\":\"" + phone.getAddress().getCity() + "\",");
        sb.append("\"state\":\"" + phone.getAddress().getState() + "\",");
        sb.append("\"zip\":\"" + phone.getAddress().getZip() + "\"},");
        sb.append("\"users\":[");
        for (int i = 0; i < phone.getUserList().size(); i++) {
            if (i>0) sb.append(",");
            sb.append(
                    "{" +
                            "\"id\":\"" + phone.getUserList().get(i).getId() + "\","
                            +
                            "\"firstname\":\"" + phone.getUserList().get(i).getFirstname() + "\","
                            +
                            "\"lastname\":\"" + phone.getUserList().get(i).getLastname() + "\""
                            +
                            "}"
            );
        }
        sb.append("]" + "}");
        return beautifyJSON(sb.toString());
    }

    /** Returns JSON string for user entity
     *
     * @param user
     * @return json string
     * @throws JSONException
     */
    public static String userJSonBuilder(User user) throws JSONException {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":\"" + user.getId() + "\",");
        sb.append("\"firstname\":\"" + user.getFirstname() + "\",");
        sb.append("\"lastname\":\"" + user.getLastname() + "\",");
        sb.append("\"title\":\"" + user.getTitle() + "\",");
        sb.append("\"address\":{");
        sb.append("\"street\":\"" + user.getAddress().getStreet() + "\",");
        sb.append("\"city\":\"" + user.getAddress().getCity() + "\",");
        sb.append("\"state\":\"" + user.getAddress().getState() + "\",");
        sb.append("\"zip\":\"" + user.getAddress().getZip() + "\"},");
        sb.append("\"phones\":[");
        for (int i = 0; i < user.getPhones().size(); i++) {
            if (i>0) sb.append(",");
            sb.append(
                    "{" +
                            "\"id\":\"" + user.getPhones().get(i).getId() + "\","
                            +
                            "\"number\":\"" + user.getPhones().get(i).getNumb() + "\","
                            +
                            "\"description\":\"" + user.getPhones().get(i).getDescription() + "\""
                            +
                            "}"
            );
        }
        sb.append("]" + "\n" + "}");
        return beautifyJSON(sb.toString());
    }

    /**
     * Beautifies JSON string
     * @param untidyString
     * @return json string
     */
    private static String beautifyJSON(String untidyString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(untidyString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}