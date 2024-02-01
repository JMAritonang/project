/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project_1;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Project_1 {
    static Map<String, Club> clubs = new HashMap<>();
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       while (true) {
            System.out.println("Menu:");
            System.out.println("1. Input Data1 Klub");
            System.out.println("2. Input Skor Pertandingan");
            System.out.println("3. Tampilkan Klasemen");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    inputDataKlub(scanner);
                    break;
                case 2:
                    inputSkorPertandingan(scanner);
                    break;
                case 3:
                    tampilkanKlasemen();
                    break;
                case 4:
                    System.out.println("Terima kasih. Keluar dari aplikasi.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih menu 1-4.");
            }
        }
    }

    private static void inputDataKlub(Scanner scanner) {
        System.out.print("Nama Klub: ");
        String clubName = scanner.nextLine();

        if (clubs.containsKey(clubName)) {
            System.out.println("Data klub sudah ada!");
            return;
        }

        System.out.print("Kota Klub: ");
        String city = scanner.nextLine();

        Club club = new Club(clubName, city);
        clubs.put(clubName, club);

        System.out.println("Data klub berhasil disimpan.");
    }

    private static void inputSkorPertandingan(Scanner scanner) {
        System.out.println("Pilih cara input skor pertandingan:");
        System.out.println("A. Satu per satu");
        System.out.println("B. Multiple");

        char choice = scanner.nextLine().charAt(0);

        switch (choice) {
            case 'A':
                inputSingleMatchScore(scanner);
                break;
            case 'B':
                inputMultipleMatchScore(scanner);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private static void inputSingleMatchScore(Scanner scanner) {
        System.out.print("Klub 1: ");
        String club1 = scanner.nextLine();

        System.out.print("Klub 2: ");
        String club2 = scanner.nextLine();

        if (!clubs.containsKey(club1) || !clubs.containsKey(club2)) {
            System.out.println("Data klub tidak ditemukan.");
            return;
        }

        System.out.print("Skor Klub 1: ");
        int score1 = scanner.nextInt();

        System.out.print("Skor Klub 2: ");
        int score2 = scanner.nextInt();

        scanner.nextLine();  // Consume the newline character

        saveMatchResult(club1, club2, score1, score2);
        System.out.println("Data pertandingan berhasil disimpan.");
    }

    private static void inputMultipleMatchScore(Scanner scanner) {
        while (true) {
            System.out.print("Klub 1: ");
            String club1 = scanner.nextLine();

            if (club1.equalsIgnoreCase("add")) {
                break;
            }

            System.out.print("Klub 2: ");
            String club2 = scanner.nextLine();

            if (!clubs.containsKey(club1) || !clubs.containsKey(club2)) {
                System.out.println("Data klub tidak ditemukan.");
                continue;
            }

            System.out.print("Skor Klub 1: ");
            int score1 = scanner.nextInt();

            System.out.print("Skor Klub 2: ");
            int score2 = scanner.nextInt();

            scanner.nextLine();  // Consume the newline character

            saveMatchResult(club1, club2, score1, score2);
            System.out.println("Data pertandingan berhasil disimpan.");
        }
    }

    private static void saveMatchResult(String club1, String club2, int score1, int score2) {
        Club team1 = clubs.get(club1);
        Club team2 = clubs.get(club2);

        if (score1 > score2) {
            team1.win();
            team2.lose();
        } else if (score1 < score2) {
            team1.lose();
            team2.win();
        } else {
            team1.draw();
            team2.draw();
        }
    }

    private static void tampilkanKlasemen() {
        System.out.println("No\tKlub\tMa\tMe\tS\tK\tGM\tGK\tPoint");

        int no = 1;
        for (Club club : clubs.values()) {
            System.out.println(no + "\t" + club);
            no++;
        }
    }
}

class Club {
    private String name;
    private String city;
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int losses;
    private int goalsScored;
    private int goalsConceded;

    public Club(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void win() {
        matchesPlayed++;
        wins++;
        goalsScored += 3;  // +3 points for a win
    }

    public void draw() {
        matchesPlayed++;
        draws++;
        goalsScored += 1;  // +1 point for a draw
    }

    public void lose() {
        matchesPlayed++;
        losses++;
    }

    @Override
    public String toString() {
        int points = wins * 3 + draws;
        return name + "\t" + matchesPlayed + "\t" + wins + "\t" + draws + "\t" + losses + "\t" +
                goalsScored + "\t" + goalsConceded + "\t" + points;
    }
}
    
  

