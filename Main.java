package netology.ru.PopulationCensus;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countYoung = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних жителей города - " + countYoung + " человек;");

        List<String> listConscripts = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily).toList();
        System.out.println("Количество призывников: " + listConscripts.size());

        List<String> employablePeople = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> (x.getAge() <= 65 && x.getSex().equals(Sex.Man) || x.getAge() <= 60 && x.getSex().equals(Sex.Woman)))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily).toList();
        System.out.println("Количество потенциально работоспособных людей с высшим образованием: " + employablePeople.size());
    }
}