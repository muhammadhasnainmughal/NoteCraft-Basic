package NoteCraftpackage;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Summary {
    GUI gui;

    // Constructor
    public Summary(GUI gui) {
        this.gui = gui;
    }

    // Method to generate a summary using the external API
    public void summary() {
        // Create a new JFrame for the summary
        JFrame summaryFrame = new JFrame("Text Summary");
        summaryFrame.setSize(400, 300);
        summaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        summaryFrame.setLocationRelativeTo(null);

        // Panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Creating a JTextArea to show the summary or error message
        JTextArea summaryArea = new JTextArea();
        summaryArea.setEditable(false);  // Make it non-editable
        summaryArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        summaryArea.setPreferredSize(new Dimension(350, 200));

        // Adding the JTextArea to a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(summaryArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Adding an "OK" button to close the window
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> summaryFrame.dispose()); // Close the window when clicked
        panel.add(okButton, BorderLayout.SOUTH);

        // Adding the panel to the frame
        summaryFrame.add(panel);
        summaryFrame.setVisible(true);

        // Call the API asynchronously to get the summary
        SwingUtilities.invokeLater(() -> {
            // Get the text from the current text area
            String textToSummarize = gui.currentTextArea.getText();

            // Send the text to the API for summarization
            String summaryText = null;
            try {
                summaryText = getSummaryFromAPI(textToSummarize);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            // Update the JTextArea with the summary result or error message
            summaryArea.setText(summaryText);
        });
    }

    // Method to send a request to the API and get the summary
    private String getSummaryFromAPI(String text) throws UnsupportedEncodingException {
        // Add a prompt for summarizing the text
        String query = text + " summarize this paragraph";
        String url = "https://google-bard-api-google-ai-bard.p.rapidapi.com/ask?q=" + URLEncoder.encode(query, "UTF-8");

        try {
            // Build the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("x-rapidapi-key", "fbb83a4b62mshc6902a8fa591ccap1f88e0jsn56c44fba4a4b") // Replace with your actual RapidAPI key
                    .header("x-rapidapi-host", "google-bard-api-google-ai-bard.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())  // Use GET method
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Log the full response body for debugging
            System.out.println("API Response: " + response.body());  // Log raw API response

            // Check if the request was successful
            if (response.statusCode() == 200) {
                String apiResponse = response.body();

                // Process the response to extract the summary text (depends on the API's response structure)
                if (apiResponse != null && !apiResponse.isEmpty()) {
                    return apiResponse; // Assuming the API returns a valid summary in the response body
                } else {
                    return "No summary found in the response.";
                }
            } else {
                return "Error: Unable to fetch data. Status code: " + response.statusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while fetching the summary.";
        }
    }
}
