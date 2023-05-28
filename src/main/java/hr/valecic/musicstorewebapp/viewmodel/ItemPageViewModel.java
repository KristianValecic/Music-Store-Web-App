package hr.valecic.musicstorewebapp.viewmodel;

import hr.valecic.musicstorewebapp.model.Item;
import lombok.Data;

@Data
public class ItemPageViewModel {
    Item item;
    Integer itemAmount;

//    public ItemPageViewModel(){
//        itemAmount = 1;
//    }
}
