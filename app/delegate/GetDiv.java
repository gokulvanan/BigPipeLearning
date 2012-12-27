package delegate;

import java.util.concurrent.Callable;

import json.Pagelet;

import com.google.gson.Gson;

public class GetDiv implements Callable<String>{

	private String div;
	public GetDiv(String div)
	{
		this.div=div;
	}
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return getDivJson(div);
	}
	

	private static String getDivJson(String div)
	{
		if("div2".equals(div))
		{
			delay(3000);
		}else if ("div3".equals(div))
		{
			delay(4000);
		}
		delay(1000);
		Pagelet divObj = new Pagelet(div);
		divObj.html="DivId for this div is "+div;

		StringBuilder obj = new StringBuilder();
		obj.append("<script type ='text/javascript'>");
		obj.append("bigPipe.renderPagelet(");
		obj.append(new Gson().toJson(divObj));
		obj.append(");");
		obj.append("</script>");


		return obj.toString();
	}

	private static void delay(long duration)
	{
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < duration)
			continue;
	}
}
