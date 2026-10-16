package model;

import java.util.ArrayList;

public class SupplierDirectory {
    private ArrayList<Supplier> suppliers = new ArrayList<>();

    public ArrayList<Supplier> getSuppliers() { return suppliers; }

    public Supplier addSupplier() {
        Supplier s = new Supplier();
        suppliers.add(s);
        return s;
    }

    public void removeSupplier(Supplier s) { suppliers.remove(s); }
}