/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.entity;

import java.io.Serializable;
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
@Table(name = "BILL_STATEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BillStatement.findAll", query = "SELECT b FROM BillStatement b")
    , @NamedQuery(name = "BillStatement.findById", query = "SELECT b FROM BillStatement b WHERE b.id = :id")
    , @NamedQuery(name = "BillStatement.findByStatementId", query = "SELECT b FROM BillStatement b WHERE b.statementId = :statementId")})
public class BillStatement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "STATEMENT_ID")
    private String statementId;
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_NO")
    @ManyToOne(optional = false)
    private Account accountId;

    public BillStatement() {
    }

    public BillStatement(Integer id) {
        this.id = id;
    }

    public BillStatement(Integer id, String statementId) {
        this.id = id;
        this.statementId = statementId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
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
        if (!(object instanceof BillStatement)) {
            return false;
        }
        BillStatement other = (BillStatement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.interfile.billstatement.entity.BillStatement[ id=" + id + " ]";
    }
    
}
