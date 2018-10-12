/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author raymond
 */
@Entity
@Table(name = "ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByAccountNo", query = "SELECT a FROM Account a WHERE a.accountNo = :accountNo")
    , @NamedQuery(name = "Account.findByAccountHolderName", query = "SELECT a FROM Account a WHERE a.accountHolderName = :accountHolderName")
    , @NamedQuery(name = "Account.findByAccountHolderIdNo", query = "SELECT a FROM Account a WHERE a.accountHolderIdNo = :accountHolderIdNo")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNT_NO")
    private BigInteger accountNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 65)
    @Column(name = "ACCOUNT_HOLDER_NAME")
    private String accountHolderName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNT_HOLDER_ID_NO")
    private BigInteger accountHolderIdNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<Contact> contactCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<Address> addressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<Bill> billCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<BillStatement> billStatementCollection;

    public Account() {
    }

    public Account(BigInteger accountNo) {
        this.accountNo = accountNo;
    }

    public Account(BigInteger accountNo, String accountHolderName, BigInteger accountHolderIdNo) {
        this.accountNo = accountNo;
        this.accountHolderName = accountHolderName;
        this.accountHolderIdNo = accountHolderIdNo;
    }

    public BigInteger getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(BigInteger accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public BigInteger getAccountHolderIdNo() {
        return accountHolderIdNo;
    }

    public void setAccountHolderIdNo(BigInteger accountHolderIdNo) {
        this.accountHolderIdNo = accountHolderIdNo;
    }

    @XmlTransient
    public Collection<Contact> getContactCollection() {
        return contactCollection;
    }

    public void setContactCollection(Collection<Contact> contactCollection) {
        this.contactCollection = contactCollection;
    }

    @XmlTransient
    public Collection<Address> getAddressCollection() {
        return addressCollection;
    }

    public void setAddressCollection(Collection<Address> addressCollection) {
        this.addressCollection = addressCollection;
    }

    @XmlTransient
    public Collection<Bill> getBillCollection() {
        return billCollection;
    }

    public void setBillCollection(Collection<Bill> billCollection) {
        this.billCollection = billCollection;
    }

    @XmlTransient
    public Collection<BillStatement> getBillStatementCollection() {
        return billStatementCollection;
    }

    public void setBillStatementCollection(Collection<BillStatement> billStatementCollection) {
        this.billStatementCollection = billStatementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountNo != null ? accountNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountNo == null && other.accountNo != null) || (this.accountNo != null && !this.accountNo.equals(other.accountNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.interfile.billstatement.entity.Account[ accountNo=" + accountNo + " ]";
    }
    
}
