package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
@XmlType(name = "shop")
@XmlRootElement
public class Shop {
    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    public Shop(Goods goods, int count, double profit, String[] secretData) {
        this.goods = goods;
        this.count = count;
        this.profit = profit;
        this.secretData = secretData;
    }

    public Shop() {
    }

    public static class Goods{
        List<String> names;

        public Goods(List<String> names) {
            this.names = names;
        }
    }
}
