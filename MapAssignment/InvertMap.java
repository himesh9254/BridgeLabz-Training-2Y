import java.util.*;

public class InvertMap {
    public static <K, V> Map<V, List<K>> invertMap(Map<K, V> originalMap) {
        Map<V, List<K>> invertedMap = new HashMap<>();
        
        for (Map.Entry<K, V> entry : originalMap.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            
            invertedMap.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        }
        
        return invertedMap;
    }
    
    public static <K, V> Map<V, K> invertMapSimple(Map<K, V> originalMap) {
        Map<V, K> invertedMap = new HashMap<>();
        
        for (Map.Entry<K, V> entry : originalMap.entrySet()) {
            invertedMap.put(entry.getValue(), entry.getKey());
        }
        
        return invertedMap;
    }
    
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);
        map1.put("C", 1);
        
        System.out.println("Original Map: " + map1);
        System.out.println("Inverted Map (with lists): " + invertMap(map1));
        
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("Apple", 100);
        map2.put("Banana", 200);
        map2.put("Cherry", 100);
        map2.put("Date", 300);
        map2.put("Elderberry", 200);
        
        System.out.println("\nOriginal Map: " + map2);
        System.out.println("Inverted Map (with lists): " + invertMap(map2));
        
        Map<String, String> map3 = new HashMap<>();
        map3.put("USA", "Washington");
        map3.put("UK", "London");
        map3.put("France", "Paris");
        map3.put("Germany", "Berlin");
        
        System.out.println("\nOriginal Map: " + map3);
        System.out.println("Inverted Map (simple, unique values): " + invertMapSimple(map3));
        
        Map<Integer, String> map4 = new HashMap<>();
        map4.put(1, "Red");
        map4.put(2, "Blue");
        map4.put(3, "Red");
        map4.put(4, "Green");
        map4.put(5, "Blue");
        
        System.out.println("\nOriginal Map: " + map4);
        System.out.println("Inverted Map (with lists): " + invertMap(map4));
    }
}
