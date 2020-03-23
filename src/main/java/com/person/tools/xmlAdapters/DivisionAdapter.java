package com.person.tools.xmlAdapters;

import com.person.tools.MyRepositoryReader;
import com.person.tools.PersonFactory;
import ru.vsu.lab.entities.IDivision;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DivisionAdapter extends XmlAdapter<String, IDivision> {

    @Override
    public IDivision unmarshal(String s) throws Exception {
        return MyRepositoryReader.readDivision(s,new PersonFactory());
    }

    @Override
    public String marshal(IDivision division) throws Exception {
        return division.getName();
    }
}
