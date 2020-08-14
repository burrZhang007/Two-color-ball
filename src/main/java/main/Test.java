package main;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Result;
import util.HttpsUtil;
import util.SendRequest;

public class Test {

	public static void main(String[] args) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			//Result result = SendRequest.sendGet("http://www.cwl.gov.cn/cwl_admin/kjxx/findDrawNotice", param, param, "utf-8");
			
				String url = "http://www.cwl.gov.cn/cwl_admin/kjxx/findDrawNotice";
				param.put("name", "ssq");
				param.put("issueStart", "2011001");
				param.put("issueEnd", "2019086");
				param.put("", "");
				String result_str = HttpsUtil.getHtml(url, param);
				
				//System.out.println(str);
				Gson gson = new Gson();
				JsonParser parser = new JsonParser();
				
				JsonObject josnObj = (JsonObject)parser.parse(result_str);
				System.out.println(josnObj);
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String doGet(String url, Map<String, String> param) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}
}
