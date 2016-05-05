package work.beltran.discogsbrowser.api.model;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public class MarketResult {
    private int release_id;
    private String price;
    private String currency;

    public int getRelease_id() {
        return release_id;
    }

    public void setRelease_id(int release_id) {
        this.release_id = release_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
