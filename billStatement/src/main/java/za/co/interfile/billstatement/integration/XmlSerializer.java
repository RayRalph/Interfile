/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interfile.billstatement.integration;

import com.google.common.collect.Maps;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import za.co.interfile.exception.SerializationException;

/**
 *
 * @author raymond
 */
@SuppressWarnings("unchecked")
public class XmlSerializer<T> implements Serializable {

    private static final long serialVersionUID = -5421645201338048261L;

    private static Map<Class<?>, JAXBContext> contextMap = Maps.newConcurrentMap();

    private Class<T> type;

    private transient Marshaller marshaller;

    private transient Unmarshaller unmarshaller;

    private XMLInputFactory xmlInputFactory;

    protected XmlSerializer(Class<T> type) {
        this.type = type;
    }

    public static <T> XmlSerializer<T> create(Class<T> type) {
        return new XmlSerializer(type);
    }


    public T deserialize(String xml) throws SerializationException {
        try  {
            StringReader reader = new StringReader(xml);
            return returnDeserializeObject(reader);
        } catch (Exception e) {
            throw new SerializationException("Failed to deserialize an instance of " + type, e);
        }
    }

    private T returnDeserializeObject(Reader reader) throws XMLStreamException, JAXBException {
        XMLStreamReader xsr = getXMLInputFactory().createXMLStreamReader(new StreamSource(reader));
        return (T) getUnmarshaller().unmarshal(xsr);
    }

    public T deserialize(File file) throws SerializationException {
        try {
            FileReader reader = new FileReader(file);
            return returnDeserializeObject(reader);
        } catch (Exception e) {
            throw new SerializationException("Failed to deserialize an instance of " + type, e);
        }
    }

    private JAXBContext getContext() throws JAXBException {
        JAXBContext context = contextMap.get(type);
        if (context == null) {
            context = JAXBContext.newInstance(type);
            contextMap.put(type, context);
        }
        return context;
    }

    private Marshaller getMarshaller() throws JAXBException {
        if (marshaller == null) {
            marshaller = getContext().createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        }
        return marshaller;
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        if (unmarshaller == null) {
            unmarshaller = getContext().createUnmarshaller();
        }
        return unmarshaller;
    }

    private XMLInputFactory getXMLInputFactory() {
        if (xmlInputFactory == null) {
            XMLInputFactory xif = XMLInputFactory.newFactory();
            xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            xmlInputFactory = xif;
        }
        return xmlInputFactory;
    }
}
