package phoneBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneBookMain {
    public static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();
    public static Scanner console = new Scanner(System.in);
    public static void add(String name, int number) {
        ArrayList<Integer> numbers = phoneBook.getOrDefault(name, new ArrayList<>());
        numbers.add(number);
        phoneBook.put(name, numbers);
        System.out.printf("Контакт %s успешно добавлен \n", name);
    }
    public static void remove(String name){
        phoneBook.remove(name);
        System.out.printf("Контакт %s успешно удалён", name);
    }
    public static void showPhoneBook(){
        System.out.println("Телефонная книга:");
        for (var item : phoneBook.entrySet()) {
            String phones = "";
            for(int el: item.getValue()){
                phones = phones + el + ", ";
            }
            System.out.printf("%s: %s \n", item.getKey(), phones);
        }
    }
    public static void removeNumber(String name, int number){
        ArrayList<Integer> numbers = phoneBook.get(name);
        numbers.remove(number - 1);
        if (numbers.isEmpty()){
            System.out.printf("Для контакта: %s\nбольше не указан номер", name);
        }
        System.out.printf("У контактка %s успешно удалён номер телефона под номером %d", name, number);
    }
    public static void reName(String nameOld, String nameNew){
        ArrayList<Integer> numbers = phoneBook.get(nameOld);
        phoneBook.remove(nameOld);
        phoneBook.put(nameNew,  numbers);
        System.out.printf("Контакт %s успешно переименован на %s", nameOld, nameNew);
    }
    public static void mainMenu(){
        System.out.println("Добро пожаловать в телефонную книгу!\n" +
                "Выберите действие:\n" +
                "1 - Посмотреть телефонную книгу\n" +
                "2 - Добавить контакт\n" +
                "3 - Удалить контакт\n" +
                "4 - Переименовать контакт\n" +
                "5 - Удалить номер у контакта\n" +
                "*Введите циру соответствующую действию\n");
    }
    
    
    public static void main(String[] args) {
        mainMenu();

        int action = Integer.parseInt(console.nextLine());
        switch (action) {
            case 1:
                showPhoneBook();
                break;
            case 2:
                System.out.println("Введите имя контакта: ");
                String addName = console.nextLine();
                System.out.println("Введите номер телефона: ");
                int number = Integer.parseInt(console.nextLine());
                add(addName, number);
                break;
            case 3:
                System.out.println("Введите имя контакта: ");
                String deleteName = console.nextLine();
                remove(deleteName);
                break;
            case 4:
                System.out.println("Введите имя контакта: ");
                String nameOld = console.nextLine();
                System.out.println("Введите новое имя контакта: ");
                String nameNew = console.nextLine();
                reName(nameOld, nameNew);
                break;
            case 5:
                System.out.println("Введите имя контакта: ");
                String name = console.nextLine();
                System.out.println("Введите номер телефона: ");
                int deleteNumber = Integer.parseInt(console.nextLine());
                removeNumber(name, deleteNumber);
                break;
            default:
                System.out.println("Введите цифру от 1 до 5");
                break;
        }
    }
}
