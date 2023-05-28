package hr.valecic.musicstorewebapp.model.shopping;

import hr.valecic.musicstorewebapp.model.Item;
import hr.valecic.musicstorewebapp.viewmodel.ItemPageViewModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
public class ShoppingCartItem {
    Item item;
    Integer itemAmount;
    BigDecimal totalPrice;

    public ShoppingCartItem(Item item, Integer itemAmount) {
        this.item = item;
        this.totalPrice = item.getPrice();
        this.itemAmount = itemAmount;
    }

    public static ShoppingCartItem getItemFromItemPageVM(ItemPageViewModel itemPageViewModel) {
        return new ShoppingCartItem(itemPageViewModel.getItem(), itemPageViewModel.getItemAmount());
    }
}
