package hr.valecic.musicstorewebapp.dal.repository;

import hr.valecic.musicstorewebapp.model.Shoppingcart;
import hr.valecic.musicstorewebapp.model.Shoppingcartitem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingcartItemRepository  extends JpaRepository<Shoppingcartitem, Long> {
    Iterable<Shoppingcartitem> getShoppingcartitemsByShoppingcartByShoppingcartid(Shoppingcart shoppingcart);
//    Iterable<Shoppingcartitem> getShoppingcartitemsByShoppingcartByShoppingcartid(Shoppingcart shoppingcart);
    void deleteShoppingcartitemsByShoppingcartByShoppingcartid(Shoppingcart shoppingcart);
}
