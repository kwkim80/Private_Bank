package ca.algonquin.kw2446.mybank.model;

public class AccountSimple {
    public int id;
    public String title;

    @Override
    public String toString() {
        return "AccountSimple{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
