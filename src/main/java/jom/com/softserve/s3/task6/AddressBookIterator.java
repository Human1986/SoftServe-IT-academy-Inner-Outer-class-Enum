package jom.com.softserve.s3.task6;

import java.util.*;


enum SortOrder {
    ASC, DESC
}


class AddressBook implements Iterable<String> {
    private NameAddressPair[] addressBook;
    private int counter = 0;

    public AddressBook(int capacity) {
        addressBook = new NameAddressPair[capacity];
    }

    public boolean create(String firstName, String lastName, String address) {

        int length = addressBook.length;

        if (firstName != null || lastName != null) {
            NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
            NameAddressPair newPerson = new NameAddressPair(person, address);
            counter = 0;
            if (counter > 0) {
                for (int i = 0; i < addressBook.length; i++) {
                    Arrays.toString(addressBook);
                    if (person.equals(addressBook[counter++].person)) {
                        return false;
                    }
                }
            }

            if (counter < length) {
                addressBook[counter++] = newPerson;
                return true;
            } else {
                NameAddressPair[] newPair = new NameAddressPair[length * 2];
                addressBook = newPair;
                System.arraycopy(addressBook, 0, newPair, 0, length);
                addressBook[counter++] = newPerson;
                Arrays.toString(addressBook);
                return true;
            }

        }
        return false;
    }

    public String read(String firstName, String lastName) {
        StringBuilder info = new StringBuilder();
        if (firstName == null || lastName == null) return null;

        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);

        for (int i = 0; i < addressBook.length; i++) {
            if (addressBook[i].person.equals(person)) {
                info.append(addressBook[i].address);
                break;
            }
        }

        return info.toString();
    }

    public boolean update(String firstName, String lastName, String address) {
        if (firstName == null || lastName == null || address == null) {
            return false;
        }
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        AddressBook.NameAddressPair updatedPerson = new NameAddressPair(person, address);

        int i = 0;
        while (i < addressBook.length) {
            if (addressBook[i].person.equals(person)) {
                addressBook[i].address = address;
//                updatedPerson.setAddress(address);
                return true;
            }
            i++;
        }

        return false;
    }


    public boolean delete(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return false;
        }

        int indexFirstName = 0;
        int indexLastName = 0;

        for (int i = 0; i < addressBook.length; i++) {
            if (addressBook[i].person.firstName.equals(firstName)) addressBook[i] = null;
            if (addressBook[i].person.lastName.equals(lastName)) addressBook[i] = null;

        }

        return true;
    }

    public int size() {
        return addressBook.length;
    }

    public void sortedBy(SortOrder order) {
        if (counter > 1) {
            Arrays.sort(addressBook, 0, counter, new Comparator<NameAddressPair>() {
                @Override
                public int compare(NameAddressPair p1, NameAddressPair p2) {
                    int firstNameCompare = p1.getPerson().getFirsName().compareTo(p2.getPerson().getFirsName());

                    if (firstNameCompare == 0) {
                        int lastNameCompare = p1.getPerson().getLastName().compareTo(p2.getPerson().getLastName());
                        return order == SortOrder.ASC ? lastNameCompare : - lastNameCompare;
                    }
                    return order == SortOrder.ASC ? firstNameCompare : - firstNameCompare;
                }
            });
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new AddressBookIterator();
    }

    private static class NameAddressPair {
        private Person person;
        private String address;


        public NameAddressPair(Person person, String address) {
            this.person = person;
            this.address = address;
        }

        public Person getPerson() {
            return person;
        }

        public void setPerson(Person person) {
            this.person = person;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        private static class Person {
            private String firstName;
            private String lastName;

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
                final StringBuilder sb = new StringBuilder("Person -> ");
                sb.append("firsName: ").append(firstName);
                sb.append(", lastName: ").append(lastName);
                return sb.toString();
            }
        }
    }

    private class AddressBookIterator implements Iterator<String> {
        private int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < addressBook.length;
        }

        @Override
        public String next() {
            if (! hasNext()) {
                throw new NoSuchElementException();
            }
            NameAddressPair pair = addressBook[counter++];

            return "First name:" + pair.getPerson().getFirsName() + ", Last name: " + pair.getPerson().getLastName() + ", Address:" + pair.getAddress();
        }
    }
}



