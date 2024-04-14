package inventoryApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    /**
     * The set of all Parts. Static so that this is the universal set throughout the entire program.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * The set of all products. Static so that this is the universal set throughout the entire program.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Add a Part to the inventory
     * @param newPart - the part to be added
     */
    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Add a Product to the inventory
     * @param newProduct- the product to be added
     */
    public void addProduct(Product newProduct) {
        System.out.println("addProduct(): " + newProduct.getName());
        allProducts.add(newProduct);
    }

    /**
     * Returns a single Part with matching id or else returns null
     * @param partId - the id to search for
     * @return - the matching part or else null
     */
    public Part lookupPart(int partId) {
        for( Part part : allParts ) {
            if(part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * Returns a single Product with matching id or else returns null
     * @param productId - the id to search for
     * @return - the matching product or else null
     */
    public Product lookupProduct(int productId) {
        for( Product product : allProducts ) {
            if(product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Returns all the Parts that have a matching substring the to the partName string passed in
     * @param partName - the search string
     * @return - all the Parts that have a matching substring to the search string
     */
    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for ( Part part : allParts ) {
            if(part.getName().toLowerCase().contains(partName.toLowerCase())) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }

    /**
     * Returns all the Products that have a matching substring the to the partName string passed in
     * @param productName - the search string
     * @return - all the Products that have a matching substring to the search string
     */
    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        for ( Product product : allProducts ) {
            if(product.getName().toLowerCase().contains(productName.toLowerCase())) {
                namedProducts.add(product);
            }
        }
        return namedProducts;
    }

    /**
     * Update the part at index with the selectedPart passed in
     * @param index
     * @param selectedPart
     */
    public void updatePart(int index, Part selectedPart) {
        Part part = allParts.get(index);
        part.setId(selectedPart.getId());
        part.setName(selectedPart.getName());
        part.setPrice(selectedPart.getPrice());
        part.setStock(selectedPart.getStock());
        part.setMin(selectedPart.getMin());
        part.setMax(selectedPart.getMax());
        if(part instanceof InHousePart) {
            ((InHousePart)part).setMachineId(((InHousePart)selectedPart).getMachineId());
        } else if(part instanceof OutSourcedPart) {
            ((OutSourcedPart)part).setCompanyName(((OutSourcedPart)part).getCompanyName());
        }
    }

    /**
     * Update the part at index with the newProduct passed in
     * @param index - the index of the Product being updated
     * @param newProduct - the new Product state we want to update to
     */
    public void updateProduct(int index, Product newProduct) {
        Product product = allProducts.get(index);
        product.setId(newProduct.getId());
        product.setName(newProduct.getName());
        product.setMin(newProduct.getMin());
        product.setMax(newProduct.getMax());
        product.setPrice(newProduct.getPrice());
        product.setStock(newProduct.getStock());
    }

    /**
     * Deletes selectedPart from the set of all parts or nothing if part is not found
     * @param selectedPart - the part that should be deleted
     * @return - returns true if Part was deleted, and false if matching Part cannot be found and nothing is deleted
     */
    public boolean deletePart(Part selectedPart) {
        for(Part part: allParts) {
            if (part.getId() == selectedPart.getId() &&
            part.getName().equals(selectedPart.getName()) &&
            part.getPrice() == selectedPart.getPrice() &&
            part.getStock() == selectedPart.getStock() &&
            part.getMin() == selectedPart.getMin() &&
            part.getMax() == selectedPart.getMax()) {
                if(selectedPart instanceof InHousePart &&
                ((InHousePart) part).getMachineId() == ((InHousePart) selectedPart).getMachineId()) {
                    return allParts.remove(part);
                } else if(selectedPart instanceof OutSourcedPart &&
                    ((OutSourcedPart)part).getCompanyName().equals(((OutSourcedPart) selectedPart).getCompanyName())) {
                    return allParts.remove(part);
                }
            }
        }
        System.out.println("No matching part was found");
        return false;
    }

    /**
     * Deletes a matching Product to selectedProduct from the Inventory
     * @param selectedProduct - a Product with matching state to the product that we want to delete
     * @return - true if a Product was deleted, and false if a Product was not deleted because no matching Product was found.
     */
    public boolean deleteProduct(Product selectedProduct) {
        for(Product product: allProducts) {
            if (product.getId() == selectedProduct.getId() &&
                    product.getName().equals(selectedProduct.getName()) &&
                    product.getPrice() == selectedProduct.getPrice() &&
                    product.getStock() == selectedProduct.getStock() &&
                    product.getMin() == selectedProduct.getMin() &&
                    product.getMax() == selectedProduct.getMax()) {
                return allProducts.remove(product);
            }
        }
        System.out.println("No matching part was found");
        return false;
    }

    /**
     * Get the set of all Parts
     * @return - the set of all Parts
     */
    public ObservableList<Part> getAllParts() { return allParts; }

    /**
     * Get the set of all Products
     * @return - the set of all Products
     */
    public ObservableList<Product> getAllProducts() { return allProducts; }

    /**
     * Create a new product id that is incremented one up from the highest current product id
     * @return - a product id that is incremented one up from the highest current product id
     */
    public int newProductId() {
        int newId = 0;
        for (Product product : allProducts) {
            if(newId <= product.getId()) {
                newId = product.getId();
            }
        }
        return ++newId;
    }

    /**
     * Create a new part id that is incremented one up from the highest current part id
     * @return - a part id that is incremented one up from the highest current part id
     */
    public int newPartId() {
        int newId = 0;
        for (Part part : allParts) {
            if(newId <= part.getId()) {
                newId = part.getId();
            }
        }
        return ++newId;
    }
}
