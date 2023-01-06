package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * CLass for user
 * @author Nermin Obucina
 */

public class User implements Idable {
    private int iduser;
    private String name;
    private String surname;
    private String mail;
    private String password;

    @Override
    public int getId() {
        return iduser;
    }

    @Override
    public void setId(int id) {
        this.iduser = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return iduser == user.iduser && name.equals(user.name) && surname.equals(user.surname) && mail.equals(user.mail) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, name, surname, mail, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + password + '\'' +
                '}';
    }

}
