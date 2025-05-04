package NoteCraftpackage;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
import org.json.JSONArray;

import okhttp3.*;
import javax.swing.*;
import java.io.IOException;

public class Dictionary
{

    // Base URL of the dictionary API (replace with actual endpoint)
    private static final String API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en";


    public static String findWordInfo(String selectedWord) {
        String apiUrl = "https://api.dictionaryapi.dev/api/v2/entries/en";
        try {
            URL url = new URL(apiUrl + "/" + selectedWord);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // Call the createInfoWindow method here
                new Dictionary().createInfoWindow(selectedWord, response.toString());

                return "Word information displayed.";
            } else {
                return "Failed to fetch information. HTTP response code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch information for: " + selectedWord;
        }
    }


    // Method to display the information in a new GUI window
    private void createInfoWindow(String word, String apiResponse) {
        // Create a new JFrame for displaying word information
        JFrame infoFrame = new JFrame("Word Information: " + word);
        infoFrame.setSize(600, 500); // Set larger dimensions for the window
        infoFrame.setLocationRelativeTo(null); // Center the window on the screen

        // Create a JTextArea for displaying detailed information
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false); // Make the text area non-editable
        infoArea.setWrapStyleWord(true); // Enable word wrapping
        infoArea.setLineWrap(true); // Wrap text to fit the window width

        // Use a StringBuilder to format and add all word details
        StringBuilder formattedInfo = new StringBuilder();
        try {
            JSONArray jsonResponse = new JSONArray(apiResponse);
            JSONObject firstEntry = jsonResponse.getJSONObject(0);

            // Word and phonetics
            formattedInfo.append("Word: ").append(firstEntry.getString("word")).append("\n\n");
            JSONArray phonetics = firstEntry.getJSONArray("phonetics");
            for (int i = 0; i < phonetics.length(); i++) {
                JSONObject phonetic = phonetics.getJSONObject(i);
                if (phonetic.has("text")) {
                    formattedInfo.append("Phonetics: ").append(phonetic.getString("text")).append("\n");
                }
                if (phonetic.has("audio")) {
                    formattedInfo.append("Audio: ").append(phonetic.getString("audio")).append("\n");
                }
            }
            formattedInfo.append("\n");

            // Meanings and definitions
            JSONArray meanings = firstEntry.getJSONArray("meanings");
            for (int i = 0; i < meanings.length(); i++) {
                JSONObject meaning = meanings.getJSONObject(i);
                formattedInfo.append("Part of Speech: ").append(meaning.getString("partOfSpeech")).append("\n");

                JSONArray definitions = meaning.getJSONArray("definitions");
                for (int j = 0; j < definitions.length(); j++) {
                    JSONObject definition = definitions.getJSONObject(j);
                    formattedInfo.append(" - Definition: ").append(definition.getString("definition")).append("\n");

                    if (definition.has("example")) {
                        formattedInfo.append("   Example: ").append(definition.getString("example")).append("\n");
                    }

                    if (definition.has("synonyms")) {
                        JSONArray synonyms = definition.getJSONArray("synonyms");
                        if (synonyms.length() > 0) {
                            formattedInfo.append("   Synonyms: ");
                            for (int k = 0; k < synonyms.length(); k++) {
                                formattedInfo.append(synonyms.getString(k));
                                if (k < synonyms.length() - 1) {
                                    formattedInfo.append(", ");
                                }
                            }
                            formattedInfo.append("\n");
                        }
                    }
                }
                formattedInfo.append("\n");
            }
        } catch (Exception e) {
            formattedInfo.append("Error parsing API response: ").append(e.getMessage());
        }

        // Set the formatted text to the JTextArea
        infoArea.setText(formattedInfo.toString());

        // Add the JTextArea to a scrollable pane
        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        // Make the frame visible
        infoFrame.setVisible(true);
    }

}
