package liao.thomas.javadev.basic.xml;

import java.util.HashMap;

/**
 *   created by     廖东明
 * ex.<dc ci="RowID" m="1" h="" focus="" na="0" tf="" stat="" l="l">编号</dc>
 * ex. <st ci="key7" m="1" h="" focus="" stat="" v="le:2073344582">统计</st>  带逻辑表达式，统计
 */
public class ColumnDescription {
    private HashMap<String, String> mAttributeHashMap;
    private boolean mIsStatColumn;//是否是统计列

    public ColumnDescription(boolean isStatColumn) {
        this.mAttributeHashMap =  new HashMap<String, String>();
        this.mIsStatColumn = isStatColumn;
    }

    public boolean isStatColumn() {
        return mIsStatColumn;
    }

    public void addAttributeToColumnDes(String key, String value) {
        this.mAttributeHashMap.put(key, value);
    }

    public String getAttributeOfColumnDes(String key) {
        String value = mAttributeHashMap.get(key);
        if(value == null) {
            value = "";
        }
        return value;
    }
}