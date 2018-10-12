/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.persistance;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.co.interfile.billstatement.entity.Bill;

/**
 *
 * @author raymond
 */
@Stateless
public class BillFacade extends AbstractFacade<Bill> {

    @PersistenceContext(unitName = "za.co.interfile_billStatement_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BillFacade() {
        super(Bill.class);
    }
    
}
