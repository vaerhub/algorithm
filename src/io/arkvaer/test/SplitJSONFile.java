package io.arkvaer.test;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

public class SplitJSONFile {
    public static void main(String[] args) {

        split(4);

    }

    public static void split(int count) {
        FileReader reader = new FileReader("D:\\git\\algorithm\\src\\io\\arkvaer\\algorithm\\videovrconfig_anchor.json");
        String s = reader.readString();
        JSONArray jsonArray = JSONUtil.parseArray(s);
        int eleCount = jsonArray.size() / count;
        int index = 0;
        String fileName = "videovrconfig_anchor";
        for (int i = 1; i < count; i++) {
            JSONArray array = JSONUtil.createArray();
            for (int j = index; j <= i * eleCount; j++) {
                // System.out.println(index);
                array.add(jsonArray.get(index));
                System.out.println(index);
                index++;
            }
            FileWriter writer = new FileWriter("./" + fileName + (i) +".json");
            writer.write(array.toStringPretty());
//            System.out.println(i);
        }
        if (index < jsonArray.size()) {
            JSONArray array = JSONUtil.createArray();
            for (int i = index; i < jsonArray.size(); i++) {
                array.add(jsonArray.get(i));
            }
            FileWriter writer = new FileWriter(fileName + (count) +".json");
            writer.write(array.toStringPretty());
        }
    }
}
