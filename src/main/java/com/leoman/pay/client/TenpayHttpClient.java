package com.leoman.pay.client;

import com.leoman.pay.util.HttpClientUtil;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;


/**
 * 锟狡革拷通http锟斤拷锟斤拷https锟斤拷锟斤拷通锟脚客伙拷锟斤拷<br/>
 * ========================================================================<br/>
 * api说锟斤拷锟斤拷<br/>
 * setReqContent($reqContent),锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟捷ｏ拷锟斤拷锟斤拷post锟斤拷get锟斤拷锟斤拷锟斤拷get锟斤拷式锟结供<br/>
 * getResContent(), 锟斤拷取应锟斤拷锟斤拷锟斤拷<br/>
 * setMethod(method),锟斤拷锟斤拷锟斤拷锟襟方凤拷,post锟斤拷锟斤拷get<br/>
 * getErrInfo(),锟斤拷取锟斤拷锟斤拷锟斤拷息<br/>
 * setCertInfo(certFile, certPasswd),锟斤拷锟斤拷证锟介，双锟斤拷https时锟斤拷要使锟斤拷<br/>
 * setCaInfo(caFile), 锟斤拷锟斤拷CA锟斤拷锟斤拷式未pem锟斤拷锟斤拷锟斤拷锟斤拷锟津不硷拷锟�<br/>
 * setTimeOut(timeOut)锟斤拷 锟斤拷锟矫筹拷时时锟戒，锟斤拷位锟斤拷<br/>
 * getResponseCode(), 取锟斤拷锟截碉拷http状态锟斤拷<br/>
 * call(),锟斤拷锟斤拷锟斤拷媒涌锟�<br/>
 * getCharset()/setCharset(),锟街凤拷锟斤拷锟�<br/>
 *
 * ========================================================================<br/>
 *
 */
public class TenpayHttpClient {

	private static final String USER_AGENT_VALUE =
		"Mozilla/4.0 (compatible; MSIE 6.0; Windows XP)";

	private static final String JKS_CA_FILENAME =
		"tenpay_cacert.jks";

	private static final String JKS_CA_ALIAS = "tenpay";

	private static final String JKS_CA_PASSWORD = "";

	/** ca证锟斤拷锟侥硷拷 */
	private File caFile;

	/** 证锟斤拷锟侥硷拷 */
	private File certFile;

	/** 证锟斤拷锟斤拷锟斤拷 */
	private String certPasswd;

	/** 锟斤拷锟斤拷锟斤拷锟捷ｏ拷锟斤拷锟斤拷post锟斤拷get锟斤拷锟斤拷锟斤拷get锟斤拷式锟结供 */
	private String reqContent;

	/** 应锟斤拷锟斤拷锟斤拷 */
	private String resContent;

	/** 锟斤拷锟襟方凤拷 */
	private String method;

	/** 锟斤拷锟斤拷锟斤拷息 */
	private String errInfo;

	/** 锟斤拷时时锟斤拷,锟斤拷锟斤拷为锟斤拷位 */
	private int timeOut;

	/** http应锟斤拷锟斤拷锟� */
	private int responseCode;

	/** 锟街凤拷锟斤拷锟� */
	private String charset;

	private InputStream inputStream;

	public TenpayHttpClient() {
		this.caFile = null;
		this.certFile = null;
		this.certPasswd = "";

		this.reqContent = "";
		this.resContent = "";
		this.method = "POST";
		this.errInfo = "";
		this.timeOut = 30;//30锟斤拷

		this.responseCode = 0;
		this.charset = "UTF-8";

		this.inputStream = null;
	}

	/**
	 * 锟斤拷锟斤拷证锟斤拷锟斤拷息
	 * @param certFile 证锟斤拷锟侥硷拷
	 * @param certPasswd 证锟斤拷锟斤拷锟斤拷
	 */
	public void setCertInfo(File certFile, String certPasswd) {
		this.certFile = certFile;
		this.certPasswd = certPasswd;
	}

	/**
	 * 锟斤拷锟斤拷ca
	 * @param caFile
	 */
	public void setCaInfo(File caFile) {
		this.caFile = caFile;
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param reqContent 锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	public void setReqContent(String reqContent) {
		this.reqContent = reqContent;
	}

	/**
	 * 锟斤拷取锟斤拷锟斤拷锟斤拷锟�
	 * @return String
	 * @throws IOException
	 */
	public String getResContent() {
		try {
			this.doResponse();
		} catch (IOException e) {
			this.errInfo = e.getMessage();
			//return "";
		}

		return this.resContent;
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟襟方凤拷post锟斤拷锟斤拷get
	 * @param method 锟斤拷锟襟方凤拷post/get
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * 锟斤拷取锟斤拷锟斤拷锟斤拷息
	 * @return String
	 */
	public String getErrInfo() {
		return this.errInfo;
	}

	/**
	 * 锟斤拷锟矫筹拷时时锟斤拷,锟斤拷锟斤拷为锟斤拷位
	 * @param timeOut 锟斤拷时时锟斤拷,锟斤拷锟斤拷为锟斤拷位
	 */
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * 锟斤拷取http状态锟斤拷
	 * @return int
	 */
	public int getResponseCode() {
		return this.responseCode;
	}

	/**
	 * 执锟斤拷http锟斤拷锟矫★拷true:锟缴癸拷 false:失锟斤拷
	 * @return boolean
	 */
	public boolean call() {

		boolean isRet = false;

		//http
		if(null == this.caFile && null == this.certFile) {
			try {
				this.callHttp();
				isRet = true;
			} catch (IOException e) {
				this.errInfo = e.getMessage();
			}
			return isRet;
		}

		//https
		try {
			this.callHttps();
			isRet = true;
		} catch (UnrecoverableKeyException e) {
			this.errInfo = e.getMessage();
		} catch (KeyManagementException e) {
			this.errInfo = e.getMessage();
		} catch (CertificateException e) {
			this.errInfo = e.getMessage();
		} catch (KeyStoreException e) {
			this.errInfo = e.getMessage();
		} catch (NoSuchAlgorithmException e) {
			this.errInfo = e.getMessage();
		} catch (IOException e) {
			this.errInfo = e.getMessage();
		}

		return isRet;

	}

	protected void callHttp() throws IOException {

		if("POST".equals(this.method.toUpperCase())) {
			String url = HttpClientUtil.getURL(this.reqContent);
			String queryString = HttpClientUtil.getQueryString(this.reqContent);
			byte[] postData = queryString.getBytes(this.charset);
			this.httpPostMethod(url, postData);

			return ;
		}

		this.httpGetMethod(this.reqContent);

	}

	protected void callHttps() throws IOException, CertificateException,
			KeyStoreException, NoSuchAlgorithmException,
			UnrecoverableKeyException, KeyManagementException {

		// ca目录
		String caPath = this.caFile.getParent();

		File jksCAFile = new File(caPath + "/"
				+ TenpayHttpClient.JKS_CA_FILENAME);
		if (!jksCAFile.isFile()) {
			X509Certificate cert = (X509Certificate) HttpClientUtil
					.getCertificate(this.caFile);

			FileOutputStream out = new FileOutputStream(jksCAFile);

			// store jks file
			HttpClientUtil.storeCACert(cert, TenpayHttpClient.JKS_CA_ALIAS,
					TenpayHttpClient.JKS_CA_PASSWORD, out);

			out.close();

		}

		FileInputStream trustStream = new FileInputStream(jksCAFile);
		FileInputStream keyStream = new FileInputStream(this.certFile);

		SSLContext sslContext = HttpClientUtil.getSSLContext(trustStream,
				TenpayHttpClient.JKS_CA_PASSWORD, keyStream, this.certPasswd);

		//锟截憋拷锟斤拷
		keyStream.close();
		trustStream.close();

		if("POST".equals(this.method.toUpperCase())) {
			String url = HttpClientUtil.getURL(this.reqContent);
			String queryString = HttpClientUtil.getQueryString(this.reqContent);
			byte[] postData = queryString.getBytes(this.charset);

			this.httpsPostMethod(url, postData, sslContext);

			return ;
		}

		this.httpsGetMethod(this.reqContent, sslContext);

	}

	public boolean callHttpPost(String url, String postdata) {
		boolean flag = false;
		byte[] postData;
		try {
			postData = postdata.getBytes(this.charset);
			this.httpPostMethod(url, postData);
			flag = true;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return flag;
	}

	/**
	 * 锟斤拷http post锟斤拷式通锟斤拷
	 * @param url
	 * @param postData
	 * @throws IOException
	 */
	protected void httpPostMethod(String url, byte[] postData)
			throws IOException {

		HttpURLConnection conn = HttpClientUtil.getHttpURLConnection(url);

		this.doPost(conn, postData);
	}

	/**
	 * 锟斤拷http get锟斤拷式通锟斤拷
	 *
	 * @param url
	 * @throws IOException
	 */
	protected void httpGetMethod(String url) throws IOException {

		HttpURLConnection httpConnection =
			HttpClientUtil.getHttpURLConnection(url);

		this.setHttpRequest(httpConnection);

		httpConnection.setRequestMethod("GET");

		this.responseCode = httpConnection.getResponseCode();

		this.inputStream = httpConnection.getInputStream();

	}

	/**
	 * 锟斤拷https get锟斤拷式通锟斤拷
	 * @param url
	 * @param sslContext
	 * @throws IOException
	 */
	protected void httpsGetMethod(String url, SSLContext sslContext)
			throws IOException {

		SSLSocketFactory sf = sslContext.getSocketFactory();

		HttpsURLConnection conn = HttpClientUtil.getHttpsURLConnection(url);

		conn.setSSLSocketFactory(sf);

		this.doGet(conn);

	}

	protected void httpsPostMethod(String url, byte[] postData,
			SSLContext sslContext) throws IOException {

		SSLSocketFactory sf = sslContext.getSocketFactory();

		HttpsURLConnection conn = HttpClientUtil.getHttpsURLConnection(url);

		conn.setSSLSocketFactory(sf);

		this.doPost(conn, postData);

	}

	/**
	 * 锟斤拷锟斤拷http锟斤拷锟斤拷默锟斤拷锟斤拷锟斤拷
	 * @param httpConnection
	 */
	protected void setHttpRequest(HttpURLConnection httpConnection) {

		//锟斤拷锟斤拷锟斤拷锟接筹拷时时锟斤拷
		httpConnection.setConnectTimeout(this.timeOut * 1000);

		//User-Agent
		httpConnection.setRequestProperty("User-Agent",
				TenpayHttpClient.USER_AGENT_VALUE);

		//锟斤拷使锟矫伙拷锟斤拷
		httpConnection.setUseCaches(false);

		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
		httpConnection.setDoInput(true);
		httpConnection.setDoOutput(true);

	}

	/**
	 * 锟斤拷锟斤拷应锟斤拷
	 * @throws IOException
	 */
	protected void doResponse() throws IOException {

		if(null == this.inputStream) {
			return;
		}

		//锟斤拷取应锟斤拷锟斤拷锟斤拷
		this.resContent=HttpClientUtil.InputStreamTOString(this.inputStream,this.charset);

		//锟截憋拷锟斤拷锟斤拷锟斤拷
		this.inputStream.close();

	}

	/**
	 * post锟斤拷式锟斤拷锟斤拷
	 * @param conn
	 * @param postData
	 * @throws IOException
	 */
	protected void doPost(HttpURLConnection conn, byte[] postData)
			throws IOException {

		// 锟斤拷post锟斤拷式通锟斤拷
		conn.setRequestMethod("POST");

		// 锟斤拷锟斤拷锟斤拷锟斤拷默锟斤拷锟斤拷锟斤拷
		this.setHttpRequest(conn);

		// Content-Type
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		BufferedOutputStream out = new BufferedOutputStream(conn
				.getOutputStream());

		final int len = 1024; // 1KB
		HttpClientUtil.doOutput(out, postData, len);

		// 锟截憋拷锟斤拷
		out.close();

		// 锟斤拷取锟斤拷应锟斤拷锟斤拷状态锟斤拷
		this.responseCode = conn.getResponseCode();

		// 锟斤拷取应锟斤拷锟斤拷锟斤拷锟斤拷
		this.inputStream = conn.getInputStream();

	}

	/**
	 * get锟斤拷式锟斤拷锟斤拷
	 * @param conn
	 * @throws IOException
	 */
	protected void doGet(HttpURLConnection conn) throws IOException {

		//锟斤拷GET锟斤拷式通锟斤拷
		conn.setRequestMethod("GET");

		//锟斤拷锟斤拷锟斤拷锟斤拷默锟斤拷锟斤拷锟斤拷
		this.setHttpRequest(conn);

		//锟斤拷取锟斤拷应锟斤拷锟斤拷状态锟斤拷
		this.responseCode = conn.getResponseCode();

		//锟斤拷取应锟斤拷锟斤拷锟斤拷锟斤拷
		this.inputStream = conn.getInputStream();
	}


}
