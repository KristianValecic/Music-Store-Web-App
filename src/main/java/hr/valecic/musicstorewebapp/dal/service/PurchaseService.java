package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.PurchaseRepository;
import hr.valecic.musicstorewebapp.dal.repository.RoleRepository;
import hr.valecic.musicstorewebapp.model.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseService {
    private PurchaseRepository purchaseRepository;

    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}
