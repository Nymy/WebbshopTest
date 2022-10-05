import bo.PersonHandler;
import ui.PersonInfo;

import java.sql.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {
    public static void main(String[] args) {

        Collection<PersonInfo> person = PersonHandler.getPerson("viktor", "groda");
        Iterator<PersonInfo> p = person.iterator();
        for (; p.hasNext(); ) {
            PersonInfo pe = p.next();
            System.out.println(pe.getUsername());
            System.out.println(pe.getPassword());
        }
    }

}
