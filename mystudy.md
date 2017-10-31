
##Mapper 节点

  cacheRefElement(context.evalNode("cache-ref"));
  cacheElement(context.evalNode("cache"));
  parameterMapElement(context.evalNodes("/mapper/parameterMap"));
  resultMapElements(context.evalNodes("/mapper/resultMap"));
  sqlElement(context.evalNodes("/mapper/sql"));
  
  
  
###  sql sqlAnnotationTypes 
```java
    sqlAnnotationTypes.add(Select.class);
    sqlAnnotationTypes.add(Insert.class);
    sqlAnnotationTypes.add(Update.class);
    sqlAnnotationTypes.add(Delete.class); 

```
### sql sqlProviderAnnotationTypes

```java
    sqlProviderAnnotationTypes.add(SelectProvider.class);
    sqlProviderAnnotationTypes.add(InsertProvider.class);
    sqlProviderAnnotationTypes.add(UpdateProvider.class);
    sqlProviderAnnotationTypes.add(DeleteProvider.class);
```



###  mybatis配置属性
settings
properties
typeAliases
plugins
objectFactory
objectWrapperFactory
reflectorFactory
environments
databaseIdProvider
typeHandlers
mappers


### mapper文件动态标签

```java
        XMLScriptBuilder.NodeHandler nodeHandlers(String nodeName) {
			Map<String, XMLScriptBuilder.NodeHandler> map = new HashMap<String, XMLScriptBuilder.NodeHandler>();
			map.put("trim", new XMLScriptBuilder.TrimHandler());
			map.put("where", new XMLScriptBuilder.WhereHandler());
			map.put("set", new XMLScriptBuilder.SetHandler());
			map.put("foreach", new XMLScriptBuilder.ForEachHandler());
			map.put("if", new XMLScriptBuilder.IfHandler());
			map.put("choose", new XMLScriptBuilder.ChooseHandler());
			map.put("when", new XMLScriptBuilder.IfHandler());
			map.put("otherwise", new XMLScriptBuilder.OtherwiseHandler());
			map.put("bind", new XMLScriptBuilder.BindHandler());
			return map.get(nodeName);
		}
```