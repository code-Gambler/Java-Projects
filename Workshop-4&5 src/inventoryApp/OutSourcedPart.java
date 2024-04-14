package inventoryApp;

import inventoryApp.Part;

public class OutSourcedPart extends Part {

    private String companyName;

    public OutSourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
    }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
