package hr.valecic.musicstorewebapp.dal.repository;

import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByPersonByPersonid(Person person);
    Purchase findByIdpurchase(Long idPurchase);
}
