/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.persistance;

import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.co.interfile.billstatement.entity.Account;

/**
 *
 * @author raymond
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "za.co.interfile_billStatement_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    private Account account;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    public Account findByAccountNo(BigInteger accountNo){
        Query query = em.createNamedQuery("Account.findByAccountNo");
        query.setParameter("accountNo", accountNo);
        try {
            account = (Account) query.getSingleResult();
        } catch (NoResultException nre) {
            account = null;
        }
        return account;
    }
    
}
