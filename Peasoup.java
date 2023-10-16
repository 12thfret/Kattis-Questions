import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int restaurants = Integer.parseInt(sc.nextLine()); //takes in the number of restaurants
        for (int i = 0; i < restaurants; i++) {
            //the next integer takes in the number of menu items
            boolean pea = false;
            boolean pancake = false;
            int menu = Integer.parseInt(sc.nextLine());
            //the next line takes in the restaurant name
            String name = sc.nextLine();
            for (int j = 0; j < menu; j++) {
                //followed by the items in the menu
                String item = sc.nextLine();
                if (item.equals("pea soup")) {
                    pea = true;
                }
                if (item.equals("pancakes")) {
                    pancake = true;
                }
            }
            if (pea && pancake) {
                System.out.println(name);
                System.exit(0);
            }
        }
        System.out.println("Anywhere is fine I guess");
    }
}
