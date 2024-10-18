package jom.com.softserve.s3.task6;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


enum SortOrder {
    ASC, DESC
}


class AddressBook implements Iterable<String> {
    private final NameAddressPair[] addressBook;
    private int counter = 0;

    public AddressBook(int capacity) {
        addressBook = new NameAddressPair[capacity];
    }

    public boolean create(String firstName, String lastName, String address) {
        int length = addressBook.length;

        if (firstName == null || lastName == null || address == null) {
            return false;
        }
        AddressBook.NameAddressPair personToCreate = new NameAddressPair(new NameAddressPair.Person(firstName, lastName), address);

        if (counter > 0) {
            for (int i = 0; i < length; i++) {
                if (addressBook[i] != null) {
                    if (personToCreate.person.equals(addressBook[i].person)) {
                        return false;
                    }
                    break;
                }
            }
        }
        if (counter < length) {
            addressBook[counter++] = personToCreate;
            return true;
        } else {
            System.arraycopy(addressBook, 0, addressBook, 0, length);
            return true;
        }
    }

    public String read(String firstName, String lastName) {
        StringBuilder info = new StringBuilder();
        if (firstName == null || lastName == null) return null;

        NameAddressPair.Person personToRead = new NameAddressPair.Person(firstName, lastName);


        for (NameAddressPair nameAddressPair : addressBook) {
            if (nameAddressPair != null && nameAddressPair.person != null && nameAddressPair.person.equals(personToRead)) {
                info.append(nameAddressPair.address);
                return info.toString();
            }
        }
        return null;
    }

    public boolean update(String firstName, String lastName, String address) {
        if (firstName == null || lastName == null || address == null) {
            return false;
        }
        AddressBook.NameAddressPair personToUpdate = new NameAddressPair(new NameAddressPair.Person(firstName, lastName), address);


        for (NameAddressPair nameAddressPair : addressBook) {
            if (nameAddressPair != null && nameAddressPair.person.equals(personToUpdate.person)) {
                nameAddressPair.address = address;
                return true;
            }
        }
        return false;
    }


    public boolean delete(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return false;
        }

        NameAddressPair.Person personToDelete = new NameAddressPair.Person(firstName, lastName);

        for (int i = 0; i < addressBook.length; i++) {

            NameAddressPair nameAddressPair = addressBook[i];
            if (nameAddressPair == null) return false;
            if (nameAddressPair.person == null) {
                continue;
            }
            if (nameAddressPair.person.equals(personToDelete)) {
                addressBook[i] = new NameAddressPair(null, null);
                nameAddressPair.address = null;
                counter--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return counter;
    }

    public void sortedBy(SortOrder order) {
        if (counter > 1) {
            Arrays.sort(addressBook, (p1, p2) -> {
                int firstNameCompare = p1.getPerson().getFirsName().compareTo(p2.getPerson().getFirsName());

                if (firstNameCompare == 0) {
                    int lastNameCompare = p1.getPerson().getLastName().compareTo(p2.getPerson().getLastName());
                    return order == SortOrder.ASC ? lastNameCompare : - lastNameCompare;
                }
                return order == SortOrder.ASC ? firstNameCompare : - firstNameCompare;
            });
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new AddressBookIterator();
    }

    private static class NameAddressPair {
        private final Person person;
        private String address;



        private NameAddressPair(Person person, String address) {
            this.person = person;
            this.address = address;
        }

        private Person getPerson() {
            return person;
        }

        private static class Person {
            private final String firstName;
            private final String lastName;

            private Person(String firsName, String lastName) {
                this.firstName = firsName;
                this.lastName = lastName;
            }

            public String getFirsName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (! (o instanceof Person person)) return false;
                return Objects.equals(getFirsName(), person.getFirsName()) && Objects.equals(getLastName(), person.getLastName());
            }

            @Override
            public int hashCode() {
                return Objects.hash(getFirsName(), getLastName());
            }

            @Override
            public String toString() {

                return "First name:" + firstName + ", Last name: " + lastName;
            }
        }
    }

    private class AddressBookIterator implements Iterator<String> {
        private int counter = 0;


        @Override
        public boolean hasNext() {
            while (counter < addressBook.length && addressBook[counter].person == null) {
                counter++;
            }
            return counter < addressBook.length;
        }

        @Override
        public String next() {
            if (! hasNext()) {
                throw new NoSuchElementException();
            }
            String result = "First name: " + addressBook[counter].person.firstName
                            + ", Last name: " + addressBook[counter].person.lastName
                            + ", Address: " + addressBook[counter].address;
            counter++;
            return result;
        }

    }
}
