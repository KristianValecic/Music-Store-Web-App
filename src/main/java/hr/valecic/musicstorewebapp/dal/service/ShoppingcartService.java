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
        if (shoppingcartRepository.existsShoppingcartByPerson(person)) {
            shoppingcart = shoppingcartRepository.getShoppingcartByPerson(person);
        }
        shoppingcart.setPersonByPersonid(person);
        shoppingcart.setCreationtime(Timestamp.valueOf(LocalDateTime.now()));
        shoppingcart.setShoppingcartitemsByIdcart(shoppingCartItemsList);

        Collection<Shoppingcartitem> shoppingCartItemsListCOPY = new ArrayList<>(shoppingCartItemsList);
        shoppingcartRepository.save(shoppingcart);

        for (Shoppingcartitem i:
                shoppingCartItemsListCOPY) {
            i.setShoppingcartByShoppingcartid(shoppingcart);
        }
        shoppingcartItemRepository.deleteShoppingcartitemsByShoppingcartByShoppingcartid(shoppingcart);
        shoppingcartItemRepository.saveAll(shoppingCartItemsListCOPY);
    }

    public boolean existsCartForPerson(Person person) {
        boolean b = shoppingcartRepository.existsShoppingcartByPerson(person);
        return shoppingcartRepository.existsShoppingcartByPerson(person);
//        return true;
    }

    public Collection<Shoppingcartitem> getCartItemsListForPerson(Person person) {
        return shoppingcartRepository.getShoppingcartByPerson(person).getShoppingcartitemsByIdcart();
    }

    public void deleteShoppingCartForPerson(Person person) {
        Shoppingcart shoppingcartByPerson = shoppingcartRepository.getShoppingcartByPerson(person);
        shoppingcartItemRepository.deleteAllInBatch(shoppingcartItemRepository.getShoppingcartitemsByShoppingcartByShoppingcartid(shoppingcartByPerson));
        shoppingcartRepository.delete(shoppingcartByPerson);
    }

    public Shoppingcart getLastCartItemsListForPerson(Person person) {
        return shoppingcartRepository.findFirstByPersonOrderByIdcartDesc(person);
    }

//    public Collection<? extends Shoppingcartitem> getCartForPerson(Person person) {
//        Shoppingcart shoppingcartByPersonid = shoppingcartRepository.getShoppingcartByPersonid(person.getIdperson());
//        return shoppingcartItemRepository.getShoppingcartitemsByShoppingcartid(shoppingcartByPersonid.getIdcart());
//    }

//    public List<ShoppingCartItem> getCartItemsListForPerson(Person person) {
//        shoppingcartRepository.
//    }
}
