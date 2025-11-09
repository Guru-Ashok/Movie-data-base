package com.moviedb.main;

import com.moviedb.repository.MovieRepository;
import com.moviedb.service.MovieService;
import com.moviedb.model.Movie;

import java.util.List;
import java.util.Scanner;

// Main class — console-based UI
public class Main {
    public static void main(String[] args) {
        MovieRepository repository = new MovieRepository();
        MovieService service = new MovieService(repository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MOVIE DATABASE MANAGEMENT =====");
            System.out.println("1. Add New Movie");
            System.out.println("2. Search Movie by ID");
            System.out.println("3. Search Movie by Title");
            System.out.println("4. Display All Movies");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Movie ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Movie Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();

                    System.out.print("Enter Director: ");
                    String director = scanner.nextLine();

                    System.out.print("Enter Release Year: ");
                    int year = scanner.nextInt();

                    service.addMovie(id, title, genre, director, year);
                    System.out.println("✅ Movie added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Movie ID to search: ");
                    int searchId = scanner.nextInt();
                    Movie foundMovie = service.searchById(searchId);
                    if (foundMovie != null)
                        System.out.println(foundMovie);
                    else
                        System.out.println("❌ Movie not found!");
                    break;

                case 3:
                    System.out.print("Enter Movie Title to search: ");
                    String searchTitle = scanner.nextLine();
                    List<Movie> movies = service.searchByTitle(searchTitle);
                    if (movies.isEmpty())
                        System.out.println("❌ No movies found!");
                    else
                        movies.forEach(System.out::println);
                    break;

                case 4:
                    List<Movie> allMovies = service.viewAllMovies();
                    if (allMovies.isEmpty())
                        System.out.println("📭 No movies available!");
                    else
                        allMovies.forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("🎬 Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("⚠️ Invalid choice. Try again!");
            }
        }
    }
}
