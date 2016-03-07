# regurgitator-extensions-web-json

regurgitator is a modular, light-weight, extendable java-based processing framework designed to 'regurgitate' canned or clever responses to incoming requests; useful for mocking or prototyping services.

start your reading here: [regurgitator-all](http://github.com/talmeym/regurgitator-all#regurgitator)

## extension web steps in json

### http-call 

```xml
<rgw:http-call host="http://otherservice.com" port="80" username="username" password="password">
	<rg:create-response source="response-metadata:status-code">
		<rge:freemarker-processor>http call returned ${value}</rge:freemarker-processor>
	</rg:create-response>
</rgw:http-call>
```

### create-http-response

## extension web constructs in json

### query-param-processor
