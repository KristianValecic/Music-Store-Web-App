package hr.valecic.musicstorewebapp.dal.repository;

import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.model.Shoppingcart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingcartRepository extends JpaRepository<Shoppingcart,Long> {
    boolean existsShoppingcartByPerson(Person person);
    Shoppingcart getShoppingcartByPerson(Person person);
    Shoppingcart findFirstByPersonOrderByIdcartDesc(Person person);
}
