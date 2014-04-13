package genebook.websvr.example.uitl;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class InputUtil {

	@SuppressWarnings("unchecked")
	public static <T> CommonInput<T> getInputData(Class<T> t, String input) {
		CommonInput<T> cip = JSON.parseObject(input, CommonInput.class);
		cip.setData(JSON.parseObject(cip.getData().toString(), t));
		return cip;
	}

	public static <T> String formatInputData(String format, String customerId,
			T data) {
		CommonInput<T> cip = new CommonInput<T>();
		cip.setCustomerId(customerId);
		cip.setOutput(format);
		cip.setData(data);
		if (StringUtils.isEmpty(format) || format.equalsIgnoreCase("json")) {
			return JSON.toJSONString(cip);
		} else {
			//todo return xml();
			return null;
		}

	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("1", "1");
		m.put("2", new Date());
		System.out.println(InputUtil.formatInputData("json","1",m));
		String s="{\"customerId\":\"1\",\"data\":{\"1\":\"1\",\"2\":1397412403326},\"output\":\"json\"}";
		CommonInput<Map> c = InputUtil.getInputData(Map.class, s);
		System.out.println(c.getData().get("2"));
	}

	

}
