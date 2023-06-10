package com.javarush.task.task36.task3604;

/* 
Разбираемся в красно-черном дереве
*/

public class Solution {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

// добавление элементов
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

// проверка наличия элемента в дереве
        if (!tree.isEmpty()) {
            System.out.println("Дерево не пустое.");
        }
        RedBlackTree.Node node = tree.find(42);
        if (node != null) {
            int element = node.getElement();
            System.out.println("Найден элемент " + element);
        } else {
            System.out.println("Элемент не найден");
        }
    }

}
