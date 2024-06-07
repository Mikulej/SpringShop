package com.example.Spring2;

import com.example.Spring2.db.Product;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value="session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<CartProduct> cartProductList = new ArrayList<>();
    private int amount = 0;
    private double sum = 0;

    public void addProduct(Product product){
        getCartProductByProduct(product).ifPresentOrElse(
                CartProduct::increaseAmount,
                () -> cartProductList.add(new CartProduct(product))
        );
        recalculatePriceAndAmount();
    }
    public void decreaseProduct(Product product){
        Optional<CartProduct> optionalCartProduct = getCartProductByProduct(product);
        if(optionalCartProduct.isPresent()){
            CartProduct cartProduct = optionalCartProduct.get();
            cartProduct.decreaseAmount();
            if(cartProduct.getAmount() == 0){
                removeAllProducts(product);
            }
        }
        recalculatePriceAndAmount();
    }
    private void recalculatePriceAndAmount(){
        sum = cartProductList.stream().map(CartProduct::getPrice)
                .reduce(0.0,Double::sum);
        amount = cartProductList.stream().mapToInt(CartProduct::getAmount)
                .reduce(0,Integer::sum);
    }
    private Optional<CartProduct> getCartProductByProduct(Product product){
        return cartProductList.stream()
                .filter(i -> i.idEquals(product))
                .findFirst();
    }
    public void removeAllProducts(Product product){
        cartProductList.removeIf(i -> i.idEquals(product));
        recalculatePriceAndAmount();
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }
    public List<CartProduct> getCartProductList(){
        return cartProductList;
    }

    public void setCartProductList(List<CartProduct> cartProductList){
        this.cartProductList = cartProductList;
    }

    public void clearCart(){
        cartProductList.clear();
        amount =0;
        sum = 0;
    }
}
