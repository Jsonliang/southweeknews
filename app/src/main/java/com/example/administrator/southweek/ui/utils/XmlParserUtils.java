package com.example.administrator.southweek.ui.utils;

import android.util.Xml;

import com.example.administrator.southweek.ui.bean.SubjectInfo;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 * XML解析工具类
 */
public class XmlParserUtils {

    public static List<SubjectInfo> parserXML(InputStream inputStream) throws Exception {
        List<SubjectInfo> subjectInfosList = new ArrayList<>();
        SubjectInfo subjectInfo = null;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, "utf-8");
        int event = parser.getEventType();
        //当没有到结束标签
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_TAG:
                    if ("item".equals(parser.getName())) {
                        subjectInfo = new SubjectInfo();
                        int id = new Integer(parser.getAttributeValue(0));//id
                        subjectInfo.setId(id);
                        subjectInfo.setSource(parser.getAttributeValue(7));//来源：例如：时尚
                        subjectInfo.setShort_subject(parser.getAttributeValue(4));//短标题
                        subjectInfo.setMedia(parser.getAttributeValue(16));//图片后面部分的url
                        subjectInfo.setDisplay_time(parser.getAttributeValue(17));//最后评论时间
                        subjectInfo.setComment_count(parser.getAttributeValue(18));//评论总数
                        subjectInfo.setShare_count(parser.getAttributeValue(19));//分享数

                    }
                   /* else if("introtext".equals(parser.getName())){
                        parser.nextText();
                    }*/
                    break;
                case XmlPullParser.END_TAG:
                   /* if("snsShare ".equals(parser.getName())){
                        // 拿属性
                    }*/
                    if ("item".equals(parser.getName())) {
                        subjectInfosList.add(subjectInfo);
                        subjectInfo = null;
                    }

                    break;
            }
            event = parser.next();
        }
        return subjectInfosList;
    }

}
