package concrete.goonie.Mql5.properties.symbol;

import concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Singleton class that manages a collection of {@link Symbol} objects.
 * Provides thread-safe access to the list of symbols and ensures that only one instance of the class is created.
 */
public class Symbols {

    // Volatile variable to ensure visibility of changes to other threads
    private static volatile Symbols instance;

    // List to hold symbols, using CopyOnWriteArrayList to ensure thread-safety
    private List<Symbol> symbols = new CopyOnWriteArrayList<>();

    // Private constructor to prevent instantiation
    private Symbols() {
    }

    /**
     * Provides access to the Singleton instance of the Symbols class.
     * If an instance does not already exist, it creates a new one in a thread-safe manner.
     *
     * @return The single instance of the Symbols class.
     */
    public static Symbols getInstance() {
        if (instance == null) { // Check if an instance already exists
            synchronized (Symbols.class) { // Synchronize to avoid race conditions
                if (instance == null) { // Double-check
                    instance = new Symbols(); // Create a new instance
                }
            }
        }
        return instance; // Return the single instance
    }

    /**
     * Adds a {@link Symbol} to the list of symbols.
     *
     * @param symbol The Symbol object to add.
     */
    public void addSymbol(Symbol symbol) {
        symbols.add(symbol);
    }

    /**
     * Retrieves a {@link Symbol} from the list by its name.
     *
     * @param name The name of the symbol to retrieve.
     * @return The Symbol object matching the specified name, or {@code null} if not found.
     */
    public Symbol getSymbolByName(String name) {
        for (Symbol symbol : symbols) {
            if (symbol.getSymbolInfoString(ENUM_SYMBOL_INFO_STRING.SYMBOL_NAME).equals(name)) {
                return symbol; // Return the matching symbol
            }
        }
        return null; // Return null if the symbol with the specified name is not found
    }

    /**
     * Returns the number of symbols in the list.
     *
     * @return The number of Symbol objects in the list.
     */
    public int size() {
        return symbols.size();
    }

    /**
     * Retrieves the list of all {@link Symbol} objects.
     *
     * @return A thread-safe list of Symbol objects.
     */
    public List<Symbol> getSymbols() {
        return symbols;
    }
}
