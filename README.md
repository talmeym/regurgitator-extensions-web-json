# regurgitator-extensions-web-json

regurgitator is a modular, light-weight, extendable java-based processing framework designed to 'regurgitate' canned or clever responses to incoming requests; useful for mocking or prototyping services.

start your reading here: [regurgitator-all](http://github.com/talmeym/regurgitator-all#regurgitator)

## extension web steps in json

### http-call 

an http-call step make an outward http call

```xml
<rgw:http-call host="http://otherservice.com" port="80" username="username" password="password">
	<rg:create-response source="response-metadata:status-code">
		<rge:freemarker-processor>http call returned ${value}</rge:freemarker-processor>
	</rg:create-response>
</rgw:http-call>
```

whilst host, port and user credentials are specified in the xml, all other attributes of the call are set from parameters within the message object, as listed below:

|context|parameter|call attribute|default (if not specified)|
|:---|:---|:---|:---|
|``request-metadata``|``method``|method|GET|
|``request-metadata``|``path-info``|path|/|
|``request-headers``|``[header name]`` | http header |not set|
|``request-payload``|``text`` (for POST calls) |[payload]|not set|
|``request-metadata``|``content-type`` (for POST calls) |content type|not set|
|``request-metadata``|``character-encoding`` (for POST calls) |character encoding|not set|

if using the ``RegurgitatorServlet`` to mock http requests, an http-call step not placed within an isolated sequence step will, upon execution, be given a message object already containing the ``request-metadata`` context from the incoming http call, and will therefore act as an http proxy, making an http call identical to the one received by the ``RegurgitatorServlet``, except redirected ot a different endpoint.

If you wish the http-call step to make an independant call, then the step should be placed within an isolated sequence, and should be preceeded by ``create-request`` steps to set the necessary method, path and header metadata.

### create-http-response

## extension web constructs in json

### query-param-processor
