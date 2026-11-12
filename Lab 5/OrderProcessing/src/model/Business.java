package model;

/**
 * The whole business: all suppliers and all customer orders.
 *
 * @author Krishiraj Bhagat
 */
public class Business {

    private SupplierDirectory supplierDirectory;
    private MasterOrderList masterOrderList;

    public Business() {
        supplierDirectory = new SupplierDirectory();
        masterOrderList = new MasterOrderList();
    }

    public SupplierDirectory getSupplierDirectory() {
        return supplierDirectory;
    }

    public MasterOrderList getMasterOrderList() {
        return masterOrderList;
    }

    /** Creates a business with sample suppliers and products so the app can be tested right away. */
    public static Business configure() {
        Business business = new Business();
        SupplierDirectory sd = business.getSupplierDirectory();

        Supplier dell = sd.addSupplier();
        dell.setSupplyName("Dell");
        addProduct(dell, "Dell XPS 13", 900, 10);
        addProduct(dell, "Dell Monitor 27", 250, 15);
        addProduct(dell, "Dell Keyboard", 40, 30);

        Supplier hp = sd.addSupplier();
        hp.setSupplyName("HP");
        addProduct(hp, "HP Spectre x360", 1100, 8);
        addProduct(hp, "HP LaserJet Printer", 200, 12);
        addProduct(hp, "HP Mouse", 25, 40);

        return business;
    }

    private static void addProduct(Supplier supplier, String name, double price, int availability) {
        Product p = supplier.getProductCatalog().addProduct();
        p.setProdName(name);
        p.setPrice(price);
        p.setAvailability(availability);
    }
}
