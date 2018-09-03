package pw.io.booker.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pw.io.booker.model.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

	Customer findByUsernameAndPassword(String username, String password);
	
}
