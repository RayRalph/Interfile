/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raymond
 */
@Entity
@Table(name = "CONTACT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
    , @NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id")
    , @NamedQuery(name = "Contact.findByPhoneHome", query = "SELECT c FROM Contact c WHERE c.phoneHome = :phoneHome")
    , @NamedQuery(name = "Contact.findByPhoneMobile", query = "SELECT c FROM Contact c WHERE c.phoneMobile = :phoneMobile")
    , @NamedQuery(name = "Contact.findByPhoneWork", query = "SELECT c FROM Contact c WHERE c.phoneWork = :phoneWork")})
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PHONE_HOME")
    private String phoneHome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PHONE_MOBILE")
    private BigInteger phoneMobile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PHONE_WORK")
    private String phoneWork;
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_NO")
    @ManyToOne(optional = false)
    private Account accountId;

    public Contact() {
    }

    public Contact(Integer id) {
        this.id = id;
    }

    public Contact(Integer id, String phoneHome, BigInteger phoneMobile, String phoneWork) {
        this.id = id;
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.phoneWork = phoneWork;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public BigInteger getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(BigInteger phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public void setPhoneWork(String phoneWork) {
        this.phoneWork = phoneWork;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.interfile.billstatement.entity.Contact[ id=" + id + " ]";
    }
    
}
