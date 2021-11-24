package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Order extends BaseModel{
    private final Set<LineItem> cart = new HashSet<>();
    private int userId;
    private int totalItems;
    private BigDecimal orderTotalValue;

    private boolean isSuccessPayment = false;
    private String paymentMethod;
    private String orderTime;
    private ShippingDetails shippingDetails;

    public Order(User user) {
        this.userId = user.getId();
        user.setOrder(this);
        shippingDetails = new ShippingDetails();
    }

    public Order(User user, ShippingDetails shippingDetails, boolean isSuccessPayment, String paymentMethod, String orderTime) {
        this.userId = user.getId();
        user.setOrder(this);
        this.shippingDetails = shippingDetails;

        this.isSuccessPayment = isSuccessPayment;
        this.paymentMethod = paymentMethod;
        this.orderTime = orderTime;
        this.totalItems = cart.size();
        this.orderTotalValue = getOrderTotalValue();
    }

    public void addItemToCart(LineItem item){
        if(checkIfItemInCart(item)){
            LineItem current = cart.stream().filter(x -> x.getName().equals(item.getName())).findFirst().get();
            current.setQuantity(current.getQuantity() + 1);
        }
        else{
            cart.add(item);
        }
        this.orderTotalValue = getOrderTotalValue();
        this.totalItems++;
    }

    public void removeItemFromCart(LineItem item){
        if(checkIfItemInCart(item)){
            LineItem current = cart.stream().filter(x -> x.getName().equals(item.getName())).findFirst().get();
            current.setQuantity(current.getQuantity() - 1);
            if(current.getQuantity() == 0){
                cart.remove(current);
            }
        }
        if(totalItems!=0){
            this.totalItems--;
        }
        this.orderTotalValue = getOrderTotalValue();

    }

    public void emptyCart(){
        cart.clear();
        totalItems = 0;
    }

    private boolean checkIfItemInCart(LineItem item){
        return cart.stream().anyMatch(lineItem -> lineItem.getName().equals(item.getName()));
    }

    public Set<LineItem> getCart() {
        return cart;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public BigDecimal getOrderTotalValue(){
        return cart.stream().map(LineItem::getItemTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
    }

//    public void saveToJson() throws IOException {
//        String filename = "src/main/webapp/order" + id + ".json";
//        FileWriter writer = new FileWriter(filename);
//
//        Gson gson = new Gson();
//        String json = gson.toJson(this);
//
//        writer.write(json, 0, json.length());
//        writer.close();
//
//        saveCheckout();
//    }
//
//
//    void saveCheckout() throws IOException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date millis = new Date(System.currentTimeMillis());
//        String date = formatter.format(millis);
//        String filename = "src/main/webapp/checkouts/" + id + "-" + date + ".json";
//
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//        String json = gson.toJson(this);
//        System.out.println(json);
//
//        json = json.concat("\n\nOrder confirmed");
//        System.out.println(json);
//        FileWriter writer = new FileWriter(filename);
//        writer.write(json, 0, json.length());
//        writer.close();
//
//    }

    public void setSuccessPayment(boolean successPayment) {
        isSuccessPayment = successPayment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public ShippingDetails getShippingDetails() {
        return shippingDetails;
    }

    public int getUserId() {
        return userId;
    }
}
