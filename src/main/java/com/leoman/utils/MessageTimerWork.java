package com.leoman.utils;

import com.leoman.entity.Message;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2016/3/10.
 */
public class MessageTimerWork {

    private Timer timer = null;

    private Message message;

    public MessageTimerWork(long time, Message message) {
        timer = new Timer();
        this.message = message;
        timer.schedule(new Mywork(), new Date(time));
    }

    class Mywork extends TimerTask {
        @Override
        public void run() {
            try {
                doExecute();
                timer.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doExecute() {
        // TODO 发送消息
        System.out.println("===============message send()============");
        WxMpService wxMpService = (WxMpService) BeanUtil.getBean("wxMpService");

        List<String> openIds = new ArrayList<String>();
        try {
            WxMpUserList userList = wxMpService.userList(null);
            openIds.addAll(userList.getOpenIds());
//            while(StringUtils.isNotBlank(userList.getNextOpenId())) {
//                System.out.println("nextOpenId:" + userList.getNextOpenId());
//                openIds.addAll(wxMpService.userList(userList.getNextOpenId()).getOpenIds());
//            }

            File file = new File(ConfigUtil.getString("upload.path") + message.getImage().getPath());
            WxMediaUploadResult uploadMediaRes = wxMpService.mediaUpload(WxConsts.MEDIA_IMAGE,file);
            System.out.println(uploadMediaRes.toString());
            WxMpMassNews news = new WxMpMassNews();
            WxMpMassNews.WxMpMassNewsArticle article = new WxMpMassNews.WxMpMassNewsArticle();
            article.setTitle(message.getTitle());
            article.setContent(message.getContent().replace("&lt","<").replace("&gt",">"));
            article.setThumbMediaId(uploadMediaRes.getMediaId());
            news.addArticle(article);

            WxMpMassUploadResult massUploadResult = wxMpService.massNewsUpload(news);
            System.out.println(massUploadResult.toString());

            WxMpMassOpenIdsMessage massMessage = new WxMpMassOpenIdsMessage();
            massMessage.setMsgType(WxConsts.MASS_MSG_NEWS);
            massMessage.setMediaId(massUploadResult.getMediaId());
            massMessage.getToUsers().addAll(openIds);

            WxMpMassSendResult massResult = wxMpService.massOpenIdsMessageSend(massMessage);
            System.out.println("massResult:" + massResult.toString());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
