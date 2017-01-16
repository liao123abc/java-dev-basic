package liao.thomas.javadev.basic.multithread.caseOne;

/**
 * Created by liaodongming on 2016/12/26.
 */
class Person{

    private int weight;

    public Person() {
        super();
    }

    public Person(int weight) {
        super();
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Person [weight=" + weight + "]";
    }
}