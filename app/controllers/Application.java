package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.Request;
import play.templates.Template;
import play.templates.TemplateLoader;

import java.awt.print.Paper;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import json.Pagelet;

import models.*;

public class Application extends Controller {

	public static void bigPipeLoad() throws InterruptedException {
		String [] divs = {"div1","div2","div3","div4"};

		System.out.println("In Big Pipe Call");
		Template template = TemplateLoader.load(template("Application/bigPipeLoad.html"));
		response.contentType="text/html";
		response.writeChunk(template.render());
		response.writeChunk(getDivJson("div1"));
		response.writeChunk(getDivJson("div2"));
		response.writeChunk(getDivJson("div3"));
		response.writeChunk(getDivJson("div4"));
//		response.writeChunk("here");
//		response.writeChunk("here");
//		response.writeChunk("here");
		/*ExecutorService executor = Executors.newFixedThreadPool(4);
		for (final String div : divs)
		{
			 getDivJson(div);
			executor.submit(new Runnable() {
				@Override
				public void run() {
					String o = getDivJson(div);
					System.out.println(o);
					response.writeChunk("here");
				}
			});
			response.writeChunk("here");
		}
		executor.shutdown();
		executor.awaitTermination(10000l, TimeUnit.SECONDS);*/
		response.writeChunk("</body></html>");
	}

	public static void normalLoad()
	{
		System.out.println("In Normal Load Call");
		List<String> resp = new ArrayList<String>();
		resp.add(getDivJson("div1"));
		resp.add(getDivJson("div2"));
		resp.add(getDivJson("div3"));
		resp.add(getDivJson("div4"));
		render(resp);
	}

	private static String getDivJson(String div)
	{
		if("div2".equals(div))
		{
			await(3000);
		}else if ("div3".equals(div))
		{
			await(4000);
		}
		await(1000);
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

	public static void csvGen()
	{
		//    	 CSVGenerator generator = new CSVGenerator();
		//    	    response.contentType = "text/csv";
		//    	    while(generator.hasMoreData()) {
		//    	          String someCsvData = await(generator.nextDataChunk());
		//    	          response.writeChunk(someCsvData);
		//    	    }
	}

	private static void delay(long duration)
	{
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < duration)
			continue;
	}

}