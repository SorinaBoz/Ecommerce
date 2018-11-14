import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Shop {
    Client client = new Client();
    public Map<Product, Integer> productStock;
    public Map<Product, Integer> productBasket;


    // create constructor
    public Shop(String filePath) {
        this.initialize(filePath); // call the method initialize
    }



    public String displayMenu(Map<Product, Integer> productStock) {
        String myMenu = "";
        myMenu = myMenu.concat("Bine ai venit. Alege produsul dorit.\n0 - IESIRE sau 99 - Finalizeaza comanda");
        myMenu = myMenu.concat("\n"); // this is a new line
        myMenu = myMenu.concat("Cod\tCategorie\tProdus\t Pret");
        myMenu = myMenu.concat("\n"); // this is a new line
        for (Product product : productStock.keySet()) {
            myMenu = myMenu.concat(product.getIdProduct() + "\t" + product.getCategoryName() + "\t" + product.getProductName() + "\t" + product.getPriceProduct());
            myMenu = myMenu.concat("\n");
        }
        System.out.println(myMenu);
        return myMenu;
    }

    // create a method fro buying products
    public Product buyProduct() {

        System.out.println("Alege produsul dorit\n0 - IESIRE sau 99 - Finalizeaza comanda");
        Scanner scanner = new Scanner(System.in);
        Double option = scanner.nextDouble();

        boolean ok = false;
        if (option == 0) {
            System.exit(0);
        } else if (option ==99){this.userData();}
        for (Product p : productStock.keySet()) { // mergem prin produse
            if (p.getIdProduct() == option) { // daca optiunea corespunde cu codul produsului
                Integer quantity = productStock.get(p); // din stoc luam produsul
                if (quantity > 0) { // daca stocul nu este epuizat
                    ok = true;
                    return p;
                } else {
                    System.out.println("Nu sunt produse suficiente");
                    break;
                }
            }
        }

        if (ok == false) {
            System.out.println("Optiunea introdusa nu este valida");
            return this.buyProduct(); // recursivitate - te reapelezi pe tine
        }
        return null;
    }

    // scadem produsul din stoc si il punem in cos
    public void addToBasket(Product product, Map<Product, Integer> productStock, Map<Product, Integer> productBasket) {
        double total = 0.0;
        productStock.put(product, productStock.get(product) - 1);
        productBasket.put(product, productBasket.get(product)+1);
        System.out.println("Cosul tau contine ");
        System.out.println("Produs\tCantitate\tPret");
        for (Product p : productBasket.keySet()) {
            if(productBasket.get(p)!=0){
            System.out.println(p.getProductName() + "\t " + productBasket.get(p) +"\t "+ p.getPriceProduct() + "$"); }
            total = total + (productBasket.get(p)* p.getPriceProduct());
        }
        System.out.println("Subtotal "+total + "$");
    }

    public void userData(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ai ales sa finalizezi comanda.\nCompleteaza datele personale");
        System.out.println("Prenume");
        client.setFirstName(scanner.nextLine());
        System.out.println("Nume");
        client.setLastName(scanner.nextLine());
        System.out.println("Adresa");
        client.setAddress(scanner.nextLine());
        System.out.println("Email");
        client.setAddress(scanner.nextLine());
        System.out.println("Telefon");
        client.setPhone(scanner.nextInt());
        System.out.println("Datele tale sunt " + client.getFirstName() + " " + client.getLastName() + " " +
                client.getAddress() + " " + client.getEmail() + " " + client.getPhone());
    }

    public void order(){
        System.out.println("Alege metoda de plata");
    }


        public void start () {
            while (true) {
                this.displayMenu(productStock); // afisam meniu
                Product product = this.buyProduct(); // selectam produs
                this.addToBasket(product, productStock, productBasket);// livram produsul

            }

        }

        // cream o metoda de initializare a fisierului de proprietati
        public void initialize (String filePath){

            Path path = Paths.get(filePath); // avoid to use the hard copy of the path in order to specify the location that you want
            List<String> lines = null;

            try {
                lines = Files.readAllLines(path);
            } catch (IOException e) {
                e.printStackTrace();
            }


            productStock = new LinkedHashMap<>();
            productBasket= new LinkedHashMap<>();
            Integer nrProducts = Integer.valueOf(lines.get(0));

            // citeste produsele inclusiv stocurile
            for (int i = 1; i < 1 + nrProducts; i++) { // citeste incepand cu linia de index 1 (produsele)
                String line = lines.get(i);
                String[] parts = line.split(" ");
                Product product = new Product(Double.valueOf(parts[0]), parts[1], parts[2], Double.valueOf(parts[3])); // convertim in integer/string
                productStock.put(product, Integer.valueOf(parts[4])); // pentru a afisa stocul (cantitatea)
                productBasket.put(product, 0);
            }
        }
    }


