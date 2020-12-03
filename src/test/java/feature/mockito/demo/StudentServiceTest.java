package feature.mockito.demo;

import org.junit.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
    @Mock
    private PersonDao personDao;//模拟对象
    private PersonService personService;//被测类

    public StudentServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
        MockitoAnnotations.initMocks(this);
        // 用模拟对象创建被测类对象
        personService = new PersonService(personDao);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldUpdatePersonName() {
        Person person = new Person(1, "Phill");
        // 设置模拟对象的返回预期值
        when(personDao.fetchPerson(1)).thenReturn(person);
        // 执行测试
        boolean updated = personService.update(1, "David");
        // 验证更新是否成功
        assertTrue(updated);
        // 验证模拟对象的fetchPerson(1)方法是否被调用了一次
        verify(personDao).fetchPerson(1);
        // 得到一个抓取器
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
        //验证模拟对象的update()是否被调用一次，并抓取调用时传入的参数值
        verify(personDao).update(personCaptor.capture());
        // 获取抓取到的参数值
        Person updatePerson = personCaptor.getValue();
        // 验证调用时的参数值
        assertEquals("David", updatePerson.getPersonName());
        // 检查模拟对象上是否还有未验证的交互
        verifyNoMoreInteractions(personDao);
    }

    @Test
    public void shouldNotUpdate() {
        // 设置模拟对象的返回预期值
        when(personDao.fetchPerson(1)).thenReturn(null);
        // 执行测试
        boolean updated = personService.update(1, "David");
        // 验证更新是否失败
        assertFalse(updated);
        // 验证模拟对象的fetchPerson(1)方法是否被调用了一次
        verify(personDao).fetchPerson(1);
        // 验证模拟对象是否没有发生任何交互
        verifyZeroInteractions(personDao);
        // 检查模拟对象上是否还有未验证的交互
        verifyNoMoreInteractions(personDao);

    }
}
