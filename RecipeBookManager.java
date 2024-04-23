package recipebookmanager;
import java.util.Scanner;

// Hauptklasse, die die Verwaltung eines Rezeptbuchs ermöglicht.
public class RecipeBookManager {

    // Aufzählung der verschiedenen Arten von Gerichten.
    enum DishType {STARTER, MAIN_COURSE, DESSERT}

    // Konstante, die die maximale Anzahl von Rezepten definiert.
    private static final int MAX_RECIPES = 50;
    // Array zur Speicherung der Rezepte.
    private static Recipe[] recipes = new Recipe[MAX_RECIPES];
    // Zähler für die Anzahl der gespeicherten Rezepte.
    private static int recipeCount = 0;

    // Hauptmethode, die die Benutzereingabe steuert.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Eine Option auswählen:");
            System.out.println("1. Rezept hinzufügen");
            System.out.println("2. Rezept suchen");
            System.out.println("3. Rezept aktualisieren");
            System.out.println("4. Rezept löschen");
            System.out.println("5. Beenden");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Überspringt den Zeilenumbruch nach der Eingabe.
            switch (choice) {
                case 1:
                    addRecipe(scanner);
                    break;
                case 2:
                    searchRecipe(scanner);
                    break;
                case 3:
                    updateRecipe(scanner);
                    break;
                case 4:
                    deleteRecipe(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Auswahl. Versuchen Sie es erneut.");
                    break;
            }
        }
        scanner.close();
    }

    // Method to add a new recipe to the book.
    private static void addRecipe(Scanner scanner) {
        if (recipeCount >= MAX_RECIPES) {
            System.out.println("Rezeptbuch ist voll.");
            return;
        }
        System.out.println("Rezeptname:");
        String name = scanner.nextLine();
        System.out.println("Dish Type (1: STARTER, 2: MAIN_COURSE, 3: DESSERT):");
        int dishTypeOrdinal = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline
        DishType dishType = DishType.values()[dishTypeOrdinal];
        System.out.println("Zutaten:");
        String ingredients = scanner.nextLine();
        System.out.println("Zubereitungsanweisungen:");
        String instructions = scanner.nextLine();
        recipes[recipeCount++] = new Recipe(name, ingredients, instructions, dishType);
        System.out.println("Rezept hinzugefügt.");
    }

    // Method to search for a recipe by name.
    private static void searchRecipe(Scanner scanner) {
        System.out.println("Den Namen des Rezepts eingeben:");
        String name = scanner.nextLine();
        for (Recipe recipe : recipes) {
            if (recipe != null && recipe.getName().equalsIgnoreCase(name)) {
                System.out.println(recipe);
                return;
            }
        }
        System.out.println("Rezept nicht gefunden.");
    }

    // Method to update an existing recipe.
    private static void updateRecipe(Scanner scanner) {
        System.out.println("Den Namen des Rezepts eingeben, das aktualisiert werden soll:");
        String name = scanner.nextLine();
        for (int i = 0; i < recipeCount; i++) {
            if (recipes[i] != null && recipes[i].getName().equalsIgnoreCase(name)) {
                System.out.println("Neue Zutaten:");
                recipes[i].setIngredients(scanner.nextLine());
                System.out.println("Neue Zubereitungsanweisungen:");
                recipes[i].setInstructions(scanner.nextLine());
                System.out.println("Rezept aktualisiert.");
                return;
            }
        }
        System.out.println("Rezept nicht gefunden.");
    }

    // Method to delete a recipe.
    private static void deleteRecipe(Scanner scanner) {
        System.out.println("Den Namen des Rezepts eingeben, das gelöscht werden soll:");
        String name = scanner.nextLine();
        for (int i = 0; i < recipeCount; i++) {
            if (recipes[i] != null && recipes[i].getName().equalsIgnoreCase(name)) {
                System.arraycopy(recipes, i + 1, recipes, i, recipeCount - i - 1);
                recipeCount--;
                System.out.println("Rezept gelöscht.");
                return;
            }
        }
        System.out.println("Rezept nicht gefunden.");
    }

    // Innere Klasse, die ein Rezept repräsentiert.
    static class Recipe {
        private String name;
        private String ingredients;
        private String instructions;
        private DishType dishType;

        public Recipe(String name, String ingredients, String instructions, DishType dishType) {
            this.name = name;
            this.ingredients = ingredients;
            this.instructions = instructions;
            this.dishType = dishType;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return "Name: " + name + "\nDish Type: " + dishType + "\nZutaten: " + ingredients + "\nZubereitung: " + instructions;
        }
    }
}

