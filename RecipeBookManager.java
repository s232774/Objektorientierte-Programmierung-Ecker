package recipebookmanager;
import java.util.ArrayList;
import java.util.List;
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
    // Liste zur Protokollierung der Aktionen.
    private static List<String> actionLog = new ArrayList<>();

    // Hauptmethode, die die Benutzereingabe steuert.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        // Hauptschleife der Anwendung.
        while (running) {
            System.out.println("Wählen Sie eine Option:");
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
                    System.out.println("Geben Sie den Namen des Rezepts ein:");
                    String name = scanner.nextLine();
                    System.out.println(searchRecipe(name));
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
                    System.out.println("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
                    break;
            }
        }
        scanner.close();
        printActionLog();
    }

    // Method to add a new recipe to the book.
    private static void addRecipe(Scanner scanner) {
        // Check if the recipe book is full.
        if (recipeCount >= MAX_RECIPES) {
            System.out.println("Rezeptbuch ist voll.");
            return;
        }
        // Get recipe details from the user.
        System.out.println("Rezeptname:");
        String name = scanner.nextLine();
        System.out.println("Zutaten (durch Komma getrennt):");
        String ingredients = scanner.nextLine();
        System.out.println("Zubereitungsanweisungen:");
        String instructions = scanner.nextLine();

        // Add the new recipe to the array and increment the count.
        recipes[recipeCount++] = new Recipe(name, ingredients, instructions);
        System.out.println("Rezept hinzugefügt.");
    }

    // Method to search for a recipe by name.
    private static String searchRecipe(String name) {
        for (Recipe recipe : recipes) {
            // Check if recipe name matches and recipe is not null.
            if (recipe != null && recipe.getName().equalsIgnoreCase(name)) {
                return recipe.toString();
            }
        }
        return "Rezept nicht gefunden.";
    }

    // Method to update an existing recipe.
    private static void updateRecipe(Scanner scanner) {
        System.out.println("Geben Sie den Namen des Rezepts ein, das aktualisiert werden soll:");
        String name = scanner.nextLine();

        for (int i = 0; i < recipeCount; i++) {
            if (recipes[i] != null && recipes[i].getName().equalsIgnoreCase(name)) {
                System.out.println("Neue Zutaten (durch Komma getrennt):");
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
        System.out.println("Geben Sie den Namen des Rezepts ein, das gelöscht werden soll:");
        String name = scanner.nextLine();

        for (int i = 0; i < recipeCount; i++) {
            if (recipes[i] != null && recipes[i].getName().equalsIgnoreCase(name)) {
                recipes[i] = null;
                System.out.println("Rezept gelöscht.");
                return;
            }
        }
        System.out.println("Rezept nicht gefunden.");
    }

    // Method to display all actions taken (optional and simpler without logging).
    private static void printActionLog() {
        System.out.println("Zusammenfassung der Aktionen:");
        for (String action : actionLog) {
            System.out.println(action);
        }
    }

    // Innere Klasse, die ein Rezept repräsentiert.
    // Definiert eine Rezeptklasse, um Details über ein Rezept zu speichern.
    static class Recipe {
        // Variablen zum Speichern der Rezeptdetails.
        private String name;
        private String ingredients;
        private String instructions;

        // Konstruktor zur Initialisierung der Rezeptdetails.
        public Recipe(String name, String ingredients, String instructions) {
            this.name = name;
            this.ingredients = ingredients;
            this.instructions = instructions;
        }

        // Methode zum Aktualisieren der Zutaten des Rezepts.
        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        // Methode zum Aktualisieren der Anweisungen des Rezepts.
        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        // Methode, um den Namen des Rezepts zu erhalten.
        public String getName() {
            return name;
        }

        // Methode, um eine Zeichenkettenrepräsentation des Rezepts zurückzugeben.
        public String toString() {
            return "Name: " + name + "\nZutaten: " + ingredients + "\nZubereitung: " + instructions;
        }
    }
}

