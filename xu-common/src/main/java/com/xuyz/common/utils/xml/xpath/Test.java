/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Test
 * Author:   xuyz
 * Date:     2019/8/25 15:29
 * Description: xpath
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xuyz.common.utils.xml.xpath;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * 〈一句话功能简述〉<br> 
 * 〈xpath〉
 *
 * @author xuyz
 * @date 2019/8/25
 * @since 1.0.0
 */
public class Test {
    /**
     * @param document Document对象（读xml生成的）
     * @return String字符串
     * @throws Throwable
     */
    public String xmlToString(Document document) throws Throwable {
        TransformerFactory ft = TransformerFactory.newInstance();
        Transformer ff = ft.newTransformer();
        ff.setOutputProperty("encoding", "UTF-8");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ff.transform(new DOMSource(document), new StreamResult(bos));
        return bos.toString();
    }

    /**
     * @param
     * @return Document 对象
     */
    public Document StringTOXml(String str) {

        StringBuilder sXML = new StringBuilder();
        sXML.append(str);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            InputStream is = new ByteArrayInputStream(sXML.toString().getBytes("utf-8"));
            doc = dbf.newDocumentBuilder().parse(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * @param document
     * @return 某个节点的值 前提是需要知道xml格式，知道需要取的节点相对根节点所在位置
     */
    public String getNodeValue(Document document, String nodePath) {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath path = xPathFactory.newXPath();
        String servInitrBrch = "";
        try {
            servInitrBrch = path.evaluate(nodePath, document);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return servInitrBrch;
    }

    /**
     * @param document
     * @param nodePath  需要修改的节点相对根节点所在位置
     * @param vodeValue 替换的值
     */
    public void setNodeValue(Document document, String nodePath, String vodeValue) {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath path = xPathFactory.newXPath();
        Node node = null;
        ;
        try {
            node = (Node) path.evaluate(nodePath, document, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        node.setTextContent(vodeValue);
    }

    public static void main(String[] args) throws Throwable {
        // 读取xml文件，生成document对象
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // 文件的位置在工作空间的根目录（位置随意，只要写对就ok）
        //Document document = builder.parse(new File("classpath:a.xml"));

        Test t = new Test();
        // XML————》String
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<transaction>\n" +
                "    <body>\n" +
                "        <request>\n" +
                "            <tranTyp>批量业务现存</tranTyp>\n" +
                "            <acctNm>0085213560</acctNm>\n" +
                "            <acctNo>6225885517843413</acctNo>\n" +
                "            <avlBal>201958.65</avlBal>\n" +
                "            <acctTyp>0</acctTyp>\n" +
                "            <tranTime>20170801101030</tranTime>\n" +
                "            <currencyTyp>CNY</currencyTyp>\n" +
                "            <tranDesc></tranDesc>\n" +
                "            <bal>201958.65</bal>\n" +
                "            <tranAmt>100000.00</tranAmt>\n" +
                "        </request>\n" +
                "    </body>\n" +
                "    <header>\n" +
                "        <msg>\n" +
                "            <sndTm>101019</sndTm>\n" +
                "            <msgCd>WCS0000200</msgCd>\n" +
                "            <seqNb>632376531000009</seqNb>\n" +
                "            <sndMbrCd>5200</sndMbrCd>\n" +
                "            <rcvMbrCd>0000</rcvMbrCd>\n" +
                "            <sndDt>20170821</sndDt>\n" +
                "            <sndAppCd>CBS</sndAppCd>\n" +
                "            <rcvAppCd>WCS</rcvAppCd>\n" +
                "            <callTyp>SYN</callTyp>\n" +
                "        </msg>\n" +
                "        <ver>1.0</ver>\n" +
                "        <pnt>\n" +
                "            <sndTm>101216</sndTm>\n" +
                "            <sndMbrCd>0000</sndMbrCd>\n" +
                "            <rcvMbrCd>0000</rcvMbrCd>\n" +
                "            <sndDt>20170809</sndDt>\n" +
                "            <sndAppCd>ESB</sndAppCd>\n" +
                "            <rcvAppCd>WCS</rcvAppCd>\n" +
                "        </pnt>\n" +
                "    </header>\n" +
                "</transaction>";//t.xmlToString(document);
        System.out.println("str:" + str);
        long time = System.currentTimeMillis();
        // String ————》XML
        Document doc = t.StringTOXml(str);
        String nodePath = "/transaction/header/msg/sndMbrCd";
        // getNodeValue
        String nodeValue = t.getNodeValue(doc, nodePath);
        System.out.println("修改前nodeValue：" + nodeValue);
        // setNodeValue
        t.setNodeValue(doc, nodePath, nodeValue + "hello");
        System.out.println("修改后nodeValue：" + t.getNodeValue(doc, nodePath));
        long time1 = System.currentTimeMillis();
        System.out.println("修改后的xml:"+t.xmlToString(doc));
        System.out.println("耗时："+(time1-time));
    }
}
