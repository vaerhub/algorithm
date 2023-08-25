package io.arkvaer.algorithm.other;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ParseToMarkDown {

    public static void main(String[] args) {
        List<String> lines = FileUtil.readLines("D:\\Projects\\algorithm\\src\\io\\arkvaer\\algorithm\\other\\137.txt", StandardCharsets.UTF_8);
        List<String> cur = new ArrayList<>();
        String dic = "├──";
        String course = "|   ├──";
        String course1 = "|   └──";
        int index = 1;
        for (String line : lines) {
            if (line.startsWith(dic)) {
                cur.add("</details>");
                cur.add("<details>");
                cur.add("  <summary>" + line.replace(dic, "") + "</summary>");
                cur.add("");
                index = 1;
            } else {
                cur.add("    " + index + ". "+ line.replace(course, "").replace(course1, ""));
                index++;
            }
        }
        FileUtil.writeLines(cur, "C:\\Users\\zhangchengtao\\Desktop\\res1.md", StandardCharsets.UTF_8);
    }
}
