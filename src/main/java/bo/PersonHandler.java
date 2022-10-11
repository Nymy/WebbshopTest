package bo;

import ui.PersonInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PersonHandler {

    /**
     * Get user from database
     * @param username userID
     * @param password password
     * @return arraylist with the user
     */
    public static ArrayList<PersonInfo> getPerson(String username, String password){
        Collection c = Person.searchUser(username, password);
        ArrayList<PersonInfo> person = new ArrayList<PersonInfo>();
        for (Iterator it = c.iterator(); it.hasNext();){
            Person p = (Person) it.next();
            person.add(new PersonInfo(p.getUsername(), p.getPassword()));
        }
        return person;
    }
}
