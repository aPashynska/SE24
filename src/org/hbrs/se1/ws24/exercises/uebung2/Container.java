package org.hbrs.se1.ws24.exercises.uebung2;
import java.util.ArrayList;
import java.util.List;
public class Container {
    // Interne Liste zur Speicherung von Member-Objekten
    private List<Member> members;
    // Konstruktor zum Initialisieren der Member-Liste
    public Container() {
        this.members = new ArrayList<>();
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
            // Schleife zum Suchen des Members mit der angegebenen ID
            for (Member m : members) {
                if (m.getID().equals(id)) {
                    // Member aus der Liste entfernen und Erfolgsnachricht zurückgeben
                    members.remove(m);
                    return "Member mit ID " + id + " wurde gelöscht.";
                }
            }
            // Fehlermeldung zurückgeben, wenn ID nicht gefunden wurde
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
    }
