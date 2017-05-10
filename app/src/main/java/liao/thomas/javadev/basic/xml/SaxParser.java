package liao.thomas.javadev.basic.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by thomasliao on 2017/5/8.
 *
 * 在Sax的解析过程中，读取到文档开头、结尾，元素的开头和结尾都会触发一些回调方法
 *
 * good :
 *  SAX 从根本上解决了 DOM 在解析 XML 文档时产生的占用大量资源的问题
 *  不需要将整个 XML 文档读入内存当中
 *
 * bad
 *   十分复杂的 API 接口令人望而生畏，
 *   其次由于其是属于类似流解析的文件扫描方式，因此 不支持应用程序对于 XML 树内容结构等的修改，可能会有不便之处 。
 *
 */
public class SaxParser {

    private UserHandler userHandler;
    private InputStream inputStream;

    public SaxParser(InputStream inputStream) {
        this.inputStream = inputStream;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            userHandler = new UserHandler();
            saxParser.parse(inputStream, userHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SheetAttributes getSheetAttribute() {
        return userHandler.getSheetAttributes();
    }

    static class UserHandler extends DefaultHandler {

        boolean pc = false;
        boolean dc = false;
        boolean st = false;

        private SheetAttributes sheetAttributes = new SheetAttributes();

        public SheetAttributes getSheetAttributes() {
            return sheetAttributes;
        }

        @Override
        public void startElement(String uri,
                                 String localName, String qName, Attributes attributes)
                throws SAXException {
            if (qName.equalsIgnoreCase("tb")) {
                sheetAttributes.addAttributes(attributes);
            } else if (qName.equalsIgnoreCase("pc")) {
                pc = true;
            } else if (qName.equalsIgnoreCase("dc")) {
                dc = true;
                ColumnDescription columnDescription = buildUpColumnDes(attributes);
                sheetAttributes.addColumnDescription(columnDescription);
            } else if (qName.equalsIgnoreCase("st")) {
                st = true;
                ColumnDescription columnDescription = buildUpColumnDes(attributes);
                sheetAttributes.addColumnDescription(columnDescription);
            }
        }

        private ColumnDescription buildUpColumnDes(Attributes attributes) {
            int length = attributes.getLength();
            ColumnDescription columnDescription = new ColumnDescription(false);
            for (int i = 0; i < length; i++) {
                String localName = attributes.getLocalName(i);
                String value = attributes.getValue(localName);
                columnDescription.addAttributeToColumnDes(localName, value);
            }
            return columnDescription;
        }

        @Override
        public void endElement(String uri,
                               String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("tb")) {
                System.out.println("End Element :" + qName);
            }
        }

        @Override
        public void characters(char ch[],
                               int start, int length) throws SAXException {
            String value = new String(ch, start, length);
            if (pc) {
                System.out.println("pc "
                        + new String(ch, start, length));
                pc = false;
            } else if (dc) {
                System.out.println("dc "
                        + new String(ch, start, length));
                dc = false;
                //add value to column description
                sheetAttributes.addValueToColumnDescription(value);
            } else if (st) {
                st = false;
                //add value to column description
                sheetAttributes.addValueToColumnDescription(value);
            }
        }
    }
}
