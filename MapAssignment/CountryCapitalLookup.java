import java.util.*;

public class CountryCapitalLookup {
    private Map<String, String> countryCapitals;
    
    public CountryCapitalLookup() {
        countryCapitals = new HashMap<>();
    }
    
    public void addCountryCapital(String country, String capital) {
        countryCapitals.put(country, capital);
        System.out.println("Added: " + country + " -> " + capital);
    }
    
    public String getCapital(String country) {
        return countryCapitals.get(country);
    }
    
    public String getCountryByCapital(String capital) {
        for (Map.Entry<String, String> entry : countryCapitals.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(capital)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    public void lookupCapital(String country) {
        String capital = getCapital(country);
        if (capital != null) {
            System.out.println("Capital of " + country + " is " + capital);
        } else {
            System.out.println("Country not found: " + country);
        }
    }
    
    public void lookupCountry(String capital) {
        String country = getCountryByCapital(capital);
        if (country != null) {
            System.out.println(capital + " is the capital of " + country);
        } else {
            System.out.println("Capital not found: " + capital);
        }
    }
    
    public void displayAllSortedByCountry() {
        System.out.println("\n=== All Countries and Capitals (Sorted by Country) ===");
        TreeMap<String, String> sorted = new TreeMap<>(countryCapitals);
        
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
    
    public void displayAllSortedByCapital() {
        System.out.println("\n=== All Countries and Capitals (Sorted by Capital) ===");
        
        List<Map.Entry<String, String>> entries = new ArrayList<>(countryCapitals.entrySet());
        entries.sort(Comparator.comparing(Map.Entry::getValue));
        
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getValue() + " <- " + entry.getKey());
        }
    }
    
    public void searchPartial(String searchTerm) {
        System.out.println("\n=== Search Results for '" + searchTerm + "' ===");
        String searchLower = searchTerm.toLowerCase();
        boolean found = false;
        
        for (Map.Entry<String, String> entry : countryCapitals.entrySet()) {
            if (entry.getKey().toLowerCase().contains(searchLower) || 
                entry.getValue().toLowerCase().contains(searchLower)) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No results found.");
        }
    }
    
    public static void main(String[] args) {
        CountryCapitalLookup lookup = new CountryCapitalLookup();
        
        System.out.println("=== Adding Countries and Capitals ===");
        lookup.addCountryCapital("India", "New Delhi");
        lookup.addCountryCapital("USA", "Washington D.C.");
        lookup.addCountryCapital("UK", "London");
        lookup.addCountryCapital("France", "Paris");
        lookup.addCountryCapital("Germany", "Berlin");
        lookup.addCountryCapital("Japan", "Tokyo");
        lookup.addCountryCapital("Australia", "Canberra");
        lookup.addCountryCapital("Canada", "Ottawa");
        lookup.addCountryCapital("Brazil", "Brasilia");
        lookup.addCountryCapital("China", "Beijing");
        
        lookup.displayAllSortedByCountry();
        lookup.displayAllSortedByCapital();
        
        System.out.println("\n=== Looking up Capitals ===");
        lookup.lookupCapital("India");
        lookup.lookupCapital("France");
        lookup.lookupCapital("Spain");
        
        System.out.println("\n=== Looking up Countries by Capital ===");
        lookup.lookupCountry("Tokyo");
        lookup.lookupCountry("London");
        lookup.lookupCountry("Madrid");
        
        System.out.println("\n=== Partial Search ===");
        lookup.searchPartial("an");
        lookup.searchPartial("new");
    }
}
