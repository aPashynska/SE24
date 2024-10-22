package uebung2;
import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;


import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    private Container container;

    @BeforeEach
    void setUp() {
        container = Container.getInstance();
        container.getCurrentList().clear();
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
        assertEquals(2, container.size());
        container.deleteMember(1);
        assertEquals(1, container.size());

    }

    @Test
    public void testStoreWithoutStrategy() {
        Container container = Container.getInstance();
        assertThrows(PersistenceException.class,()-> container.store());
    }

    @Test
    public void testMongoDBStrategy() {
        Container container = Container.getInstance();
        PersistenceStrategy<Member> mongoStrategy = new PersistenceStrategyMongoDB<>();
        container.setPersistenceStrategy(mongoStrategy);
        assertThrows(UnsupportedOperationException.class, ()-> container.store());  //exception.getMessage());
    }


    @Test
    public void testInvalidFileLocation() throws PersistenceException {
        Container container = Container.getInstance();
        PersistenceStrategyStream<Member> strategy = new PersistenceStrategyStream<>();

        // Path as catalog
        strategy.setLocation("/path/to/directory/");  // that shouldnt work

        container.setPersistenceStrategy(strategy);

        PersistenceException exception = assertThrows(PersistenceException.class, container::store);
        assertThrows(PersistenceException.class, ()-> container.store());
        assertEquals(PersistenceException.ExceptionType.SaveFailure, exception.getExceptionTypeType());
    }


}
