package com.poi.export;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zsl
 * @date 2023/2/5
 * @apiNote
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;
    @ExcelNeed(order = 2)
    private String name;
    @ExcelNeed(order = 1)
    private String score;
}
