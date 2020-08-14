package util;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import model.Model;
import parse.JdParse;

public class URLFecter {
    public static List<Model> URLParser (CloseableHttpClient client, String url) throws Exception {
        //用来接收解析的数据
        List<Model> JingdongData = new ArrayList<Model>();
        //获取网站响应的html，这里调用了HTTPUtils类
        HttpResponse response = HTTPUtils.getRawHtml(client, url);      
        //获取响应状态码
        int StatusCode = response.getStatusLine().getStatusCode();
        //如果状态响应码为200，则获取html实体内容或者json文件
        if(StatusCode == 200){
        	HttpEntity responseEntity = response.getEntity();
        	System.out.println("response.getEntity():"+responseEntity);
            String entity = EntityUtils.toString (responseEntity,"utf-8");    
            JingdongData = JdParse.getData(entity);
            EntityUtils.consume(response.getEntity());
        }else {
            //否则，消耗掉实体
            EntityUtils.consume(response.getEntity());
        }
        return JingdongData;
    }
}