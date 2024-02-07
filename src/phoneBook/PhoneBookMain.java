package phoneBook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneBookMain {
    public static final int SHOW_PHONE_BOOK = 1;
    public static final int ADD_CONTACT = 2;
    public static final int REMOVE_CONTACT = 3;
    public static final int RENAME_CONTACT = 4;
    public static final int REMOVE_NUMBER = 5;
    public static final int EXIT = 0;

    public static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();
    public static Scanner console = new Scanner(System.in);

    public static void add(String name, int number) {
        ArrayList<Integer> numbers = phoneBook.getOrDefault(name, new ArrayList<>());
        numbers.add(number);
        phoneBook.put(name, numbers);
        System.out.printf("Контакт %s успешно добавлен \n", name);
    }

    public static void remove(String name) {
        phoneBook.remove(name);
        System.out.printf("Контакт %s успешно удалён", name);
    }

    public static void showPhoneBook() {
        System.out.println("Телефонная книга:");
        for (var item : phoneBook.entrySet()) {
            String phones = "";
            for (int el : item.getValue()) {
                phones = phones + el + ", ";
            }
            System.out.printf("%s: %s \n", item.getKey(), phones);
        }
    }

    public static void removeNumber(String name, int number) {
        ArrayList<Integer> numbers = phoneBook.get(name);
        numbers.remove(number - 1);
        if (numbers.isEmpty()) {
            System.out.printf("Для контакта: %s\nбольше не указан номер", name);
        }
        System.out.printf("У контакта %s успешно удалён номер телефона под номером %d", name, number);
    }

    public static void reName(String nameOld, String nameNew) {
        ArrayList<Integer> numbers = phoneBook.get(nameOld);
        phoneBook.remove(nameOld);
        phoneBook.put(nameNew, numbers);
        System.out.printf("Контакт %s успешно переименован на %s", nameOld, nameNew);
    }

    public static void mainMenu() {
        System.out.println("Добро пожаловать в телефонную книгу!\n" +
                "Выберите действие:\n" +
                "1 - Посмотреть телефонную книгу\n" +
                "2 - Добавить контакт\n" +
                "3 - Удалить контакт\n" +
                "4 - Переименовать контакт\n" +
                "5 - Удалить номер у контакта\n" +
                "0 - Выход из телефонной книги\n" +
                "*Введите цифру соответствующую действию\n");
    }

    public static void main(String[] args) {
        int action;

        do {
            mainMenu();
            try {
                action = Integer.parseInt(console.nextLine());
                switch (action) {

                    case SHOW_PHONE_BOOK:
                        showPhoneBook();
                        break;
                    case ADD_CONTACT:
                        System.out.println("Введите имя контакта: ");
                        String addName = console.nextLine();
                        System.out.println("Введите номер телефона: ");
                        int number;
                        try {
                            number = Integer.parseInt(console.nextLine());
                            add(addName, number);
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: Некорректный формат номера");
                        }
                        break;
                    case REMOVE_CONTACT:
                        System.out.println("Введите имя контакта: ");
                        String deleteName = console.nextLine();
                        remove(deleteName);
                        break;
                    case RENAME_CONTACT:
                        System.out.println("Введите имя контакта: ");
                        String nameOld = console.nextLine();
                        System.out.println("Введите новое имя контакта: ");
                        String nameNew = console.nextLine();
                        reName(nameOld, nameNew);
                        break;
                    case REMOVE_NUMBER:
                        System.out.println("Введите имя контакта: ");
                        String name = console.nextLine();
                        System.out.println("Введите номер телефона: ");
                        int deleteNumber;
                        try {
                            deleteNumber = Integer.parseInt(console.nextLine());
                            removeNumber(name, deleteNumber);
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: Некорректный формат номера");
                        }
                        break;
                    default:
                        System.out.println("Введите цифру от 1 до 5");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: Некорректный формат номера!\n" +
                        "Нажмите Enter для продолжения\n");
                action = -1;
                console.nextLine();
            }
        } while (action != EXIT);
        // Добавить подпись коммита1
    }
}