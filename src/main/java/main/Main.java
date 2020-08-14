package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.http.HttpEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import db.MYSQLControl;
import model.Model;
import model.Result;
import parse.JdParse;
import util.HttpsUtil;
import util.SendRequest;
//import util.URLFecter;

public class Main {
    //log4j的是使用，不会的请看之前写的文章
    static final Log logger = LogFactory.getLog(Main.class);
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
    	//CloseableHttpClient client = HttpClientBuilder.create().build();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        //String url="http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
        //抓取的数据
        List<Model> bookdatas = new ArrayList<Model>();
        //URLFecter.URLParser(client, url);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("keyword", "Python");
        params.put("enc", "utf-8");
        params.put("book", "y");
        String url = "http://search.jd.com/Search";
        //Result result = SendRequest.sendGet(, params, params, "utf-8");
        //int StatusCode = result.getStatusCode();
        //if(StatusCode == 200){
        	bookdatas = JdParse.getData(HttpsUtil.getHtml(url, params));
        //}else {
        //	EntityUtils.consume(result.getHttpEntity());
       // }
        //循环输出抓取的数据
        for (Model jd:bookdatas) {
            logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
            System.out.println("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
        }
        //将抓取的数据插入数据库
        MYSQLControl.executeInsert(bookdatas);
    }
}