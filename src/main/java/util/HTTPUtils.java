package util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

public abstract class HTTPUtils {
	public static HttpResponse getRawHtml(CloseableHttpClient client, String personalUrl) {
		// 获取响应文件，即html，采用get方法获取响应数据
		HttpGet httpGet = new HttpGet(personalUrl);
		
		CloseableHttpResponse response = null;
		try {
			// 配置信息
			RequestConfig requestConfig = RequestConfig.custom()
					// 设置连接超时时间(单位毫秒)
					.setConnectTimeout(5000)
					// 设置请求超时时间(单位毫秒)
					.setConnectionRequestTimeout(5000)
					// socket读写超时时间(单位毫秒)
					.setSocketTimeout(5000)
					// 设置是否允许重定向(默认为true)
					.setRedirectsEnabled(true).build();

			// 将上面的配置信息 运用到这个Get请求里
			httpGet.setConfig(requestConfig);
			// 执行get方法
			response = client.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			httpGet.abort();
		}
		return response;
	}

}