package com.example.administrator.southweek.ui.utils;

import android.util.Xml;

import com.example.administrator.southweek.ui.bean.ArtcleDetail;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/7/20.
 */
public class DetailXmlParserUtils {
    public static ArtcleDetail parserXML(InputStream inputStream) throws Exception {
        // List<ArtcleDetail> mDetailList = new ArrayList<>();
        ArtcleDetail artcleDetail = null;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, "utf-8");
        int event = parser.getEventType();
        //当没有到结束标签
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    artcleDetail = new ArtcleDetail();
                    break;
                case XmlPullParser.START_TAG:
                    if ("item".equals(parser.getName())) {
                        int id = new Integer(parser.getAttributeValue(0));//id
                        artcleDetail.setId(id);
                        artcleDetail.setSubject(parser.getAttributeValue(2));
                        artcleDetail.setShort_subject(parser.getAttributeValue(3));//短标题
                        artcleDetail.setSub_subject(parser.getAttributeValue(4));
                        artcleDetail.setTag(parser.getAttributeValue(5));
                        artcleDetail.setSource(parser.getAttributeValue(6));//来源：例如：时尚
                        artcleDetail.setAuthor(parser.getAttributeValue(7));
                        artcleDetail.setPublish_time(parser.getAttributeValue(11));
                        artcleDetail.setDescription(parser.getAttributeValue(16));//图片后面部分的url
                        artcleDetail.setComment_count(parser.getAttributeValue(21));//评论总数
                        artcleDetail.setShare_count(parser.getAttributeValue(22));//分享数
                    } else if ("fulltext".equals(parser.getName())) {
                        artcleDetail.setFulltext(parser.nextText());//详细文字
                    }
                   /*
                   单标签
                   else if("introtext".equals(parser.getName())){
                        parser.nextText();
                    }*/
                    break;
                case XmlPullParser.END_TAG:
                    if ("snsShare ".equals(parser.getName())) {
                        // 拿属性
                        //分享的url
                        artcleDetail.setSnsShare(parser.nextText());
                    }
                    break;
            }
            event = parser.next();
        }
        return artcleDetail;
    }
}
