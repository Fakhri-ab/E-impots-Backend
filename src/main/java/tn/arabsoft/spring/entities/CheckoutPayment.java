package tn.arabsoft.spring.entities;

public class CheckoutPayment {
    private String name;
    private String currency;
    private String successUrl;
    private String cancelUrl;
    private long amount;
    private long quantity;
    private int decId ;
    private String typedec ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public int getDecId() {
        return decId;
    }

    public void setDecId(int decId) {
        this.decId = decId;
    }

    public String getTypedec() {
        return typedec;
    }

    public void setTypedec(String typedec) {
        this.typedec = typedec;
    }

    @Override
    public String toString() {
        return "CheckoutPayment{" +
                "name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", successUrl='" + successUrl + '\'' +
                ", cancelUrl='" + cancelUrl + '\'' +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", decId=" +decId  +
                '}';
    }
}
