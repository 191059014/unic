package com.hb.unic.util.helper;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * toString辅助
 *
 * @version v0.1, 2020/9/4 11:05, create by huangbiao.
 */
public class ToStringHelper {

    /**
     * 不为空
     *
     * @param obj 对象
     * @return String
     */
    public static String printNoNull(Object obj) {
        return ToStringBuilder.reflectionToString(obj, new ToStringHelper().new NoNullStyle());
    }

    /**
     * json+不为空
     *
     * @param obj 对象
     * @return String
     */
    public static String printJsonNoNull(Object obj) {
        return ToStringBuilder.reflectionToString(obj, new ToStringHelper().new JsonNoNullStyle());
    }


    /**
     * 功能描述：重写ToStringStyle，不打印出对象的空属性字段
     * <p>
     * null 不打印
     * “” 打印
     *
     * @version v0.1, 2020/9/4 11:05, create by huangbiao.
     */
    public class NoNullStyle extends ToStringStyle implements Serializable {

        // serialVersionUID
        private static final long serialVersionUID = -2447181869531207794L;

        NoNullStyle() {
            this.setUseShortClassName(true);
            this.setUseIdentityHashCode(false);
        }

        @Override
        public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
            if (value != null) {
                super.append(buffer, fieldName, value, fullDetail);
            }
        }
    }

    /**
     * 功能描述：重写ToStringStyle，json格式但是不打印出对象的空属性字段
     * <p>
     * null 不打印
     * “” 打印
     *
     * @version v0.1, 2020/9/4 11:05, create by huangbiao.
     */
    public class JsonNoNullStyle extends ToStringStyle implements Serializable {

        // serialVersionUID
        private static final long serialVersionUID = 3530726176160345864L;

        JsonNoNullStyle() {
            this.setUseClassName(false);
            this.setUseIdentityHashCode(false);
            this.setContentStart("{");
            this.setContentEnd("}");
            this.setArrayStart("[");
            this.setArrayEnd("]");
            this.setFieldSeparator(",");
            this.setFieldNameValueSeparator(":");
            this.setNullText("null");
            this.setSummaryObjectStartText("\"<");
            this.setSummaryObjectEndText(">\"");
            this.setSizeStartText("\"<size=");
            this.setSizeEndText(">\"");
        }

        @Override
        public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
            if (value != null) {
                super.append(buffer, fieldName, value, fullDetail);
            }
        }
    }

}

    