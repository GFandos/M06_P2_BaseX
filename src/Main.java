import java.io.IOException;
import java.util.Scanner;

/**
 * Created by 47989768s on 13/03/17.
 */
public class Main {

    public static void main(final String... args) {

        boolean playing = true;
        Scanner sc = new Scanner(System.in);

        try {
            BaseXdbManager manager = new BaseXdbManager();

            while(playing) {
                showMenu(sc, manager);
            }

            manager.dropDatabase();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void showMenu(Scanner sc, BaseXdbManager manager) throws IOException {

        System.out.println("------------------------------------ Selecciona una opció ------------------------------------");
        System.out.println("\t\t\t\t\t1. Quins paisos hi ha en el fitxer <<factbook.xml>>?");
        System.out.println("\t\t\t\t\t2. Quants paisos hi ha?");
        System.out.println("------------------------------------ Selecciona una opció ------------------------------------");

        selectOption(sc, manager);

    }

    private static void selectOption(Scanner sc, BaseXdbManager manager) throws IOException {

        int option = sc.nextInt();

        switch (option) {
            case 1:
                selectCountries(manager);
                break;
            case 2:
                countCountries(manager);
                break;
            default:
                System.out.println("Opció no vàlida");
                break;
        }

    }

    private static void countCountries(BaseXdbManager manager) throws IOException {

        String query;

        query = "for $c in collection('MondialDB')/factbook//country " +
                "return " +
                "<co>{"+"$c"+"}</co>";

        manager.runQuery(query);
    }

    private static void selectCountries(BaseXdbManager manager) throws IOException {

        String query;

        query = "for $c in collection('MondialDB')/factbook//country " +
                "return $c/text()";

        manager.runQuery(query);

    }



}
