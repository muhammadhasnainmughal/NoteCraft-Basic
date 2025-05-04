package NoteCraftpackage;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class chatbot {

    // Method to open the chatbot window
    public void openChatbotWindow() {
        // Create a new JFrame for the chatbot
        JFrame chatbotFrame = new JFrame("Ask NoteCraft");
        chatbotFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Increase the default window size
        chatbotFrame.setSize(800, 600); // Set to a larger size for better usability

        // Center the chatbot window on the screen
        chatbotFrame.setLocationRelativeTo(null);

        chatbotFrame.setLayout(new BorderLayout());

        // Chat area to display conversation history
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        chatbotFrame.add(scrollPane, BorderLayout.CENTER);

        // Input panel for user interaction
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField userInputField = new JTextField();
        JButton sendButton = new JButton("Send");
        inputPanel.add(userInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        chatbotFrame.add(inputPanel, BorderLayout.SOUTH);

        // Action listener for the send button
        sendButton.addActionListener(e -> {
            String userMessage = userInputField.getText().trim();
            if (!userMessage.isEmpty()) {
                // Display user's message in the chat area
                chatArea.append("You: " + userMessage + "\n");
                userInputField.setText("");

                // Send the message to the chatbot API and display the response
                SwingUtilities.invokeLater(() -> {
                    String chatbotResponse = sendMessageToChatbotAPI(userMessage);
                    chatArea.append("Chatbot: " + chatbotResponse + "\n");
                });
            }
        });

        // Focus on the input field when the window opens
        chatbotFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                userInputField.requestFocusInWindow();
            }
        });

        // Make the chatbot window visible
        chatbotFrame.setVisible(true);
    }


    // Method to send a message to the chatbot API
    private String sendMessageToChatbotAPI(String userMessage) {
        try {
            // Build the HTTP request body
            String requestBody = String.format(
                    "{\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}],\"model\":\"gpt-4o\",\"max_tokens\":100,\"temperature\":0.9}",
                    userMessage
            );

            // Create the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://cheapest-gpt-4-turbo-gpt-4-vision-chatgpt-openai-ai-api.p.rapidapi.com/v1/chat/completions"))
                    .header("x-rapidapi-key", "63266c3046mshec7a5bbf99022d0p122af9jsn3191dca02f28") // Replace with your RapidAPI key
                    .header("x-rapidapi-host", "cheapest-gpt-4-turbo-gpt-4-vision-chatgpt-openai-ai-api.p.rapidapi.com")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the request and get the response
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check for successful response
            if (response.statusCode() == 200) {
                return parseChatbotResponse(response.body());
            } else {
                return "Error: Unable to fetch response (Status Code: " + response.statusCode() + ")";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while communicating with the chatbot.";
        }
    }

    // Method to parse the chatbot API response
    private String parseChatbotResponse(String responseBody) {
        try {
            JsonObject jsonResponse = com.google.gson.JsonParser.parseString(responseBody).getAsJsonObject();
            JsonArray choices = jsonResponse.getAsJsonArray("choices");

            if (choices.size() > 0) {
                JsonObject message = choices.get(0).getAsJsonObject().getAsJsonObject("message");
                return message.get("content").getAsString();
            } else {
                return "No response from the chatbot.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing the chatbot response.";
        }
    }
}
