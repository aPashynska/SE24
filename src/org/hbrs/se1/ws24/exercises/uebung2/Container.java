package org.hbrs.se1.ws24.exercises.uebung2;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Container {
    // Interne Liste zur Speicherung von Member-Objekten
    private List<Member> members;
    //Singleton for Container
    private static volatile Container instance;
    //Strategy to save/load data
    private PersistenceStrategy<Member> strategy;

    // Konstruktor zum Initialisieren der Member-Liste
    private Container() {
        this.members = new ArrayList<>();
    }

    public Container getInstance() {
        if(instance == null) {
            synchronized (Container.class) {
                if (instance == null) {
                    instance = new Container();
                }
            }
        }
        return instance;
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> strategy) {
        this.strategy = strategy;
    }

    /**
     * FA1: Methode zum Hinzufügen eines Member-Objekts in die Container-Liste.
     * Überprüft, ob ein Member mit derselben ID bereits existiert.
     * Wenn ja, wird eine ContainerException ausgelöst.
     *
     * @param member das hinzuzufügende Member-Objekt
     * @throws ContainerException wenn ein Member mit der gleichen ID bereits existiert
     */
    public void addMember(Member member) throws ContainerException {
        // Schleife zur Überprüfung auf doppelte ID
        for (Member m : members) {
            if (m.getID().equals(member.getID())) {
                // Ausnahme auslösen, falls ID bereits vorhanden
                throw new ContainerException(member.getID());
            }
        }
        // Member zur Liste hinzufügen, wenn keine doppelte ID gefunden wurde
        members.add(member);
    }

        /**
         * FA2: Methode zum Löschen eines Member-Objekts anhand seiner ID.
         * Wenn kein Member mit der übergebenen ID gefunden wird, wird eine Fehlermeldung zurückgegeben.
         *
         * @param id die ID des zu löschenden Member-Objekts
         * @return eine Nachricht über den Erfolg oder Misserfolg der Löschung
         */
        public String deleteMember(Integer id) {
            Iterator<Member> iter = members.iterator();
            while(iter.hasNext()) {
                Member m = iter.next();
                if(m.getID().equals(id)) {
                    iter.remove();
                    return "Member mit ID " + id + " wurde gelöscht.";
                }
            }
            return "Kein Member mit ID " + id + " gefunden.";
        }


        /**
         * FA3: Methode zur Ausgabe der IDs aller aktuell gespeicherten Member-Objekte.
         * Verwendet die Methode toString() jedes Member-Objekts zur Formatierung der Ausgabe.
         */
        public void dump() {
            // Ausgabe aller Member-IDs durch Aufruf von toString() auf jedem Member-Objekt
            for (Member m : members) {
                System.out.println(m);
            }
        }

        /**
         * FA4: Methode zur Ermittlung der Anzahl der aktuell gespeicherten Member-Objekte.
         *
         * @return die Anzahl der gespeicherten Member-Objekte
         */
        public int size() {
            return members.size();
        }

        public void store() throw PersistenceException {
            if (strategy == null) {
                throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy set for persistence");
        }
            try {
                strategy.save(members);  // Сохраняем данные с использованием установленной стратегии
            } catch (PersistenceException e) {
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Error saving members", e);
            }
    }
    }
