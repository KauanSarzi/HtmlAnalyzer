import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class HtmlAnalyzer {

    public static void main(String[] args) {
        // checks if the URL was provided as an argument
        if (args.length == 0) {
            return;
        }

        String urlString = args[0];
        try {
            processHtml(urlString);
        } catch (IOException e) {
            // displays a specific message in case of connection error
            System.out.println("URL connection error");
        } catch (Exception e) {
            // handles other unexpected errors
            System.out.println("URL connection error");
        }
    }

    // reads the content from the URL and identifies the deepest text node
    private static void processHtml(String urlString) throws IOException {
        URL url = new URL(urlString);
        
        Stack<String> tagStack = new Stack<>();
        String deepestText = null;
        int maxDepth = -1;
        boolean structureError = false;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                
                // ignores empty lines
                if (line.isEmpty()) {
                    continue;
                }

                if (isOpeningTag(line)) {
                    String tagName = extractTagName(line);
                    tagStack.push(tagName);
                } else if (isClosingTag(line)) {
                    String tagName = extractTagName(line);
                    
                    // checks if the closing tag matches the last opened tag
                    if (tagStack.isEmpty() || !tagStack.peek().equals(tagName)) {
                        structureError = true;
                        break;
                    }
                    tagStack.pop();
                } else {
                    // If it is not a tag, it is considered text content
                    int currentDepth = tagStack.size();
                    
                    //updates the result if this text is at a deeper level
                    if (currentDepth > maxDepth) {
                        maxDepth = currentDepth;
                        deepestText = line;
                    }
                }
            }
        }

        // FINAL STRUCTURE VALIDATION (all tags must be properly closed)
        if (structureError || !tagStack.isEmpty()) {
            System.out.println("malformed HTML");
        } else if (deepestText != null) {
            System.out.println(deepestText);
        }
    }

    // CHECKS IF THE LINE IS AN OPENING TAG
    private static boolean isOpeningTag(String line) {
        return line.startsWith("<") && !line.startsWith("</") && line.endsWith(">");
    }

    // CHECKS IF THE LINE IS A CLOSING TAG
    private static boolean isClosingTag(String line) {
        return line.startsWith("</") && line.endsWith(">");
    }

    // EXTRACTS THE TAG NAME REMOVING <, > AND / CHARACTERS
    private static String extractTagName(String line) {
        if (line.startsWith("</")) {
            return line.substring(2, line.length() - 1);
        } else {
            return line.substring(1, line.length() - 1);
        }
    }
}
