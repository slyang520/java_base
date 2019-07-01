package com.slyang.test.nio.socket;

import org.junit.Test;

import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class SocketAPITest {

    
    // https://juejin.im/entry/59e44ae951882578d5038a7d
    @Test
    public void helloSocket(){

        try {
            // 创建与端口为80的网络服务器对应的客户端socket
            Socket socket = new Socket( "www.baidu.com", 80 );


            //从服务器端获取一个PrintStream
            PrintStream out = new PrintStream( socket.getOutputStream() );
            //获取服务器端的InputStream，用一个BufferedReader将其包装
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );


            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("GET").append(" ").append("/").append(" ").append("HTTP/1.0")
                    .append("\r\n");
            stringBuffer.append("Host:").append(" ").append("www.baidu.com")
                    .append("\r\n");


            out.println( stringBuffer);

            String line = in.readLine();
            while( line != null )
            {
                System.out.println( line );
                line = in.readLine();
            }

            in.close();
            out.close();
            socket.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }


    @Test
    public void helloSSLContext() throws Exception{

        X509TrustManager x509m = new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        };
        // 获取一个SSLContext实例
        SSLContext s = SSLContext.getInstance("SSL");
        // 初始化SSLContext实例
        s.init(null, new TrustManager[] { x509m },
                new java.security.SecureRandom());

        // 打印这个SSLContext实例使用的协议
        System.out.println("缺省安全套接字使用的协议: " + s.getProtocol());


        // 获取SSLContext实例相关的SSLEngine
        SSLEngine e = s.createSSLEngine();


        System.out.println("支持的协议: " + Arrays.asList(e.getSupportedProtocols()));
        System.out.println("启用的协议: " + Arrays.asList(e.getEnabledProtocols()));

        System.out.println("支持的加密套件: " + Arrays.asList(e.getSupportedCipherSuites()));
        System.out.println("启用的加密套件: " + Arrays.asList(e.getEnabledCipherSuites()));

    }



    @Test
    public void helloSocketSSL() throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException, UnrecoverableKeyException, KeyManagementException {

        // https://blog.csdn.net/kimylrong/article/details/43603815

        String host = "127.0.0.1";
        int port = 1234;
        String keystorePath = "/home/user/CA/certs/client.keystore";
        String trustKeystorePath = "/home/user/CA/certs/ca-trust.keystore";
        String keystorePassword = "abc123_";
        SSLContext context = SSLContext.getInstance("SSL");
        //客户端证书库
        KeyStore clientKeystore = KeyStore.getInstance("pkcs12");
        FileInputStream keystoreFis = new FileInputStream(keystorePath);
        clientKeystore.load(keystoreFis, keystorePassword.toCharArray());
        //信任证书库
        KeyStore trustKeystore = KeyStore.getInstance("jks");
        FileInputStream trustKeystoreFis = new FileInputStream(trustKeystorePath);
        trustKeystore.load(trustKeystoreFis, keystorePassword.toCharArray());

        //密钥库
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
        kmf.init(clientKeystore, keystorePassword.toCharArray());

        
        //信任库
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(trustKeystore);

        //初始化SSL上下文
        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        SSLSocket sslSocket = (SSLSocket)context.getSocketFactory().createSocket(host, port);


    }


}
