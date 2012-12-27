
/**
* This file is loaded on first page load. 
* provides utilities to asncy - parallel process web page.
* Uitlitiy funcs are use in rendering page from web server chunks 
* web server flushses response chunks for independent elements as an when they are ready/
*/


var bigPipe=(function(){
	
	
	var $head=null;
	/**
	 * returns true is jsobObj exist and has html, id as its attributes
	 * other options attributes expected are css, js .. 
	 */
	function validateJson(jsonObj)
	{
		if(jsonObj && jsonObj.id && jsonObj.html)
		{
			if($("#"+jsonObj.id).length !== 0)
			{ // id corresponds to a dom elementId to place the html element
				return true;
			}
		}
		return false
	}
	
	/**
	 * loads css files asynchronusly for each pagelet
	 */
	
	function loadCss(cssObjs)
	{
		$(cssObjs).each(function(cssobj){
			var css=$("link")
			css.attr({
				rel:  "stylesheet",
				type: "text/css",
				href: cssobj.href
			});
			$head.append(css);
		});
	}
	
	/**
	 * Load js files asynchronusly for each pagelet
	 */
	 function loadJs(jsObjs)
	 {
		 $(jsObjs).each(function(jsObj)
		 {
			 $.getScript(jsObj.href, function(){
		      console.log("js file load call for "+jsObj.href);
			  //wait before initialising to prevent intermittent load error
			  ///etTimeout("init_wysiwyg_editor()",250);
		    });
		 });
	 }
	
	//TODO: verify if we can load js files before html content call as it can be async
	return {
		renderPagelet: function (jsonObj){
			
		 	console.log(jsonObj);
			this.$head=$("head");
			// validate json
			if (!validateJson(jsonObj))
			{
				return;
			}
			
			// load css content async
			if(jsonObj.css)
			{
				loadCss(jsonObj.css);
			}
			// load html content
			$("#"+jsonObj.id).html(jsonObj.html);
			
			// load javascript content async
			if(jsonObj.js)
			{
				loadJs(jsonObj.js);
			}
			
			// customLogic call
			//TODO: Work on generic custom logic call
		}
	}
})();



	