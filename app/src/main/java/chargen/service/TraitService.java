package chargen.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject; // If using Guice for constructor injection (optional for now)
import javax.inject.Singleton; // If you want this to be a Guice singleton

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
// Optional: For more specific configuration if needed
// import com.fasterxml.jackson.databind.DeserializationFeature;

import chargen.model.Trait; // Assuming Trait.java is in chargen.model

/**
 * Service responsible for loading and providing access to {@link Trait} definitions.
 * Traits are loaded from a JSON resource file.
 */
@Singleton // Marks this class as a singleton if managed by Guice
public class TraitService {

    private static final String DEFAULT_TRAITS_FILE_PATH = "/data/traits.json"; // Path within resources
    private final Map<String, Trait> traitsByName;

    /**
     * Constructs the TraitService, loading traits from the default JSON file path.
     * This constructor can be used by Guice for dependency injection.
     * If not using Guice, you can instantiate it directly.
     */
    @Inject // For Guice: Guice will call this constructor
    public TraitService() {
        this(DEFAULT_TRAITS_FILE_PATH); // Delegate to the constructor that takes a path
    }

    /**
     * Constructs the TraitService, loading traits from the specified JSON file path.
     * Primarily for testing or if the file path needs to be configurable.
     *
     * @param resourceFilePath The path to the JSON file within the classpath resources.
     * @throws RuntimeException if the traits file cannot be loaded or parsed.
     */
    public TraitService(String resourceFilePath) {
        Objects.requireNonNull(resourceFilePath, "resourceFilePath cannot be null");

        ObjectMapper objectMapper = new ObjectMapper();
        // Configure if needed, e.g.:
        // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Trait> loadedTraits = Collections.emptyList();
        try (InputStream inputStream = TraitService.class.getResourceAsStream(resourceFilePath)) {
            if (inputStream == null) {
                // Consider a more specific custom exception or logging
                throw new IOException("Cannot find the traits resource file: " + resourceFilePath);
            }
            loadedTraits = objectMapper.readValue(inputStream, new TypeReference<List<Trait>>() { } );
        } catch (IOException e) {
            // Log the error appropriately
            // For a critical data loading failure, rethrowing as a runtime exception
            // might be suitable to halt application startup if traits are essential.
            System.err.println("Failed to load traits from " + resourceFilePath + ": " + e.getMessage());
            e.printStackTrace(); // Replace with a proper logging framework
            // Or throw new RuntimeException("Failed to initialize TraitService: Could not load traits.", e);
        }

        // Using a concurrent map if this service might be accessed by multiple threads,
        // though for a generator, a regular HashMap is likely fine if loaded at startup.
        // For simplicity, let's stick to HashMap for now.
        this.traitsByName = loadedTraits.stream()
            .collect(Collectors.toMap(Trait::getName, Function.identity(), (existing, replacement) -> {
                // Handle duplicate trait names if that's a possibility in your data
                // This example keeps the first one encountered and prints a warning.
                System.err.println("Warning: Duplicate trait name encountered and ignored for '" +
                        existing.getName() + "'. Keeping the first instance.");
                return existing;
            }));

        System.out.println("TraitService: Loaded " + this.traitsByName.size() + " traits from " + resourceFilePath);
    }

    /**
     * Retrieves a trait by its unique name.
     *
     * @param name The name of the trait to retrieve.
     * @return The {@link Trait} object if found, or {@code null} otherwise.
     */
    public Trait getTraitByName(String name) {
        return traitsByName.get(name);
    }

    /**
     * Retrieves all loaded traits.
     *
     * @return An unmodifiable list of all {@link Trait} objects.
     */
    public List<Trait> getAllTraits() {
        // Return a copy or unmodifiable view to protect the internal map's values
        return List.copyOf(traitsByName.values());
    }

    // --- Example main method for standalone testing of this service ---
    public static void main(String[] args) {
        System.out.println("Testing TraitService standalone...");
        TraitService traitService = null;
        try {
            traitService = new TraitService(); // Uses default path: /data/traits.json
        } catch (Exception e) {
            System.err.println("Error initializing TraitService for testing: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        System.out.println("\n--- All Loaded Traits ---");
        traitService.getAllTraits().forEach(trait -> {
            System.out.println("Name: " + trait.getName() + ", Modifiers: " + trait.getModifiers().size());
            // System.out.println("  Desc: " + trait.getDescription()); // Can be verbose
        });

        System.out.println("\n--- Testing getTraitByName ---");
        String testTraitName = "Darkvision"; // Assuming "Darkvision" is in your traits.json
        Trait darkvision = traitService.getTraitByName(testTraitName);
        if (darkvision != null) {
            System.out.println("Found Trait: " + darkvision.getName());
            System.out.println("  Description: " + darkvision.getDescription());
            darkvision.getModifiers().forEach(mod -> System.out.println("    Modifier: " + mod));
        } else {
            System.out.println("Trait '" + testTraitName + "' not found.");
        }

        String nonExistentTrait = "SuperSpeed";
        Trait notFound = traitService.getTraitByName(nonExistentTrait);
        if (notFound == null) {
            System.out.println("Trait '" + nonExistentTrait + "' correctly not found.");
        } else {
            System.err.println("Error: Trait '" + nonExistentTrait + "' was unexpectedly found.");
        }
    }
}