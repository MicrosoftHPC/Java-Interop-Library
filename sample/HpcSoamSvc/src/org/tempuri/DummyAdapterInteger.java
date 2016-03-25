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
public class DummyAdapterInteger extends XmlAdapter<Integer, Integer> {

    @Override
    public Integer unmarshal(Integer val) throws Exception {
        return val;
    }

    @Override
    public Integer marshal(Integer val) throws Exception {
        return val;
    }

}
