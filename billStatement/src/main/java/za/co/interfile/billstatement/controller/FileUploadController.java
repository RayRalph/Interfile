/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import org.primefaces.model.UploadedFile;
import za.co.interfile.billstatement.entity.Account;
import za.co.interfile.billstatement.entity.Bill;
import za.co.interfile.billstatement.entity.BillStatement;
import za.co.interfile.billstatement.entity.Contact;
import za.co.interfile.exception.SerializationException;
import za.co.interfile.billstatement.integration.XmlSerializer;
import za.co.interfile.billstatement.integration.utils.HandlerUtils;
import za.co.interfile.billstatement.persistance.AccountFacade;
import za.co.interfile.billstatement.persistance.AddressFacade;
import za.co.interfile.billstatement.persistance.BillFacade;
import za.co.interfile.billstatement.persistance.ContactFacade;
import za.co.interfile.billstatement.persistance.StatementFacade;
import za.co.interfile.schema.statement.Address;
import za.co.interfile.schema.statement.Bills;
import za.co.interfile.schema.statement.ContactDetails;
import za.co.interfile.schema.statement.Statement;

/**
 *
 * @author raymond
 */
@ManagedBean
public class FileUploadController {
    protected static final Logger logger = Logger.getLogger("FileUploadController");

    @Inject
    AccountFacade accountRepo;

    @Inject
    AddressFacade addressRepo;
    @Inject
    BillFacade billRepo;
    @Inject
    ContactFacade contactRepo;
    @Inject
    StatementFacade statementRepo;
    
    private UploadedFile file;
    private boolean fileSelected;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
            fileSelected = true;
        this.file = file;
    }

    public void upload() throws JAXBException, SerializationException, IOException {
        if (file != null && file.getFileName().contains(".xml")) {
            deserializeFile(file.getInputstream());
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            FacesMessage message = new FacesMessage("Failure", file.getFileName() + " is not of type XML.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public boolean isFileSelected(){
        return fileSelected;
    }
    
    private void deserializeFile(InputStream stream) throws JAXBException, SerializationException {
        XmlSerializer<za.co.interfile.schema.statement.Statements> serializer = XmlSerializer
                .create(za.co.interfile.schema.statement.Statements.class);
        String result = new BufferedReader(new InputStreamReader(stream)).lines()
                .parallel().collect(Collectors.joining("\n"));
        logger.info(MessageFormat.format("Processing: RESULT message {0}", result));
        za.co.interfile.schema.statement.Statements statements = serializer.deserialize(result);
        processStatements(statements);
    }

    private void processStatements(za.co.interfile.schema.statement.Statements statements) {
        logger.info(MessageFormat.format("Processing: STATEMENTS message {0}", statements));
        for(Statement stat : statements.getStatement()){
            setAccount(stat.getAccount());
            Account accId = getAccountFacade().findByAccountNo(stat.getAccount().getAccountNumber());
            setStatement(stat.getId(), accId);
            setContactDetails(stat.getAccount().getAccountHolderDetails().getContactDetails(), accId);
            setAddress(stat.getAccount().getAccountHolderDetails().getAddressDetails().getAddress(), accId);
            setBills(stat.getAccount().getBills(), accId);
        }

    }

    private void setAccount(za.co.interfile.schema.statement.Account stateAccount) {
        za.co.interfile.billstatement.entity.Account account = new za.co.interfile.billstatement.entity.Account();
        account.setAccountNo((null != stateAccount.getAccountNumber()) ? stateAccount.getAccountNumber() : BigInteger.ZERO);
        account.setAccountHolderIdNo((null != stateAccount.getAccountHolderIDNumber()) ? stateAccount.getAccountHolderIDNumber() : BigInteger.ZERO);
        account.setAccountHolderName(HandlerUtils.returnPlaceHolderIfEmptyOrNull(stateAccount.getAccountHolderName()));

        try {
            getAccountFacade().edit(account);
        } catch (NullPointerException e) {
            getAccountFacade().create(account);
        }finally {
            getAccountFacade().flush(account);
        }
    }

    private void setStatement(String id, Account accId) {
        za.co.interfile.billstatement.entity.BillStatement stat = new BillStatement();
        stat.setStatementId(id);
        stat.setAccountId(accId);
        getStatementFacade().create(stat);
    }

    private void setContactDetails(ContactDetails details, Account accId) {
        za.co.interfile.billstatement.entity.Contact contactDetails = new Contact();
        contactDetails.setPhoneHome(HandlerUtils.returnPlaceHolderIfEmptyOrNull(details.getHome()));
        contactDetails.setPhoneMobile((null != details.getMobile()) ? details.getMobile() : BigInteger.ZERO);
        contactDetails.setPhoneWork(HandlerUtils.returnPlaceHolderIfEmptyOrNull(details.getWork()));
        contactDetails.setAccountId(accId);
//        try {
            getContactFacade().edit(contactDetails);
//        } catch (Exception e) {
//            getContactFacade().create(contactDetails);
//        }
    }

    private void setAddress(Address address, Account accId) {
        za.co.interfile.billstatement.entity.Address addr = new za.co.interfile.billstatement.entity.Address();
        addr.setAddressLine1(HandlerUtils.returnPlaceHolderIfEmptyOrNull(address.getLine1()));
        addr.setAddressLine2(HandlerUtils.returnPlaceHolderIfEmptyOrNull(address.getLine2()));
        addr.setAddressLine3(HandlerUtils.returnPlaceHolderIfEmptyOrNull(address.getLine3()));
        addr.setPostalCode((null != address.getPostalCode())?address.getPostalCode():BigInteger.ZERO);
        addr.setAccountId(accId);
//        try {
            getAddressFacade().edit(addr);
//        } catch (NullPointerException npe) {
//            getAddressFacade().create(addr);
//        }
    }

    private void setBills(Bills bills, Account accId) {
        bills.getBill().stream().forEach(bill -> {
            za.co.interfile.billstatement.entity.Bill b = new Bill();
            b.setBillCharges((null != bill.getCharges())?bill.getCharges():BigDecimal.ZERO);
            b.setBillOutstanding((null != bill.getOutstanding()) ? bill.getOutstanding() : BigDecimal.ZERO);
            b.setBillPeriod(HandlerUtils.returnPlaceHolderIfEmptyOrNull(bill.getPeriod()));
            b.setBillDate(HandlerUtils.convertXmlGregorianToDate(bill.getBillDate()));
            b.setBillDueDate(HandlerUtils.convertXmlGregorianToDate(bill.getDueDate()));
            b.setAccountId(accId);
            getBillFacade().create(b);
        });
    }
    
    private AccountFacade getAccountFacade() {
        return accountRepo;
    }
    
    private AddressFacade getAddressFacade(){
        return addressRepo;
    }
    
    private BillFacade getBillFacade(){
        return billRepo;
    }
    
    private ContactFacade getContactFacade(){
        return contactRepo;
    }
    
    private StatementFacade getStatementFacade(){
        return statementRepo;
    }
}
