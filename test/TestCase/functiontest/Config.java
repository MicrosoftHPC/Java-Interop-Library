/**
 * 
 */
package functiontest;

import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

/**
 * @author yutongs This class is to store the config setting for the tests.
 */
public class Config {

    public String UserName = "fareast\\wsdcta";
    public String Password = "Pa55word##3";
    public String UserName2 = "fareast\\wsdcta2";
    public String Password2 = "Pa55word##3";

    public String Scheduler = "wcs-1515118";
    public String ServiceName = "JavaTestService";
    public int NbOfCalls = 12;
    public int NbOfClients = 1;
    public int NbOfBatches = 1;
    public int NbOfSessions = 1;

    public Config() {
        File f = new File("TestData.xml");
        if (f.exists()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();
                doc = builder.parse(f);
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

            XPathFactory xpFactory = XPathFactory.newInstance();
            path = xpFactory.newXPath();

            // read the global config data
            try {
                Scheduler = path.evaluate("/Config/Global/Scheduler", doc);
                UserName = path.evaluate("/Config/Global/UserName", doc);
                Password = path.evaluate("/Config/Global/Password", doc);
                UserName2 = path.evaluate("/Config/Global/UserName2", doc);
                Password2 = path.evaluate("/Config/Global/Password2", doc);

                ServiceName = path.evaluate("/Config/Global/ServiceName", doc);
                NbOfCalls = Integer.parseInt(path.evaluate(
                        "/Config/Global/NbOfCalls", doc));
                NbOfClients = Integer.parseInt(path.evaluate(
                        "/Config/Global/NbOfClients", doc));
                NbOfBatches = Integer.parseInt(path.evaluate(
                        "/Config/Global/NbOfBatches", doc));
                NbOfSessions = Integer.parseInt(path.evaluate(
                        "/Config/Global/NbOfSessions", doc));

            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }

        }

    }

    public Config(String groupName) {
        this();
        this.groupName = groupName;

    }

    public String getValue(String name) {
        String value = "";

        try {
            value = path.evaluate("/Config/Group[@name=\"" + groupName + "\"]/"
                    + name, doc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return value;
    }

    private String groupName = "";
    private Document doc = null;
    private XPath path = null;

}
