package inventoryApp;

import inventoryApp.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setPrice(double price) { this.price = price; }

    public void setStock(int stock) { this.stock = stock; }

    public void setMin(int min) { this.min = min; }

    public void setMax(int max) { this.max = max; }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public double getPrice() { return this.price; }

    public int getStock() { return this.stock; }

    public int getMin() { return this.min; }

    public int getMax() { return this.max; }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedPart) {
        for(Part part : associatedParts) {
            if(part.getId() == selectedPart.getId() &&
            part.getName() == selectedPart.getName() &&
            part.getMax() == selectedPart.getMax() &&
            part.getMin() == selectedPart.getMin() &&
            part.getPrice() == selectedPart.getPrice() &&
            part.getStock() == selectedPart.getStock()) {
                if(selectedPart instanceof InHousePart &&
                        ((InHousePart) part).getMachineId() == ((InHousePart) selectedPart).getMachineId()) {
                    return associatedParts.remove(part);
                } else if(selectedPart instanceof OutSourcedPart &&
                        ((OutSourcedPart)part).getCompanyName().equals(((OutSourcedPart) selectedPart).getCompanyName())) {
                    return associatedParts.remove(part);
                }
            }
        }
        System.out.println("No matching part was found");
        return false;
    }

    public ObservableList<Part> getAllAssociatedParts() { return associatedParts; }






}
