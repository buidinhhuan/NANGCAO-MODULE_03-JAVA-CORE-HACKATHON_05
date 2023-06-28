package ra.service;

import ra.model.CartItem;



import java.util.ArrayList;
import java.util.List;

public class CartService {
    List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getAll() {
        return cartItems;
    }

    public int getSize() {
        return cartItems.size();
    }

    public  void  Save(CartItem newCart){
        if (finById(newCart.getCartItemId()) == null){
            //thêm　mới
            cartItems.add(newCart);
        }else{
            //update
            int index = cartItems.indexOf(finById(newCart.getCartItemId()));
            cartItems.set(index,newCart );
        }
    }
    public CartItem finById(String id){
        for (CartItem cartItem: cartItems) {
            if (cartItem.getCartItemId().equals(id) ){
                return cartItem;
                //tìm thấy
            }
        }
        //không tìm thấy
        return null;
    }
    public  boolean deleteCart(String delId){
        if (finById(delId)==null){
            return false;
        }
        cartItems.remove(finById(delId));
        return  true;
    }



}
