package com.leoman.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.leoman.core.Constant;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import net.sf.json.JSONObject;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.leoman.entity.Attach;

import org.springframework.web.context.request.ServletRequestAttributes;

public class CommonUtils {

    /**
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: getWebPath
     * @Description: 获取项目路径 例:http://localhost:8080/yqss
     */
    public static String getRemotePath() {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        String absPath = null;

        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();
        String context = request.getContextPath();
        absPath = scheme + "://" + host + (port != 80 ? ":" + port : "") + context;
        return absPath;
    }

    /**
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: getRemotePathWithoutContext
     * @Description: 获取项目路径 例:http://localhost:8080/
     */
    public static String getRemotePathWithoutContext() {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        String absPath = null;

        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();
        absPath = scheme + "://" + host + (port != 80 ? ":" + port : "");
        return absPath;
    }

    /**
     * 上传附件
     *
     * @param file
     * @param attachPath
     * @return
     */
    public static Attach uploadAttach(MultipartFile file, String webRoot, String attachPath, String userId) {

        if (file != null && !file.isEmpty() && file.getSize() > 0) {
            webRoot = webRoot.substring(0, webRoot.lastIndexOf(File.separator)) + File.separator + attachPath + File.separator;
            File f = new File(webRoot);
            if (!f.exists()) {
                f.mkdirs();
            }

            FileOutputStream fos = null;
            BufferedInputStream bis = null;
            String oldFileName = file.getOriginalFilename();
            //文件扩展名
            String kzm = oldFileName.substring(oldFileName.lastIndexOf("."));
            //服务器存储文件名
            String newFileName = oldFileName.substring(0, oldFileName.lastIndexOf(".")) + "_" + getDateOf16() + kzm;
            String newFilePath = getRemotePathWithoutContext() + attachPath + "/" + newFileName;

            Attach attach = null;
            try {

                fos = new FileOutputStream(webRoot + newFileName);
                bis = new BufferedInputStream(file.getInputStream());
                int c;
                byte buffer[] = new byte[1024];
                while ((c = bis.read(buffer)) != -1) {
                    for (int i = 0; i < c; i++)
                        fos.write(buffer[i]);
                }

//		        AttachManagerImpl attachMgr = (AttachManagerImpl)ServiceLocator.lookup(AttachManagerImpl.class);
                attach = new Attach();
                attach.setAttachName(newFileName);
                attach.setAttachTruename(oldFileName);
                attach.setAttachPath(attachPath);
                attach.setAttachSize(file.getSize() / 1024.00);
                attach.setAttachUser("");
                attach.setBak1(newFilePath);
//				newattach = attachMgr.save(attach);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return attach;
        }
        return null;
    }

    /**
     * 获取16为日期(yyyyMMddHHmmssSS)
     */
    public static String getDateOf16() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String id = sdf.format(new Date());
        return id;
    }

    /**
     * 生成验证码
     *
     * @param length
     * @return
     */
    public static String getCode(int length) {
        Random random = new Random();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    public static String getSignature(HttpServletRequest request, String noncestr, String timestamp, String url, WxMpConfigStorage wxMpConfigStorage) {
        try {
            String ticket = (String) request.getSession().getAttribute(Constant.jsApi_ticket);

            System.out.println("ticket：" + ticket);

            if (null != ticket && !ticket.equals("")) {
                return getSignature(ticket, timestamp, noncestr, url);
            }

            String accesstoken = wxMpConfigStorage.getAccessToken();
            String urljson = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accesstoken + "&type=jsapi";

            String result = HttpRequestUtil.sendGet(urljson);

            JSONObject str = JSONObject.fromObject(result);

            String jsApi_ticket = str.get("ticket").toString();

            System.out.println("jsApi_ticket：" + jsApi_ticket);

            request.getSession().setAttribute(Constant.jsApi_ticket, jsApi_ticket);

            System.out.println("accessToken：" + accesstoken);
            System.out.println("urljson：" + urljson);
            System.out.println("jsApi_ticket：" + jsApi_ticket);

            return getSignature(jsApi_ticket, timestamp, noncestr, url);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }

    // 获得js signature
    public static String getSignature(String jsapi_ticket, String timestamp, String nonce, String jsurl) throws IOException {
        /****
         * 对 jsapi_ticket、 timestamp 和 nonce 按字典排序 对所有待签名参数按照字段名的 ASCII
         * 码从小到大排序（字典序）后，使用 URL 键值对的格式（即key1=value1&key2=value2…）拼接成字符串
         * string1。这里需要注意的是所有参数名均为小写字符。 接下来对 string1 作 sha1 加密，字段名和字段值都采用原始值，不进行
         * URL 转义。即 signature=sha1(string1)。
         * **如果没有按照生成的key1=value&key2=value拼接的话会报错
         */
        String[] paramArr = new String[]{"jsapi_ticket=" + jsapi_ticket,
                "timestamp=" + timestamp, "noncestr=" + nonce, "url=" + jsurl};
        Arrays.sort(paramArr);
        // 将排序后的结果拼接成一个字符串
        String content = paramArr[0].concat("&" + paramArr[1]).concat("&" + paramArr[2])
                .concat("&" + paramArr[3]);
        System.out.println("拼接之后的content为:" + content);
        String gensignature = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对拼接后的字符串进行 sha1 加密
            byte[] digest = md.digest(content.toString().getBytes());
            gensignature = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将 sha1 加密后的字符串与 signature 进行对比
        if (gensignature != null) {
            return gensignature.toLowerCase();// 返回signature
        } else {
            return "false";
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
}
