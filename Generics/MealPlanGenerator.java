import java.util.ArrayList;
import java.util.List;

interface MealPlan {
    String getMealType();
    int getCalories();
    boolean isValid();
}

class VegetarianMeal implements MealPlan {
    private String name;
    private int calories;
    private boolean containsDairy;

    public VegetarianMeal(String name, int calories, boolean containsDairy) {
        this.name = name;
        this.calories = calories;
        this.containsDairy = containsDairy;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getMealType() {
        return "Vegetarian";
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public boolean isValid() {
        return calories > 0 && calories <= 800;
    }
}

class VeganMeal implements MealPlan {
    private String name;
    private int calories;
    private boolean isOrganic;

    public VeganMeal(String name, int calories, boolean isOrganic) {
        this.name = name;
        this.calories = calories;
        this.isOrganic = isOrganic;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getMealType() {
        return "Vegan";
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public boolean isValid() {
        return calories > 0 && calories <= 600;
    }
}

class KetoMeal implements MealPlan {
    private String name;
    private int calories;
    private int carbsGrams;

    public KetoMeal(String name, int calories, int carbsGrams) {
        this.name = name;
        this.calories = calories;
        this.carbsGrams = carbsGrams;
    }

    public String getName() {
        return name;
    }

    public int getCarbsGrams() {
        return carbsGrams;
    }

    @Override
    public String getMealType() {
        return "Keto";
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public boolean isValid() {
        return carbsGrams <= 20 && calories > 0;
    }
}

class HighProteinMeal implements MealPlan {
    private String name;
    private int calories;
    private int proteinGrams;

    public HighProteinMeal(String name, int calories, int proteinGrams) {
        this.name = name;
        this.calories = calories;
        this.proteinGrams = proteinGrams;
    }

    public String getName() {
        return name;
    }

    public int getProteinGrams() {
        return proteinGrams;
    }

    @Override
    public String getMealType() {
        return "High-Protein";
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public boolean isValid() {
        return proteinGrams >= 30 && calories > 0;
    }
}

class Meal<T extends MealPlan> {
    private List<T> meals = new ArrayList<>();

    public void addMeal(T meal) {
        meals.add(meal);
    }

    public List<T> getMeals() {
        return meals;
    }

    public void displayMeals() {
        for (T meal : meals) {
            String mealName = "";
            if (meal instanceof VegetarianMeal) mealName = ((VegetarianMeal) meal).getName();
            else if (meal instanceof VeganMeal) mealName = ((VeganMeal) meal).getName();
            else if (meal instanceof KetoMeal) mealName = ((KetoMeal) meal).getName();
            else if (meal instanceof HighProteinMeal) mealName = ((HighProteinMeal) meal).getName();
            
            System.out.println("- " + mealName + " (" + meal.getMealType() + ") - " + meal.getCalories() + " cal");
        }
    }
}

class MealPlannerService {
    public static <T extends MealPlan> void generateMealPlan(Meal<T> mealPlan, String userName) {
        System.out.println("\n=== Personalized Meal Plan for " + userName + " ===");
        List<T> validMeals = new ArrayList<>();
        List<T> invalidMeals = new ArrayList<>();

        for (T meal : mealPlan.getMeals()) {
            if (validateMeal(meal)) {
                validMeals.add(meal);
            } else {
                invalidMeals.add(meal);
            }
        }

        System.out.println("\nValid Meals:");
        for (T meal : validMeals) {
            String mealName = getMealName(meal);
            System.out.println("✓ " + mealName + " (" + meal.getMealType() + ") - " + meal.getCalories() + " cal");
        }

        if (!invalidMeals.isEmpty()) {
            System.out.println("\nInvalid Meals (excluded from plan):");
            for (T meal : invalidMeals) {
                String mealName = getMealName(meal);
                System.out.println("✗ " + mealName + " (" + meal.getMealType() + ")");
            }
        }

        int totalCalories = validMeals.stream().mapToInt(MealPlan::getCalories).sum();
        System.out.println("\nTotal Daily Calories: " + totalCalories);
    }

    public static <T extends MealPlan> boolean validateMeal(T meal) {
        return meal.isValid();
    }

    private static <T extends MealPlan> String getMealName(T meal) {
        if (meal instanceof VegetarianMeal) return ((VegetarianMeal) meal).getName();
        if (meal instanceof VeganMeal) return ((VeganMeal) meal).getName();
        if (meal instanceof KetoMeal) return ((KetoMeal) meal).getName();
        if (meal instanceof HighProteinMeal) return ((HighProteinMeal) meal).getName();
        return "Unknown";
    }
}

public class MealPlanGenerator {
    public static void main(String[] args) {
        Meal<VegetarianMeal> vegetarianPlan = new Meal<>();
        vegetarianPlan.addMeal(new VegetarianMeal("Paneer Tikka", 450, true));
        vegetarianPlan.addMeal(new VegetarianMeal("Greek Salad", 300, true));
        vegetarianPlan.addMeal(new VegetarianMeal("Veggie Pasta", 550, true));
        MealPlannerService.generateMealPlan(vegetarianPlan, "Alice");

        Meal<VeganMeal> veganPlan = new Meal<>();
        veganPlan.addMeal(new VeganMeal("Quinoa Bowl", 400, true));
        veganPlan.addMeal(new VeganMeal("Tofu Stir Fry", 350, true));
        veganPlan.addMeal(new VeganMeal("Fruit Smoothie", 200, false));
        MealPlannerService.generateMealPlan(veganPlan, "Bob");

        Meal<KetoMeal> ketoPlan = new Meal<>();
        ketoPlan.addMeal(new KetoMeal("Grilled Salmon", 500, 5));
        ketoPlan.addMeal(new KetoMeal("Avocado Eggs", 400, 8));
        ketoPlan.addMeal(new KetoMeal("Bread Sandwich", 350, 45));
        MealPlannerService.generateMealPlan(ketoPlan, "Charlie");

        Meal<HighProteinMeal> proteinPlan = new Meal<>();
        proteinPlan.addMeal(new HighProteinMeal("Chicken Breast", 450, 50));
        proteinPlan.addMeal(new HighProteinMeal("Protein Shake", 250, 40));
        proteinPlan.addMeal(new HighProteinMeal("Egg White Omelette", 200, 35));
        MealPlannerService.generateMealPlan(proteinPlan, "Diana");
    }
}
