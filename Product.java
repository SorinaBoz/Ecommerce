import java.util.ArrayList;
import java.util.Map;

public class Product {

    private double idProduct;
    private String categoryName;
    private String productName;
    private double priceProduct;

    public Product(double idProduct, String categoryName, String productName, double priceProduct ){
        this.idProduct = idProduct;
        this.categoryName = categoryName;
        this.productName = productName;
        this.priceProduct = priceProduct;
    }

    public double getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }
}
