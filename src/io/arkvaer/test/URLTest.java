package io.arkvaer.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.URLUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.io.File;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class URLTest {

    public static void main(String[] args) {
        transUrlToExcel();
    }

    private static void transUrlToExcel() {
        List<String> urls = FileUtil.readLines("C:\\Users\\zhangchengtao\\Desktop\\empty_urls.txt", StandardCharsets.UTF_8);
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\zhangchengtao\\Desktop\\empty_urls.txt");
        List<List<String>> data = new ArrayList<>();
        urls.forEach(url -> {
            List<String> line = new ArrayList<>();
            URI uri = URI.create(url);
            String path = uri.getPath();
            line.add(path);
            String query = uri.getQuery();
            ArrayList<String> strings = new ArrayList<>(Arrays.asList(query.split("&")));
            line.addAll(strings);
            data.add(line);
        });
        writer.write(data);
        writer.flush();
    }
}
