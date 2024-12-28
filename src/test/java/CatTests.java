import groupId.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; //first need to import external libs

public class CatTests {

    @Test //annotation that used imported JUnit library
    void simpleCatTest() {
        Cat cat = new Cat("Barsik");
        String catName = cat.getName();

        Assertions.assertEquals("Barsik2", catName);

//        Dog dog = new Dog();
//        dog.getName();


        //1st Cat - type the same as the name of class
        //2nd cat - variable name
        // = equal sign for assign variable a value
        //new means create a new objet
        // new Cat() mean use a contructor
    }
}
