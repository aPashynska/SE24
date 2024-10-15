package uebung2;
import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    Container container;
    @BeforeEach
    void setUp() {
        container = new Container();
    }

    @Test
    void addMember() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);
        container.addMember(member1);
        assertEquals(1, container.size());
        container.addMember(member2);
        assertEquals(2, container.size());
        assertThrows(ContainerException.class, ()-> container.addMember(member1)); //testing for duplicates
        assertThrows(ContainerException.class, ()-> container.addMember(member1));
        assertThrows(Exception.class, ()->container.addMember(null));
    }

    @Test
    void deleteMember() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);
        container.addMember(member1);
        container.addMember(member2);
        assertEquals("Member mit ID " + 1 + " wurde gelöscht.", container.deleteMember(1));
        assertEquals("Member mit ID " + 2 + " wurde gelöscht.", container.deleteMember(2));
        assertEquals("Kein Member mit ID " + 2 + " gefunden.", container.deleteMember(2));

    }

    @Test
    void size() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);
        container.addMember(member1);
        assertEquals(1, container.size());
        container.addMember(member2);
        assertEquals(2, container.size());
        container.deleteMember(1);
        assertEquals(1, container.size());

    }
}
