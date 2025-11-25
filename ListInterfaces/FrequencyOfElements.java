import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyOfElements {
    public static Map<String, Integer> countFrequency(List<String> list) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String element : list) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }
        return frequencyMap;
    }

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("apple");
        fruits.add("orange");

        System.out.println("Input: " + fruits);
        Map<String, Integer> frequency = countFrequency(fruits);
        System.out.println("Output: " + frequency);

        List<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("blue");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("red");

        System.out.println("\nInput: " + colors);
        System.out.println("Output: " + countFrequency(colors));

        List<String> words = new ArrayList<>();
        words.add("java");
        words.add("python");
        words.add("java");
        words.add("javascript");
        words.add("python");
        words.add("java");

        System.out.println("\nInput: " + words);
        System.out.println("Output: " + countFrequency(words));
    }
}
