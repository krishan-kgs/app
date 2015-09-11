# app
learning app 

@RestController - 
When you implement a RESTful web services, the response would be always sent with the response body. To make this simple, Spring 4.0 has provided a specialized version of controller. Look at the definition of the @RestController implementation.

A convenience annotation that is itself annotated with @Controller and @ResponseBody. Types that carry this annotation are treated as controllers where @RequestMapping methods assume @ResponseBody semantics by default. When using @RestController we do not need to mention @responsebody on evry method.



@ResponseBody - If a method is annotated with @ResponseBody, Spring will bind the return value to outgoing HTTP response body. While doing that, Spring will [behind the scenes] use HTTP Message converters to convert the return value to HTTP response body [serialize the object to response body], based on Content-Type present in request HTTP header.


@RequestBody - If a method parameter is annotated with @RequestBody, Spring will bind the incoming HTTP request body(for the URL mentioned in @RequestMapping for that method) to that parameter. While doing that, Spring will [behind the scenes] use HTTP Message converters to convert the HTTP request body into domain object [deserialize request body to domain object], based on Accept header present in request.

The Accept header is used by HTTP clients [browsers] to tell the server what content types they’ll accept.
The server sends back the response, which will include a Content-Type header telling the client what the content type of the returned content actually is. In case of POST or PUT request, browsers do send data in request, so they actually send content-type as well.


@ExceptionHandler - We can define exception handler methods in our controller classes. All we need is to annotate these methods with @ExceptionHandler annotation. This annotation takes Exception class as argument. So if we have defined one of these for Exception class, then all the exceptions thrown by our request handler method will have handled.
These exception handler methods are just like other request handler methods and we can build error response and respond with different error page. We can also send JSON error response
If there are multiple exception handler methods defined, then handler method that is closest to the Exception class is used. For example, if we have two handler methods defined for IOException and Exception and our request handler method throws IOException, then handler method for IOException will get executed.


Links use for this POC
http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/
http://opensourcesoftwareandme.blogspot.in/2013/03/posting-processing-json-with-jquery.html
http://websystique.com/springmvc/spring-mvc-requestbody-responsebody-example/

********************* for uplaod image in excel file with porper dimension *****************************************

http://stackoverflow.com/questions/19291948/in-apache-poi-3-9-using-autosizecolumn-the-image-present-on-the-same-column-gett



