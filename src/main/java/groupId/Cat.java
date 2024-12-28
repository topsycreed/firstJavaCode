package groupId;

public class Cat {
    String name;//field = Barsik

    //void - return nothing
    public void say() {
        String sayWord = "Meow! Мяу! "; //not field, but variable
        System.out.print(sayWord + name);
    }

    //String - return value
    public String getName() {
        return name;//Barsik
    }

    //String getName() // () used for input parameters/arguments of the method/contructor
    // {} - used for body of the method, of the class

    //default cobtructor Cat()
    public Cat() {
    }

    //public - access modifier.. private, protected and default (package-privilage)

    //contructor with name field
    public Cat(String nameCat) {
        //name = 'Barsik'
        this.name = nameCat;
    }
}
