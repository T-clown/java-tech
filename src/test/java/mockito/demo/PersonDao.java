package mockito.demo;

public interface PersonDao {
    public Person fetchPerson(Integer personId);
    public void update(Person person);
}
