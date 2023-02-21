import org.reficio.ws.builder.SoapBuilder;
import org.reficio.ws.builder.SoapOperation;
import org.reficio.ws.builder.core.Wsdl;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetWebServiceFunction {
    public static List<String> getBindingOperations(String wsdlUrl) {
        List<String> operationList = new ArrayList();
        List<SoapOperation> soapOperationList = new ArrayList();
        Wsdl parser = Wsdl.parse(wsdlUrl);
        List<QName> bindQnames = parser.getBindings();
        for (QName qName : bindQnames) {
            SoapBuilder soapBuilder = parser.binding().localPart(qName.getLocalPart()).find();
            soapOperationList.addAll(soapBuilder.getOperations());
        }
        for (SoapOperation soapOperation : soapOperationList) {
            //operationList.add(soapOperation.getOperationName());
            operationList.add(soapOperation.getOperationName());
        }
        return operationList;
    }

    public static void main(String[] args) {
        List<String> bindingOperations = GetWebServiceFunction.getBindingOperations("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl");
        System.out.println(bindingOperations);
    }
————————————————
版权声明：本文为CSDN博主「老吉会飞」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/u010925982/article/details/96160161