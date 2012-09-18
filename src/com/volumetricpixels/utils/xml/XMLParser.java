package com.volumetricpixels.utils.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Basic XML Parser
 */
public class XMLParser {
    public Document readXml(File f) throws XMLException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(f);
        } catch (Exception e) {
            throw new XMLException(e);
        }
    }

    public Document readXml(String fileName) throws XMLException {
        return readXml(new File(fileName));
    }

    public String readProperty(Element element, String name) throws XMLException {
        NodeList nodeList = element.getElementsByTagName(name);
        if (nodeList.getLength() == 1) {
            return ((Element) nodeList.item(0)).getTextContent();
        }
        if (nodeList.getLength() > 1) {
            throw new XMLException("Too many occurences of the \"" + name + "\" property");
        }
        throw new XMLException("Missing \"" + name + "\" property");
    }

    public String readProperty(Element element, String name, String defValue) throws XMLException {
        NodeList nodeList = element.getElementsByTagName(name);
        if (nodeList.getLength() == 1) {
            return ((Element) nodeList.item(0)).getTextContent();
        }
        if (nodeList.getLength() > 1) {
            throw new XMLException("Too many occurences of the \"" + name + "\" property");
        }
        return defValue;
    }

    public void writeProperty(Element element, String name, String value) {
        NodeList nodeList = element.getElementsByTagName(name);
        if (nodeList.getLength() == 1) {
            nodeList.item(0).setTextContent(value);
        } else {
            Element newProp = element.getOwnerDocument().createElement(name);
            newProp.setTextContent(value);
            element.appendChild(newProp);
        }
    }
}
