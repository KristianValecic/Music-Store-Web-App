package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.PurchaseRepository;
import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.model.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseService {
    private PurchaseRepository purchaseRepository;

    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> getAllForPerson(Person person) {
        return purchaseRepository.findAllByPersonByPersonid(person);
    }

    public Purchase getPurchaseByID(Long idPurchase) {
        return purchaseRepository.findByIdpurchase(idPurchase);
    }

    public List<Purchase> getFilteredByDate(String fromDate, String toDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp fromStamp = null;
        Timestamp toStamp = null;
        try {
            Date parsedFromDate = dateFormat.parse(fromDate);
            Date parsedToDate = dateFormat.parse(toDate);
            fromStamp = new Timestamp(parsedFromDate.getTime());
            toStamp = new Timestamp(parsedToDate.getTime());

            System.out.println(fromStamp);
            System.out.println(toStamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return purchaseRepository.findPurchasesByTimeofpurchaseBetween(fromStamp,toStamp);
    }
}
