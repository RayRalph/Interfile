/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raymond
 */
@Entity
@Table(name = "BILL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b")
    , @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id")
    , @NamedQuery(name = "Bill.findByBillDueDate", query = "SELECT b FROM Bill b WHERE b.billDueDate = :billDueDate")
    , @NamedQuery(name = "Bill.findByBillDate", query = "SELECT b FROM Bill b WHERE b.billDate = :billDate")
    , @NamedQuery(name = "Bill.findByBillCharges", query = "SELECT b FROM Bill b WHERE b.billCharges = :billCharges")
    , @NamedQuery(name = "Bill.findByBillOutstanding", query = "SELECT b FROM Bill b WHERE b.billOutstanding = :billOutstanding")
    , @NamedQuery(name = "Bill.findByBillPeriod", query = "SELECT b FROM Bill b WHERE b.billPeriod = :billPeriod")})
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_DUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date billDueDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_DATE")
    @Temporal(TemporalType.DATE)
    private Date billDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_CHARGES")
    private BigDecimal billCharges;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_OUTSTANDING")
    private BigDecimal billOutstanding;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "BILL_PERIOD")
    private String billPeriod;
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_NO")
    @ManyToOne(optional = false)
    private Account accountId;

    public Bill() {
    }

    public Bill(Integer id) {
        this.id = id;
    }

    public Bill(Integer id, Date billDueDate, Date billDate, BigDecimal billCharges, BigDecimal billOutstanding, String billPeriod) {
        this.id = id;
        this.billDueDate = billDueDate;
        this.billDate = billDate;
        this.billCharges = billCharges;
        this.billOutstanding = billOutstanding;
        this.billPeriod = billPeriod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBillDueDate() {
        return billDueDate;
    }

    public void setBillDueDate(Date billDueDate) {
        this.billDueDate = billDueDate;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public BigDecimal getBillCharges() {
        return billCharges;
    }

    public void setBillCharges(BigDecimal billCharges) {
        this.billCharges = billCharges;
    }

    public BigDecimal getBillOutstanding() {
        return billOutstanding;
    }

    public void setBillOutstanding(BigDecimal billOutstanding) {
        this.billOutstanding = billOutstanding;
    }

    public String getBillPeriod() {
        return billPeriod;
    }

    public void setBillPeriod(String billPeriod) {
        this.billPeriod = billPeriod;
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
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.interfile.billstatement.entity.Bill[ id=" + id + " ]";
    }
    
}
