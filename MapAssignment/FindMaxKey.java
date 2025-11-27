import java.util.*;

public class FindMaxKey {
    public static <K> K findKeyWithMaxValue(Map<K, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        
        K maxKey = null;
        int maxValue = Integer.MIN_VALUE;
        
        for (Map.Entry<K, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        
        return maxKey;
    }
    
    public static <K> List<K> findAllKeysWithMaxValue(Map<K, Integer> map) {
        List<K> maxKeys = new ArrayList<>();
        
        if (map == null || map.isEmpty()) {
            return maxKeys;
        }
        
        int maxValue = Collections.max(map.values());
        
        for (Map.Entry<K, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxValue) {
                maxKeys.add(entry.getKey());
            }
        }
        
        return maxKeys;
    }
    
    public static <K, V extends Comparable<V>> K findKeyWithMaxValueGeneric(Map<K, V> map) {
        return map.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }
    
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 10);
        map1.put("B", 20);
        map1.put("C", 15);
        
        System.out.println("Map: " + map1);
        System.out.println("Key with maximum value: " + findKeyWithMaxValue(map1));
        
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("Alice", 85);
        map2.put("Bob", 92);
        map2.put("Charlie", 78);
        map2.put("Diana", 92);
        map2.put("Eve", 88);
        
        System.out.println("\nMap: " + map2);
        System.out.println("Key with maximum value: " + findKeyWithMaxValue(map2));
        System.out.println("All keys with maximum value: " + findAllKeysWithMaxValue(map2));
        
        Map<String, Integer> map3 = new HashMap<>();
        map3.put("Product1", 500);
        map3.put("Product2", 1200);
        map3.put("Product3", 800);
        map3.put("Product4", 1500);
        
        System.out.println("\nMap: " + map3);
        System.out.println("Key with maximum value (using streams): " + findKeyWithMaxValueGeneric(map3));
        
        Map<Integer, Integer> map4 = new HashMap<>();
        map4.put(1, 100);
        map4.put(2, 200);
        map4.put(3, 150);
        
        System.out.println("\nMap: " + map4);
        System.out.println("Key with maximum value: " + findKeyWithMaxValue(map4));
    }
}
