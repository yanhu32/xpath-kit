# xpath-kit

Java XPath辅助工具，解析xml

示例：

student.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<class>
    <student id="1" code="zs">
        <name>张三</name>
        <sid>111111</sid>
    </student>
    <student id="2" code="ls">
        <name>李四</name>
        <sid>222222</sid>
    </student>
</class>
```

Student.java

```java
import XPath;
import lombok.Data;

@Data
@XPath("/class")
public class Student {

    @XPath("/student[@id='2']/name")
    private String name;

    @XPath("/student[@id='2']/@code")
    private String code;

    @XPath("/student/sid")
    private String sid;

}
```

XPathMain.java

```java
import io.github.yanhu32.xpathkit.XPathHelper;
import io.github.yanhu32.xpathkit.filter.SpaceXPathFilter;
import io.github.yanhu32.xpathkit.parser.JaxpXPathParser;
import io.github.yanhu32.xpathkit.utils.IOStreams;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class XPathMain {

    public static void main(String[] args) throws Exception {
        ClassPathResource resource = new ClassPathResource("student.xml");
        try (InputStream inputStream = resource.getInputStream()) {

            String xml = IOStreams.copyToString(inputStream, StandardCharsets.UTF_8);
            Student student = new Student();

            Student parse = XPathHelper.create(JaxpXPathParser.of(xml))
                    .addFilter(new SpaceXPathFilter())
                    .parse(student);

            System.out.println(parse);
        }
    }
}
```

结果：

```shell
Student(name=李四, code=ls, sid=111111)
```