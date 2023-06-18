package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.ShoppingcartItemRepository;
import hr.valecic.musicstorewebapp.dal.repository.ShoppingcartRepository;
import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.model.Shoppingcart;
import hr.valecic.musicstorewebapp.model.Shoppingcartitem;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class ShoppingcartService {
    private ShoppingcartRepository shoppingcartRepository;
    private ShoppingcartItemRepository shoppingcartItemRepository;
    @Transactional
    public void saveCart(Person person, Collection<Shoppingcartitem> shoppingCartItemsList) {
        Shoppingcart shoppingcart = new Shoppingcart();
//        if (shoppingcartRepository.existsShoppingcartByPerson(person)) {
//            if (!shoppingcartRepository.findFirstByPersonOrderByIdcartDesc(person).getIspurchased()){
////                gets most recent (last) shoppingcart by that person
//                System.out.println("got to findFirstByPersonOrderByIdcartDesc");
//                shoppingcart = shoppingcartRepository.findFirstByPersonOrderByIdcartDesc(person);
//            }
//        }
        shoppingcart.setPersonByPersonid(person);
        shoppingcart.setCreationtime(Timestamp.valueOf(LocalDateTime.now()));
        shoppingcart.setShoppingcartitemsByIdcart(shoppingCartItemsList);
        shoppingcart.setIspurchased(false);

        Collection<Shoppingcartitem> shoppingCartItemsListCOPY = new ArrayList<>(shoppingCartItemsList);
        shoppingcartRepository.save(shoppingcart);

        for (Shoppingcartitem i:
                shoppingCartItemsListCOPY) {
            i.setShoppingcartByShoppingcartid(shoppingcart);
        }
//        System.out.println("got to findFirstByPersonOrderByIdcartDesc");
//        shoppingcartItemRepository.deleteShoppingcartitemsByShoppingcartByShoppingcartid(shoppingcart);
        shoppingcartItemRepository.saveAll(shoppingCartItemsListCOPY);
    }

    public boolean existsCartForPerson(Person person) {
//        boolean b = shoppingcartRepository.existsShoppingcartByPerson(person);
        return shoppingcartRepository.existsShoppingcartByPerson(person);
//        return true;
    }

    public Collection<Shoppingcartitem> getCartItemsListForPerson(Person person) {
        return shoppingcartRepository.findFirstByPersonOrderByIdcartDesc(person).getShoppingcartitemsByIdcart();
    }

    public void deleteShoppingCartForPerson(Person person) {
        Shoppingcart shoppingcartByPerson = shoppingcartRepository.findFirstByPersonOrderByIdcartDesc(person);
        shoppingcartItemRepository.deleteAllInBatch(shoppingcartItemRepository.getShoppingcartitemsByShoppingcartByShoppingcartid(shoppingcartByPerson));
        shoppingcartRepository.delete(shoppingcartByPerson);
    }

    public Shoppingcart getLastCartItemsListForPerson(Person person) {
        return shoppingcartRepository.findFirstByPersonOrderByIdcartDesc(person);
    }

    public void setCartPurchased(Shoppingcart shoppingcart) {
        shoppingcart.setIspurchased(true);
        shoppingcartRepository.save(shoppingcart);
    }

    public boolean existsUnpurchasedCartForPerson(Person person) {
//        boolean b = shoppingcartRepository.existsShoppingcartByPerson(person);
        Shoppingcart shoppingcart = shoppingcartRepository.findFirstByPersonOrderByIdcartDesc(person);
        System.out.println("is purchased: " + shoppingcart.getIspurchased());
        return shoppingcart.getIspurchased();
    }
    @Transactional
    public void deleteAllUnpurchasedShoppingCartForPerson(Person person) {
        for (Shoppingcart shoppingcart : shoppingcartRepository.findShoppingcartsByPersonAndIspurchased(person, false)) {
            shoppingcartItemRepository.deleteAllInBatch(shoppingcartItemRepository.getShoppingcartitemsByShoppingcartByShoppingcartid(shoppingcart));
        }
        shoppingcartRepository.deleteShoppingcartsByPersonAndIspurchased(person, false);
    }

//    public Collection<? extends Shoppingcartitem> getCartForPerson(Person person) {
//        Shoppingcart shoppingcartByPersonid = shoppingcartRepository.getShoppingcartByPersonid(person.getIdperson());
//        return shoppingcartItemRepository.getShoppingcartitemsByShoppingcartid(shoppingcartByPersonid.getIdcart());
//    }

//    public List<ShoppingCartItem> getCartItemsListForPerson(Person person) {
//        shoppingcartRepository.
//    }
}
