package src;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import model.Player;
import model.Game;
import model.Scores;

public class App {
    /** Attribute scan: Scanner to read user input. **/
    private static Scanner scan = new Scanner(System.in);

    /** Color **/

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    /** Display the main menu. **/
    public static void showMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("|--------- Menu ---------|");
        menus.add("1 - Joueur contre Joueur");
        menus.add("2 - Jouer contre l'IA");
        menus.add("3 - Afficher le top 10");
        menus.add("q - Quitter");
        menus.add("- Veuillez entrer un choix");
        for (String menu : menus) {
            System.out.println(menu);
        }
    }

    public static void showColorMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-- Menu Couleur --");
        menus.add("1 - BLACK");
        menus.add("2 - RED");
        menus.add("3 - GREEN");
        menus.add("4 - YELLOW");
        menus.add("5 - BLUE");
        menus.add("6 - PURPLE");
        menus.add("7 - CYAN");
        menus.add("8 - WHITE");
        menus.add("- Veuillez entrer un choix");
        for (String menu : menus) {
            System.out.println(menu);
        }
    }

    public static void showLevelMenuIA() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-- Menu niveau IA --");
        menus.add("1 - Niveau 1");
        menus.add("2 - Niveau 2");
        menus.add("3 - Niveau 3");
        menus.add("4 - Niveau 4");
        menus.add("- Veuillez entrer un choix");
        for (String menu : menus) {
            System.out.println(menu);
        }
    }

    public static int selectLevel() {
        int level = 0;
        boolean isLevel = false;
        while (!isLevel) {
            showLevelMenuIA();
            String levelIA = scan.nextLine();
            switch (levelIA) {
                case "1":
                    level = 1;
                    isLevel = true;
                    break;
                case "2":
                    level = 2;
                    isLevel = true;
                    break;
                case "3":
                    level = 3;
                    isLevel = true;
                    break;
                case "4":
                    level = 4;
                    isLevel = true;
                    break;
                default:
                    System.out.println("Veuillez entrer un choix de niveau valide");
                    break;
            }
        }
        return level;
    }

    /** Create IA PLAYER random symbol/color **/
    public static Player createPlayerIA(int level) {
        String nameIA = "";
        if (level == 1)
            nameIA = "IA Niveau 1";
        else if (level == 2)
            nameIA = "IA Niveau 2";
        else if (level == 3)
            nameIA = "IA Niveau 3";
        else if (level == 4)
            nameIA = "IA Niveau 4";

        String charList = "!\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        Random rand_symbol = new Random();
        char symbolIA = charList.charAt(rand_symbol.nextInt(charList.length())); // Get random char from charList with
                                                                                 // rand.nextInt as index from
                                                                                 // charList's List

        String[] colorList = { BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE };
        Random rand_color = new Random();
        String colorIA = colorList[rand_color.nextInt(colorList.length)]; // Get random color from colorList with
                                                                          // rand.nextInt as index from colorList's List

        Player PlayerIA = new Player(nameIA, symbolIA, colorIA);
        return (PlayerIA);
    }

    public static Player createPlayer() {
        Player newPlayer = new Player(" ", ' ', " ");

        // Select player name
        while (true) {
            System.out.println("- Entrez votre nom : ");
            try {
                String name = scan.nextLine();
                newPlayer.setName(name);
                break;
            } catch (Exception e) {
                System.out.println("/!\\ Veuillez entrer un nom valide (non vide)\n");
            }
        }

        // Select player symbol
        System.out.println("\n");
        while (true) {
            System.out.println("- Entrez votre symbole : ");
            try {
                String symbolInput = scan.nextLine();
                if (symbolInput.length() == 1) {
                    char symbol = symbolInput.charAt(0);
                    newPlayer.setSymbol(symbol);
                    break;
                }
                System.out.println("/!\\ Veuillez entrer un symbole valide (1 caractère et non vide)\n");
            } catch (Exception e) {
                System.out.println("/!\\ Erreur lors de la saisi de votre symbole !\n");
            }
        }
        System.out.println("\n");

        // Select player symbol color
        while (true) {
            System.out.println("- Choississez votre couleur : ");
            showColorMenu();

            String choice = scan.nextLine();
            switch (choice) {
                case "1" -> {
                    newPlayer.setSymbolColor(BLACK);
                    break;
                }
                case "2" -> {
                    newPlayer.setSymbolColor(RED);
                    break;
                }
                case "3" -> {
                    newPlayer.setSymbolColor(GREEN);
                    break;
                }
                case "4" -> {
                    newPlayer.setSymbolColor(YELLOW);
                    break;
                }
                case "5" -> {
                    newPlayer.setSymbolColor(BLUE);
                    break;
                }
                case "6" -> {
                    newPlayer.setSymbolColor(PURPLE);
                    break;
                }
                case "7" -> {
                    newPlayer.setSymbolColor(CYAN);
                    break;
                }
                case "8" -> {
                    newPlayer.setSymbolColor(WHITE);
                    break;
                }
                default -> {
                    System.out.println(
                            "/!\\ Veuillez entrer un nombre entre 1 et 8, correspondant aux couleurs disponibles\n");
                    continue;
                }
            }
            break;
        }
        System.out.println("\n\n----> Joueur créé : " + newPlayer.getName() + " !\n\n");
        return newPlayer;
    }

    public static void main(String[] args) throws Exception {
        Player player1;
        Player player2;
        int level = 0;

        while (true) {
            showMenu();
            String choice = scan.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println("Mode : Joueur contre Joueur");
                    System.out.println("Création du joueur 1 ...\n");
                    player1 = createPlayer();
                    System.out.println("Création du joueur 2 ...\n");
                    player2 = createPlayer();
                    System.out.println("----> Création de la partie ...\n");
                    Game newGame = new Game();
                    String gameMode = "PVP";
                    newGame.startGame(player1, player2, gameMode, level);
                    return;
                }
                case "2" -> {
                    System.out.println("Mode : Joueur contre IA");
                    System.out.println("Création du joueur ...\n");
                    player1 = createPlayer();
                    level = selectLevel();
                    player2 = createPlayerIA(level);
                    System.out.println("----> Création de la partie ...\n");
                    Game newGame = new Game();
                    String gameMode = "PVE";
                    newGame.startGame(player1, player2, gameMode, level);
                    return;
                }
                case "3" -> {
                    Scores.showTop10();
                }
                case "q" -> {
                    System.out.println("----> Fermeture de l'application ...");
                    return;
                }
                default -> System.out.println("/!\\ Veuillez choisir une option valable (default)\n");
            }
        }
    }
}