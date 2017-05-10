package liao.thomas.javadev.basic.xml;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 廖东明 on 2015-9-29.
 */
public class SheetAttributes {

    // 因为逻辑表达式问题，都要定义成String

    //ds:数据源ID(支持使用数据源视图控件的ID，目前在使用本地数据源时，不支持数据聚焦范围切换)
    private String id = "";
    private String key = "";
    private String ds = null;
    private String gn = null;
    private String gn2 = null;
    private String gn3 = null;
    /**
     * ec:固定为1（独占控件）
     */
    private String ec = null;
    /**
     * fz:冻结属性，格式为 行号:列号  0表示不冻结 如0:1表示只冻结第一列
     */
    private String fz = null;
    /**
     * sc:检索标识 格式为： 0 隐藏检索或无检索 空 默认为按ti检索 $key1#;$key2# 按选择字段的值检索
     */
    private String sc = null;
    private String cen = null;
    private String readOnlyDs = null;

    private int fzHorizontal = 0;//冻结的行数
    private int fzVertical = 0;//冻结的列数
    private boolean hasFreeze = false;

    private ArrayList<ColumnDescription> columnDescriptionList = new ArrayList<>();

    public SheetAttributes() {}

    public void addColumnDescription(ColumnDescription columnDescription) {
        columnDescriptionList.add(columnDescription);
    }

    /**
     * 获取每一列的标题
     */
    public void addValueToColumnDescription(String value) {
        int index = columnDescriptionList.size() - 1;
        String key = "value";
        addAttributeToColumnDescription(index, key, value);
    }

    private void addAttributeToColumnDescription(int index, String key, String value) {
        ColumnDescription columnDescription = columnDescriptionList.get(index);
        columnDescription.addAttributeToColumnDes(key, value);
    }

    public void setColumnDescriptionList(ArrayList<ColumnDescription> columnDescriptionList) {
        this.columnDescriptionList = columnDescriptionList;
    }

    public ArrayList<ColumnDescription> getColumnDescriptionList() {
        return columnDescriptionList;
    }

    public void addAttributes(Attributes attributes) {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            String localName = attributes.getLocalName(i);
            String value = attributes.getValue(localName);
            if ("i".equalsIgnoreCase(localName)) {//控件ID，由系统生成的不重复的值
                id = value;
            } else if ("c".equalsIgnoreCase(localName)) {//控件名称，由配置人员输入
            } else if ("k".equalsIgnoreCase(localName)) {//控件key值，由配置人员输入，用于唯一标示一个控件
                key = value;
            } else if ("ds".equalsIgnoreCase(localName)) {//为控件提供数据的数据源ID
                ds = value;
            } else if ("gn".equalsIgnoreCase(localName)) {//  通用属性，分组检索标识
                if (value != null && value.contains("$") && value.indexOf(";") == -1) {
                    value = value.substring(1, value.length() - 1);
                }
                gn = value;
            } else if ("gn2".equalsIgnoreCase(localName)) {
                gn2 = value;
            } else if ("gn3".equalsIgnoreCase(localName)) {
                gn3 = value;
            } else if ("fz".equalsIgnoreCase(localName)) {
                if (null != value) {
                    String[] str = value.split(":");
                    if (2 == str.length) {
                        try {
                            //fzHorizontal = Integer.parseInt(str[0].trim());//冻结行
                            fzHorizontal = 1;//当前表格有且只有一行冻结行
                            fzVertical = Integer.parseInt(str[1].trim());//冻结列
                            // tableFrameLayout.setFz(Integer.parseInt(str[0].trim()),
                            // Integer.parseInt(str[1].trim()));
                            if (fzHorizontal > 0 || fzVertical > 0) {
                                hasFreeze = true;//fz:冻结属性，格式为 行号:列号  0表示不冻结 如0:1表示只冻结第一列
                            }
                        } catch (Exception e) {

                        }
                    } else {
                        fzHorizontal = 1;//当前表格有且只有一行冻结行
                        hasFreeze = true;
                    }
                }
            } else if ("ec".equalsIgnoreCase(localName)) {//通用属性，标识是否为独占控件
                ec = value;
                if (value.equals("1")) {
                    // TODO: 2017/5/4 独占非独占
                }
            } else if ("cen".equalsIgnoreCase(localName)) {//通用属性，条码检索对应的数据字段
                if (value != null && value.contains("$")) {
                    value = value.substring(1, value.length() - 1);
                }
                cen = value;
            } else if ("sc".equalsIgnoreCase(localName)) {// 通用属性，检索标识
                sc = value;
            } else if ("ods".equalsIgnoreCase(localName)) {//只读情况下会优先读取该数据源
                readOnlyDs = value;
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}