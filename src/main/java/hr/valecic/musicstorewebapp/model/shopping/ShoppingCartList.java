package hr.valecic.musicstorewebapp.model.shopping;

import hr.valecic.musicstorewebapp.model.Shoppingcartitem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShoppingCartList {
    private static List<ShoppingCartItem> itemsList;

    private ShoppingCartList() {
    }

    public static List<ShoppingCartItem> getInstance() {
        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }
        return itemsList;
    }

    public static void addItem(ShoppingCartItem itemFromItemPageVM) {
        if (getInstance().stream().anyMatch(cartItem -> cartItem.getItem().equals(itemFromItemPageVM.item))) {
            ShoppingCartItem shoppingCartItem =
                    getInstance()
                    .stream()
                    .filter(cartItem -> cartItem.item.equals(itemFromItemPageVM.item)).toList().get(0);

            BigDecimal itemPrice = itemFromItemPageVM
                    .getItem()
                    .getPrice()
                    .multiply(new BigDecimal(itemFromItemPageVM.itemAmount));
            BigDecimal newPrice = shoppingCartItem.totalPrice
                    .add(itemPrice);
            shoppingCartItem.totalPrice = newPrice;

            shoppingCartItem.setItemAmount(shoppingCartItem.itemAmount + itemFromItemPageVM.getItemAmount());
        } else {
            addItemWithMultiply(itemFromItemPageVM);
        }
    }

    private static void addItemWithMultiply(ShoppingCartItem itemFromItemPageVM) {
        itemFromItemPageVM.totalPrice = itemFromItemPageVM.totalPrice
                .multiply(new BigDecimal(itemFromItemPageVM.itemAmount));
        getInstance().add(itemFromItemPageVM);
    }

    public static void decreaseItemAmount(Integer itemIndex, Integer amountToDecrease) {
        ShoppingCartItem shoppingCartItem = itemsList.get(itemIndex);
        shoppingCartItem.setItemAmount(shoppingCartItem.itemAmount - amountToDecrease);
        for (int i = 0; i < amountToDecrease; i++){
            shoppingCartItem.setTotalPrice(shoppingCartItem.totalPrice.subtract(shoppingCartItem.getItem().getPrice()));
        }
        if (shoppingCartItem.itemAmount == 0){
            itemsList.remove(shoppingCartItem);
        }
    }

    public static Collection<ShoppingCartItem> convertFromShoppingcartitem(Collection<Shoppingcartitem> cartItemsListForPerson) {
        Collection<ShoppingCartItem> tempList = new ArrayList<>();
        cartItemsListForPerson.forEach(shoppingcartitem -> {
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingcartitem.getItemByItemid(), shoppingcartitem.getItemamount());
            shoppingCartItem.setTotalPrice(shoppingcartitem.getTotalprice());
            tempList.add(shoppingCartItem);
        });
        return tempList;
    }

//    public static List<ShoppingCartItem> convertList(Collection<? extends Shoppingcartitem> cartForPerson) {
//        List<ShoppingCartItem> tempList = new ArrayList<>();
//        cartForPerson.forEach(item -> {
//            ShoppingCartItem newItem = new ShoppingCartItem(item.ite)
//            tempList.add();
//        });
//    }
}
