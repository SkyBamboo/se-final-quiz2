
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class Parser {

    private String xml;

    private void putStart(String key) {
        xml += "<" + key + ">";
    }

    private void putEnd(String key) {
        xml += "</" + key + ">";
    }

    private void putValue(String value) {
        xml += value;
    }

    public static void main(String[] args) {
        new Parser().run();
    }

    private void run() {
        try {
            System.out.println("Please enter the path of JSON file:");
            Scanner scanner = new Scanner(System.in);

            String filePath = scanner.next();
            scanner = new Scanner(new File(filePath));
            String json = "";
            while (scanner.hasNext()) {
                json += scanner.next();
            }

            if (validJson(json)) {
                json = json.replaceAll("\"", "");
                json = json.replaceAll("\n", "");
                json = json.replaceAll(" ", "");

                json = removeFirstChar(json);
                json = removeLastChar(json);

                xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

                parseStart(json);
                System.out.println(xml);
                PrintStream out = new PrintStream(new FileOutputStream("result.xml"));
                out.print(xml);
            } else {
                System.out.println("Error! Please check your JSON!");
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private boolean validJson(String json) {
        if (json.startsWith("{") && json.endsWith("}")) {
            int countOpen = countChar('{', json);
            int countClose = countChar('}', json);
            if (countOpen == countClose) {
                return true;
            }
            return false;
        }
        return false;
    }

    private void parseStart(String json) {
        System.out.println("START: --->> " + json);
        if (json == null || json.length() <= 0) {
        } else {
            if (json.startsWith("{")) {
                removeBorders(json, null);
            } else if (json.startsWith(":")) {
                parseValue(json, null);
            } else if (json.startsWith(",")) {
                json = removeFirstChar(json);
                parseKey(json, null);
            } else {
                parseKey(json, null);
            }
        }
    }

    private void parseKey(String json, String prevKey) {
        System.out.println("\n parseKey: --->> " + json);
        String key = json.substring(0, json.indexOf(':'));
        System.out.println("key: " + key);
        json = removeKey(json);
        putStart(key);
        parseValue(json, key);
        if (prevKey != null) {
            putEnd(prevKey);
        }
    }

    private void removeBorders(String json, String key) {
        System.out.println("\nremoveBorders: --->> " + json);
        json = removeFirstChar(json);
        json = removeLastChar(json);
        parseKey(json, key);
    }

    private void parseValue(String json, String key) {
        System.out.println("\nparseValue: --->> " + json);
        json = removeFirstChar(json);
        if (json.startsWith("{")) {
            removeBorders(json, key);
        } else if (json.startsWith("[")) {
            json = json.substring(json.indexOf('[') + 1, json.indexOf(']'));
            if (key != null) {
                parseArray(json, key);
            } else {
                parseArray(json, null);
            }
        } else {
            String value;
            if (json.contains(",")) {
                value = json.substring(0, json.indexOf(','));
            } else {
                value = json.substring(0, json.length());
            }
            System.out.println("value: " + value);
            if (json.contains(",")) {
                json = removeValue(json);
            } else {
                json = removeLastValue(json);
            }
            putValue(value);
            if (key != null) {
                putEnd(key);
            }
            parseStart(json);
        }
    }

    private void parseArray(String json, String key) {
        System.out.println("\nparseArray: --->> " + json);
        int membersCount = countChar('{', json);
        String[] members = new String[membersCount];
        for (int i = 0; i < membersCount; i++) {
            if ((i + 1) == membersCount) {
                members[i] = json.substring(1, json.length() - 1);
            } else {
                members[i] = json.substring(1, json.indexOf('}'));
                json = json.substring(json.indexOf('}') + 2);
            }
        }
        for (int i = 0; i < members.length; i++) {
            System.out.println("MEMBER: --->> " + members[i]);
            parseKey(members[i], null);
            if (key != null) {
                putEnd(key);
                if (i + 1 != membersCount) {
                    putStart(key);
                }
            }
        }
    }

    private int countChar(char c, String json) {
        int n = 0;
        char[] array = json.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                n++;
            }
        }
        return n;
    }

    private String removeLastChar(String json) {
        if (json != null && json.length() > 0) {
            json = json.substring(0, json.length() - 1);
        }
        return json;
    }

    private String removeFirstChar(String json) {
        if (json != null && json.length() > 0) {
            json = json.substring(1);
        }
        return json;
    }

    private String removeKey(String json) {
        if (json != null && json.length() > 0) {
            json = json.substring(json.indexOf(':'));
        }
        return json;
    }

    private String removeValue(String json) {
        if (json != null && json.length() > 0) {
            json = json.substring(json.indexOf(','));
        }
        return json;
    }

    private String removeLastValue(String json) {
        if (json != null && json.length() > 0) {
            json = json.substring(json.length());
        }
        return json;
    }


}