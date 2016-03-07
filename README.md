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

whilst host, port and user credentials are specified in the xml, all other attributes of the call are defined by parameters within the message object, as listed below:

|parameter|call attribute|default (if not specified)|
|:---|:---|:---|
|request-metadata:method|method|GET|
|request-metadata:path-info|path|/|
|request-payload:text (for POST calls) |[payload]|not set|
|request-metadata:content-type (for POST calls) |content type|not set|
|request-metadata:character-encoding (for POST calls) |character encoding|not set|

### create-http-response

## extension web constructs in json

### query-param-processor
