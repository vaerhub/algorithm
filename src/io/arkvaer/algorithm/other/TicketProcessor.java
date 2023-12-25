package io.arkvaer.algorithm.other;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketProcessor {

    public static void main(String[] args) {
        String sourcePath = "D:\\Sync\\Files\\Personal\\AutoHome\\ticket.txt";
        String targetPath = "D:\\Sync\\Files\\Personal\\AutoHome\\ticket.xlsx";
        FileReader reader = new FileReader(sourcePath, StandardCharsets.UTF_8);
        List<String> lines = reader.readLines();
        List<String> current = new ArrayList<>(4);
        List<Item> result = new ArrayList<>(lines.size() / 4);
        for (int i = 0; i < lines.size(); i++) {
            current.add(lines.get(i));
            if (CharSequenceUtil.isBlank(lines.get(i))) {
                String stateAndName = current.get(1);
                String[] split = stateAndName.split(" ");
                System.out.println(Arrays.toString(split)+ current.get(0));
                result.add(Item.getInstance(current.get(0), split[0], split[1], current.get(2)));
                current.clear();
            }
        }
        ExcelWriter writer = ExcelUtil.getWriter(targetPath);
        writer.addHeaderAlias("time", "开票时间");
        writer.addHeaderAlias("state", "状态");
        writer.addHeaderAlias("storeName", "店家名称");
        writer.addHeaderAlias("price", "价格");
        writer.write(result);
        writer.flush();
        writer.close();
        System.out.println("输出完成");

    }

    public static class Item{

        private String time;
        private String state;
        private String storeName;

        private String price;

        public static Item getInstance(String time, String state, String storeName, String price) {
            return new Item(time, state, storeName, price);
        }

        public Item(String time, String state, String storeName, String price) {
            this.time = time;
            this.state = state;
            this.storeName = storeName;
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}