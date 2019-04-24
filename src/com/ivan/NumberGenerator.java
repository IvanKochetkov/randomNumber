package com.ivan;

public class NumberGenerator {
    // класс который генерирует число
    static String generateNumber() {
        int result = 0; // изначальное число 0
        int rate = 1; // стартовый разряд числа
        // перебираем и радноим единицы, десятки и сотни.
        for(int i = 0; i < 3; i++) {
            result += ((int)(Math.random() * 9)) * rate; // рандом от 0 до 9 и умножаме на цифру разряда
            rate *= 10; // увеличиваем разряд
        }
        result += 1 + ((int)(Math.random() * 9)) * rate; // на цифру старшего разряда рандомим отдельно т.к. в цикле может быть 0. тут же от 1 до 9 * на разряд

        return Integer.toString(result); // перевод числа в строку
    }
}
