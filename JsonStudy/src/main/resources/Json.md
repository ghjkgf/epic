@RestController 在 Controller 类中使用@RestController注解即可返回 JSON格式的数据。
只使用controller 报错，有没有其他方式？

@RestController 注解包含了：@Controller和@ResponseBody两个核心注解。

@Controller：当一个类引入这个注解，表明当前的这个类是一个controller控制器类；
@ResponseBody：表示方法返回值的注释应该绑定到web响应的消息体中。支持带注释的处理程序方法。
从4.0版本开始，这个注释也可以添加到类型级别，在这种情况下，它是继承的，不需要添加到方法级别。

@JsonIgnore 用于过滤单个字段。
@JsonIgnoreProperties 用于过滤多个字段。

自定义 序列化和反序列化
 自定义(反)序列化类需要继承 StdSerializer ,重写 serialize() 方法
 并将其注册到 ObjectMapper的 Module中