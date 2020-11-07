package kalaitzidis.alexandros.johnbeker.Models;

public class Person {
    private Name name;
    private Boolean isCheating5;
    private Boolean isCheating6;

    public Person(Name name, Boolean isCheating5, Boolean isCheating6) {
        this.name = name;
        this.isCheating5 = isCheating5;
        this.isCheating6 = isCheating6;
    }

    public Person(Name name) {
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Boolean getCheating5() {
        return isCheating5;
    }

    public void setCheating5(Boolean cheating5) {
        isCheating5 = cheating5;
    }

    public Boolean getCheating6() {
        return isCheating6;
    }

    public void setCheating6(Boolean cheating6) {
        isCheating6 = cheating6;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", isCheating5=" + isCheating5 +
                ", isCheating6=" + isCheating6 +
                '}';
    }
}
