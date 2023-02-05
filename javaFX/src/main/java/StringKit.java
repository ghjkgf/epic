import java.util.ArrayList;
import java.util.List;

/**
 * @author zsl
 * @date 2023/2/5
 * @apiNote
 */
public class StringKit {
    /**
     * 批量替换掉模板里的字符串,生成多条相同结构的字符串
     * @param templates 模板字符串
     * @param placeholders 占位符
     * @param data 需要替换成的内容
     *             还需要加个参数,来控制遍历方向,选择连续输出一条模板的所有数据还是连续输出一条数据的所有模板
     */
    public static void superReplace(List<String> templates,List<String> placeholders,List<List<String>> data){
        ArrayList<String> resultList = new ArrayList<>();
        // 连续输出一条模板的所有数据
        for (String template : templates) {
            for (int j = 0; j < placeholders.size(); j++) {
                if (template.contains(placeholders.get(j))) {
                    for (List<String> datum : data) {
                        String result = template.replace(placeholders.get(j), datum.get(j));
                        resultList.add(result);
                    }
                }
            }
        }
        // 连续输出一条数据的所有模板
        for (List<String> datum : data) {
            for (String template : templates) {
                for (int k = 0; k < placeholders.size(); k++) {
                    if (template.contains(placeholders.get(k))) {
                        String result = template.replace(placeholders.get(k), datum.get(k));
                        resultList.add(result);
                    }
                }
            }
        }
    }
}
