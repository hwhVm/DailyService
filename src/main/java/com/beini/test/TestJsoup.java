package com.beini.test;

import com.beini.util.BLog;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beini on 2017/12/6.
 */
public class TestJsoup {

    //        Document doc =Jsoup.connect("网址/")
//                .data("query", "Java") //请求参数
//                .userAgent("I’mjsoup") //设置User-Agent
//                .cookie("auth", "token") //设置cookie
//                .timeout(3000) //设置连接超时时间
//                .post(); //使用POST方法访问URL

    public static void main(String[] args) throws IOException {
        getData();
    }

    public static void getData() throws IOException {
//        http://blog.csdn.net/ccg_201216323/article/details/53576654
//        String url = "https://m.baidu.com/s?word=" + "足球" + "&from=1013938e";
        String url = "http://blog.csdn.net/";
        Connection conn = Jsoup.connect(url);
        // 修改http包中的header,伪装成浏览器进行抓取
        //User-Agent: Mozilla/5.0 (Linux; U; Android 7.0; zh-CN; E6653 Build/32.3.A.0.376) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/40.0.2214.89 UCBrowser/11.7.5.955 Mobile Safari/537.36
        conn.header("User-Agent", "Mozilla/5.0 (Linux; U; Android 7.0; zh-CN; E6653 Build/32.3.A.0.376) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/40.0.2214.89 UCBrowser/11.7.5.955 Mobile Safari/537.36");
        Document doc = conn.get();
        String string = doc.body().toString();
//        BLog.d("        string==" + string);
        String title = doc.getElementsByClass("list_con").text();
        BLog.d("----------------->title=" + title);
    }
}
