package recommendation;

import java.util.*;

public class RecommendationSystem {

    // User movie ratings
    static Map<String, Map<String, Integer>> userRatings = new HashMap<>();

    public static void main(String[] args) {

        // Sample Data
        addRating("Alice", "Avengers", 5);
        addRating("Alice", "Batman", 4);

        addRating("Bob", "Avengers", 5);
        addRating("Bob", "Superman", 4);

        addRating("Charlie", "Batman", 5);
        addRating("Charlie", "Superman", 5);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user name:");
        String user = scanner.nextLine();

        recommendMovies(user);
    }

    // Add ratings
    public static void addRating(String user, String movie, int rating) {

        userRatings.putIfAbsent(user, new HashMap<>());
        userRatings.get(user).put(movie, rating);
    }

    // Recommendation Logic
    public static void recommendMovies(String targetUser) {

        if (!userRatings.containsKey(targetUser)) {
            System.out.println("User not found!");
            return;
        }

        Set<String> recommendedMovies = new HashSet<>();

        Map<String, Integer> targetRatings = userRatings.get(targetUser);

        for (String otherUser : userRatings.keySet()) {

            if (!otherUser.equals(targetUser)) {

                Map<String, Integer> otherRatings =
                        userRatings.get(otherUser);

                // Find common interests
                for (String movie : targetRatings.keySet()) {

                    if (otherRatings.containsKey(movie)
                            && otherRatings.get(movie) >= 4
                            && targetRatings.get(movie) >= 4) {

                        // Recommend new movies
                        for (String recommendedMovie :
                                otherRatings.keySet()) {

                            if (!targetRatings.containsKey(recommendedMovie)) {

                                recommendedMovies.add(recommendedMovie);
                            }
                        }
                    }
                }
            }
        }

        // Display recommendations
        if (recommendedMovies.isEmpty()) {

            System.out.println("No recommendations found.");

        } else {

            System.out.println("Recommended Movies:");

            for (String movie : recommendedMovies) {

                System.out.println("- " + movie);
            }
        }
    }
}
