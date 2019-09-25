package com.hb.unic.base.tool;

import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ========== 数据库表工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.helper.DbTools.java, v1.0
 * @date 2019年07月13日 08时14分
 */
public class DbTools {

    /**
     * ########## 通过类名和分表后缀生成创建表的sql ##########
     *
     * @param tableClass  表对应的类
     * @param shardSuffix 分表时的表后缀
     * @return 创建表的sql
     */
    public static String generateTable(Class tableClass, String shardSuffix) {
        SelfTableClass selfTableClass = (SelfTableClass) tableClass.getAnnotation(SelfTableClass.class);
        String tableName = selfTableClass.value();
        String comment = selfTableClass.comment();
        if (shardSuffix != null && shardSuffix.length() > 0) {
            tableName += "_" + shardSuffix;
        }
        StringBuilder sb = new StringBuilder("create table " + tableName + " (");
        List<Field> allFields = getAllFields(tableClass);
        for (int i = 0; i < allFields.size(); i++) {
            sb.append(addColumn(allFields.get(i)));
            if (i != allFields.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(");");
        sb.append("alter table ").append(tableName).append(" comment '").append(comment).append("';");
        return sb.toString();
    }

    /**
     * ########## 添加列 ##########
     *
     * @param field 属性
     * @return 创建列的sql
     */
    private static String addColumn(Field field) {
        SelfTableColumn selfTableColumn = field.getAnnotation(SelfTableColumn.class);
        String columnName = selfTableColumn.value();
        boolean isId = selfTableColumn.id();
        int length = selfTableColumn.length();
        String defaultValue = selfTableColumn.defaultValue();
        String comment = selfTableColumn.comment();
        StringBuilder sb = new StringBuilder(columnName);
        String name = field.getType().getSimpleName();
        switch (name) {
            case "String":
                sb.append(" varchar(").append(length == 0 ? 255 : length).append(")");
                sb.append(isId ? " primary key not null " : "");
                break;
            case "Integer":
                sb.append(" int(").append(length == 0 ? 10 : length).append(")");
                sb.append(isId ? " primary key not null AUTO_INCREMENT" : "");
                break;
            case "Long":
                sb.append(" numeric(").append(length == 0 ? 16 : length).append(")");
                sb.append(isId ? " primary key not null " : "");
                break;
            case "BigDecimal":
                sb.append(" numeric(").append(length == 0 ? 12 : length).append(",2)");
                break;
            case "Date":
                sb.append(" timestamp ");
                break;
            default:
                break;
        }
        if (defaultValue != null && defaultValue.length() > 0) {
            sb.append(" default ").append(defaultValue);
        }
        sb.append(" comment '").append(comment).append("'");
        return sb.toString();
    }

    /**
     * ########## 获取类所有属性 ##########
     *
     * @param tableClass 表类
     * @return 属性集合
     */
    private static List<Field> getAllFields(Class tableClass) {
        List<Field> fieldList = new ArrayList<>();
        if (tableClass == null) {
            return fieldList;
        }
        Field[] fields = tableClass.getDeclaredFields();
        for (Field field : fields) {
            if (!"serialVersionUID".equals(field.getName())) {
                fieldList.add(field);
            }
        }
        Class superclass = tableClass.getSuperclass();
        fieldList.addAll(getAllFields(superclass));
        return fieldList;
    }

}
