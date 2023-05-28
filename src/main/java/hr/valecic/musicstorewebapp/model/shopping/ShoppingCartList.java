package hr.valecic.musicstorewebapp.model.shopping;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        if (shoppingCartItem.itemAmount == 0){
            itemsList.remove(shoppingCartItem);
        }
    }
}
