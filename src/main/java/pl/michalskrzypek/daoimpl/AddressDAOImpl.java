package pl.michalskrzypek.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.michalskrzypek.dao.AddressDAO;
import pl.michalskrzypek.entity.Address;

@Repository("addressDAO")
@Transactional
public class AddressDAOImpl implements AddressDAO{

	@Autowired
	SessionFactory sessionFactory;

	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Address getBillingAddress(int accountId) {
		try {
			String dbQuery = "FROM Address WHERE accountId = :accountId and isBilling = :isBilling";
			return sessionFactory.getCurrentSession().createQuery(dbQuery, Address.class)
					.setParameter("accountId", accountId).setParameter("isBilling", true).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateAddress(Address address) {

		try {
			sessionFactory.getCurrentSession().update(address);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().delete(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Address> getShippingAddresses(int accountId) {
		try {
			String dbQuery = "FROM Address WHERE accountId = :accountId and isShipping = :isShipping";
			return sessionFactory.getCurrentSession().createQuery(dbQuery, Address.class)
					.setParameter("accountId", accountId).setParameter("isShipping", true).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Address getAddress(int addressId) {
		try {
			String dbQuery = "FROM Address WHERE Id = :Id";
			return sessionFactory.getCurrentSession().createQuery(dbQuery, Address.class)
					.setParameter("Id", addressId).getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
