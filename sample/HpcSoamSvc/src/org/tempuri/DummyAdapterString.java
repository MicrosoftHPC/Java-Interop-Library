/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author v-dafu
 */
public class DummyAdapterString extends XmlAdapter<JAXBElement<String>, JAXBElement<String>> {

    @Override
    public JAXBElement<String> unmarshal(JAXBElement<String> val) throws Exception {
        return val;
    }

    @Override
    public JAXBElement<String> marshal(JAXBElement<String> val) throws Exception {
        return val;
    }
}
