package pro.tremblay.java8;

public class User implements Comparable<User> {

  private String name;

  private User(String name) {
    this.name = name;
  }

  public static User create(String name) {
    return new User(name);
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) { return true; }
    if(o == null || getClass() != o.getClass()) { return false; }

    User user = (User) o;

    return name.equals(user.name);

  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public int compareTo(User o) {
    return name.compareTo(o.name);
  }
}
